package org.architecturemining.fam.model;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MyDomParser {
	
	public static String result;
	
	public static void main(String[] args) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse("src\\org\\architecturemining\\fam\\model\\FAM.xml");
			NodeList nodeList = doc.getElementsByTagName("node");
			NodeList infoFlowList = doc.getElementsByTagName("informationFlow");
			
			//looping through nodeList
			for(int i=0; i < nodeList.getLength() ; i++) {
				Node p = nodeList.item(i);
				if(p.getNodeType() == Node.ELEMENT_NODE) {
					Element node = (Element) p;
					//short type = node.getNodeType();
					NodeList nameList = node.getChildNodes();
					System.out.println("------------------");

					System.out.println(node.getAttribute("type"));

					
					//looping through nameList
					for(int j=0;j<nameList.getLength();j++) {
						Node n = nameList.item(j);
						if(n.getNodeType() == node.ELEMENT_NODE) {
							Element name = (Element) n;
							//if(name.getTagName() == "name") {
								//System.out.println(name.getTagName());
							//}
							System.out.println(name.getTagName() + ":" + name.getTextContent());
							//Feature f8 = new Feature(new Point2D.Double(1,1), Integer.parseInt(name.getTextContent()), 3);
							//System.out.println(name.getTextContent());
							
							//type, name.getTagName(), name.getTextContent()
						}
					}
				}
			}
			
			//looping through infoFlowList
			for(int i=0; i < infoFlowList.getLength() ; i++) {
				Node p = infoFlowList.item(i);
				if(p.getNodeType() == Node.ELEMENT_NODE) {
					Element infoFlow = (Element) p;
					//short type = infoFlow.getNodeType();
					NodeList nameList = infoFlow.getChildNodes();
					System.out.println("------------------");
					System.out.println(infoFlow.getAttribute("type"));
					
					//looping through nameList
					for(int j=0;j<nameList.getLength();j++) {
						Node n = nameList.item(j);
						if(n.getNodeType() == infoFlow.ELEMENT_NODE) {
							Element nameInfoFlow = (Element) n;
							//if(name.getTagName() == "name") {
								//System.out.println(name.getTagName());
							//}
							System.out.println(nameInfoFlow.getTagName() + ": " + nameInfoFlow.getTextContent());
							System.out.println("" + infoFlow.getAttributes().getNamedItem("source") + infoFlow.getAttributes().getNamedItem("target"));
							//type, name.getTagName(), name.getTextContent()
						}
					}
				}
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
