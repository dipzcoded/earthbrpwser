package com.example.earthbrowserapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectionManager {

    static boolean checkConnection(Context context)
    {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if(connectivityManager != null)
        {

            NetworkInfo[] info = connectivityManager.getAllNetworkInfo();

            if(info != null)
            {

                for(int i =0; i <info.length;i++)
                {

                    if(info[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return  true;

                    }

                }

            }


        }


        return false;

    }

}
