package com.ghorabaa.cultureguide.Utilities;

import android.app.DownloadManager;
import android.content.Context;

import com.android.volley.AuthFailureError;
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

public class DBConnection {

    private static final String URL = "http://medo4cross.000webhostapp.com/execute.php";
    private static final String DB_PASSWORD = "password";
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

    public void executeQuery(final String query, Response.Listener<String> onSuccess, Response.ErrorListener onFail){
        StringRequest request =  new StringRequest(Request.Method.POST,URL,onSuccess,onFail){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("DB_PASSWORD",DB_PASSWORD);
                params.put("query",query);
                return params;
            }
        };

        mRequestQueue.add(request);
    }

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

        StringRequest request =  new StringRequest(Request.Method.POST,URL,onSuccess,onFail ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("DB_PASSWORD",DB_PASSWORD);
                params.put("query",query);
                return params;
            }
        };

        mRequestQueue.add(request);
    }

}
