package com.ghorabaa.cultureguide.OrganizationEvent.Homepage;

import android.content.Context;

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
 * Created by Roba Gamal on 4/29/18.
 */

public class OrgHomepageModel {

    private OrgHomepagePresenter mPresenter;
    private Context mContext;
    private DBConnection db;
    private ArrayList<MEvent> mEvents;

    public OrgHomepageModel(OrgHomepagePresenter presenter,Context context){
        mContext = context;
        db = DBConnection.getInstance(context);
        mPresenter = presenter;
        mEvents = new ArrayList<>();
    }

    public void retrieveEvents() {
        String query = "SELECT EID,Title From Event WHERE OID = %d Order By Date";
        query = String.format(Locale.ENGLISH, query, Authenticator.getID());
        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mEvents.clear();
                try {
                    JSONArray result = new JSONArray(response);
                    for (int i = 0; i < result.length(); ++i) {
                        MEvent added = new MEvent();
                        JSONObject event = result.getJSONObject(i);
                        added.setID(event.getInt("EID"));
                        added.setTitle(event.getString("Title"));
                        mEvents.add(added);
                    }
                    mPresenter.onRetrieveEvents(mEvents);
                } catch (JSONException e) {
                    e.printStackTrace();
                    mPresenter.onRetrieveFail("An error has occurred!");
                }
            }
        };

        Response.ErrorListener onFail = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mPresenter.onRetrieveFail("Connection Error!");
            }
        };


        db.executeQuery(query, onSuccess, onFail);
    }
}
