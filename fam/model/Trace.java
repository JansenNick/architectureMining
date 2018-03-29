package org.architecturemining.fam.model;

import java.util.ArrayList;

public class Trace {
	
	public ArrayList<Feature> featureNameList = new ArrayList<Feature>();
	String id = "t0";
	String name = "name";
	String description = "description";
	
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
	
	public String getDescriptionTrace() {
		return this.description;
	}
	
	public void setDescriptionTrace(String description) {
		this.description = description;
	}

	public void setIdTrace(String newId) {
		id = newId;
	}
	
	public String getIdTrace() {
		return id;
	}
	
}
