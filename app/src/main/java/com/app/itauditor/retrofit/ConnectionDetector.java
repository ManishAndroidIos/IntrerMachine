package com.app.itauditor.retrofit;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * Created by Manish Ahire on 02,February,2020
 */


public class ConnectionDetector {

    private Activity _context;

    public ConnectionDetector(Activity _context) {
        this._context = _context;
    }

    public boolean isConnectedToInternet() {
        try {
            if (_context != null) {
                ConnectivityManager connectivityManager = (ConnectivityManager) _context.getSystemService(CONNECTIVITY_SERVICE);
                assert connectivityManager != null;
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

    public boolean isConnectedToInternet(Context _context) {
        try {
            if (_context != null) {
                ConnectivityManager connectivityManager = (ConnectivityManager) _context.getSystemService(CONNECTIVITY_SERVICE);
                assert connectivityManager != null;
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
