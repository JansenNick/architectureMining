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

	public void addModule(Module m)
	{
		listModules.add(m);
	}
	
	public void addInfoFlow(InfoFlow i) {
		listInfoFlow.add(i);
	}
	
	public static void main (String[] args) {

		FunctionalArchitectureModel fam = new FunctionalArchitectureModel();
		
		JDOMparser.main(fam);
		
		for(int i = 0 ; i < fam.listModules.size(); i++) {
			System.out.println("Module Origin:");
			System.out.println(fam.listModules.get(i).getOrigin());
			
			for(int j = 0; j < fam.listModules.get(i).getFeatureList().size(); j++) {
				System.out.println("Feature width");			
				System.out.println(fam.listModules.get(i).getFeatureList().get(j).getWidth());
				
			}			
		}		
	}	
}
