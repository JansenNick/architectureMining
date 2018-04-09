package org.architecturemining.fam.IO;

import org.architecturemining.fam.model.FunctionalArchitectureModel;

/**Prints the content of the FAM to the console for debugging purposes.
 * 
 * @author Nick
 */
public class ConsoleDemo {
	
	public static void main(FunctionalArchitectureModel fam) {
		
		for(int i = 0 ; i < fam.getListModules().size(); i++) {
			System.out.println("---------------------");
			System.out.println("Module name: " + fam.getListModules().get(i).getName());
			System.out.println("Origin: X" + fam.getListModules().get(i).getOrigin().getX() + " Y" +fam.getListModules().get(i).getOrigin().getY());
			System.out.println("Height: " + fam.getListModules().get(i).getHeight());
			System.out.println("Width: " + fam.getListModules().get(i).getWidth());
			
			for(int j = 0; j < fam.getListModules().get(i).getFeatureList().size(); j++) {
				System.out.println("\t" + "---------------------");
				System.out.println("\t" +"Feature name: " + fam.getListModules().get(i).getFeatureList().get(j).getName());			
				System.out.println("\t" +"Origin: X" + fam.getListModules().get(i).getFeatureList().get(j).getOrigin().getX() + " Y"+fam.getListModules().get(i).getFeatureList().get(j).getOrigin().getY());	
				System.out.println("\t" +"Height: " + fam.getListModules().get(i).getFeatureList().get(j).getHeight());	
				System.out.println("\t" +"Width: " + fam.getListModules().get(i).getFeatureList().get(j).getWidth());	
			}
		}
		
		for(int i = 0 ; i < fam.getListInfoFlow().size(); i++) {
			System.out.println("---------------------");
			System.out.println("InfoFlow name: " + fam.getListInfoFlow().get(i).getName());			
			System.out.println("InfoFlow source :" + fam.getListInfoFlow().get(i).getSource().getName());			
			System.out.println("InfoFlow target :" + fam.getListInfoFlow().get(i).getTarget().getName());		
		
		}
		
	}
	
}