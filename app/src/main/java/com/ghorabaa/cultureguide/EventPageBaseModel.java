package com.ghorabaa.cultureguide;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ghorabaa.cultureguide.Utilities.DBConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ruba on 03/05/18.
 */

public abstract class EventPageBaseModel {
    protected DBConnection db;
    protected int eventID;

    protected EventPageBaseModel(DBConnection db, int eventID){
        this.eventID = eventID;
        this.db = db;

    }

    public void retrieveEvent(){
        String query = " SELECT Event.EID,Title,Description,Event.Date,Location,Category.Name as CatName,Category.ID as CatID,Organization.Name,Organization.ID as OrgID FROM `Event`,`Category`,`Organization` WHERE Event.CategoryID=Category.ID&& Event.OID=Organization.ID&& Event.EID= %d";
        query = String.format(query,eventID);

        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray result = new JSONArray(response);
                    JSONObject objectResult = result.getJSONObject(0);
                    MEvent Event = new MEvent(objectResult);

                    passToPresenter(Event);

                } catch (JSONException e) {
                    Log.w("EventRetrieval", e.getMessage());
                    onRetrieveFail("Event not Found!");
                } catch (Exception e) {
                    onRetrieveFail("un supported date");
                }

            }


        };

        db.executeQuery(query, onSuccess, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }




    public void GetEventRate() {


        String query = "SELECT AVG(Rate) FROM Rate WHERE Rate.EID= " + eventID;
        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    MEvent Event = new MEvent();
                    Event.SetID(eventID);
                    JSONArray result = new JSONArray(response);
                    int rate;

                    rate = Integer.parseInt(result.getJSONObject(0).getString("Rate"));
                    Event.setRating(rate);
                    passToPresenter(Event);

                } catch (JSONException e) {
                    Log.w("error msg", e.getMessage());
                }


            }
        };
       db.executeQuery(query, onSuccess, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }


    public void getmostrated()
    {
        String query = "SELECT EID ,a.average" +
                "FROM (SELECT AVG(Rate)as average,EID  FROM Rate GROUP BY EID) as a" +
                "HAVING a.average= (SELECT MAX(a.average) FROM (SELECT AVG(Rate)as average,EID  FROM Rate GROUP BY EID) as a)";

        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray result = new JSONArray(response);
                    JSONObject objectResult = result.getJSONObject(0);
                    MEvent Event = new MEvent(objectResult);

                   passToPresenter(Event);




                } catch (JSONException e) {
                    Log.w("json error msg", e.getMessage());
                   onRetrieveFail(" event not found");
                } catch (Exception e) {
                    Log.w("json error msg", e.getMessage());
                   onRetrieveFail("un supported date");
                }

            }


        };

        db.executeQuery(query, onSuccess, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }







    public abstract void passToPresenter(MEvent mEvent);

    public abstract void onRetrieveFail(String errorMessage);
}
