package com.ghorabaa.cultureguide.OrganizationEvent.EventPage;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ghorabaa.cultureguide.MEvent;
import com.ghorabaa.cultureguide.Utilities.DBConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

/**
 * Created by Ahmed Ibrahim on 5/8/18.
 */

public class OrgEventPageModel {
    private DBConnection db;
    private Context mContext;
    private OrgEventPagePresenter mPresenter;
    private int eventID;
    private MEvent mEvent;

    public OrgEventPageModel(OrgEventPagePresenter presenter,Context context,int eventID){
        mPresenter = presenter;
        mContext = context;
        db = DBConnection.getInstance(mContext);
        this.eventID = eventID;
    }

    public void retrieveEvent() {
        String query = " SELECT Event.EID,Title,Description,Event.Date,Location,Category.Name as CatName,Category.ID as CatID,Organization.Name,Organization.ID as OrgID FROM `Event`,`Category`,`Organization` WHERE Event.CategoryID=Category.ID&& Event.OID=Organization.ID&& Event.EID= %d";
        query = String.format(Locale.ENGLISH,query, eventID);


        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray result = new JSONArray(response);
                    JSONObject objectResult = result.getJSONObject(0);
                    mEvent = new MEvent(objectResult);
                    mPresenter.onRetrieveEvent(mEvent);
                } catch (JSONException e) {
                    e.printStackTrace();
                    mPresenter.onRetrieveFail("Event not Found!");
                } catch (Exception e) {
                    mPresenter.onRetrieveFail("un supported date");
                }

            }


        };

        Response.ErrorListener onFail = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mPresenter.onRetrieveFail("Connection error!");
            }
        };

        db.executeQuery(query, onSuccess, onFail);
    }

    public void removeEvent() {
        String query = "DELETE FROM Event WHERE EID = %d";
        query = String.format(Locale.ENGLISH,query,eventID);

        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("true"))
                    mPresenter.onRemoveEventSuccess();
                else
                    mPresenter.onRemoveEventFail("An error has occurred");
            }

        };

        Response.ErrorListener onFail = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mPresenter.onRemoveEventFail("Connection error!");
            }
        };

        db.executeQuery(query, onSuccess, onFail);
    }
}
