package org.architecturemining.fam.model;

import java.util.ArrayList;

/**A Trace is the order of features pressed by the user. Traces are stored in a traceList of the Window class which 
 * represents the frame of the application. The current Trace is represented as one or more red arrows on the screen and
 * in the current trace text box. Saved Traces are represented in the logbook.
 * 
 * A Trace consists of an id, a name, a description and a list of features.
 * @param id				id of the Trace
 * @param name				name of the Trace
 * @param description		description of the Trace
 * @param featureNameList	ordered list of all the features in the trace
 * 
 * @author Nick
 */
public class Trace {
	
	String id = "empty id";
	String name = "empty name";
	String description = "empty description";
	public ArrayList<Feature> featureNameList = new ArrayList<Feature>();
	
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
