package ui;

import model.League;
import model.Team;

import javax.swing.table.AbstractTableModel;

public class TeamsGui extends AbstractTableModel {

    private League league;

    private final String[] columnNames = new String[] {
            "Name", "Points"
    };

    private final Class[] columnClass = new Class[] {
            String.class, Integer.class
    };

    public TeamsGui(League lg) {
        this.league = lg;
    }

    public void updateLeague(League lg) {
        this.league = lg;
        this.fireTableDataChanged();
    }


    @Override
    public int getRowCount() {
        return league.getTeamsInLeague().size();
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
    public Class getColumnClass(int columnIndex) {
        return columnClass[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Team team = league.getTeamsInLeague().get(rowIndex);
        if (columnIndex == 0) {
            return team.getTeamName();
        } else if (columnIndex == 1) {
            return team.getPoints();
        }
        return null;
    }
}
