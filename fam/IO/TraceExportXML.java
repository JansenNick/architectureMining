package org.architecturemining.fam.IO;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import org.architecturemining.fam.model.Trace;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**Handles the exporting of the created traces to a new .xml document.
 * 
 * @author Nick
 */
public class TraceExportXML {
	
	public static void main() {
		
		}
	
	/** Writes an XML document based on the given traceList.
	 * 
	 * @param traceList		array with all the created traces by the user
	 */
	@SuppressWarnings("unused")
	public static void writeXML(ArrayList<Trace> traceList) {
		try {
		Document doc = new Document();
		//create root
		Element theRoot = new Element("TraceList");
		doc.setRootElement(theRoot);
		
		for(int i = 0; traceList.size() > i ; i++) {
			Element trace = new Element("trace");
			trace.setAttribute("id", traceList.get(i).getIdTrace());
			
			Element name = new Element("name");
			name.addContent(traceList.get(i).getNameTrace());
			
			Element description = new Element("description");
			description.addContent(traceList.get(i).getDescriptionTrace());
			
			trace.addContent(name);
			trace.addContent(description);
			
			for(int j = 0; traceList.get(i).getNameFeatureList().size() > j ; j++) {
				
				Element feature = new Element("feature");
				feature.setAttribute("order", ""+j);
				feature.setAttribute("id",traceList.get(i).getNameFeatureList().get(j).getId());
				feature.setAttribute("name",traceList.get(i).getNameFeatureList().get(j).getName());
				trace.addContent(feature);
			}
			
			theRoot.addContent(trace);
		}
	
		XMLOutputter xmlOutput = new XMLOutputter(Format.getPrettyFormat());
		
		xmlOutput.output(doc, new FileOutputStream(new File("./src/org.architecturemining.fam.IOfiles/traceExport.xml")));
		
		System.out.println("Traces exported");
		}
		
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}