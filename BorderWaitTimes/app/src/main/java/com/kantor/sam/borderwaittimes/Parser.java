package com.kantor.sam.borderwaittimes;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by sam on 12/30/2015.
 */

public class Parser extends AsyncTask <Void, Void, Void>
{
    Activity mActivity;

    public Parser(Activity activity)
    {
        mActivity = activity;
    }

    public String Bridge1_time = "no delay";
    public String Bridge2_time = "no delay";
    public String Bridge3_time = "no delay";

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Bridge1_time = "Updating";
        Bridge2_time = "Updating";
        Bridge3_time = "Updating";
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
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("port");
            for (int i = 0; i < nodeList.getLength(); i++)
            {
                Node nNode = nodeList.item(i);
                NodeList nodeChildList = nNode.getChildNodes();
                String Bridge_Name = nodeChildList.item(3).getTextContent(); // Debugging
                String Crossing_name = nodeChildList.item(3).getNodeName(); // Debugging
                //String asdd = nodeChildList.item(3).getChildNodes().item(0).getNodeValue(); // used for debugging
                 if (Crossing_name.equals("crossing_name"))
                 {
                     if (Bridge_Name.equals("Lewiston Bridge"))
                     {
                         String tempDelay = nodeChildList.item(8).getChildNodes().item(1).getChildNodes().item(1).getTextContent();
                         String tempMinutes = nodeChildList.item(8).getChildNodes().item(1).getChildNodes().item(2).getChildNodes().item(0).getNodeValue();
                         Bridge1_time = setDelayStatus(tempDelay, tempMinutes);
                     }
                     else if (Bridge_Name.equals("Peace Bridge"))
                     {
                         String tempDelay = nodeChildList.item(8).getChildNodes().item(1).getChildNodes().item(1).getTextContent();
                         String tempMinutes = nodeChildList.item(8).getChildNodes().item(1).getChildNodes().item(2).getChildNodes().item(0).getNodeValue();
                         Bridge2_time = setDelayStatus(tempDelay, tempMinutes);
                     }
                     else if (Bridge_Name.equals("Rainbow Bridge"))
                     {
                         String tempDelay = nodeChildList.item(8).getChildNodes().item(1).getChildNodes().item(1).getTextContent();
                         String tempMinutes = nodeChildList.item(8).getChildNodes().item(1).getChildNodes().item(2).getChildNodes().item(0).getNodeValue();
                         Bridge3_time = setDelayStatus(tempDelay, tempMinutes);
                         //Log.i("Bridge Name: ", Bridge_Name); // debugging
                     }
                 }
                //Log.i("Delay Info: ",  nodeChildList.item(8).getChildNodes().item(1).getChildNodes().item(1).getTextContent());
                //Log.i("Crossing Name: ", Crossing_name);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    protected String setDelayStatus(String status, String minutes)
    {
        if (status.equals("no delay"))
        {
            return "no delay";
        }
        else if (status.equals("delay"))
        {
            return minutes;
        }
        else
        {
            return "Not operational";
        }
    }

    protected void setStatus ()
    {
        if (Bridge1_time.equals(""))
        {
            Bridge1_time = "Not operational";
        }
        else if (Bridge2_time.equals(""))
        {
            Bridge2_time = "Not operational";
        }
        else if (Bridge3_time.equals(""))
        {
            Bridge3_time = "Not operational";
        }
    }

    protected void onPostExecute(Void result)
    {
        super.onPostExecute(result);
        setStatus();

        TextView textView = (TextView)mActivity.findViewById(R.id.Delay_QL_Textview);
        TextView textView2 = (TextView)mActivity.findViewById(R.id.Delay_Peace_Textview);
        TextView textView3 = (TextView)mActivity.findViewById(R.id.Delay_Rainbow_Textview);

        textView.setText(Bridge1_time);
        textView2.setText(Bridge2_time);
        textView3.setText(Bridge3_time);

        Display dp = new Display(mActivity);
        dp.main(textView.getText().toString(), textView2.getText().toString(), textView3.getText().toString());
    }

}
