package com.ghorabaa.cultureguide.AdminViewEvent;

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

public class AdminViewEventModel {
    
    private Context mContext;
    private DBConnection db;
    private AdminViewEventPresenter mPresenter;


    public AdminViewEventModel(AdminViewEventPresenter presenter, Context context) {

        mContext = context;
        mPresenter = presenter;
        db = DBConnection.getInstance(mContext);
    }

    public void getEvents() {

        String query = "SELECT EID, Title FROM Event";

        Response.Listener onSuccess = new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                ArrayList<Pair<Integer, String> > events = new ArrayList<>();

                try
                {
                    JSONArray result = new JSONArray(response);

                    for(int i=0; i<result.length(); i++)
                    {
                        JSONObject event = result.getJSONObject(i);
                        int id = event.getInt("EID");
                        String title = event.getString("Title");
                        Pair<Integer, String> p = new Pair<>(id, title);
                        events.add(p);
                    }

                    mPresenter.onRetrieve(events);
                }

                catch (JSONException e) {
                    e.printStackTrace();
                    mPresenter.onFail("An error has ocurred");
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

    public void getEvent(int id) {

        String query = "SELECT EID, Title FROM Event WHERE EID = %d";
        query = String.format(Locale.ENGLISH,query, id);

        Response.Listener onSuccess = new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                ArrayList<Pair<Integer, String> > events = new ArrayList<>();

                try
                {
                    JSONArray result = new JSONArray(response);

                    for(int i=0; i<result.length(); i++)
                    {
                        JSONObject event = result.getJSONObject(i);
                        int id = event.getInt("EID");
                        String title = event.getString("Title");
                        Pair<Integer, String> p = new Pair<>(id, title);
                        events.add(p);
                    }

                    mPresenter.onRetrieve(events);
                }

                catch (JSONException e) {
                    e.printStackTrace();
                    mPresenter.onFail("An error has ocurred");
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

    public void removeEvent(Integer id) {

        String query = "DELETE FROM Event WHERE EID = %d";
        query = String.format(Locale.ENGLISH,query, id);

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
