package com.app.itauditor.retrofit;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * Created by Manish Ahire on 02,February,2020
 */


public class Receiver extends BroadcastReceiver {

    public static final String NETWORK_AVAILABLE_ACTION = "com.itsknow.NetworkAvailable";
    public static final String IS_NETWORK_AVAILABLE = "isNetworkAvailable";

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            Intent networkStateIntent = new Intent(NETWORK_AVAILABLE_ACTION);
            networkStateIntent.putExtra(IS_NETWORK_AVAILABLE, isConnectedToInternet(context));
            //LocalBroadcastManager.getInstance(context).sendBroadcast(networkStateIntent);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /*
   Check Internet Connection...
    */
    public boolean isConnectedToInternet(Context context) {
        try {
            if (context != null) {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                return networkInfo != null && networkInfo.isConnected();
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(Receiver.class.getName(), e.getMessage());
            return false;
        }
    }
}

