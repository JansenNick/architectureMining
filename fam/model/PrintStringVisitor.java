package org.architecturemining.fam.model;

class PrintStringVisitor implements Visitor {

	public String visit(Module module) {
		System.out.println("Module size:" + " Width = " + module.getWidth()+ " Height = " + module.getHeight());
		return null;
	}

	public String visit(Feature feature) {
		System.out.println(feature.getName() + " has width = " + feature.getWidth()+ " and height = " + feature.getHeight());
		return null;
	}

	public String visit(InfoFlow infoFlow) {
		System.out.println(infoFlow.getName() + " has as source " + infoFlow.getSource().getName() + 
												" and as target " + infoFlow.getTarget().getName());
		return null;
	}
}