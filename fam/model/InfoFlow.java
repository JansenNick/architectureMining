package org.architecturemining.fam.model;

public class InfoFlow implements Visitable {
	private Feature source;
	private Feature target;
	private String name;
	
	/**
	 * Constructor for an Information Flow in an FAM diagram
	 * @param source is the starting point of an information flow
	 * @param target is the ending point of an information flow
	 */
	public InfoFlow(Feature source, Feature target) {
		this.source = source;
		this.target = target;
	}
	
	public String accept(Visitor visitor) {
		return visitor.visit(this);
	}
	
	public Feature getSource() {
		return this.source;
	}
	
	public void setSource(Feature feature) {
		this.source = feature;
	}
	
	public Feature getTarget() {
		return this.target;
	}
	
	public void setTarget(Feature feature) {
		this.target = feature;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String newName) {
		this.name = newName;
	}
	
}
