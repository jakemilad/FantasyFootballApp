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

    // EFFECTS: constructs an abstract table model with a given league input
    public TeamsGui(League lg) {
        this.league = lg;
    }

    // EFFECTS: updates all table model events such that JSon is able to read it
    public void updateLeague(League lg) {
        this.league = lg;
        this.fireTableDataChanged();
    }

    // EFFECTS: returns the number of rows in the abstract table model for JTable
    @Override
    public int getRowCount() {
        return league.getTeamsInLeague().size();
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
    public Class getColumnClass(int columnIndex) {
        return columnClass[columnIndex];
    }

    // EFFECTS: gets the corresponding information to each column.
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
