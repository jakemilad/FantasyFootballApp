package ui;

import model.League;
import model.Player;
import model.Team;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;

public class PlayersGui extends AbstractTableModel {

    private League league;

    public PlayersGui(League lg) {
        this.league = lg;
        showWindow();
    }

    public void showWindow() {
        JFrame frame = new JFrame("All Players");
        frame.setLayout(new BorderLayout(30, 30));
        frame.setMinimumSize(new Dimension(400, 400));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.pack();

        JPanel playerPanel = new JPanel();
        playerPanel.setVisible(true);
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
        //playerPanel.setBackground(Color.orange);

        frame.add(playerPanel);
    }

    public final String[] columnNames = new String[]{
            "Name", "Position", "Price", "Goals", "Assists", "Points"
    };

    public final Class[] columnClass = new Class[]{
            String.class, String.class, Double.class, Integer.class, Integer.class, Integer.class
    };

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


}
