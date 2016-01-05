package com.kantor.sam.borderwaittimes;

import android.app.Activity;
import android.widget.TextView;

/**
 * Created by sam on 1/4/2016.
 */
public class Display
{
    Activity mActivity;

    public Display (Activity activity)
    {
        mActivity = activity;
    }

    public void setDelayQueenston(String Queenston_Time)
    {
        TextView textView = (TextView)mActivity.findViewById(R.id.queenstonDelay);
        textView.setText(Queenston_Time);
    }

    public void setDelayRainbow(String Queenston_Time)
    {
        TextView textView = (TextView)mActivity.findViewById(R.id.rainBridgeDelay);
        textView.setText(Queenston_Time);
    }

    public void setDelayPeace(String Peace)
    {
        TextView textView = (TextView)mActivity.findViewById(R.id.peaceBridgeDelay);
        textView.setText(Peace);
    }
}
