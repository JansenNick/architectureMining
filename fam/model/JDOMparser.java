package org.architecturemining.fam.model;

import java.io.File;
import java.io.FileOutputStream;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Text;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class JDOMparser {
	
	public static void main(String[] args) {
		
	
	writeXML();
	
	//readXML();
	}


private static void writeXML() {
	try {
	Document doc = new Document();
	
	Element theRoot = new Element("tvshows");
	doc.setRootElement(theRoot);
	
	Element show = new Element("show");
	
	Element name = new Element("name");
	name.setAttribute("show_id", "show_001");
	name.addContent(new Text("Life on Mars"));
	
	Element network = new Element("network");
	network.setAttribute("country", "US");
	network.addContent(new Text("ABC"));
	
	show.addContent(name);
	show.addContent(network);
	
	theRoot.addContent(show);
	
	//  -----------
	
	
	XMLOutputter xmlOutput = new XMLOutputter(Format.getPrettyFormat());
	
	xmlOutput.output(doc, new FileOutputStream(new File("./src/jdomMade.xml")));
	
	System.out.println("XML created");
	}
	
	catch (Exception ex) {
		ex.printStackTrace();
	}
}
}