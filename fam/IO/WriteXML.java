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
	Element m1 = createFAMnode("module", "m1", "Payment"			, "130,45" , "124", "183");
	Element m2 = createFAMnode("module", "m2", "Ticket Sales"		, "491,45" , "208", "365");
	Element m3 = createFAMnode("module", "m3", "Contract management", "104,407", "213", "311");
	Element m4 = createFAMnode("module", "m4", "Acquisition"		, "492,307", "311", "362");
	
	//creation of features
	Element f1 = createFAMnode("feature", "f1", "Send"		, "140,71" , "60", "120");
	Element f2 = createFAMnode("feature", "f2", "Receive"	, "520,144", "60", "120");
	Element f3 = createFAMnode("feature", "f3", "Process"	, "726,71" , "60", "120");
	Element f4 = createFAMnode("feature", "f4", "Calculate"	, "141,428", "60", "120");	
	Element f5 = createFAMnode("feature", "f5", "Split"		, "287,511", "60", "120");
	Element f6 = createFAMnode("feature", "f6", "Authorize"	, "520,365", "60", "120");
	Element f7 = createFAMnode("feature", "f7", "Delete"	, "520,512", "60", "120");
	Element f8 = createFAMnode("feature", "f8", "Save"		, "728,367", "60", "120");
	Element f9 = createFAMnode("feature", "f9", "Open"		, "728,510", "60", "120");
	
	//creation of lines
	Element line1 = createLine("infoFlow","ticketData", "Receive"  , "Process");
	Element line2 = createLine("infoFlow","contracts", "Split"    ,"Authorize");
	Element line3 = createLine("infoFlow","price", "Calculate","Send");
	
	//adding features to modules
	m1.addContent(f1);
	m2.addContent(f2);
	m2.addContent(f3);
	m3.addContent(f4);
	m3.addContent(f5);
	m4.addContent(f6);
	m4.addContent(f7);
	m4.addContent(f8);
	m4.addContent(f9);
	
	//adding modules to root
	theRoot.addContent(m1);
	theRoot.addContent(m2);
	theRoot.addContent(m3);
	theRoot.addContent(m4);
	
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