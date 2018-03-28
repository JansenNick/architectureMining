package org.architecturemining.fam.model;

import java.util.ArrayList;

public class Trace {
	
	public ArrayList<Feature> featureNameList = new ArrayList<Feature>();
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
