package org.architecturemining.fam.graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.architecturemining.fam.IO.ReadXML;
import org.architecturemining.fam.IO.WriteXML;
import org.architecturemining.fam.model.ConsoleDemo;
import org.architecturemining.fam.model.FunctionalArchitectureModel;;

@SuppressWarnings("serial")


public class WindowBackup extends JFrame implements MouseListener {
	
	FunctionalArchitectureModel fam = new FunctionalArchitectureModel();
	
	JTextField textField1;
	JTextArea textArea1;
	
	JButton but1, but2, but3, but4, but5, but6,
	but7, but8, but9, but0, butPlus, butMinus,
	clearAll;

JTextField textResult;

int num1, num2;
	
	public static void main(){
		
		new WindowBackup();

	}
	
	public WindowBackup(){
		
			WriteXML.main(); 
			ReadXML.readXML(fam);
			
			ConsoleDemo.main(fam);
			
			// Window stuff
			this.setSize(1400, 1000);
			
			Toolkit tk = Toolkit.getDefaultToolkit();
			Dimension dim = tk.getScreenSize();
			
			int xPos = (dim.width / 2) - (this.getWidth() / 2);
			int yPos = (dim.height / 2) - (this.getHeight() / 2);
			 
			this.setLocation(xPos, yPos);
			
			this.setTitle("FAM Sequence Creator");
			
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			
			
			addMouseListener(this);
			
			
			
			//Panel stuff
			JPanel mainPanel = new JPanel();
			JPanel menuPanel = new JPanel();
			JPanel famPanel = new JPanel();
			
			mainPanel.setLayout(new BorderLayout());
			mainPanel.add(menuPanel, BorderLayout.WEST);
			mainPanel.add(famPanel, BorderLayout.CENTER); 
			
			famPanel.setLayout(null);
			addFeatureButtons(fam, famPanel);
			
			menuPanel.setLayout(new GridBagLayout());
			
			
			
			// You create a GridBagContraints object that defines
			// defaults for your components
			
			GridBagConstraints gridConstraints = new GridBagConstraints();
			
			// Define the x position of the component
			
			gridConstraints.gridx = 1;
			
			// Define the y position of the component
			
			gridConstraints.gridy = 1;
			
			// Number of columns the component takes up
			
			gridConstraints.gridwidth = 1;
			
			// Number of rows the component takes up
			
			gridConstraints.gridheight = 1;
			
			// Gives the layout manager a hint on how to adjust
			// component width (0 equals fixed)
			
			gridConstraints.weightx = 50;
			
			// Gives the layout manager a hint on how to adjust
			// component height (0 equals fixed)
			
			gridConstraints.weighty = 100;
			
			// Defines padding top, left, bottom, right
			
			gridConstraints.insets = new Insets(5,5,5,5);
			
			// Defines where to place components if they don't
			// fill the space: CENTER, NORTH, SOUTH, EAST, WEST
			// NORTHEAST, etc.
			
			gridConstraints.anchor = GridBagConstraints.CENTER;
			
			// How should the component be stretched to fill the
			// space: NONE, HORIZONTAL, VERTICAL, BOTH
			
			gridConstraints.fill = GridBagConstraints.BOTH;
			
			textResult = new JTextField("0",20);
			
			// Defines the font to use in the text field
			
			Font font = new Font("Helvetica", Font.PLAIN, 18);
	        textResult.setFont(font);
			
			but1 = new JButton("1");
			but2 = new JButton("2");
			but3 = new JButton("3");
			but4 = new JButton("4");
			but5 = new JButton("5");
			but6 = new JButton("6");
			but7 = new JButton("7");
			but8 = new JButton("8");
			but9 = new JButton("9");
			butPlus = new JButton("+");
			but0 = new JButton("0");
			butMinus = new JButton("-");
			clearAll = new JButton("C");
			
			menuPanel.add(clearAll,gridConstraints);
			gridConstraints.gridwidth = 20;
			gridConstraints.gridx = 5;
			menuPanel.add(textResult,gridConstraints);
			gridConstraints.gridwidth = 1;
			gridConstraints.gridx = 1;
			gridConstraints.gridy = 2;
			menuPanel.add(but1,gridConstraints);
			gridConstraints.gridx = 5;
			menuPanel.add(but2,gridConstraints);
			gridConstraints.gridx = 9;
			menuPanel.add(but3,gridConstraints);
			gridConstraints.gridx = 1;
			gridConstraints.gridy = 3;
			menuPanel.add(but4,gridConstraints);
			gridConstraints.gridx = 5;
			menuPanel.add(but5,gridConstraints);
			gridConstraints.gridx = 9;
			menuPanel.add(but6,gridConstraints);
			gridConstraints.gridx = 1;
			gridConstraints.gridy = 4;
			menuPanel.add(but7,gridConstraints);
			gridConstraints.gridx = 5;
			menuPanel.add(but8,gridConstraints);
			gridConstraints.gridx = 9;
			menuPanel.add(but9,gridConstraints);
			gridConstraints.gridx = 1;
			gridConstraints.gridy = 5;
			menuPanel.add(butPlus,gridConstraints);
			gridConstraints.gridx = 5;
			menuPanel.add(but0,gridConstraints);
			gridConstraints.gridx = 9;
			menuPanel.add(butMinus,gridConstraints);
			
			
			
			/*
			int i = 3;
			int j = 4;
			JPanel[][] panelHolder = new JPanel[i][j];    
			
			
			menuPanel.setLayout(new GridLayout(i,j));

			for(int m = 0; m < i; m++) {
			   for(int n = 0; n < j; n++) {
			      panelHolder[m][n] = new JPanel();
			      menuPanel.add(panelHolder[m][n]);
			   }
			}
			
			for(int m = 0; m < i; m++) {
				   for(int n = 0; n < j; n++) {
					   panelHolder[m][n].add(new JButton("Foo"));
				   }
			}
			
			
			mainPanel.setLayout(new BorderLayout());
			famPanel.setLayout(null);
			
			mainPanel.add(menuPanel, BorderLayout.WEST);
			mainPanel.add(famPanel, BorderLayout.CENTER); 
			*/
			/*
			Button recordNewLog = new Button("Record new log");
			Button stopLog = new Button("Stop log");
			
			Button tablePanel = new Button("table");
			
			JComboBox<Object> typeSelection = new JComboBox<Object>();
			JComboBox<Object> logSelection = new JComboBox<Object>();
			
			menuPanel.add(recordNewLog, 0,0);
			
			menuPanel.add(stopLog, 1,1);
			
			menuPanel.add(tablePanel, 2,1);
			
			menuPanel.add(typeSelection, 3,0);
			menuPanel.add(logSelection, 3,1);
			*/
			
			
			this.add(mainPanel);
			this.setVisible(true);
		}

	public void addFeatureButtons(FunctionalArchitectureModel fam, JPanel rightFAM) {
		
		for(int i = 0 ; i < fam.getListModules().size(); i++) {
			
			for(int j = 0; j < fam.getListModules().get(i).getFeatureList().size(); j++) {
				JButton button = new JButton(fam.getListModules().get(i).getFeatureList().get(j).getName());
				button.setBounds(
						(int) fam.getListModules().get(i).getFeatureList().get(j).getOrigin().getX(),
						(int) fam.getListModules().get(i).getFeatureList().get(j).getOrigin().getY(), 
						fam.getListModules().get(i).getFeatureList().get(j).getWidth(), 
						fam.getListModules().get(i).getFeatureList().get(j).getHeight()
						);
				rightFAM.add(button);
			}
		}
	}
	
	

	
	private class DrawStuff extends JPanel{

	// Graphics is the base class that allows for drawing on components	
	public void paintComponent(Graphics g){		
			
			Graphics2D graph2 = (Graphics2D)g;
			
			for(int i = 0 ; i < fam.getListModules().size(); i++) {
				
				graph2.draw(new Rectangle2D.Float(	(float) fam.getListModules().get(i).getOrigin().getX(),
													(float) fam.getListModules().get(i).getOrigin().getY(), 
													fam.getListModules().get(i).getWidth(), 
													fam.getListModules().get(i).getHeight()	));
				graph2.drawString(	fam.getListModules().get(i).getName(),
								  	(int)fam.getListModules().get(i).getOrigin().getX() + 7, 
								  	(int)fam.getListModules().get(i).getOrigin().getY() + 15);
			}
			
			for(int i = 0 ; i < fam.getListInfoFlow().size(); i++) {
				
				graph2.draw( new Line2D.Double(	fam.getListInfoFlow().get(i).getSource().getOrigin().getX() + fam.getListInfoFlow().get(i).getSource().getWidth()/2, 
												fam.getListInfoFlow().get(i).getSource().getOrigin().getY() + fam.getListInfoFlow().get(i).getSource().getHeight()/2, 
												fam.getListInfoFlow().get(i).getTarget().getOrigin().getX() + fam.getListInfoFlow().get(i).getTarget().getWidth()/2, 
												fam.getListInfoFlow().get(i).getTarget().getOrigin().getY() + fam.getListInfoFlow().get(i).getTarget().getHeight()/2
												));
				
			}	
		}
	}

	public void mouseClicked(MouseEvent e) {
		checkHit(e.getX(), e.getY());
	}
	
	public void checkHit(int x, int y) {
		
		for(int i = 0 ; i < 	fam.getListModules().size(); i++) {
			for(int j = 0; j < 	fam.getListModules().get(i).getFeatureList().size(); j++) {
				
				if(	//clicked point is larger than left border
							fam.getListModules().get(i).getFeatureList().get(j).getOrigin().getX() 		< x) {
				if(	//clicked point is smaller than right border	
							fam.getListModules().get(i).getFeatureList().get(j).getOrigin().getX() +
							fam.getListModules().get(i).getFeatureList().get(j).getWidth() 				> x) {
				if(	//clicked point is larger than top border
							fam.getListModules().get(i).getFeatureList().get(j).getOrigin().getY() 		< y) {
				if(	//clicked point is smaller than bottom border
							fam.getListModules().get(i).getFeatureList().get(j).getOrigin().getY() +
							fam.getListModules().get(i).getFeatureList().get(j).getHeight() 			> y) {
						
				System.out.println("Clicked feature: " + fam.getListModules().get(i).getFeatureList().get(j).getName());
				}
				}
				}
				}
			}
		}
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	public void mouseReleased(MouseEvent e) {
		
	} 
}


