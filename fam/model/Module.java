package org.architecturemining.fam.model;

import java.awt.geom.Point2D;
import java.util.ArrayList;

/**A module is an structural element in a FAM, it is represented as an black rectangle on the screen.
 * Within it features are stored, those are stored in the listFeatures. Both module and feature are
 * generalized from FAMNode, within FAMNode all the information both need is stored.
 * 
 * @author Nick
 */
public class Module extends FAMNode implements Visitable {

	private ArrayList<Feature> listFeatures = new ArrayList<Feature>();			
	
	/**Constructors of a Module in a FAM diagram.
	 * @param id		id of the module
	 * @param name		name of the module
	 * @param point		point are the (x,y) coordinates of the origin of the module on the screen stored in an Point2D
	 * @param width		width determines how wide the module should appear on the screen
	 * @param height	height determines how high the module should appear on the screen
	 */
	public Module(Point2D.Double point, int width, int height)
	{ 
		super(point, width , height);
	}

	public Module(String id, String name, Point2D.Double point, int width, int height)
	{ 
		super(id, name, point, width , height);
	}
	
	//adds features to the listFeatures
	public void addFeature(Feature feature)
	{
		listFeatures.add(feature);
	}

	public String accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	public ArrayList<Feature> getFeatureList() {
		return listFeatures;
	}
	

	

}
