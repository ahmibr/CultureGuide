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

    private ArrayList<MEvent> currentEvents; //current shown events

    /**
     * Constructor of User homepage Model
     * @param presenter The presenter attached to the model, to handle callbacks
     * @param context Application context to sync with
     */
    UserHomepageModel(UserHomepagePresenter presenter,Context context){
        mPresenter = presenter;
        mContext = context;
        db = DBConnection.getInstance(context);
        currentEvents = new ArrayList<>();
    }

    /**
     * Helper function to retrieve events based on which query(past,upcoming,favourite)
     * @param query
     *
     */
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
                        added.setID(event.getInt("EID"));
                        added.setDescription(event.getString("Description"));
                        added.setOrgID(event.getInt("OID"));
                        added.setTitle(event.getString("Title"));
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

    /**
     * Retrieves upcoming events
     */
    public void getUpcomingEvents(){
        String query = "SELECT * FROM Event WHERE (Date > %d) AND (CategoryID IN (SELECT CID FROM Subscription WHERE UID = %d) ) ORDER BY Date";
        final Time currentTime = new Time();
        currentTime.setToNow();
        long lCurrentTime = currentTime.toMillis(false);

        query = String.format(Locale.ENGLISH,query,lCurrentTime, Authenticator.getID());
        Log.e("query",query);
        getEvents(query);
    }

    /**
     * Retrieves past events
     */
    public void getPastEvent(){
        String query = "SELECT * FROM Event WHERE (Date < %d) AND (EID IN (SELECT EID FROM Attend WHERE UID = %d) ) ORDER BY Date";

        final Time currentTime = new Time();
        currentTime.setToNow();
        long lCurrentTime = currentTime.toMillis(false);

        query = String.format(Locale.ENGLISH,query,lCurrentTime, Authenticator.getID());

        getEvents(query);
    }

    /**
     * Retrieves events from favourite organizations
     */
    public void getFavouriteEvent(){
        String query = "SELECT * FROM Event WHERE (Date > %d) AND OID IN (SELECT OID FROM Favorite WHERE UID = %d) ORDER BY Date";

        final Time currentTime = new Time();
        currentTime.setToNow();
        long lCurrentTime = currentTime.toMillis(false);

        query = String.format(Locale.ENGLISH,query,lCurrentTime, Authenticator.getID());

        getEvents(query);
    }
}
