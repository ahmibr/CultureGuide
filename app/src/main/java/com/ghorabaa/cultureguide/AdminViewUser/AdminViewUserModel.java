package com.ghorabaa.cultureguide.AdminViewUser;

import android.content.Context;
import android.util.Pair;

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

public class AdminViewUserModel {

    Context mContext;
    DBConnection db;
    AdminViewUserPresenter mPresenter;

    public AdminViewUserModel(AdminViewUserPresenter presenter, Context context) {

        mContext = context;
        db = DBConnection.getInstance(mContext);
        mPresenter = presenter;
    }

    public void getUsers()
    {
        String query = "SELECT Email, Name FROM AppUser";

        Response.Listener onSuccess = new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                ArrayList<Pair<String, String> > users = new ArrayList<>();

                try {
                    JSONArray result = new JSONArray(response);

                    for(int i=0; i<result.length(); i++)
                    {
                        JSONObject user = result.getJSONObject(i);
                        String email = user.getString("Email");
                        String name = user.getString("Name");
                        Pair<String, String> p = new Pair<>(email, name);
                        users.add(p);
                    }

                    mPresenter.onRetrieve(users);

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

    public void getUser(String email)
    {
        String query = "SELECT Email, Name FROM AppUser WHERE Email = %s";
        query = String.format(Locale.ENGLISH,query, email);

        Response.Listener onSucccess = new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                ArrayList<Pair<String, String> > users = new ArrayList<>();

                try {
                    JSONArray result = new JSONArray(response);

                    for(int i=0; i<result.length(); i++)
                    {
                        JSONObject user = result.getJSONObject(i);
                        String email = user.getString("Email");
                        String name = user.getString("Name");
                        Pair<String, String> p = new Pair<>(email, name);
                        users.add(p);
                    }

                    mPresenter.onRetrieve(users);

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

        db.executeQuery(query, onSucccess, onFail);
    }

    public void removeUser(String email) {

        String query = "DELETE FROM Users WHERE Email = '%s'";
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
