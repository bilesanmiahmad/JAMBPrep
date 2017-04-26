package com.example.ahmad.jambprepguide;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by ahmad on 3/23/17.
 */

public class JambSingleton {
    private static JambSingleton mInstance;
    private RequestQueue requestQueue;
    private static Context mCtx;

    public JambSingleton(Context context) {
        mCtx = context;
        requestQueue = getRequestQueue();
    }

    public RequestQueue getRequestQueue()
    {
        if (requestQueue==null){
            requestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }

        return requestQueue;
    }

    public static synchronized JambSingleton getInstance(Context context){
        if (mInstance == null){
            mInstance = new JambSingleton(context);
        }

        return  mInstance;
    }

    public <T> void addToRequestqueue(Request<T> request){
        requestQueue.add(request);
    }
}
