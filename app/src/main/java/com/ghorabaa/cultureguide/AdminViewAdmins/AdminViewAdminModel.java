package com.ghorabaa.cultureguide.AdminViewAdmins;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ghorabaa.cultureguide.Utilities.DBConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by megem on 5/1/2018.
 */

public class AdminViewAdminModel {

    private Context mContext;
    private DBConnection db;
    private AdminViewAdminPresenter mPresenter;

    public AdminViewAdminModel(AdminViewAdminPresenter presenter, Context context) {

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

    public void getAdmin(String email) {

        String query = "SELECT Email FROM Users WHERE Email = '%s'";
        query = String.format(Locale.ENGLISH,query, email);

        Response.Listener onSuccess = new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                ArrayList<String> admins = new ArrayList<>();

                try {
                    JSONArray result = new JSONArray(response);

                    //TODO test both cases with loop and without
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

    public void removeAdmin(String email) {

        String query = "DELETE FROM Users Where Email = '%s'";
        query = String.format(Locale.ENGLISH,query, email);

        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                switch (response) {

                    case "true":
                        mPresenter.onSuccess();
                        break;

                    case "false":
                        mPresenter.onFail("Database Not Affected");
                        break;

                    default:
                        mPresenter.onFail("An error has occurred");
                        break;
                }
            }
        };

        Response.ErrorListener onFail = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                mPresenter.onFail("Connection Error");
            }
        };

        db.executeQuery(query, onSuccess, onFail);
    }
}
