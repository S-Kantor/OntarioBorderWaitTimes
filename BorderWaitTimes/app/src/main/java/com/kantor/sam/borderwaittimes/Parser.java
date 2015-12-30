package com.kantor.sam.borderwaittimes;

import android.os.AsyncTask;
import android.widget.Toast;

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
        Bridge2_time = "Updating";
        Bridge3_time = "Updating";
    }

    @Override
    protected Void doInBackground(Void ... params)
    {
        try
        {
            org.jsoup.nodes.Document doc = Jsoup.connect("https://apps.cbp.gov/bwt/mobile.asp?action=n&pn=0901").get();
            org.jsoup.nodes.Element pass_details_1 = doc.select("div.pass_details").select("b").get(1);
            org.jsoup.nodes.Element pass_details_2 = doc.select("div.pass_details").select("b").get(3);
            org.jsoup.nodes.Element pass_details_3 = doc.select("div.pass_details").select("b").get(5);
            Bridge1_time = pass_details_1.text();
            Bridge2_time = pass_details_2.text();
            Bridge3_time = pass_details_3.text();
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
    }

}
