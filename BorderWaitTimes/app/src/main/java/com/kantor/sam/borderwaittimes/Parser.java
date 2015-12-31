package com.kantor.sam.borderwaittimes;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.net.URL;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


/**
 * Created by sam on 12/30/2015.
 */

public class Parser extends AsyncTask <Void, Void, Void>
{

    Activity mActivity;

    public Parser(Activity activity) {
        mActivity = activity;
    }

    public String Bridge1_time = "no delay";
    public String Bridge2_time = "no delay";
    public String Bridge3_time = "no delay";

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Bridge1_time = "Not operational";
        Bridge2_time = "Not operational";
        Bridge3_time = "Not operational";
    }

    @Override
    protected Void doInBackground(Void ... params)
    {
        try
        {
            URL url = new URL("http://apps.cbp.gov/bwt/bwt.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new InputSource(url.openStream()));
            //doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("port");
            for (int i = 0; i < nodeList.getLength(); i++)
            {
                Node nNode = nodeList.item(i);
                NodeList nodeChildList = nNode.getChildNodes();
                String Bridge_Name = nodeChildList.item(3).getTextContent();
                String Crossing_name = nodeChildList.item(3).getNodeName();
                String asdd = nodeChildList.item(3).getChildNodes().item(0).getNodeValue(); // used for debugging
                 if (Crossing_name.equals("crossing_name"))
                 {
                     if (Bridge_Name.equals("Lewiston Bridge"))
                     {
                         Bridge1_time = nodeChildList.item(7).getChildNodes().item(1).getChildNodes().item(1).getTextContent();
                     }
                     else if (Bridge_Name.equals("Peace Bridge"))
                     {
                         Bridge2_time = nodeChildList.item(7).getChildNodes().item(1).getChildNodes().item(1).getTextContent();
                     }
                     else if (Bridge_Name.equals("Rainbow Bridge"))
                     {
                         Bridge3_time = nodeChildList.item(7).getChildNodes().item(1).getChildNodes().item(1).getTextContent();
                     }
                     Log.i("Bridge Name: ", Bridge_Name); // debugging
                 }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(Void result)
    {
        super.onPostExecute(result);
        Bridge1_time = "Delay is: " + Bridge1_time;
        Bridge2_time = "Delay is: " + Bridge2_time;
        Bridge3_time = "Delay is: " + Bridge3_time;
        TextView textView = (TextView)mActivity.findViewById(R.id.textView1);
        TextView textView2 = (TextView)mActivity.findViewById(R.id.textView2);
        TextView textView3 = (TextView)mActivity.findViewById(R.id.textView3);
        textView.setText(Bridge1_time);
        textView2.setText(Bridge2_time);
        textView3.setText(Bridge3_time);
    }

}
