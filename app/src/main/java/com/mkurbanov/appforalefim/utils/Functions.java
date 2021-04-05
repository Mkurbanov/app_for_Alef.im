package com.mkurbanov.appforalefim.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.mkurbanov.appforalefim.App;
import com.mkurbanov.appforalefim.R;
import com.mkurbanov.appforalefim.config;

public class Functions {

    public static void logWrite(String message) {
        Log.i(config.LOG_TAG, message);
    }

    public static void logWriteError(String error_message) {
        Log.e(config.LOG_TAG, error_message);
    }

    public static void toast(Context context, String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER | Gravity.BOTTOM, 0, 150);
        toast.show();
    }

    public static String getNetworkError(){
        if (!isNetworkConnected())
            return App.getInstance().getString(R.string.please_connect_to_the_network);
        else return App.getInstance().getString(R.string.error);
    }

    private static boolean isNetworkConnected() {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) App.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }

        return connected;
    }

}
