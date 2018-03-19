package org.architecturemining.fam.graphics;

import java.awt.FlowLayout;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class TraceTable extends JComponent{
	
	JTable traceTable;
	
	public TraceTable() {
		
		setLayout(new FlowLayout());
		
		String[] columnNames = {"Selection", "Case", "Trace", "Delete"};
		Object [][] data = {{"Bill","Hazel","Male"}};
		
		traceTable = new JTable(data, columnNames);
		
		//traceTable.setPreferredScrollableViewportSize(new Dimension(200,50));
		traceTable.setFillsViewportHeight(true);
		
		JScrollPane scrollPane = new JScrollPane(traceTable);
		add(scrollPane);
	}
	
}