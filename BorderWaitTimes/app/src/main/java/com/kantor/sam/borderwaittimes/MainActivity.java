package com.kantor.sam.borderwaittimes;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "MyPrefsFile"; // Used for Storing Variables

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String Bridge1_Time = "no delay";
        String Bridge2_Time = "no delay";
        String Bridge3_Time = "no delay";

        // Restoring Variables (Last Bridge Time)
        SharedPreferences Bridge_Times = getSharedPreferences(PREFS_NAME, 0);
        Bridge1_Time = Bridge_Times.getString("Bridge_1", Bridge1_Time);
        Bridge2_Time = Bridge_Times.getString("Bridge_2", Bridge2_Time);
        Bridge3_Time = Bridge_Times.getString("Bridge_3", Bridge3_Time);
        setBridge1_Time(Bridge1_Time);
        setBridge2_Time(Bridge2_Time);
        setBridge3_Time(Bridge3_Time);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
        editor.putString("Bridge_1", getBridge1());
        editor.putString("Bridge_2", getBridge2());
        editor.putString("Bridge_3", getBridge3());
        editor.apply();
    }

    public String getBridge1()
    {
        TextView tmp = (TextView)findViewById(R.id.Bridge_1);
        return tmp.toString();
    }
    public String getBridge2()
    {
        TextView tmp = (TextView)findViewById(R.id.Bridge_2);
        return tmp.toString();
    }
    public String getBridge3()
    {
        TextView tmp = (TextView)findViewById(R.id.Bridge_3);
        return tmp.toString();
    }
    public void setBridge1_Time(String string)
    {
        TextView tmp = (TextView)findViewById(R.id.Bridge_1);
        tmp.setText(string);
    }
    public void setBridge2_Time(String string)
    {
        TextView tmp = (TextView)findViewById(R.id.Bridge_2);
        tmp.setText(string);
    }
    public void setBridge3_Time(String string)
    {
        TextView tmp = (TextView)findViewById(R.id.Bridge_3);
        tmp.setText(string);
    }
}
