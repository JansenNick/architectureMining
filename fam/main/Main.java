package org.architecturemining.fam.main;

import java.io.InputStream;

import javax.swing.JComponent;
import javax.swing.JLabel;

import org.architecturemining.fam.IO.ReadXML;
import org.architecturemining.fam.graphics.Window;
import org.architecturemining.fam.model.FunctionalArchitectureModel;
import org.processmining.contexts.uitopia.annotations.UIImportPlugin;
import org.processmining.contexts.uitopia.annotations.UITopiaVariant;
import org.processmining.contexts.uitopia.annotations.Visualizer;
import org.processmining.framework.abstractplugins.AbstractImportPlugin;
import org.processmining.framework.plugin.PluginContext;
import org.processmining.framework.plugin.annotations.Plugin;

public class Main{
	
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
	
	@UIImportPlugin(
             description = "Functional Architecture Model",
             extensions = { "xml" }
	)

	public class FAMImporter extends AbstractImportPlugin {   

	    @Override
        protected Object importFromStream(PluginContext context, InputStream input, String filename, long fileSizeInBytes)  throws Exception {
	                FunctionalArchitectureModel fam = ReadXML.readXML(input);
	                context.getFutureResult(0).setLabel("FAM");
	                return fam;
	        }
		}
	 
	 
	 @Visualizer
     public JComponent visualize(PluginContext context, FunctionalArchitectureModel fam) {
		 
		 return new JLabel("FAM");
		 
	 } 

	public static void main (String[] args) {
		
	Window.main();
	
	}
}