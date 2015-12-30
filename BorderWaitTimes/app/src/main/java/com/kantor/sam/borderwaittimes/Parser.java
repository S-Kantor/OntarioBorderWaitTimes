package com.kantor.sam.borderwaittimes;

import android.os.AsyncTask;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.net.URL;

import org.jsoup.Jsoup;


/**
 * Created by sam on 12/30/2015.
 */

public class Parser extends AsyncTask <Void, Void, Void>
{
    public String Bridge1_time = "no delay";
    public String Bridge2_time = "no delay";
    public String Bridge3_time = "no delay";

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Bridge1_time = "Updating";
    }

    @Override
    protected Void doInBackground(Void... params)
    {
        try
        {
            URL url = new URL("https://apps.cbp.gov/bwt/mobile.asp?action=n&pn=0901");
            Document doc = (Document) Jsoup.connect("https://apps.cbp.gov/bwt/mobile.asp?action=n&pn=0901").get();

            Element container = doc.getElementById("container");
            Element pass_details = doc.getElementById("pass_details");
            NodeList nodeList = doc.getElementsByTagNameNS("pass_details", "b");


            Node el1 = nodeList.item(1);
            Bridge1_time = el1.getTextContent();
        /*
        Element el1 = (Element)nodeList.item(4);
        Element el2 = (Element)nodeList.item(5);
        Element el3 = (Element)nodeList.item(6);

        Bridge1_time = el1.getTextContent();
        Bridge2_time = el2.getTextContent();
        Bridge3_time = el3.getTextContent();
        */
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
    }

}
