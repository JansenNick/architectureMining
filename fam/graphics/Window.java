package org.architecturemining.fam.graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.architecturemining.fam.IO.ReadXML;
import org.architecturemining.fam.IO.WriteXML;
import org.architecturemining.fam.model.ConsoleDemo;
import org.architecturemining.fam.model.FunctionalArchitectureModel;;

@SuppressWarnings("serial")


public class Window extends JFrame {
	
	JButton recordLog, stopLog, exportLog;
	JLabel recordingLabel, exportLabel;
	JTextArea textArea;
	ArrayList<JButton> featureButtons;
	
	FunctionalArchitectureModel fam = new FunctionalArchitectureModel();
	
	int menuSpace = 220;
	
	public static void main(){
		
		new Window();

	}
	
	public Window(){
		
			WriteXML.main(); 
			ReadXML.readXML(fam);
			
			ConsoleDemo.main(fam);
			
			// Window stuff
			this.setSize(1200, 800);

			this.setTitle("FAM Sequence Creator");
			
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			//Panel stuff
			MyPanel mainPanel = new MyPanel();
			
			mainPanel.setLayout(null);
			
			addMenuPanelComponents(mainPanel);
			addFamPanelComponents(fam, mainPanel);
			
			this.add(mainPanel);
			
			this.setVisible(true);
		}

	private void addMenuPanelComponents(JPanel menuPanel) {
		
		recordLog = new JButton("Record new log");
		stopLog = new JButton("Stop log");
		exportLog = new JButton("Export");
		recordingLabel = new JLabel("Recording");
		exportLabel = new JLabel("Export");
		textArea = new JTextArea("");
		
		
		recordLog.setBounds(15, 60, 140, 40);
		
		stopLog.setBounds(170,60, 100, 40);
		exportLog.setBounds(15, 600, 100,40);
		recordingLabel.setBounds(15, 40, 150 ,20);
		exportLabel.setBounds(15, 580, 150, 20);
		
		textArea.setBounds(15, 200, 250, 300);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		menuPanel.add(recordLog);
		menuPanel.add(stopLog);
		menuPanel.add(exportLog);
		menuPanel.add(recordingLabel);
		menuPanel.add(exportLabel);
		menuPanel.add(textArea);

						
	}

	public void addFamPanelComponents(FunctionalArchitectureModel fam, JPanel famPanel) {
		
		featureButtons = new ArrayList<JButton>();
		
		int counter = 0;
		
		ListenForButton lForButton = new ListenForButton();
		
		for(int i = 0 ; i < fam.getListModules().size(); i++) {
			
			for(int j = 0; j < fam.getListModules().get(i).getFeatureList().size(); j++) {
	
				featureButtons.add(new JButton(fam.getListModules().get(i).getFeatureList().get(j).getName()));
				
				featureButtons.get(counter).setBounds(
						menuSpace + (int) fam.getListModules().get(i).getFeatureList().get(j).getOrigin().getX(),
									(int) fam.getListModules().get(i).getFeatureList().get(j).getOrigin().getY(), 
						fam.getListModules().get(i).getFeatureList().get(j).getWidth(), 
						fam.getListModules().get(i).getFeatureList().get(j).getHeight()
						);
				
				featureButtons.get(counter).addActionListener(lForButton);
				featureButtons.get(counter).setName(fam.getListModules().get(i).getFeatureList().get(j).getName());
				famPanel.add(featureButtons.get(counter));
			
				counter++;
			}
		}
	}
	
	private class ListenForButton implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			
			for(int i = 0 ; featureButtons.size() > i ; i++) {
				
				if(e.getSource() == featureButtons.get(i)) {
					
					textArea.append("-"+ featureButtons.get(i).getName());		
				}
				
			}

		}
	}
	
	class MyPanel extends JPanel {
		  
		public void paintComponent(Graphics g) {

			Graphics2D graph2 = (Graphics2D)g;
				
			for(int i = 0 ; i < fam.getListModules().size(); i++) {
				
				graph2.draw(new Rectangle2D.Float(	menuSpace + (float) fam.getListModules().get(i).getOrigin().getX(),
													(float) fam.getListModules().get(i).getOrigin().getY(), 
													fam.getListModules().get(i).getWidth(), 
													fam.getListModules().get(i).getHeight()	));
				graph2.drawString(	 fam.getListModules().get(i).getName(),
						menuSpace + (int)fam.getListModules().get(i).getOrigin().getX() + 7, 
								  	(int)fam.getListModules().get(i).getOrigin().getY() + 15);
			}
				
			for(int i = 0 ; i < fam.getListInfoFlow().size(); i++) {
				
				graph2.draw( new Line2D.Double(	menuSpace +fam.getListInfoFlow().get(i).getSource().getOrigin().getX() + fam.getListInfoFlow().get(i).getSource().getWidth()/2, 
												fam.getListInfoFlow().get(i).getSource().getOrigin().getY() + fam.getListInfoFlow().get(i).getSource().getHeight()/2, 
												menuSpace +fam.getListInfoFlow().get(i).getTarget().getOrigin().getX() + fam.getListInfoFlow().get(i).getTarget().getWidth()/2, 
												fam.getListInfoFlow().get(i).getTarget().getOrigin().getY() + fam.getListInfoFlow().get(i).getTarget().getHeight()/2
												));			
			}
		}
	}
}


