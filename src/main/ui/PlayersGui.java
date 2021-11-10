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

    public PlayersGui(League lg) {
        this.league = lg;
    }

    public void updateLeagueStatistics(League league) {
        this.league = league;
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return league.getPlayersInLeague().size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClass[columnIndex];
    }

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
            return ((player.getGoals() * 2) + (player.getAssists()));
        }
        return null;
    }


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

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

}
