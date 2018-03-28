package org.architecturemining.fam.graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.architecturemining.fam.graphics.Window.ListenForButton;

public class MenuPanelComponents {
	
	JButton recordLog, stopLog, exportLog;
	JLabel recordingLabel, exportLabel;
	JTextArea textArea;
	
	public void addMenuPanelComponents(JPanel menuPanel, int currentTrace, Window window) {
			
			menuPanel.setLayout(new GridBagLayout());	
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.BOTH;
			
			recordingLabel = new JLabel("Recording");
			c.gridx = 0;
			c.gridy = 0;
			c.weighty = 1;
			menuPanel.add(recordingLabel,c);
	
			recordLog = new JButton("Record new log");
			ListenForButton lForButton = window.new ListenForButton();
			recordLog.addActionListener(lForButton);
			c.gridx = 0;
			c.gridy = 1;
			c.weighty = 5;
			menuPanel.add(recordLog,c);
			
			stopLog = new JButton("Stop log");
			stopLog.addActionListener(lForButton);
			c.gridx = 1;
			c.gridy = 1;
			c.weighty = 5;
			menuPanel.add(stopLog,c);
	
			textArea = new JTextArea("");
			c.gridx = 0;
			c.gridy = 2;
			c.ipady = 400; 
			c.gridwidth = c.REMAINDER;
			textArea.setLineWrap(true);
			textArea.setWrapStyleWord(true);
			textArea.append("New trace created \n" + "Trace "+currentTrace+": ");
			JScrollPane scrollpane= new JScrollPane(textArea);
			menuPanel.add(scrollpane,c);
	
			exportLabel = new JLabel("Export");
			c.gridx = 0;
			c.gridy = 3;
			c.gridwidth = 1; //reset
			c.weighty = 1;
			c.ipady = 0; //reset
			c.ipadx = 0;
			
			menuPanel.add(exportLabel,c);
			
			exportLog = new JButton("Export");
			exportLog.addActionListener(lForButton);
			c.gridx = 0;
			c.gridy = 4;
			c.weighty = 5;
			menuPanel.add(exportLog,c);		
		}


	public JButton getRecordLog() {
		return recordLog;
	}
	
	public JButton getStopLog() {
		return stopLog;
	}
	
	public JButton getExportLog() {
		return exportLog;
	}
	
	public JTextArea getTextArea() {
		return textArea;
	}

}