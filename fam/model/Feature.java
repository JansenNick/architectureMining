package org.architecturemining.fam.model;
import java.awt.geom.Point2D;

public class Feature extends FAMNode implements Visitable {
	
	public Feature(Point2D.Double point, int width, int height) {
		super(point, width, height);
		};
		
	public Feature(String id, String name,Point2D.Double point, int width, int height) {
		super(id, name, point, width , height);
		};		
		
		public String accept(Visitor visitor) {
			return visitor.visit(this);
		}
}
