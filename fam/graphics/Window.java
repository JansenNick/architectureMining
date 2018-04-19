package org.architecturemining.fam.graphics;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import org.architecturemining.fam.IO.ConsoleDemo;
import org.architecturemining.fam.IO.ReadXML;
import org.architecturemining.fam.IO.TraceExportXML;
import org.architecturemining.fam.IO.WriteXML;
import org.architecturemining.fam.model.FunctionalArchitectureModel;
import org.architecturemining.fam.model.Trace;;

/** The Window class represents the JFrame in which everything happens. It is layed out according to the Border layout manager
 *  and consists of two panels. The left panel is the menuPanel and is an regular JPanel, the right panel which shows the FAM diagram
 *  is of the custom FAMPanel class which is an extension of a JPanel. The FAMPanel class is a private class of the Window class and is
 *  defined at the bottom of this file.
 * 
 * A Window consists of the variables below:
 * traceList			holds a list of all the created traces of the user
 * fam					the data model of the functional architecture model
 * menuPanelComponents	a custom object which stores all the menu panel components
 * famPanelComponents	a custom object which stores all the fam panel components
 * currentTrace			a simple counter to keep track which trace is currently active
 * 
 * @author Nick
 */
@SuppressWarnings("serial")

public class Window extends JFrame {

	ArrayList<Trace> traceList = new ArrayList<Trace>();
	
	FunctionalArchitectureModel fam = new FunctionalArchitectureModel();
	
	MenuPanelComponents menuPanelComponents = new MenuPanelComponents();
	FamPanelComponents famPanelComponents = new FamPanelComponents();
	
	int currentTrace = 0;
	
	public static void main(){
		
		new Window();

	}
	
	/**Constructor of the Window class, sets the layout manager, creates the panels, adds components to the panels and
	 * adds the panels to the JFrame at the right place. Also initiates the first Trace so the user can start building traces
	 * right away.
	 */
	public Window(){
		
			WriteXML.main();
			ReadXML.readXML(fam);
			ConsoleDemo.main(fam);
			
			//Window
			this.setSize(1200, 800);

			this.setTitle("FAM Sequence Creator");
			
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
			traceList.add(new Trace());
			
			this.setLayout(new BorderLayout());
			
			JPanel menuPanel = new JPanel();
			FamPanel famPanel = new FamPanel();
			
			menuPanelComponents.addMenuPanelComponents(menuPanel, this);
			famPanelComponents.addFamPanelComponents(fam, famPanel, this);
			
			traceList.get(currentTrace).setNameTrace("Trace" + currentTrace);
			
			this.add(menuPanel, BorderLayout.WEST);
			this.add(famPanel, BorderLayout.CENTER);

			this.setVisible(true);
			this.setResizable(true);
			
		}

	/**Implementation of an action listener to respond to all user activity. 
	 * 
	 * @author Nick
	 */
	public class ListenForButton implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			
			//happens if the "Save trace" button is pushed
			if(e.getSource() == menuPanelComponents.getSaveTrace()) {
				menuPanelComponents.getTextArea().append(	"Trace: " + menuPanelComponents.getNameField().getText() + "\n" + 
															menuPanelComponents.getCurrentTraceTextArea().getText() + "\n\n"
															);
				menuPanelComponents.updateTable(menuPanelComponents.getNameField().getText(), currentTrace, 0);
				menuPanelComponents.updateTable(menuPanelComponents.getCurrentTraceTextArea().getText(), currentTrace, 1);
				//object meegeven gaat nog mis
				Object[] toAddObject = {};
				menuPanelComponents.addRow(toAddObject);
				
				traceList.get(currentTrace).setIdTrace("t"+currentTrace);
				traceList.get(currentTrace).setNameTrace(menuPanelComponents.getNameField().getText());
				traceList.get(currentTrace).setDescriptionTrace(menuPanelComponents.getDescriptionTextArea().getText());
				
				menuPanelComponents.getNameField().setText("");
				menuPanelComponents.getDescriptionTextArea().setText("");
				menuPanelComponents.getCurrentTraceTextArea().setText("");
				
				currentTrace++;
				traceList.add(new Trace());
			}
			//happens if the "Export" button is pushed
			if(e.getSource() == menuPanelComponents.getExportLog()) {
				TraceExportXML.writeXML(traceList);
			}
			
			//happens every time a button is pushed
			for(int i = 0 ; famPanelComponents.getFeatureButtonList().size() > i ; i++) {
				//happens if one of the feature buttons is pushed
				if(e.getSource() == famPanelComponents.getFeatureButtonList().get(i)) {
					//adds pressed feature buttons to the tracelist
					traceList.get(currentTrace).addFeature( famPanelComponents.getFeatureButtonList().get(i).getFeature());
					
					//updates current trace text area, when the text area is empty the "-" is left out
					if(menuPanelComponents.getCurrentTraceTextArea().getText().isEmpty()) {
						menuPanelComponents.getCurrentTraceTextArea().append(		famPanelComponents.getFeatureButtonList().get(i).getFeature().getId());
					}
					else {
						menuPanelComponents.getCurrentTraceTextArea().append("-" + 	famPanelComponents.getFeatureButtonList().get(i).getFeature().getId());
					}	
				}
			}
			//refreshes all elements on the screen, so the new trace step becomes visible
            repaint();
		}
	}
	
	public class ListenForTable implements TableModelListener{

		public void tableChanged(TableModelEvent e) {
			repaint();
		}
		
	}
	
	/**Draws all the elements required in the FamPanel(except for there arrowhead, those a defined in an seperate class).
	 * @author Nick
	 *
	 */
	class FamPanel extends JPanel {
		  
		public void paintComponent(Graphics g) {

			Graphics2D graph2 = (Graphics2D)g;
			
			//draw module borders and module names
			for(int i = 0 ; i < fam.getListModules().size(); i++) {
				//draw module borders
				graph2.draw(new Rectangle2D.Float(	(float) fam.getListModules().get(i).getOrigin().getX(),
													(float) fam.getListModules().get(i).getOrigin().getY(), 
															fam.getListModules().get(i).getWidth(), 
															fam.getListModules().get(i).getHeight()	));
				//draw module names
				graph2.drawString(			fam.getListModules().get(i).getName(),
				            		(int)	fam.getListModules().get(i).getOrigin().getX() + 7, 
				            		(int)	fam.getListModules().get(i).getOrigin().getY() + 15);
			}
			//draw info flows
			for(int i = 0 ; i < fam.getListInfoFlow().size(); i++) {
				
				Double startX = fam.getListInfoFlow().get(i).getSource().getOrigin().getX() + fam.getListInfoFlow().get(i).getSource().getWidth()/2;
				Double startY =	fam.getListInfoFlow().get(i).getSource().getOrigin().getY() + fam.getListInfoFlow().get(i).getSource().getHeight()/2;
				Double endX = 	fam.getListInfoFlow().get(i).getTarget().getOrigin().getX() + fam.getListInfoFlow().get(i).getTarget().getWidth()/2;
				Double endY = 	fam.getListInfoFlow().get(i).getTarget().getOrigin().getY() + fam.getListInfoFlow().get(i).getTarget().getHeight()/2;

				graph2.draw( new Line2D.Double(	startX, startY, endX, endY));
				
			}
			
			//draw traces
			for(int j = 0; j + 1 < traceList.get(currentTrace).featureNameList.size(); j++) {
						                    
				Double startX = (traceList.get(currentTrace).featureNameList.get(j).getOrigin().getX() 		+ traceList.get(currentTrace).featureNameList.get(j).getWidth()/2)-5;
				Double startY = (traceList.get(currentTrace).featureNameList.get(j).getOrigin().getY() 		+ traceList.get(currentTrace).featureNameList.get(j).getHeight()/2)-5;
				Double endX = 	(traceList.get(currentTrace).featureNameList.get(j+1).getOrigin().getX() 	+ traceList.get(currentTrace).featureNameList.get(j+1).getWidth()/2)-5;
				Double endY = 	(traceList.get(currentTrace).featureNameList.get(j+1).getOrigin().getY() 	+ traceList.get(currentTrace).featureNameList.get(j+1).getHeight()/2)-5;
				
				//set color and stroke
				graph2.setColor(Color.RED);
				graph2.setStroke(new BasicStroke(2f));
				
				//draw arrowhead
				int middleX = (int) (startX + ((endX-startX)/2));
				int middleY = (int) (startY + ((endY-startY)/2));						
				
				Double angle = Math.atan2(endY-startY, endX-startX)* (180 / Math.PI);
				
				Arrowhead arrowHead = new Arrowhead(12,12);	
				arrowHead.rotateByDegrees(angle - 90);	
				arrowHead.setLocation(middleX, middleY);
				graph2.draw(arrowHead.getTransformedInstance());
				graph2.drawString("" + j, middleX+10, middleY+10);
				
				//draw trace
				graph2.draw(new Line2D.Double(startX, startY, endX, endY));
				
				//reset color and stroke
				graph2.setStroke(new BasicStroke(1f));
				graph2.setColor(Color.BLACK);
			}
			
			for (int i = 0; i<20 ; i++) {
				
				if(menuPanelComponents.checkActiveRow(i)){
					for(int j = 0; j + 1 < traceList.get(i).featureNameList.size(); j++) {
	                    
						Double startX = 2*i+(traceList.get(i).featureNameList.get(j).getOrigin().getX() 		+ traceList.get(i).featureNameList.get(j).getWidth()/2);
						Double startY = 2*i+(traceList.get(i).featureNameList.get(j).getOrigin().getY() 		+ traceList.get(i).featureNameList.get(j).getHeight()/2);
						Double endX = 	2*i+(traceList.get(i).featureNameList.get(j+1).getOrigin().getX() 		+ traceList.get(i).featureNameList.get(j+1).getWidth()/2);
						Double endY = 	2*i+(traceList.get(i).featureNameList.get(j+1).getOrigin().getY() 		+ traceList.get(i).featureNameList.get(j+1).getHeight()/2);
						
						//set color and stroke
						switch (i) {
							case 0:	graph2.setColor(Color.BLUE);
									break;
							case 1:	graph2.setColor(Color.MAGENTA);
									break;
							case 2:	graph2.setColor(Color.GREEN);
									break;
							case 3:	graph2.setColor(Color.GRAY);
									break;
							case 4:	graph2.setColor(Color.PINK);
									break;
							case 5: graph2.setColor(Color.CYAN);
						}

						graph2.setStroke(new BasicStroke(2f));
						
						//draw arrowhead
						int middleX = (int) (startX + ((endX-startX)/2));
						int middleY = (int) (startY + ((endY-startY)/2));						
						
						Double angle = Math.atan2(endY-startY, endX-startX)* (180 / Math.PI);
						
						Arrowhead arrowHead = new Arrowhead(12,12);	
						arrowHead.rotateByDegrees(angle - 90);	
						arrowHead.setLocation(middleX, middleY);
						graph2.draw(arrowHead.getTransformedInstance());
						
						//draw trace
						graph2.draw(new Line2D.Double(startX, startY, endX, endY));
						graph2.drawString("" + j, middleX + 10, middleY + 10);
						
						//reset color and stroke
						graph2.setStroke(new BasicStroke(1f));
						graph2.setColor(Color.BLACK);
					}	
				}
			}
		}
	}

}