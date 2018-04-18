package org.architecturemining.fam.IO;

import java.io.File;
import java.io.FileOutputStream;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Text;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**functionality for building an FAM in .xml format using the JDOM2 library. Right now all parameters is hard coded to build an FAM, in the future
 * this can be build responsive. For the current project this was not yet needed, a hand build xml also would have sufficed. But I
 * enjoyed playing around with the jdom2 functionality. The exported xml file is stored in the /src/org.architecturemining.fam.IOfiles 
 * folder and is named traceExport.xml.
 * 
 * @author Nick
 */
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
	
	//creation of modules
	Element moduleA = createFAMnode("module", "m1", "Payment", "130,45", "124", "183");
	Element moduleB = createFAMnode("module", "m2", "Ticket Sales", "491,45", "208", "365");
	Element moduleC = createFAMnode("module", "m3", "Contract management", "104,407", "213", "311");
	Element moduleD = createFAMnode("module", "m4", "Acquisition", "492,307", "311", "362");
	
	//creation of features
	Element featureQ = createFAMnode("feature", "f1", "Test", "140,71", "60", "120");
	Element featureR = createFAMnode("feature", "f2", "R", "520,144", "60", "120");
	Element featureP = createFAMnode("feature", "f3", "P", "726,71", "60", "120");
	Element featureW = createFAMnode("feature", "f4", "W", "141,428", "60", "120");	
	Element featureX = createFAMnode("feature", "f5", "X", "287,511", "60", "120");
	Element featureT = createFAMnode("feature", "f6", "T", "520,365", "60", "120");
	Element featureU = createFAMnode("feature", "f7", "J", "520,512", "60", "120");
	Element featureS = createFAMnode("feature", "f8", "S", "728,367", "60", "120");
	Element featureV = createFAMnode("feature", "f9", "V", "728,510", "60", "120");
	
	//creation of lines
	Element line1 = createLine("infoFlow","infoFlow1", "R", "P");
	Element line2 = createLine("infoFlow","infoFlow2", "X","T");
	Element line3 = createLine("infoFlow","infoFlow3", "W","Test");
	
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
	
	//adding modules to root
	theRoot.addContent(moduleA);
	theRoot.addContent(moduleB);
	theRoot.addContent(moduleC);
	theRoot.addContent(moduleD);
	
	//adding infoFlows to root
	theRoot.addContent(line1);
	theRoot.addContent(line2);
	theRoot.addContent(line3);

	XMLOutputter xmlOutput = new XMLOutputter(Format.getPrettyFormat());
	
	xmlOutput.output(doc, new FileOutputStream(new File("./src/org.architecturemining.fam.IOfiles/jdomMade.xml")));
	
	System.out.println("XML created");
	}
	
	catch (Exception ex) {
		ex.printStackTrace();
	}
}

private static Element createFAMnode(String nodeType, String nodeId, String nodeName, String nodeOrigin, String nodeHeight, String nodeWidth) {
	
	Element FAMnode = new Element("FAMnode");
	FAMnode.setAttribute("type", nodeType);
		
		Element name = new Element("name");
		name.addContent(new Text(nodeName));

		Element id = new Element("id");
		id.addContent(new Text(nodeId));
		
		Element origin = new Element("origin");
		origin.addContent(new Text(nodeOrigin));
		
		Element height = new Element("height");
		height.addContent(new Text(nodeHeight));
		
		Element width = new Element("width");
		width.addContent(new Text(nodeWidth));
	
	FAMnode.addContent(name);
	FAMnode.addContent(id);
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