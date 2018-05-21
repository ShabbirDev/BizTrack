package com.zerocool.biztrack.SynchronizingData;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


/**
 * Created by Shabbir on 1-Aug-17.
 */


public class VolleySingleton {

    private static VolleySingleton mInstance;
    private RequestQueue mRequestQueue;
    private Context mCtx;

    private VolleySingleton(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();
        //mRequestQueue.getCache().clear();

    }

    public static synchronized VolleySingleton getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new VolleySingleton(context);
        }
        return mInstance;
    }


//    public static void init(Context context) {
//        if (context == null)
//            throw new NullPointerException("context must not be null.");
//
//        Cache diskCache = getDefaultDiskCache(context);
//        ImageCache memoryCache = getDefaultImageCache(context);
//        requestQueue = new RequestQueue(diskCache, new BasicNetwork(
//                new HurlStack()));
//
//        imageLoader = new ImageLoader(requestQueue, memoryCache);
//
//        requestQueue.start();
//    }


    public RequestQueue getRequestQueue() {

//        if (mRequestQueue == null)
//            throw new IllegalStateException("RequestQueue is not initialized.");
//        return mRequestQueue;
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 400,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        getRequestQueue().add(req);
    }

}