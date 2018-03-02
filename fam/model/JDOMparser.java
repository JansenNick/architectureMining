package org.architecturemining.fam.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Text;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class JDOMparser {
	
public static void main(String[] args) {
	
	writeXML();
	
	readXML();
	
	}

private static void readXML() {
	
	SAXBuilder builder = new SAXBuilder();
		
		Document readDoc = null;
		
		try {
			readDoc = builder.build(new File("./src/jdomMade.xml"));
		} catch (JDOMException | IOException e) {

			e.printStackTrace();
		}
		
		Element root = readDoc.getRootElement();
		
		System.out.println("---------------------");
		
		for (Element moduleEle : root.getChildren("FAMnode")) {
			
			//Module m1 = new Module(new Point2D.Double(0,0), Integer.parseInt(moduleEle.getChildText("heigth")), Integer.parseInt(moduleEle.getChildText("width")));
			
			
			System.out.println(moduleEle.getAttributeValue("type") + " " + moduleEle.getChildText("name"));
			System.out.println("Origin: " + moduleEle.getChildText("origin"));
			System.out.println("Height: " + moduleEle.getChildText("height"));
			System.out.println("Width: " + moduleEle.getChildText("width"));
			System.out.println("Has features:");
			
			for (Element featureEle : moduleEle.getChildren("FAMnode")) {
				
				System.out.println("\t" + featureEle.getAttributeValue("type") + " " + featureEle.getChildText("name"));
				System.out.println("\t" + "Origin: " + featureEle.getChildText("origin"));
				System.out.println("\t" + "Height: " + featureEle.getChildText("height"));
				System.out.println("\t" + "Width: " + featureEle.getChildText("width"));				
			}
			
			System.out.println("---------------------");
		}
		
		for (Element lineEle : root.getChildren("line")) {
			
			System.out.println(lineEle.getAttributeValue("type") + " " + lineEle.getChildText("name"));	
			System.out.println("Source: " + lineEle.getAttributeValue("source"));
			System.out.println("Target: " + lineEle.getAttributeValue("target"));
			System.out.println("---------------------");
		}
	
}

private static void writeXML() {
	try {
	Document doc = new Document();
	//create root
	Element theRoot = new Element("FAM");
	doc.setRootElement(theRoot);
	
	//Mogelijk modules/features/lines niet eerst in Elements op te slaan door parent mee te geven aan de functie
	//Gaat alleen mis bij het creeren van een feature omdat je dan de naam van een child van theRoot moet hebben
	//Echter geef ik dergelijke childs nergens een naam
	//creation of modules
	Element moduleA = createFAMnode("module", "A", "130,45", "60", "120");
	Element moduleB = createFAMnode("module", "B", "491,45", "208", "365");
	
	//creation of features
	Element featureQ = createFAMnode("feature", "Q", "140,71", "60", "120");
	Element featureR = createFAMnode("feature", "R", "520,144", "60", "120");
	Element featureP = createFAMnode("feature", "P", "726,71", "60", "120");
	
	//creation of lines
	Element line1 = createLine("infoFlow","infoFlow1", "V", "S");
	
	//adding features to modules
	moduleA.addContent(featureQ);
	moduleB.addContent(featureR);
	moduleB.addContent(featureP);
	
	//adding modules and lines to root
	theRoot.addContent(moduleA);
	theRoot.addContent(moduleB);
	theRoot.addContent(line1);
	
	XMLOutputter xmlOutput = new XMLOutputter(Format.getPrettyFormat());
	
	xmlOutput.output(doc, new FileOutputStream(new File("./src/jdomMade.xml")));
	
	System.out.println("XML created");
	}
	
	catch (Exception ex) {
		ex.printStackTrace();
	}
}

private static Element createFAMnode(String nodeType, String nodeName, String nodeOrigin, String nodeHeight, String nodeWidth) {
	
	Element FAMnode = new Element("FAMnode");
	FAMnode.setAttribute("type", nodeType);
		
		//create first node in FAMnode
		Element name = new Element("name");
		//add content to first node
		name.addContent(new Text(nodeName));
	
		//create second node in show node
		Element origin = new Element("origin");
		//add content to second node
		origin.addContent(new Text(nodeOrigin));
		
		//create second node in show node
		Element height = new Element("height");
		//add content to second node
		height.addContent(new Text(nodeHeight));
		
		//create second node in show node
		Element width = new Element("width");
		//add content to second node
		width.addContent(new Text(nodeWidth));
	
	FAMnode.addContent(name);
	FAMnode.addContent(origin);
	FAMnode.addContent(height);
	FAMnode.addContent(width);
	
	return FAMnode;
}

private static Element createLine(String lineType, String lineName, String source, String target) {
	Element line = new Element("line");
	
	line.setAttribute("type", lineType);
	line.setAttribute("source", source);
	line.setAttribute("target", target);
	
		Element name = new Element("name");
		name.addContent(new Text(lineName));
	
	line.addContent(name);
	
	return line;
}

}