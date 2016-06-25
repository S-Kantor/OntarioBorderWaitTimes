package com.kantor.sam.borderwaittimes;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

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

    public void main(String bridge1, String bridge2, String bridge3)
    {
        TextView textView1 = (TextView) mActivity.findViewById(R.id.Delay_QL_Textview);
        TextView textView2 = (TextView) mActivity.findViewById(R.id.Delay_Peace_Textview);
        TextView textView3 = (TextView) mActivity.findViewById(R.id.Delay_Rainbow_Textview);

        pickRecommendedImage();
        setBridge1Image(textView1.getText().toString());
        setBridge2Image(textView2.getText().toString());
        setBridge3Image(textView3.getText().toString());

    }

    /*public void setDelayQueenston(String Queenston_Time)
    {
        TextView textView = (TextView)mActivity.findViewById(R.id.Delay_QL_Textview);
        textView.setText(Queenston_Time);
    }

    public void setDelayRainbow(String Rainbow_Time)
    {
        TextView textView = (TextView)mActivity.findViewById(R.id.Delay_Rainbow_Textview);
        textView.setText(Rainbow_Time);
    }

    public void setDelayPeace(String Peace_Time)
    {
        TextView textView = (TextView)mActivity.findViewById(R.id.Delay_Peace_Textview);
        textView.setText(Peace_Time);
    }*/

    public void pickRecommendedImage()
    {
        TextView textView1 = (TextView) mActivity.findViewById(R.id.Delay_QL_Textview);
        TextView textView2 = (TextView) mActivity.findViewById(R.id.Delay_Peace_Textview);
        TextView textView3 = (TextView) mActivity.findViewById(R.id.Delay_Rainbow_Textview);
        TextView textView4 = (TextView) mActivity.findViewById(R.id.recommendedDelay); // Delay
        TextView textView5 = (TextView) mActivity.findViewById(R.id.recommended_bridge); // Title


        if (textView1.getText().toString().equals("no delay"))
        {
            textView5.setText("Queenston Lewiston");
            setRecommendedImage(textView1.getText().toString());
            return;
        }
        else if (textView2.getText().toString().equals("no delay"))
        {
            textView5.setText("Peace");
            setRecommendedImage(textView2.getText().toString());
            return;
        }
        else if (textView3.getText().toString().equals("no delay"))
        {
            textView5.setText("Rainbow");
            setRecommendedImage(textView3.getText().toString());
            return;
        }


        int b1_time = Integer.parseInt(textView1.getText().toString());
        int b2_time = Integer.parseInt(textView2.getText().toString());
        int b3_time = Integer.parseInt(textView3.getText().toString());

        if (b1_time <= b2_time)
        {
            if (b1_time <= b3_time)
            {
                textView5.setText("Queenston Lewiston");
                setRecommendedImage(textView1.getText().toString());
            }
            else if (b2_time <= b3_time)
            {
                textView5.setText("Peace");
                setRecommendedImage(textView2.getText().toString());
            }
            else
            {
                textView5.setText("Rainbow");
                setRecommendedImage(textView3.getText().toString());
            }
        }

    }

    public void setRecommendedImage(String delay)
    {
        ImageView imageView = (ImageView)mActivity.findViewById(R.id.recommendPicture);
        imageView.setImageResource(chooseImage(delay));
    }

    public void setBridge1Image(String delay)
    {
        TextView textView = (TextView)mActivity.findViewById((R.id.Title_Bridge_1));
        ImageView imageView = (ImageView)mActivity.findViewById(R.id.delayBridge1);

        textView.setText("Queenston Lewiston");
        imageView.setImageResource(chooseImage(delay));
    }
    public void setBridge2Image(String delay)
    {
        TextView textView = (TextView)mActivity.findViewById((R.id.Title_Bridge_2));
        ImageView imageView = (ImageView)mActivity.findViewById(R.id.delayBridge2);

        textView.setText("Peace");
        imageView.setImageResource(chooseImage(delay));
    }

    public void setBridge3Image(String delay)
    {
        TextView textView = (TextView)mActivity.findViewById((R.id.Title_Bridge_3));
        ImageView imageView = (ImageView)mActivity.findViewById(R.id.delayBridge3);

        textView.setText("Rainbow");
        imageView.setImageResource(chooseImage(delay));
    }


    public int chooseImage(String delay)
    {
        if (delay.equals("Not operational"))
        {
            return R.drawable.green;
        }

        else if (delay.equals("no delay"))
        {
            return R.drawable.green;
        }

        int delay_int = Integer.parseInt(delay);
        if (delay_int > 0 && delay_int < 10)
        {
            return R.drawable.green;
        }
        else if (delay_int >= 10 && delay_int < 30)
        {
            return R.drawable.green;
        }
        else
        {
            return R.drawable.red;
        }
    }


}
