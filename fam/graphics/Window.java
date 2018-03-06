package org.architecturemining.fam.graphics;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;
import javax.swing.JFrame;

import org.architecturemining.fam.model.FunctionalArchitectureModel;

@SuppressWarnings("serial")


public class Window extends JFrame {
	
	public static void main(FunctionalArchitectureModel fam){
		
		new Window(fam);
		
	}
	
	public Window(FunctionalArchitectureModel fam){
			
			this.setSize(500, 500);
			
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
				
			Graphics2D graph2 = (Graphics2D)g;		
		
			graph2.fill(new Rectangle2D.Float(10, 10, 150, 100));
					
		}
		
	}
	
	
	public static void createWindow(FunctionalArchitectureModel fam) {
		//1. Create the frame.
		JFrame frame = new JFrame("FrameDemo");

		//2. Optional: What happens when the frame closes?
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//4. Size the frame.
		frame.pack();
		//5. Show it.
		frame.setVisible(true);
		frame.setBounds(0, 0, 1200, 800);
		
		
	}

}