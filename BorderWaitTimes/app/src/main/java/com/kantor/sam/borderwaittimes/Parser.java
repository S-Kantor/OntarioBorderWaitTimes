package com.kantor.sam.borderwaittimes;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by sam on 12/30/2015.
 */

public class Parser{
    public String Bridge1_time = "no delay";
    public String Bridge2_time = "no delay";
    public String Bridge3_time = "no delay";


    public void getXMLelementsfromURL() throws Exception
    {
        URL url = new URL ("https://apps.cbp.gov/bwt/mobile.asp?action=n&pn=0901");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new InputSource(url.openStream()));
        doc.getDocumentElement().normalize();
        NodeList nodeList = doc.getElementsByTagName("basic_text text_margin");

        Element el1 = (Element)nodeList.item(4);
        Element el2 = (Element)nodeList.item(5);
        Element el3 = (Element)nodeList.item(6);

        Bridge1_time = el1.getTextContent();
        Bridge2_time = el2.getTextContent();
        Bridge3_time = el3.getTextContent();
    }

}
