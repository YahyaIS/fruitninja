/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Momento;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author omar_
 */
public class CareTaker {
    Momento momento;
    Originator originator;
    int high ;

    public CareTaker() {
        this.high = 0;
    }
    
    public void addMomento(int highscore) throws ParserConfigurationException, SAXException, IOException{
        File xmlfile= new File("C:\\Users\\omar_\\OneDrive\\Documents\\NetBeansProjects\\omar-1\\omar1\\FruitNinja\\game.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(xmlfile);
        doc.getDocumentElement().normalize();
        System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
        String highscoree = doc.getElementsByTagName("HighScore").item(0).getTextContent();
        System.out.println("high= " + highscoree);
        int highscoreStoI = Integer.parseInt(highscoree);
        if(highscore>highscoreStoI){
            originator.save();
        }
    }
}
