package org.architecturemining.fam.model;

import java.util.ArrayList;

public class FunctionalArchitectureModel {
	
	private ArrayList<Module> listModules = new ArrayList<Module>();
	private ArrayList<InfoFlow> listInfoFlow = new ArrayList<InfoFlow>();
	
	private int sourceModule;
	private int sourceFeature;
	private  int targetModule;
	private int targetFeature;
	
	
	public static void main (String[] args) {
	
	}
	
	public void addModule(Module m)
	{
		getListModules().add(m);
	}
	
	public void addInfoFlow(InfoFlow i) {
		getListInfoFlow().add(i);
	}

	public ArrayList<Module> getListModules() {
		return listModules;
	}

	public void setListModules(ArrayList<Module> listModules) {
		this.listModules = listModules;
	}
	
	public ArrayList<InfoFlow> getListInfoFlow() {
		return listInfoFlow;
	}
	
	public void setListInfoFlow(ArrayList<InfoFlow> listInfoFlow) {
		this.listInfoFlow = listInfoFlow;
	}

	public int getSourceModule() {
		return sourceModule;
	}

	public void setSourceModule(int sourceModule) {
		this.sourceModule = sourceModule;
	}

	public int getSourceFeature() {
		return sourceFeature;
	}

	public void setSourceFeature(int sourceFeature) {
		this.sourceFeature = sourceFeature;
	}

	public int getTargetModule() {
		return targetModule;
	}

	public void setTargetModule(int targetModule) {
		this.targetModule = targetModule;
	}

	public int getTargetFeature() {
		return targetFeature;
	}

	public void setTargetFeature(int targetFeature) {
		this.targetFeature = targetFeature;
	}
	
}
