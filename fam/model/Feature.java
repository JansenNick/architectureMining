package org.architecturemining.fam.model;
import java.awt.geom.Point2D;

/**A features is an structural element in an FAM. Features are stored in the featureList of an Module.
 * A feature is represented as an button on the screen.
 * 
 * @author Nick
 */
public class Feature extends FAMNode  {
	
	public Feature(Point2D.Double point, int width, int height) {
		super(point, width, height);
		};

	/**Constructor methods for a Feature in an FAM diagram.
	 * @param id		id of the feature
	 * @param name		name of the feature
	 * @param point		point are the (x,y) coordinates of the origin of the feature on the screen stored in an Point2D
	 * @param width		width determines how wide the feature should appear on the screen
	 * @param height	height determines how high the feature should appear on the screen
	 */		
	public Feature(String id, String name,Point2D.Double point, int width, int height) {
		super(id, name, point, width, height);
		};			
}
