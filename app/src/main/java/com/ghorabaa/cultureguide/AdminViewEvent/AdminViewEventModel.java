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

/**
 * Created by megem on 5/1/2018.
 */

public class AdminViewEventModel {
    
    private Context mContext;
    private DBConnection db;
    private AdminViewEventContract.Presenter mPresenter;


    public AdminViewEventModel(AdminViewEventPresenter presenter, Context context) {

        mContext = context;
        mPresenter = presenter;
        db = DBConnection.getInstance(mContext);
    }

    public void retrieveEvents() {

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
}
