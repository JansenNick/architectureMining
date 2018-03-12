package org.architecturemining.fam.graphics;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;
import javax.swing.JFrame;

import org.architecturemining.fam.IO.ReadXML;
import org.architecturemining.fam.IO.WriteXML;
import org.architecturemining.fam.model.ConsoleDemo;
import org.architecturemining.fam.model.FunctionalArchitectureModel;

@SuppressWarnings("serial")


public class Window extends JFrame implements MouseListener {
	
	FunctionalArchitectureModel fam = new FunctionalArchitectureModel();
	
	public static void main(){
		
		new Window();

	}
	
	public Window(){
		
			WriteXML.main(); 
			ReadXML.readXML(fam);
			
			ConsoleDemo.main(fam);
			
			this.setSize(1000, 1000);
			
			this.setTitle("Functional Architecture Model");
			
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			this.add(new DrawStuff(), BorderLayout.CENTER);
			
			this.setVisible(true);
			
			addMouseListener(this);
		}
	
	private class DrawStuff extends JComponent{
		
		// Graphics is the base class that allows for drawing on components	
		public void paint(Graphics g){		
			
			Graphics2D graph2 = (Graphics2D)g;
			
			for(int i = 0 ; i < fam.getListModules().size(); i++) {
				
				graph2.draw(new Rectangle2D.Float(	(float) fam.getListModules().get(i).getOrigin().getX(),
													(float) fam.getListModules().get(i).getOrigin().getY(), 
													fam.getListModules().get(i).getWidth(), 
													fam.getListModules().get(i).getHeight()	));
				graph2.drawString(	fam.getListModules().get(i).getName(),
								  	(int)fam.getListModules().get(i).getOrigin().getX() + 7, 
								  	(int)fam.getListModules().get(i).getOrigin().getY() + 15);
						
				for(int j = 0; j < fam.getListModules().get(i).getFeatureList().size(); j++) {

					graph2.draw(new Rectangle2D.Float(	(float) fam.getListModules().get(i).getFeatureList().get(j).getOrigin().getX(),
														(float) fam.getListModules().get(i).getFeatureList().get(j).getOrigin().getY(), 
														fam.getListModules().get(i).getFeatureList().get(j).getWidth(), 
														fam.getListModules().get(i).getFeatureList().get(j).getHeight() ));
					graph2.drawString(	fam.getListModules().get(i).getFeatureList().get(j).getName(),
						  				(int)fam.getListModules().get(i).getFeatureList().get(j).getOrigin().getX() + 7, 
						  				(int)fam.getListModules().get(i).getFeatureList().get(j).getOrigin().getY() + 15 );
				}	
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
		
		for(int i = 0 ; i < 	fam.getListModules().size(); i++) {
			for(int j = 0; j < 	fam.getListModules().get(i).getFeatureList().size(); j++) {
				if(	(int)		fam.getListModules().get(i).getFeatureList().get(j).getOrigin().getX() > e.getX()  ) {
					System.out.println("Clicked feature: " + fam.getListModules().get(i).getFeatureList().get(j).getName());
					/*if(((int)	fam.getListModules().get(i).getFeatureList().get(j).getOrigin().getX() +
								fam.getListModules().get(i).getFeatureList().get(j).getWidth()) < e.getX()) {
					
					System.out.println("Clicked feature: " + fam.getListModules().get(i).getFeatureList().get(j).getName());
					}
					
					System.out.println("test");
					System.out.println(		((int)	fam.getListModules().get(i).getFeatureList().get(j).getOrigin().getX() +
											fam.getListModules().get(i).getFeatureList().get(j).getWidth()));
					System.out.println(e.getX());
					//System.out.println("test");*/
					
				}
			}
		}
		
		System.out.println("click" + e.getX());
		
		
		

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
		// TODO Auto-generated method stub
		
	} 
}


