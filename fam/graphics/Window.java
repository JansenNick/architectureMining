package org.architecturemining.fam.graphics;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;
import javax.swing.JFrame;

import org.architecturemining.fam.IO.ReadXML;
import org.architecturemining.fam.IO.WriteXML;
import org.architecturemining.fam.model.ConsoleDemo;
import org.architecturemining.fam.model.FunctionalArchitectureModel;

@SuppressWarnings("serial")


public class Window extends JFrame {
	
	public static void main(){
		
		new Window();
		
	}
	
	public Window(){
			
			this.setSize(1000, 1000);
			
			this.setTitle("Drawing Shapes");
			
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			this.add(new DrawStuff(), BorderLayout.CENTER);
			
			this.setVisible(true);
		}
	
//Creating my own component by extending JComponent
	// JComponent is the base class for all swing components. Even custom ones
	
	private class DrawStuff extends JComponent{
		
		// Graphics is the base class that allows for drawing on components	
		public void paint(Graphics g){		
			
			FunctionalArchitectureModel fam = new FunctionalArchitectureModel();
			
			WriteXML.main();
			ReadXML.main(fam);
			
			ConsoleDemo.main(fam);
			
			Graphics2D graph2 = (Graphics2D)g;		
			
			for(int i = 0 ; i < fam.getListModules().size(); i++) {
				
				graph2.draw(new Rectangle2D.Float(	(float) fam.getListModules().get(i).getOrigin().getX(),
													(float) fam.getListModules().get(i).getOrigin().getY(), 
													fam.getListModules().get(i).getWidth(), 
													fam.getListModules().get(i).getHeight()	));
						
				for(int j = 0; j < fam.getListModules().get(i).getFeatureList().size(); j++) {

					graph2.draw(new Rectangle2D.Float(	(float) fam.getListModules().get(i).getFeatureList().get(j).getOrigin().getX(),
														(float) fam.getListModules().get(i).getFeatureList().get(j).getOrigin().getY(), 
														fam.getListModules().get(i).getFeatureList().get(j).getWidth(), 
														fam.getListModules().get(i).getFeatureList().get(j).getHeight() ));
				}	
			}		
		}		
	

		
	}

	
}


