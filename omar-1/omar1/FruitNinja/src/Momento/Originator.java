package Momento;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Originator {
    Momento momento;
    int highscoreStoI;

    public Originator() {
        this.highscoreStoI=0;
    }
    
    
    
    public void save() throws ParserConfigurationException, SAXException, IOException{
        File xmlfile= new File("C:\\Users\\omar_\\OneDrive\\Documents\\NetBeansProjects\\omar-1\\omar1\\FruitNinja\\game.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(xmlfile);
        doc.getDocumentElement().normalize();
        System.out.println("Root Element: " + doc.getDocumentElement().getNodeName());
        NodeList nodeList = doc.getElementsByTagName("Player");
        Node node = nodeList.item(0);
        Element element = (Element) node;
        
        String highscoreItoS = String.valueOf(momento.getHighscore());
             element = (Element) doc.getElementsByTagName("HighScore");
            element.setTextContent(highscoreItoS);
            System.out.println("HighScore " + element.getElementsByTagName("HighScore").item(0).getTextContent());
            
        }
    public int restore() throws ParserConfigurationException, SAXException, IOException{
        String highscoree =" ";
        File xmlfile= new File("C:\\Users\\omar_\\OneDrive\\Documents\\NetBeansProjects\\omar-1\\omar1\\FruitNinja\\game.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(xmlfile);
        doc.getDocumentElement().normalize(); 
        System.out.println("Root Element: " + doc.getDocumentElement().getNodeName());
        NodeList nodeList = doc.getElementsByTagName("Player");
        for(int i=0 ; i< nodeList.getLength();i++){
            Node node = nodeList.item(i);
            System.out.println("CurrentElement: " + node.getNodeName());
            if(node.getNodeType()==Node.ELEMENT_NODE){
                Element element = (Element) node;
                System.out.println("PlayerID=  " + element.getAttribute("Id"));
                System.out.println("HighScore= " + element.getElementsByTagName("HighScore").item(0).getTextContent());
                highscoree = element.getElementsByTagName("HighScore").item(0).getTextContent();
            }
        }
        highscoreStoI = Integer.parseInt(highscoree);
        return highscoreStoI;
    }
    
    
    }


