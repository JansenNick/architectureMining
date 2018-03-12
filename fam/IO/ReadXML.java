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

public class ReadXML {
	
public static void main(FunctionalArchitectureModel fam) {
	}

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

private static FunctionalArchitectureModel buildFromSAX(Document doc) throws FamException {
	
	FunctionalArchitectureModel fam = new FunctionalArchitectureModel();

	Element root = doc.getRootElement();
	
	//Parsing of Modules and Features
	for (Element moduleEle : root.getChildren("FAMnode")) {
		//Parsing of Modules
		Module module = new Module("3", 
								   moduleEle.getChildText("name"),
								   new Point2D.Double(Integer.parseInt(moduleEle.getChildText("origin").split(",")[0]),
												      Integer.parseInt(moduleEle.getChildText("origin").split(",")[1])),
								   Integer.parseInt(moduleEle.getChildText("width")), 
								   Integer.parseInt(moduleEle.getChildText("height")));
		
		fam.addModule(module);
		
		for (Element featureEle : moduleEle.getChildren("FAMnode")) {	
			//Parsing of Features
			Feature feature = new Feature(	"5", 
											featureEle.getChildText("name"), 
											new Point2D.Double(Integer.parseInt(featureEle.getChildText("origin").split(",")[0]),
					  									  	   Integer.parseInt(featureEle.getChildText("origin").split(",")[1])), 
											Integer.parseInt(featureEle.getChildText("width")), 
											Integer.parseInt(featureEle.getChildText("height")));
			
			module.addFeature(feature);				
		}
	}
	
	//Parsing of InfoFlows
	for (Element lineEle : root.getChildren("line")) {
		
		InfoFlow infoFlow = new InfoFlow(lineEle.getChildText("name"));		
	
		fam.addInfoFlow(infoFlow);		
		
		for(int i = 0 ; i < fam.getListModules().size(); i++) {	
			
			for(int j = 0; j < fam.getListModules().get(i).getFeatureList().size(); j++) {
				
				if(lineEle.getAttributeValue("source").equals(fam.getListModules().get(i).getFeatureList().get(j).getName())){
					
					for(int p = 0 ; p < fam.getListInfoFlow().size(); p++) {
						
						if (fam.getListInfoFlow().get(p).getName() == lineEle.getChildText("name")) {
							fam.getListInfoFlow().get(p).setSource(fam.getListModules().get(i).getFeatureList().get(j));
						}
					}
				}
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
	return fam;
}

public class FamException extends Exception {

}

public static void readXML(FunctionalArchitectureModel fam) {
	
	SAXBuilder builder = new SAXBuilder();
		
		Document readDoc = null;
		 
		try {
			readDoc = builder.build(new File("./src/jdomMade.xml"));
		} catch (JDOMException | IOException e) {

			e.printStackTrace();
		}
		
		
		Element root = readDoc.getRootElement();
		
		//Parsing of Modules and Features
		for (Element moduleEle : root.getChildren("FAMnode")) {
			//Parsing of Modules
			Module module = new Module("3", 
									   moduleEle.getChildText("name"),
									   new Point2D.Double(Integer.parseInt(moduleEle.getChildText("origin").split(",")[0]),
													      Integer.parseInt(moduleEle.getChildText("origin").split(",")[1])),
									   Integer.parseInt(moduleEle.getChildText("width")), 
									   Integer.parseInt(moduleEle.getChildText("height")));
			
			fam.addModule(module);
			
			for (Element featureEle : moduleEle.getChildren("FAMnode")) {	
				//Parsing of Features
				Feature feature = new Feature(	"5", 
												featureEle.getChildText("name"), 
												new Point2D.Double(Integer.parseInt(featureEle.getChildText("origin").split(",")[0]),
						  									  	   Integer.parseInt(featureEle.getChildText("origin").split(",")[1])), 
												Integer.parseInt(featureEle.getChildText("width")), 
												Integer.parseInt(featureEle.getChildText("height")));
				
				module.addFeature(feature);				
			}
		}
		
		//Parsing of InfoFlows
		for (Element lineEle : root.getChildren("line")) {
			
			InfoFlow infoFlow = new InfoFlow(lineEle.getChildText("name"));		
		
			fam.addInfoFlow(infoFlow);		
			
			for(int i = 0 ; i < fam.getListModules().size(); i++) {	
				
				for(int j = 0; j < fam.getListModules().get(i).getFeatureList().size(); j++) {
					
					if(lineEle.getAttributeValue("source").equals(fam.getListModules().get(i).getFeatureList().get(j).getName())){
						
						for(int p = 0 ; p < fam.getListInfoFlow().size(); p++) {
							
							if (fam.getListInfoFlow().get(p).getName() == lineEle.getChildText("name")) {
								fam.getListInfoFlow().get(p).setSource(fam.getListModules().get(i).getFeatureList().get(j));
							}
						}
					}
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

}