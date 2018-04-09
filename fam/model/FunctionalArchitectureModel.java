package org.architecturemining.fam.model;

import java.util.ArrayList;

/**Master class for the data model, the FunctionalArchitectureModel holds an array of modules and a list of infoFlows.
 * Together they represent the functional architecture model. Each module can contain features, those are stored
 * in a array in each module. 
 * 
 * @author Nick
 */
public class FunctionalArchitectureModel {
	
	private ArrayList<Module> listModules = new ArrayList<Module>();
	private ArrayList<InfoFlow> listInfoFlow = new ArrayList<InfoFlow>();
	
	public static void main (String[] args) {
	
	}
	
	//Adding of modules to the corresponding arrays
	public void addModule(Module m){
		getListModules().add(m);
	}
	
	public void addInfoFlow(InfoFlow i) {
		getListInfoFlow().add(i);
	}
	
	//Getters and setters
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
	
}
