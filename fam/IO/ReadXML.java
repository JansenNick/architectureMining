package org.architecturemining.fam.IO;

import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.architecturemining.fam.model.Feature;
import org.architecturemining.fam.model.FunctionalArchitectureModel;
import org.architecturemining.fam.model.InfoFlow;
import org.architecturemining.fam.model.Module;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**Class that handles the reading of XML files in several ways. The readXML(File) and readXML(InputStream) are present for future
 * functionality to make it possible for the user to load xml files in ProM. The readXML(FunctionalArchitectureModel) uses a hard
 * coded location of the xml document, this version is called in the current version of the program. The xml file to read should be stored in 
 * the /src/org.architecturemining.fam.IOfiles folder and be named jdomMade.xml.
 * 
 * @author Nick
 */
public class ReadXML {
	
	public static FunctionalArchitectureModel readXML(File file) throws JDOMException, IOException, FamException {
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(file);
	
		return buildFromSAX(doc);
	}
	
	public static FunctionalArchitectureModel readXML(InputStream input) throws JDOMException, IOException, FamException {
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(input);
	
		return buildFromSAX(doc);
	}
	
	public static void readXML(FunctionalArchitectureModel fam) {
		
		SAXBuilder builder = new SAXBuilder();
		Document readDoc = null;
			 
		try {
			readDoc = builder.build(new File("./src/org.architecturemining.fam.IOfiles/jdomMade.xml"));
		} 
		catch (JDOMException | IOException e) {
			e.printStackTrace();
		}
			
		Element root = readDoc.getRootElement();
		
		parseNodes(root, fam);
		parseInfoFlows(root, fam);
		
		}
	
	@SuppressWarnings("unused")
	private static FunctionalArchitectureModel buildFromSAX(Document doc) throws FamException {
		
		FunctionalArchitectureModel fam = new FunctionalArchitectureModel();
		Element root = doc.getRootElement();
		
		parseNodes(root, fam);
		parseInfoFlows(root, fam);
		
		return fam;
	}
	
	//Parsing of Modules and Features
	public static void parseNodes(Element root, FunctionalArchitectureModel fam) {
		for (Element moduleEle : root.getChildren("FAMnode")) {
			//Parsing of Modules
			//Creates new module(id, name, point, width, height)
			Module module = new Module("3", 
									   moduleEle.getChildText("name"),
									   new Point2D.Double(	Integer.parseInt(moduleEle.getChildText("origin").split(",")[0]),
													      	Integer.parseInt(moduleEle.getChildText("origin").split(",")[1])	),
									   Integer.parseInt(moduleEle.getChildText("width")), 
									   Integer.parseInt(moduleEle.getChildText("height")));
			
			fam.addModule(module);
			
			for (Element featureEle : moduleEle.getChildren("FAMnode")) {	
				//Parsing of Features
				//Creates new feature(id, name, point, width, height)
				Feature feature = new Feature(	"5", 
												featureEle.getChildText("name"), 
												new Point2D.Double(	Integer.parseInt(featureEle.getChildText("origin").split(",")[0]),
						  									  	   	Integer.parseInt(featureEle.getChildText("origin").split(",")[1])	), 
												Integer.parseInt(featureEle.getChildText("width")), 
												Integer.parseInt(featureEle.getChildText("height")));
				
				module.addFeature(feature);				
			}
		}
	}
	
	//Parsing of InfoFlows
	public static void parseInfoFlows(Element root, FunctionalArchitectureModel fam) {
	
		for (Element lineEle : root.getChildren("line")) {
			
			InfoFlow infoFlow = new InfoFlow(lineEle.getChildText("name"));		
		
			fam.addInfoFlow(infoFlow);		
			
			//Loops through all modules in the fam
			for(int i = 0 ; i < fam.getListModules().size(); i++) {	
				//Loop through all features in the modules
				for(int j = 0; j < fam.getListModules().get(i).getFeatureList().size(); j++) {
					//if the source value of the current item is equal to one of the features the source is set to that feature
					if(lineEle.getAttributeValue("source").equals(fam.getListModules().get(i).getFeatureList().get(j).getName())){
						
						for(int p = 0 ; p < fam.getListInfoFlow().size(); p++) {
							
							if (fam.getListInfoFlow().get(p).getName() == lineEle.getChildText("name")) {
								fam.getListInfoFlow().get(p).setSource(fam.getListModules().get(i).getFeatureList().get(j));
							}
						}
					}
					//if the target value of the current item is equal to one of the features the target is set to that feature
					if(lineEle.getAttributeValue("target").equals(fam.getListModules().get(i).getFeatureList().get(j).getName())){
						
						for(int p = 0 ; p < fam.getListInfoFlow().size(); p++) {
							
							if (fam.getListInfoFlow().get(p).getName() == lineEle.getChildText("name")) {
								fam.getListInfoFlow().get(p).setTarget(fam.getListModules().get(i).getFeatureList().get(j));
							}
						}
					}
				}
			}			
		}
	}
	
	@SuppressWarnings("serial")
	public class FamException extends Exception {
	
	}
}