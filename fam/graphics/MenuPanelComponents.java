package org.architecturemining.fam.graphics;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.architecturemining.fam.graphics.Window.ListenForButton;
import org.architecturemining.fam.graphics.Window.ListenForTable;

/**Handles all logic regarding the components in the menu panel. Holds all the required components for the menu panel.
 * 
 * @author Nick
 */
public class MenuPanelComponents {
	
	JButton saveTrace, exportLog, deleteTraces;
	JLabel recordingLabel, exportLabel, nameLabel, descriptionLabel, currentTraceLabel;
	JTextArea descriptionArea, currentTraceArea;
	JTextField nameField;
	JTable table;
	MyTableModel tableModel;
	
	/**Adds all the components to the menuPanel at the right place and size.
	 * @param menuPanel		panel to which the components should be added
	 * @param window		top level JFrame(Window) to which the components are added, required of the action listeners.
	 */
	public void addMenuPanelComponents(JPanel menuPanel, Window window) {
		
			menuPanel.setLayout(new GridBagLayout());	
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.BOTH;
			ListenForButton lForButton = window.new ListenForButton();
			ListenForTable lForTable = window.new ListenForTable();
						
			nameLabel = new JLabel("Name trace:");
			c.gridx = 0;
			c.gridy = 1;
			c.weighty = 1;
			menuPanel.add(nameLabel,c);
			
			nameField = new JTextField();
			c.gridx = 0;
			c.gridy = 2;
			c.weighty = 1;
			c.ipadx =185;
			c.gridwidth = c.REMAINDER;
			menuPanel.add(nameField,c);
			c.ipadx = 0;//reset
			
			descriptionLabel = new JLabel("Description trace:");
			c.gridx = 0;
			c.gridy = 3;
			c.weighty = 1;
			menuPanel.add(descriptionLabel,c);
			
			descriptionArea = new JTextArea();
			descriptionArea.setLineWrap(true);
			descriptionArea.setWrapStyleWord(true);
			c.gridx = 0;
			c.gridy = 4;
			c.ipady = 100;
			c.weighty = 1;
			JScrollPane scrollpaneDescription= new JScrollPane(descriptionArea);
			menuPanel.add(scrollpaneDescription,c);
			c.gridwidth = 1; //reset
			c.ipady = 0; //reset
			
			currentTraceLabel = new JLabel("Current trace:");
			c.gridx = 0;
			c.gridy = 5;
			c.weighty = 1;
			menuPanel.add(currentTraceLabel,c);
			
			currentTraceArea = new JTextArea();
			currentTraceArea.setLineWrap(true);
			currentTraceArea.setWrapStyleWord(true);
			currentTraceArea.setEditable(false);
			c.gridx = 0;
			c.gridy = 6;
			c.ipady = 75; 
			c.weighty = 1;
			JScrollPane scrollpaneCurrentTrace= new JScrollPane(currentTraceArea);
			menuPanel.add(scrollpaneCurrentTrace,c);
			c.gridwidth = 1; //reset
			c.ipady = 0; //reset
			
			saveTrace = new JButton("Save trace");
			saveTrace.addActionListener(lForButton);
			c.gridx = 0;
			c.gridy = 7;
			c.weighty = 5;
			menuPanel.add(saveTrace,c);
			
			recordingLabel = new JLabel("Logbook");
			c.gridx = 0;
			c.gridy = 8;
			c.weighty = 1;
			menuPanel.add(recordingLabel,c);
	
			tableModel = new MyTableModel();
			c.gridx = 0;
			c.gridy = 9;
			c.ipady = 220; 
			c.gridwidth = c.REMAINDER;
			table = new JTable(tableModel);
			table.setPreferredScrollableViewportSize(new Dimension(250,70));
			table.setFillsViewportHeight(true);
			table.getColumnModel().getColumn(2).setPreferredWidth(10);
			table.getModel().addTableModelListener(lForTable);
			JScrollPane scrollpane= new JScrollPane(table);
			menuPanel.add(scrollpane,c);
			c.gridwidth = 1; //reset
			c.ipady = 0; //reset
			
			deleteTraces = new JButton("Delete selected traces");
			deleteTraces.addActionListener(lForButton);
			c.gridx = 0;
			c.gridy = 10;
			menuPanel.add(deleteTraces,c);
			
			exportLabel = new JLabel("Export");
			c.gridx = 0;
			c.gridy = 11;
			c.weighty = 1;
			c.ipadx = 0;
			
			menuPanel.add(exportLabel,c);
			
			exportLog = new JButton("Export");
			exportLog.addActionListener(lForButton);
			c.gridx = 0;
			c.gridy = 12;
			c.weighty = 5;
			menuPanel.add(exportLog,c);		
		}
	
	public JButton getSaveTrace() {
		return saveTrace;
	}
	
	public JButton getExportLog() {
		return exportLog;
	}
	
	public JButton getDeleteTraces() {
		return deleteTraces;
	}
	
	public JTextArea getCurrentTraceTextArea() {
		return currentTraceArea;
	}
	
	public JTextField getNameField() {
		return nameField;
	}
	
	public JTextArea getDescriptionTextArea() {
		return descriptionArea;
	}
	
	public void updateTable(Object a, int row, int column) {
		table.setValueAt(a, row, column);
	}
	
	public void addRow(Object[] rowData) {
		tableModel.addRow(rowData);
	}
	
	public boolean checkActiveRow(int row) {
		return (boolean) tableModel.getValueAt(row, 2); 
	}
	
}