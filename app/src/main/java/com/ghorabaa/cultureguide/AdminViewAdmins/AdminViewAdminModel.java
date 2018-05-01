package com.ghorabaa.cultureguide.AdminViewAdmins;

import android.content.Context;
import android.util.Pair;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ghorabaa.cultureguide.Utilities.DBConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by megem on 5/1/2018.
 */

public class AdminViewAdminModel {

    private Context mContext;
    private DBConnection db;
    private AdminViewAdminContract.Presenter mPresenter;

    public AdminViewAdminModel(AdminViewAdminContract.Presenter presenter, Context context) {

        mContext = context;
        mPresenter = presenter;
        db = DBConnection.getInstance(mContext);
    }

    public void getAdmins(){

        String query = "SELECT Email FROM Users WHERE Type = 'Admin'";

        Response.Listener onSuccess = new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                ArrayList<String> admins = new ArrayList<>();

                try {
                    JSONArray result = new JSONArray(response);

                    for(int i=0; i<result.length(); i++)
                    {
                        JSONObject admin = result.getJSONObject(i);
                        String email = admin.getString("Email");
                        admins.add(email);
                    }

                    mPresenter.onRetrieve(admins);

                } catch (JSONException e) {
                    e.printStackTrace();
                    mPresenter.onFail("An error has occurred!");
                }

            }

        };

        Response.ErrorListener onFail = new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                mPresenter.onFail("Connection Error");
            }
        };

        db.executeQuery(query,onSuccess,onFail);
    }
}
