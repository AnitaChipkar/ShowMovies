package com.anitatheone.moviecta.utils;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.anitatheone.moviecta.R;
import java.net.UnknownHostException;



public class VolleyErrorHelper {
    /**
     * Returns appropriate message which is to be displayed to the user
     * against the specified error object.
     *
     * @param error
     * @param context
     * @return
     */
    public static String getMessage(Object error, Context context) {
        if (error instanceof TimeoutError) {
            return context.getResources().getString(R.string.generic_server_down);
        } else if (isServerProblem(error)) {
            return handleServerError(error, context);

        } else if (isNetworkProblem(error)) {
            return context.getResources().getString(R.string.no_internet);
        }
        return context.getResources().getString(R.string.generic_error);

    }

    public static String getresponse(VolleyError error, Context context) {
        if (error instanceof TimeoutError) {
            return context.getResources().getString(R.string.generic_server_down);
        } else if (isServerProblem(error)) {
            return handleServerError(error, context);

        } else if (isNetworkProblem(error)) {
            return context.getResources().getString(R.string.no_internet);
        }
        else if (error instanceof ParseError) {
            return "No appliance found.";
        }
        return context.getResources().getString(R.string.generic_error);

    }

    private static String handleServerError(Object error, Context context) {

        VolleyError er = (VolleyError) error;
        NetworkResponse response = er.networkResponse;
        String message = "";
        if (response != null) {
            switch (response.statusCode) {

                case 404:
                case 422:
                case 401:
                     message = ((VolleyError) error).getMessage();
                break;

                default:
                    message = context.getResources().getString(R.string.generic_error);
            }
        }

        return message;
    }

    private static boolean isServerProblem(Object error) {
        return (error instanceof ServerError || error instanceof AuthFailureError);
    }

    private static boolean isNetworkProblem(Object error) {
        return (error instanceof NetworkError || error instanceof NoConnectionError  || error instanceof UnknownHostException );
    }
}