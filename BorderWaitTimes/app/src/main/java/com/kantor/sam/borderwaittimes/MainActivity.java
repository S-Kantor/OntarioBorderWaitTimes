package com.kantor.sam.borderwaittimes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "MyPrefsFile"; // Used for Storing Variables
    Display dp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);

        String Bridge1_Time = "no delay";
        String Bridge2_Time = "no delay";
        String Bridge3_Time = "no delay";

        // Restoring Variables (Last Bridge Time)
        SharedPreferences Bridge_Times = getSharedPreferences(PREFS_NAME, 0);
        Bridge1_Time = Bridge_Times.getString("Delay_QL_Textview", Bridge1_Time);
        Bridge2_Time = Bridge_Times.getString("Delay_Peace_Textview", Bridge2_Time);
        Bridge3_Time = Bridge_Times.getString("Delay_Rainbow_Textview", Bridge3_Time);
        setBridge1_Time(Bridge1_Time);
        setBridge2_Time(Bridge2_Time);
        setBridge3_Time(Bridge3_Time);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.about:
                Snackbar snackbar = Snackbar
                        .make(this.findViewById(android.R.id.content),  "Developed by: Sam Kantor", Snackbar.LENGTH_LONG);
                snackbar.show();
                return true;

            case R.id.website:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("www.samkantor.me"));
                startActivity(browserIntent);

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    public void update (View v) throws Exception
    {
        Parser ps = new Parser(this);
        ps.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        //Toast toast = Toast.makeText(getApplicationContext(), ps.Bridge1_time, Toast.LENGTH_LONG); // testing
    }

    @Override
    protected  void onStop()
    {
        super.onStop();

        // Commit Save Changes
        SharedPreferences prefs = getSharedPreferences("preference_file_name", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("Delay_QL_Textview", getBridge1());
        editor.putString("Delay_Peace_Textview", getBridge2());
        editor.putString("Delay_Rainbow_Textview", getBridge3());
        editor.apply();
    }

    public String getBridge1()
    {
        TextView tmp = (TextView)findViewById(R.id.Delay_Peace_Textview);
        return tmp.toString();
    }
    public String getBridge2()
    {
        TextView tmp = (TextView)findViewById(R.id.Delay_Rainbow_Textview);
        return tmp.toString();
    }
    public String getBridge3()
    {
        TextView tmp = (TextView)findViewById(R.id.Delay_QL_Textview);
        return tmp.toString();
    }
    public void setBridge1_Time(String string)
    {
        TextView tmp = (TextView)findViewById(R.id.Delay_Rainbow_Textview);
        tmp.setText(string);
    }
    public void setBridge2_Time(String string)
    {
        TextView tmp = (TextView)findViewById(R.id.Delay_Peace_Textview);
        tmp.setText(string);
    }
    public void setBridge3_Time(String string)
    {
        TextView tmp = (TextView)findViewById(R.id.Delay_Rainbow_Textview);
        tmp.setText(string);
    }
}
