package com.anitatheone.moviecta.application;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;




public class MovieApplication extends Application {
    private RequestQueue requestQueue;
    private static MovieApplication movieApplication;
    public static final String TAG = MovieApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        movieApplication = this;
    }

    public static synchronized MovieApplication getInstance() {
        return movieApplication;
    }

    /**
     * Singleton request queue.
     *
     * @return
     */
    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return requestQueue;
    }



    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (requestQueue != null) {
            requestQueue.cancelAll(tag);
        }

    }



}
