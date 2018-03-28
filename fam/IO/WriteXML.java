package org.architecturemining.fam.IO;

import java.io.File;
import java.io.FileOutputStream;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Text;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class WriteXML {
	
public static void main() {
	
	writeXML();
	
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
	//creation of modules, type, name, origin, height, width
	Element moduleA = createFAMnode("module", "A", "130,45", "124", "183");
	Element moduleB = createFAMnode("module", "B", "491,45", "208", "365");
	Element moduleC = createFAMnode("module", "C", "104,407", "213", "311");
	Element moduleD = createFAMnode("module", "D", "492,307", "311", "362");
	
	//creation of features
	Element featureQ = createFAMnode("feature", "Q", "140,71", "60", "120");
	Element featureR = createFAMnode("feature", "R", "520,144", "60", "120");
	Element featureP = createFAMnode("feature", "P", "726,71", "60", "120");
	Element featureW = createFAMnode("feature", "W", "141,428", "60", "120");	
	Element featureX = createFAMnode("feature", "X", "287,511", "60", "120");
	Element featureT = createFAMnode("feature", "T", "520,365", "60", "120");
	Element featureU = createFAMnode("feature", "U", "520,512", "60", "120");
	Element featureS = createFAMnode("feature", "S", "728,367", "60", "120");
	Element featureV = createFAMnode("feature", "V", "728,510", "60", "120");
	
	//creation of lines
	Element line1 = createLine("infoFlow","infoFlow1", "R", "P");
	
	//adding features to modules
	moduleA.addContent(featureQ);
	moduleB.addContent(featureR);
	moduleB.addContent(featureP);
	moduleC.addContent(featureW);
	moduleC.addContent(featureX);
	moduleD.addContent(featureT);
	moduleD.addContent(featureU);
	moduleD.addContent(featureS);
	moduleD.addContent(featureV);
	
	//adding modules and lines to root
	theRoot.addContent(moduleA);
	theRoot.addContent(moduleB);
	theRoot.addContent(moduleC);
	theRoot.addContent(moduleD);
	
	theRoot.addContent(line1);

	XMLOutputter xmlOutput = new XMLOutputter(Format.getPrettyFormat());
	
	xmlOutput.output(doc, new FileOutputStream(new File("./src/org.architecturemining.fam.IOfiles/jdomMade.xml")));
	
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