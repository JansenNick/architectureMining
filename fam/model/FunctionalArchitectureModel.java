package org.architecturemining.fam.model;

import java.awt.geom.Point2D;
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
		//creation of the Functional architecture model
		FunctionalArchitectureModel fam = new FunctionalArchitectureModel();
		
		//creation of an module + adding it to the list of the FAM
		Module m1 = new Module(new Point2D.Double(0,0), 10, 10);
		fam.addModule(m1);
		
		//creation of 2 features + adding them to the list of the module m1
		Feature f1 = new Feature(new Point2D.Double(1,1), 3, 3);
		Feature f2 = new Feature(new Point2D.Double(5,1), 3, 3);
		m1.addFeature(f1);
		m1.addFeature(f2);
		
		//creation of an InfoFlow + adding it to the lijstInfoFlow
		InfoFlow i1 = new InfoFlow(f1,f2);
		fam.addInfoFlow(i1);
		
		//test
		System.out.println("testnick");
		
		//visitor testing
		PrintStringVisitor stringPrint = new PrintStringVisitor();
		m1.setName("Module 1");
		f1.setName("Feature 1");
		f2.setName("Feature 2");
		i1.setName("Information Flow 1");
		m1.accept(stringPrint);
		f1.accept(stringPrint);
		i1.accept(stringPrint);
		//MyDomParser parser = new MyDomParser();
		//parser.main(args);
		System.out.println("github");
		
	}
	
}
