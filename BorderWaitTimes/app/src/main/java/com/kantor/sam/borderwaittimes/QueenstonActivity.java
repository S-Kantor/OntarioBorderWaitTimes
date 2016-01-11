package com.kantor.sam.borderwaittimes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class QueenstonActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qlbridge);

        setDelayTime();

        //Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        //setSupportActionBar(myToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        getMenuInflater().inflate(R.menu.actionbutton, menu);
        return true;
    }

    public void setDelayTime()
    {
        Intent intent = getIntent();
        TextView textView = (TextView)findViewById(R.id.queenstonDelay);
        textView.setText(intent.getExtras().getString("Bridge_Time"));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Snackbar snackbar = Snackbar
                .make(this.findViewById(android.R.id.content),  "You clicked a button", Snackbar.LENGTH_LONG);

        snackbar.show();
        switch (item.getItemId()) {
            case R.id.queenstonBridgepop:
                snackbar.show();
                return true;

            case R.id.rainbowBridgpop:
                snackbar.show();
                return true;

            case R.id.peaceBridgepop:
                snackbar.show();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
    @Override
    protected  void onStop()
    {
        super.onStop();
    }
}
