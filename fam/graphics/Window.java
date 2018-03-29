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

import org.architecturemining.fam.IO.ConsoleDemo;
import org.architecturemining.fam.IO.ReadXML;
import org.architecturemining.fam.IO.TraceExportXML;
import org.architecturemining.fam.IO.WriteXML;
import org.architecturemining.fam.model.FunctionalArchitectureModel;
import org.architecturemining.fam.model.Trace;;

@SuppressWarnings("serial")

public class Window extends JFrame {
	
	FamPanel mainPanel;

	ArrayList<Trace> traceList = new ArrayList<Trace>();
	
	FunctionalArchitectureModel fam = new FunctionalArchitectureModel();
	
	MenuPanelComponents menuPanelComponents = new MenuPanelComponents();
	FamPanelComponents famPanelComponents = new FamPanelComponents();
	
	int currentTrace = 0;
	
	public static void main(){
		
		new Window();

	}
	
	public Window(){
		
			WriteXML.main();
			ReadXML.readXML(fam);
			ConsoleDemo.main(fam);
			
			//Window
			this.setSize(1100, 800);

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

	public class ListenForButton implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == menuPanelComponents.getSaveTrace()) {
				menuPanelComponents.getTextArea().append(	"Trace: " + menuPanelComponents.getNameField().getText() + "\n" + 
															menuPanelComponents.getCurrentTraceTextArea().getText() + "\n\n"
															);
				
				traceList.get(currentTrace).setIdTrace("t"+currentTrace);
				traceList.get(currentTrace).setNameTrace(menuPanelComponents.getNameField().getText());
				traceList.get(currentTrace).setDescriptionTrace(menuPanelComponents.getDescriptionTextArea().getText());
				
				menuPanelComponents.getNameField().setText("");
				menuPanelComponents.getDescriptionTextArea().setText("");
				menuPanelComponents.getCurrentTraceTextArea().setText("");
				
				currentTrace++;
				traceList.add(new Trace());
			}
			
			if(e.getSource() == menuPanelComponents.getExportLog()) {
				TraceExportXML.writeXML(traceList);
			}
			
			//adds pressed feature buttons to the tracelist
			for(int i = 0 ; famPanelComponents.getFeatureButtonList().size() > i ; i++) {
				if(e.getSource() == famPanelComponents.getFeatureButtonList().get(i)) {
					traceList.get(currentTrace).addFeature( famPanelComponents.getFeatureButtonList().get(i).getFeature());
					menuPanelComponents.getCurrentTraceTextArea().append("-"+ famPanelComponents.getFeatureButtonList().get(i).getName());
					
				}
			}
            repaint();
		}

	}
		
	class FamPanel extends JPanel {
		  
		public void paintComponent(Graphics g) {

			Graphics2D graph2 = (Graphics2D)g;
			
			//draw module borders and feature names
			for(int i = 0 ; i < fam.getListModules().size(); i++) {
				//draw module borders
				graph2.draw(new Rectangle2D.Float(	(float) fam.getListModules().get(i).getOrigin().getX(),
													(float) fam.getListModules().get(i).getOrigin().getY(), 
															fam.getListModules().get(i).getWidth(), 
															fam.getListModules().get(i).getHeight()	));
				//draw feature names
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
						                    
				Double startX = (traceList.get(currentTrace).featureNameList.get(j).getOrigin().getX() 		+ traceList.get(currentTrace).featureNameList.get(j).getWidth()/2);
				Double startY = (traceList.get(currentTrace).featureNameList.get(j).getOrigin().getY() 		+ traceList.get(currentTrace).featureNameList.get(j).getHeight()/2);
				Double endX = 	(traceList.get(currentTrace).featureNameList.get(j+1).getOrigin().getX() 	+ traceList.get(currentTrace).featureNameList.get(j+1).getWidth()/2);
				Double endY = 	(traceList.get(currentTrace).featureNameList.get(j+1).getOrigin().getY() 	+ traceList.get(currentTrace).featureNameList.get(j+1).getHeight()/2);
				
				int middleX = (int) (startX + ((endX-startX)/2));
				int middleY = (int) (startY + ((endY-startY)/2));						
				
				Double angle = Math.atan2(endY-startY, endX-startX)* (180 / Math.PI);
				
				graph2.setColor(Color.RED);
				graph2.setStroke(new BasicStroke(2f));
				
				//draw arrowhead
				Arrowhead arrowHead = new Arrowhead(12,12);	
				arrowHead.rotateByDegrees(angle - 90);	
				arrowHead.setLocation(middleX, middleY);
				graph2.draw(arrowHead.getTransformedInstance());
				
				//draw trace
				graph2.draw(new Line2D.Double(startX, startY, endX, endY));
				
				graph2.setStroke(new BasicStroke(1f));
				graph2.setColor(Color.BLACK);
			}
		}	
	}

}