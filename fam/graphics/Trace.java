package org.architecturemining.fam.graphics;

import java.util.ArrayList;

import org.architecturemining.fam.model.Feature;

public class Trace {
	
	ArrayList<Feature> featureNameList = new ArrayList<Feature>();
	String name = "";
	
	public ArrayList<Feature> getNameFeatureList() {
		return featureNameList;
	}
	
	public void setNameFeatureList(ArrayList<Feature> listTrace) {
		this.featureNameList = listTrace;
	}
	
	public void addFeature(Feature feature) {
		getNameFeatureList().add(feature);
	}
	
	public void setNameTrace(String name) {
		this.name = name;
	}
	
	public String getNameTrace() {
		return this.name;
	}
	
}
