package org.architecturemining.fam.graphics;

import javax.swing.table.DefaultTableModel;

/**
 * @author nick
 *
 */
@SuppressWarnings("serial")
class MyTableModel extends DefaultTableModel{
	
	private boolean DEBUG = false;
	
	private String[] columnNames = {"Name", "Trace", "#"};
	private Object[][] data = {
			{"", "", new Boolean(false)}, {"", "", new Boolean(false)}, {"", "", new Boolean(false)}, {"", "", new Boolean(false)}, {"", "", new Boolean(false)}, {"", "", new Boolean(false)}, {"", "", new Boolean(false)}, {"", "", new Boolean(false)}, {"", "", new Boolean(false)}, {"", "", new Boolean(false)}, {"", "", new Boolean(false)}, {"", "", new Boolean(false)}, {"", "", new Boolean(false)}, {"", "", new Boolean(false)}, {"", "", new Boolean(false)}, {"", "", new Boolean(false)}, {"", "", new Boolean(false)}, {"", "", new Boolean(false)}, {"", "", new Boolean(false)}, {"", "", new Boolean(false)}, {"", "", new Boolean(false)}, {"", "", new Boolean(false)}, {"", "", new Boolean(false)}, {"", "", new Boolean(false)}, {"", "", new Boolean(false)}, {"", "", new Boolean(false)}};

	
	public int getColumnCount() {
		return columnNames.length;
	}

    public String getColumnName(int col) {
        return columnNames[col];
    }
	
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}
	
	/** JTable uses this method to determine the default renderer/
	 * editor for each cell. If we didn't implement this method,
	 * then the last column would contain text("true"/"false"),
	 * rather than a checkbox.
	 * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
	 */
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
	
	public boolean isCellEditable(int row, int col) {
		if(col < 2) {
			return false;
		} else {
			return true;
		}
	}
	
	 public void setValueAt(Object value, int row, int col) {
         if (DEBUG) {
             System.out.println("Setting value at " + row + "," + col
                                + " to " + value
                                + " (an instance of "
                                + value.getClass() + ")");
         }

         data[row][col] = value;
         fireTableCellUpdated(row, col);

         if (DEBUG) {
             System.out.println("New value of data:");
             printDebugData();
         }
     }

     private void printDebugData() {
         int numRows = getRowCount();
         int numCols = getColumnCount();

         for (int i=0; i < numRows; i++) {
             System.out.print("    row " + i + ":");
             for (int j=0; j < numCols; j++) {
                 System.out.print("  " + data[i][j]);
             }
             System.out.println();
         }
     }
   
}
