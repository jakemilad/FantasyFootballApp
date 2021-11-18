package ui;

import model.League;
import model.Player;
import model.Team;

import javax.swing.table.AbstractTableModel;

public class PlayersGui extends AbstractTableModel {

    private League league;
    private final String[] columnNames = new String[]{
            "Name", "Position", "Price", "Goals", "Assists", "Points"
    };
    private final Class[] columnClass = new Class[]{
            String.class, String.class, Double.class, Integer.class, Integer.class, Integer.class
    };

    // EFFECTS: constructs an abstract table model with a given league
    public PlayersGui(League lg) {
        this.league = lg;
    }

    // EFFECTS: updates all table model events such that JSon is able to read it
    public void updateLeagueStatistics(League league) {
        this.league = league;
        this.fireTableDataChanged();
    }

    // EFFECTS: returns the number of rows in the abstract table model for JTable
    @Override
    public int getRowCount() {
        return league.getPlayersInLeague().size();
    }

    // EFFECTS: returns the number of columns in the abstract table model for JTable
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    // EFFECTS: sets the column names
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    // EFFECTS: sets the column class
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClass[columnIndex];
    }


    // EFFECTS: gets the corresponding information to each column.
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Player player = league.getPlayersInLeague().get(rowIndex);
        if (0 == columnIndex) {
            return player.getName();
        } else if (1 == columnIndex) {
            return player.getPosition();
        } else if (2 == columnIndex) {
            return player.getPrice();
        } else if (3 == columnIndex) {
            return player.getGoals();
        } else if (4 == columnIndex) {
            return player.getAssists();
        } else if (5 == columnIndex) {
            return ((player.getGoals() * 2) + (player.getAssists() *  1));
        }
        return null;
    }


    // EFFECTS: sets the behaviour to update statistics of goals and assists on the table.
    @Override
    public void setValueAt(Object val, int rowIndex, int columnIndex) {
        Player player = league.getPlayersInLeague().get(rowIndex);
        if (columnIndex == 3) {
            if (!player.inTeamForPlayer()) {
                player.scoredGoal((Integer) val);
            } else {
                for (Team team : league.getTeamsInLeague()) {
                    if (team.inTeamForGivenPlayer(player)) {
                        player.scoredGoalTeam((Integer) val, team);
                    }
                }
            }
        } else if (columnIndex == 4) {
            if (!player.inTeamForPlayer()) {
                player.scoredAssist((Integer) val);
            } else {
                for (Team tm : league.getTeamsInLeague()) {
                    if (tm.inTeamForGivenPlayer(player)) {
                        player.scoredAssistTeam((Integer) val, tm);
                    }
                }
            }
        }
    }

    // EFFECTS: allows the table to be edited
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

}
