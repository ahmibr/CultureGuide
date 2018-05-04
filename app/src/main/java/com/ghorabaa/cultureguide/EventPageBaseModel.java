package com.ghorabaa.cultureguide;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ghorabaa.cultureguide.Utilities.DBConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

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

    public abstract void passToPresenter(MEvent mEvent);

    public abstract void onRetrieveFail(String errorMessage);
}
