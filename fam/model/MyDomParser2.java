package org.architecturemining.fam.model;

//allows you to get xml elements and store them as DOM objects
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

//read xml file into memory, allows nodes to be stored as object, and to write back
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
//sax = simple api for xml, only allows us to read from xml not write back
import org.xml.sax.InputSource;

public class MyDomParser2{
	
	public static void main(String[] args) {
		
		// Creates a DOM object in memory. Now you can access data in the XML file
		Document xmlDoc = getDocument("FAM.xml");
		
		// Get the name of the root element
		System.out.println("Root: " + xmlDoc.getDocumentElement().getNodeName());
		
		// The FAMnodelist contains all of the nodes in the xml file
		// Dit klopt toch niet, zou toch moeten zoeken op FAMnode ipv node
		NodeList listOfFAMnodes = xmlDoc.getElementsByTagName("node");
		
		// Get the number of children in the root element
		System.out.println("Number of nodes: " + listOfFAMnodes.getLength());
		
		// The element you want to print out
		String elementName = "name";
		
		// The attribute to search for
		String attributeName = "country";
		
		getElementAndAttribute(listOfFAMnodes, elementName, attributeName);
		
		//remember
		//listOfNodes.item(0)
	}

	private static Document getDocument(String docString) {
		
		try {
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			
			factory.setIgnoringComments(true);
			factory.setIgnoringElementContentWhitespace(true);
			//factory.setValidating(true);
			
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			return builder.parse(new InputSource(docString));
			
		}
		
		catch(Exception ex) {
			
			System.out.println(ex.getMessage());
			
		}
		
		return null;
	}
	//																		   name				   country
	private static void getElementAndAttribute(NodeList listOfFAMnodes, String elementName, String attributeName) {
		
		try {
			
			// Cycle through the number of FAMnodes			
			for(int i=0; i < listOfFAMnodes.getLength(); i++) {
				
				// Get the first FAMnode
				Node nodeFAMnode = listOfFAMnodes.item(i);
				
				// Convert into an element to gain access to element methods
				Element FAMnodeElement = (Element) nodeFAMnode;
				
				// Create a list of nodes that have the name defined in elementName
				NodeList nameList = FAMnodeElement.getElementsByTagName(elementName);
				
				// Get the first and only element in this situation
				Element nameElement = (Element)nameList.item(0); 
				
				// Returns a list of node elements
				// Each Value is in a node in side of the name node
				// That's why you have to get the child nodes for name
				
				NodeList elementList = nameElement.getChildNodes();
				
				// Check if the element has the attribute set
				if(nameElement.hasAttribute(attributeName)) {
					
					System.out.println(elementName + " : " + elementList.item(0).getNodeValue().trim() + "has attribute " + nameElement.getAttribute(attributeName));
					
				} else {
					
					System.out.println("else " + elementList.item(0).getNodeValue().trim());
				
				}
			}
			
		}
		
		catch(Exception ex) {
		
			System.out.println(ex.getMessage());
			
		}
	}
}