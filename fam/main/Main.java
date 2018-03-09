package org.architecturemining.fam.main;

import org.architecturemining.fam.graphics.Window;
import org.processmining.contexts.uitopia.annotations.UITopiaVariant;
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
	
	public static void main (String[] args) {
		
	Window.main();;
	
	}
}