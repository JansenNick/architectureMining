package org.architecturemining.fam.model;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Module extends ModelNode implements Visitable {

	private ArrayList<Feature> listFeatures = new ArrayList<Feature>();			
	
	public Module(Point2D.Double point, int width, int height)
	{ 
		super(point, width , height);
	}

	public void addFeature(Feature feature)
	{
		listFeatures.add(feature);
	}

	public String accept(Visitor visitor) {
		return visitor.visit(this);
	}

}
