package org.architecturemining.fam.model;

import java.util.ArrayList;

import org.processmining.contexts.uitopia.annotations.UITopiaVariant;
import org.processmining.framework.plugin.PluginContext;
import org.processmining.framework.plugin.annotations.Plugin;

public class FunctionalArchitectureModel {

    @Plugin(
            name = "Log Generator FAM", 
            parameterLabels = {}, 
            returnLabels = { "Log file" }, 
            returnTypes = { String.class }, 
            userAccessible = true, 
            help = "Produces a log file for a functional architecture model based on user input"
    )
    @UITopiaVariant(
            affiliation = "University of Utrecht", 
            author = "Nick Jansen", 
            email = "n.jansen2@students.uu.nl"
    )
    
    public static String helloWorld(PluginContext context) {
            return "Hello World!";
    }
	
	private ArrayList<Module> listModules = new ArrayList<Module>();
	private ArrayList<InfoFlow> listInfoFlow = new ArrayList<InfoFlow>();
	
	
	private int sourceModule;
	private int sourceFeature;
	private  int targetModule;
	private int targetFeature;
	
	
	public void addModule(Module m)
	{
		getListModules().add(m);
	}
	
	public void addInfoFlow(InfoFlow i) {
		getListInfoFlow().add(i);
	}
	
	public static void main (String[] args) {

		FunctionalArchitectureModel fam = new FunctionalArchitectureModel();
		
		JDOMparser.main(fam);
		
		for(int i = 0 ; i < fam.getListModules().size(); i++) {
			System.out.println("Module name:");
			System.out.println(fam.getListModules().get(i).getName());
			
			for(int j = 0; j < fam.getListModules().get(i).getFeatureList().size(); j++) {
				System.out.println("\t" +"Feature name:");			
				System.out.println("\t" +fam.getListModules().get(i).getFeatureList().get(j).getName());
				
			}			
		}
		
		for(int i = 0 ; i < fam.getListInfoFlow().size(); i++) {
			System.out.println("InfoFlow name:");			
			System.out.println(fam.getListInfoFlow().get(i).getName());
			System.out.println("InfoFlow source :");			
			System.out.println(fam.getListInfoFlow().get(i).getSource().getName()); 
			System.out.println("InfoFlow target :");		
			System.out.println(fam.getListInfoFlow().get(i).getTarget().getName());
		
		}
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
