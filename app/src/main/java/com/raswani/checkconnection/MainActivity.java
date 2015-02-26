package com.raswani.checkconnection;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import javax.xml.datatype.Duration;

import static android.view.View.OnClickListener;


public class MainActivity extends ActionBarActivity
{
    private static String flag = "not connected";
    // Whether there is a Wi-Fi connection.
    private static boolean wifiConnected = false;
    // Whether there is a mobile connection.
    private static boolean mobileConnected = false;
    TextView status;
    ImageView statusimg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        status = (TextView)findViewById(R.id.connectionstatus);
        statusimg = (ImageView) findViewById(R.id.connectionimg);
        Button test = (Button)findViewById(R.id.testbttn);
        test.setOnClickListener(new OnClickHandler());
    }

    public class OnClickHandler implements OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            isConnected();
                if(!flag.equals("not connected"))
                {
                        if (flag.equals("wifi")) {
                            status.setText("You are connected with : " + flag);
                            statusimg.setImageResource(R.drawable.connected);
                        } else {
                            status.setText("You are connected with : " + flag);
                            statusimg.setImageResource(R.drawable.connected);
                        }
                    flag = "not connected";
                    }else
                    {
                        status.setText("You are "+ flag);
                        statusimg.setImageResource(R.drawable.disconnected);
                    }
            } /* catch (Exception e) {
                Log.d("rahul", "Ëxception is : " + e.getMessage());
                Toast.makeText(getApplicationContext(),"You are Disconnected",Toast.LENGTH_LONG).show();
            }
        } */
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

    public void isConnected()
    {
            // BEGIN_INCLUDE(connect)
            ConnectivityManager connMgr =
                    (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeInfo = connMgr.getActiveNetworkInfo();
            if (activeInfo != null && activeInfo.isConnected()) {
                wifiConnected = activeInfo.getType() == ConnectivityManager.TYPE_WIFI;
                mobileConnected = activeInfo.getType() == ConnectivityManager.TYPE_MOBILE;
                if(wifiConnected)
                {
                    flag = "wifi";
                   // Log.i(TAG, getString(R.string.wifi_connection));
                } else if (mobileConnected)
                {
                    flag = "mobile data";
                    // Log.i(TAG, getString(R.string.mobile_connection));
                }
            }
           // else { Log.i(TAG, getString(R.string.no_wifi_or_mobile)); }

            // END_INCLUDE(connect)
    }
}
