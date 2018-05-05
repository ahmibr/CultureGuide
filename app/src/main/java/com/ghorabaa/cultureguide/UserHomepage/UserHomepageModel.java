package com.ghorabaa.cultureguide.UserHomepage;

import android.content.Context;
import android.text.format.Time;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ghorabaa.cultureguide.MEvent;
import com.ghorabaa.cultureguide.Utilities.Authenticator;
import com.ghorabaa.cultureguide.Utilities.DBConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Ahmed Ibrahim on 4/28/18.
 */

public class UserHomepageModel {
    private UserHomepagePresenter mPresenter;
    private Context mContext;
    private DBConnection db;
    private ArrayList<MEvent> currentEvents;
    UserHomepageModel(UserHomepagePresenter presenter,Context context){
        mPresenter = presenter;
        mContext = context;
        db = DBConnection.getInstance(context);
        currentEvents = new ArrayList<>();
    }

    private void getEvents(String query){

        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                currentEvents.clear();
                try {
                    JSONArray result = new JSONArray(response);
                    for(int i=0;i<result.length();++i){
                        MEvent added = new MEvent();
                        JSONObject event = result.getJSONObject(i);
                        added.SetID(event.getInt("EID"));
                        added.setDescription(event.getString("Description"));
                        added.SetOrgID(event.getInt("OID"));
                        added.SetTitle(event.getString("Title"));
                        currentEvents.add(added);
                    }
                    mPresenter.onRetrievingEvents(currentEvents);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        db.executeQuery(query, onSuccess, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mPresenter.onFail("Connection Error");
            }
        });


    }
    public void getUpcomingEvents(){
        String query = "SELECT * FROM Event WHERE (Date > %d) AND (CategoryID IN (SELECT CID FROM Subscription WHERE UID = %d) )";
        final Time currentTime = new Time();
        currentTime.setToNow();
        long lCurrentTime = currentTime.toMillis(false);

        query = String.format(Locale.ENGLISH,query,lCurrentTime, Authenticator.getID());
        Log.e("query",query);
        getEvents(query);
    }

    public void getPastEvent(){
        String query = "SELECT * FROM Event WHERE (Date < %d) AND (EID IN (SELECT DISTINCT EID FROM Attend WHERE UID = %d) )";

        final Time currentTime = new Time();
        currentTime.setToNow();
        long lCurrentTime = currentTime.toMillis(false);

        query = String.format(Locale.ENGLISH,query,lCurrentTime, Authenticator.getID());

        getEvents(query);
    }

    public void getFavouriteEvent(){
        String query = "SELECT * FROM Event WHERE (Date > %d) AND OID IN (SELECT OID FROM Favorite WHERE UID = %d)";

        final Time currentTime = new Time();
        currentTime.setToNow();
        long lCurrentTime = currentTime.toMillis(false);

        query = String.format(Locale.ENGLISH,query,lCurrentTime, Authenticator.getID());

        getEvents(query);
    }
}
