package com.ghorabaa.cultureguide.Utilities;

import android.app.DownloadManager;
import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ahmed Ibrahim on 4/27/18.
 */

/**
 * Helper class to handle connection to online database
 */
public class DBConnection {

    private static final String URL = "http://medo4cross.000webhostapp.com/execute.php";
    private static final String DB_PASSWORD = "password"; //for authentication
    private static Context context = null;
    private static DBConnection myInstance = null;

    private static RequestQueue mRequestQueue;
    private DBConnection(Context context){
        this.context = context;
        mRequestQueue = Volley.newRequestQueue(context);
    }

    public static synchronized DBConnection getInstance(Context context){
        if(myInstance == null)
            myInstance = new DBConnection(context);


        return myInstance;
    }

    /**
     * Sends query to online database, and call backs after executing
     * @param query query to be send
     * @param onSuccess Async class to retrieve result
     * @param onFail  Async class in case connection isn't established
     */
    public void executeQuery(final String query, Response.Listener<String> onSuccess, Response.ErrorListener onFail){
        //send request to server
        StringRequest request =  new StringRequest(Request.Method.POST,URL,onSuccess,onFail){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("DB_PASSWORD",DB_PASSWORD);
                params.put("query",query);
                return params;
            }
        };

        //retry connection in case of timeout
        request.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(request);
    }

    /**
     * Sends query to database without feedback
     * @param query query to be executed
     */
    public void executeQuery(final String query){

        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        };

        Response.ErrorListener onFail = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        };

        //send request to server
        StringRequest request =  new StringRequest(Request.Method.POST,URL,onSuccess,onFail ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("DB_PASSWORD",DB_PASSWORD);
                params.put("query",query);
                return params;
            }
        };

        //retry connection in case of timeout
        request.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(request);
    }

}
