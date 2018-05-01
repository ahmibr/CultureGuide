package com.ghorabaa.cultureguide.AdminHomepage;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ghorabaa.cultureguide.Utilities.DBConnection;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by Ahmed Ibrahim on 4/30/18.
 */

public class AdminHomepageModel {
    private Context mContext;
    private DBConnection db;
    private AdminHomepageContract.Presenter mPresenter;

    AdminHomepageModel(AdminHomepagePresenter presenter, Context context) {
        mContext = context;
        mPresenter = presenter;
        db = DBConnection.getInstance(context);
    }

    public void testQuery() {

        String query = "INSERT INTO Users VALUES('%s',%d,'%s')";
        query = String.format(query, "ahmed@gmail.com", 20, "ahmed");

        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                switch (response) {
                    case "true":
                        //operation done
                        break;
                    case "false":
                        //query succeeded, but with no change
                        break;
                    default:
                        //wrong query
                        break;

                }
            }
        };

        Response.ErrorListener onFail = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        };

        db.executeQuery(query, onSuccess, onFail);
    }

}
