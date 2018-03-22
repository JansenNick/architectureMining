package org.architecturemining.fam.IO;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import org.architecturemining.fam.graphics.Trace;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class TraceExportXML {
	
	public static void main() {
		
		}
	
	@SuppressWarnings("unused")
	public static void writeXML(ArrayList<Trace> traceList) {
		try {
		Document doc = new Document();
		//create root
		Element theRoot = new Element("TraceList");
		doc.setRootElement(theRoot);
		
		for(int i = 0; traceList.size() > i ; i++) {
			Element trace = new Element(traceList.get(i).getNameTrace());
			
			String x = " ";
			
			for(int j = 0; traceList.get(i).getNameFeatureList().size() > j ; j++) {
				
				if(j==0) {
				x = x + traceList.get(i).getNameFeatureList().get(j).getName();	
				}
				else {
				x = x + "-" + traceList.get(i).getNameFeatureList().get(j).getName();
				}
			}
			trace.addContent(x);
			theRoot.addContent(trace);
		}
	
		XMLOutputter xmlOutput = new XMLOutputter(Format.getPrettyFormat());
		
		xmlOutput.output(doc, new FileOutputStream(new File("./src/traceExport.xml")));
		
		System.out.println("Traces exported");
		}
		
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}