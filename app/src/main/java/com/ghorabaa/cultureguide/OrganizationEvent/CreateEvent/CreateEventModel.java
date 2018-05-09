package com.ghorabaa.cultureguide.OrganizationEvent.CreateEvent;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ghorabaa.cultureguide.MEvent;
import com.ghorabaa.cultureguide.Utilities.Authenticator;
import com.ghorabaa.cultureguide.Utilities.DBConnection;
import com.ghorabaa.cultureguide.Utilities.SQLInjectionEscaper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Roba Gamal on 3/26/18.
 *
 */

public class CreateEventModel {

    private CreateEventPresenter mPresenter;
    private DBConnection db;
    private Context mContext;
    private ArrayList<Integer> categoryIDs;
    private ArrayList<String> categoryList;

    public CreateEventModel(CreateEventPresenter presenter, Context context) {
        mContext = context;
        mPresenter = presenter;
        db = DBConnection.getInstance(context);
        categoryIDs = new ArrayList<>();
        categoryList = new ArrayList<>();
    }

    public void createEvent(String title, String description, String location, Date date, int cIndex) {

        //check if date hasn't passed yet
        if (!MEvent.isValidDate(date)) {
            mPresenter.onCreateEventFail("Invalid date!");
            return;
        }
        int cID;
        try {
            cID = categoryIDs.get(cIndex);
        } catch (Exception e) {
            e.printStackTrace();
            mPresenter.onCreateEventFail("An error has occurred!");
            return;
        }
        title = SQLInjectionEscaper.escapeString(title);
        description = SQLInjectionEscaper.escapeString(description);
        location = SQLInjectionEscaper.escapeString(location);

        String query = "INSERT INTO Event(OID, Title, CategoryID, Date, Description, Location) Values(%d,'%s',%d,%d,'%s','%s')";
        query = String.format(Locale.ENGLISH, query, Authenticator.getID(), title, cID, date.getTime(), description, location);

        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("true"))
                    mPresenter.onCreateEventSuccess();
                else
                    mPresenter.onCreateEventFail("An error has occurred!");
            }
        };


        Response.ErrorListener onFail = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mPresenter.onCreateEventFail("Connection error!");
            }
        };

        db.executeQuery(query, onSuccess, onFail);
    }

    public void retrieveCategories() {
        String query = "SELECT * FROM Category";
        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray result = new JSONArray(response);
                    categoryIDs.clear();
                    categoryList.clear();
                    for (int i = 0; i < result.length(); ++i) {
                        JSONObject category = result.getJSONObject(i);
                        int categoryID = category.getInt("ID");
                        String categoryName = category.getString("Name");
                        categoryIDs.add(categoryID);
                        categoryList.add(categoryName);
                    }
                    mPresenter.onRetrieveCategories(categoryList);
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
