package org.architecturemining.fam.model;

interface Visitor{
	public String visit(Module module);
	public String visit(Feature feature);
	public String visit(InfoFlow infoFlow);
}