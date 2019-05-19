package Momento;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Originator {
    Momento momento;
    int highscoreStoI;

    public Originator() {
        this.highscoreStoI=0;
        this.momento = new Momento();
    }
    
    
    
    public void save(String level,int high) throws ParserConfigurationException, SAXException, IOException, TransformerConfigurationException, TransformerException{
        String highscoreItoS = String.valueOf(high);
        String highscoree =" ";
        File xmlfile= new File("C:\\Users\\omar_\\OneDrive\\Documents\\NetBeansProjects\\fn-master\\fn-master\\FruitNinja\\game.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(xmlfile);
        doc.getDocumentElement().normalize();
        System.out.println("Root Element: " + doc.getDocumentElement().getNodeName());
        
        if(level.equals("easy"))
        {
            Node levell = doc.getElementsByTagName("Level").item(0);
            NodeList nodeList = levell.getChildNodes();
            Node node = nodeList.item(0);
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                
                node.setTextContent(highscoreItoS);
                System.out.println("Kosomk " + node.getTextContent());
            }
            
        }
        else if(level.equals("Medium")){
            Node levell = doc.getElementsByTagName("Level").item(1);
            NodeList nodeList = levell.getChildNodes();
            Node node = nodeList.item(0);
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                node.setTextContent(highscoreItoS);            
            }
        }
        else if(level.equals("Hard")){
            Node levell = doc.getElementsByTagName("Level").item(2);
            NodeList nodeList = levell.getChildNodes();
            Node node = nodeList.item(0);
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                
                node.setTextContent(highscoreItoS);            
            }
        }
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(xmlfile);
		transformer.transform(source, result);
        
            
            
        }
    public int restore(String level) throws ParserConfigurationException, SAXException, IOException{
        String highscoree =" ";
        File xmlfile= new File("C:\\Users\\omar_\\OneDrive\\Documents\\NetBeansProjects\\fn-master\\fn-master\\FruitNinja\\game.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(xmlfile);
        doc.getDocumentElement().normalize(); 
        System.out.println("Root Element: " + doc.getDocumentElement().getNodeName());
        NodeList nodeList = doc.getElementsByTagName("Level");
        System.out.println("Number " + nodeList.getLength());
        if(level.equals("easy"))
        {
            Node node = nodeList.item(0);
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                Element element = (Element) node;
                highscoree = element.getElementsByTagName("HighScore").item(0).getTextContent();
            }
        }
        else if(level.equals("Medium")){
            Node node = nodeList.item(1);
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                Element element = (Element) node;
                highscoree = element.getElementsByTagName("HighScore").item(0).getTextContent();
            }
        }
        else if(level.equals("Hard")){
            Node node = nodeList.item(2);
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                Element element = (Element) node;
                highscoree = element.getElementsByTagName("HighScore").item(0).getTextContent();
            }
        }
        
        highscoreStoI = Integer.parseInt(highscoree);
        return highscoreStoI;
    }
    
    
    }


