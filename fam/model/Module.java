package org.architecturemining.fam.model;

import java.awt.geom.Point2D;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Module extends FAMNode implements Visitable {

	private ArrayList<Feature> listFeatures = new ArrayList<Feature>();			
	
	public Module(Point2D.Double point, int width, int height)
	{ 
		super(point, width , height);
	}
	
	public Module(String id, String name, Point2D.Double point, int width, int height)
	{ 
		super(id, name, point, width , height);
	}


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
