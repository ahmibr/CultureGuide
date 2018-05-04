package com.ghorabaa.cultureguide.AdminViewOrganization;

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

public class AdminViewOrganizationModel {

    private Context mContext;
    private DBConnection db;
    private AdminViewOrganizationPresenter mPresenter;


    public AdminViewOrganizationModel(AdminViewOrganizationPresenter presenter, Context context) {

        mContext = context;
        db = DBConnection.getInstance(mContext);
        mPresenter = presenter;
    }

    public void getOrganizations()
    {
        String query = "SELECT Email, Name FROM Organization";

        Response.Listener<String> onSuccess = new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                ArrayList<Pair<String, String> > organizations = new ArrayList<>();

                try {

                    JSONArray result = new JSONArray(response);

                    for(int i=0; i<result.length(); i++)
                    {
                        JSONObject organization = result.getJSONObject(i);
                        String email = organization.getString("Email");
                        String name = organization.getString("Name");
                        Pair<String, String> p = new Pair<>(email, name);
                        organizations.add(p);
                    }

                    mPresenter.onRetrieve(organizations);

                } catch (JSONException e) {

                    e.printStackTrace();
                    mPresenter.onFail("An error has occurred");

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

    public void getOrganization(String email) {

        String query = "SELECT Email, Name FROM Organization WHERE Email = '%s";
        query = String.format(query, email);

        Response.Listener<String> onSuccess = new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                ArrayList<Pair<String, String> > organizations = new ArrayList<>();

                try {

                    JSONArray result = new JSONArray(response);

                    for(int i=0; i<result.length(); i++)
                    {
                        JSONObject organization = result.getJSONObject(i);
                        String email = organization.getString("Email");
                        String name = organization.getString("Name");
                        Pair<String, String> p = new Pair<>(email, name);
                        organizations.add(p);
                    }

                    mPresenter.onRetrieve(organizations);

                } catch (JSONException e) {

                    e.printStackTrace();
                    mPresenter.onFail("An error has occurred");

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
