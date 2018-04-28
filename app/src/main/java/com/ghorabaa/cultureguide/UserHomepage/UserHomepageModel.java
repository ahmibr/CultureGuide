package com.ghorabaa.cultureguide.UserHomepage;

import android.content.Context;
import android.text.format.Time;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ghorabaa.cultureguide.MEvent;
import com.ghorabaa.cultureguide.Utilities.Authenticator;
import com.ghorabaa.cultureguide.Utilities.DBConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Ahmed Ibrahim on 4/28/18.
 */

public class UserHomepageModel {
    private UserHomepagePresenter mPresenter;
    private Context mContext;
    private DBConnection db;

    UserHomepageModel(UserHomepagePresenter presenter,Context context){
        mPresenter = presenter;
        mContext = context;
        db = DBConnection.getInstance(context);
    }

    public void getUpcomingEvents(){
        String query = "SELECT * FROM Event WHERE (Date > %d) AND (CategoryID IN (SELECT CID FROM Subscription WHERE UID = %d) )";

        final Time currentTime = new Time();
        currentTime.setToNow();
        long lCurrentTime = currentTime.toMillis(false);

        query = String.format(query,lCurrentTime, Authenticator.getID());

        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<MEvent> currentEvents = new ArrayList<>();

                mPresenter.onRetrievingEvents(response);
                return;
                //TODO Send currentEvents to presenter, after filling it

            }
        };

        db.executeQuery(query, onSuccess, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mPresenter.onFail("Connection Error");
            }
        });

    }

    public void getPastEvent(){
        String query = "SELECT * FROM Event WHERE (Date < %d) AND (EID IN (SELECT DISTINCT EID FROM Attend WHERE UID = %d) )";

        final Time currentTime = new Time();
        currentTime.setToNow();
        long lCurrentTime = currentTime.toMillis(false);

        query = String.format(query,lCurrentTime, Authenticator.getID());

        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<MEvent> currentEvents = new ArrayList<>();

                mPresenter.onRetrievingEvents(response);
                return;
                //TODO Send currentEvents to presenter, after filling it

            }
        };

        db.executeQuery(query, onSuccess, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mPresenter.onFail("Connection Error");
            }
        });
    }

    public void getFavouriteEvent(){
        String query = "SELECT * FROM Event WHERE (Date > %d) AND OID IN (SELECT OID FROM Favorite WHERE UID = %d)";

        final Time currentTime = new Time();
        currentTime.setToNow();
        long lCurrentTime = currentTime.toMillis(false);

        query = String.format(query,lCurrentTime, Authenticator.getID());

        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<MEvent> currentEvents = new ArrayList<>();

                mPresenter.onRetrievingEvents(response);
                return;
                //TODO Send currentEvents to presenter, after filling it

            }
        };

        db.executeQuery(query, onSuccess, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mPresenter.onFail("Connection Error");
            }
        });
    }
}
