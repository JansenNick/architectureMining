package org.architecturemining.fam.model;

public abstract class Visitor {
	
	public void visit(FunctionalArchitectureModel model) {
		preVisit(model);
		
		for(model.getModules(): module) {
			visit(module);
		}
		
		postVisit(model);
	}
	
	public void visit(Module module) {
		preVisit(module);
		
		for(module.getFeatures() : feature) {
			visit(feature);
		}
		
		postVisit(module);
	}
	
	protected void preVisit(FunctionalArchitectureModel model) {}
	protected void postVisit(FunctionalArchitectureModel model) {}
	
	protected void preVisit(Module module) {}
	protected void postVisit(Module module) {}
	
	public void visit(Feature feature) {}
	public void visit(InfoFlow infoFlow) {}
}