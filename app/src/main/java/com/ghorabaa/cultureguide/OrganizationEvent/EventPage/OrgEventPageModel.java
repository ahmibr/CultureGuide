package com.ghorabaa.cultureguide.OrganizationEvent.EventPage;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ghorabaa.cultureguide.MEvent;
import com.ghorabaa.cultureguide.Utilities.DBConnection;
import com.ghorabaa.cultureguide.Utilities.SQLInjectionEscaper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Roba Gamal on 4/5/18.
 */

public class OrgEventPageModel {
    private DBConnection db;
    private Context mContext;
    private OrgEventPagePresenter mPresenter;
    private int eventID;
    private MEvent mEvent;
    private ArrayList<Integer> categoryIDs;
    private ArrayList<String> categoryList;

    public OrgEventPageModel(OrgEventPagePresenter presenter, Context context, int eventID) {
        mPresenter = presenter;
        mContext = context;
        db = DBConnection.getInstance(mContext);
        this.eventID = eventID;
        categoryIDs = new ArrayList<>();
        categoryList = new ArrayList<>();
    }

    public void retrieveEvent() {
        String query = " SELECT Event.EID,Title,Description,Event.Date,Location,Category.Name as CatName,Category.ID as CatID,Organization.Name,Organization.ID as OrgID FROM `Event`,`Category`,`Organization` WHERE Event.CategoryID=Category.ID&& Event.OID=Organization.ID&& Event.EID= %d";
        query = String.format(Locale.ENGLISH, query, eventID);


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
        query = String.format(Locale.ENGLISH, query, eventID);

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

    public void updateEventTitle(String title) {

        title = SQLInjectionEscaper.escapeString(title);
        String query = "UPDATE Event SET Title ='%s' WHERE EID = %d";
        query = String.format(Locale.ENGLISH, query, title, eventID);


        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("true"))
                    mPresenter.onUpdateSuccess("Title updated successfully!");
                else
                    mPresenter.onUpdateFail("An error has occurred!");
            }
        };

        Response.ErrorListener onFail = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mPresenter.onUpdateFail("Connection error!");

            }
        };

        db.executeQuery(query, onSuccess, onFail);
    }

    public void updateEventLocation(String location) {
        location = SQLInjectionEscaper.escapeString(location);
        String query = "UPDATE Event SET Location ='%s' WHERE EID = %d";
        query = String.format(Locale.ENGLISH, query, location, eventID);


        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("true"))
                    mPresenter.onUpdateSuccess("Location updated successfully!");
                else
                    mPresenter.onUpdateFail("An error has occurred!");
            }

        };

        Response.ErrorListener onFail = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mPresenter.onUpdateFail("Connection error!");

            }
        };

        db.executeQuery(query, onSuccess, onFail);
    }


    public void updateEventDescription(String description) {
        description = SQLInjectionEscaper.escapeString(description);
        String query = "UPDATE Event SET Description = '%s' WHERE EID = %d";
        query = String.format(Locale.ENGLISH, query, description, eventID);


        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("true"))
                    mPresenter.onUpdateSuccess("Description updated successfully!");
                 else
                     mPresenter.onUpdateFail("An error has occurred!");
            }

        };

        Response.ErrorListener onFail = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mPresenter.onUpdateFail("Connection error!");

            }
        };

        db.executeQuery(query, onSuccess, onFail);
    }

    public void updateEventDate(Date date){
        if (!MEvent.isValidDate(date)) {
            mPresenter.onUpdateFail("Invalid date!");
            return;
        }

        String query = "UPDATE Event SET Date = %d WHERE EID = %d";
        query = String.format(Locale.ENGLISH, query, date.getTime(), eventID);


        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("true"))
                    mPresenter.onUpdateSuccess("Date updated successfully!");
                else
                    mPresenter.onUpdateFail("An error has occurred!");
            }

        };

        Response.ErrorListener onFail = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mPresenter.onUpdateFail("Connection error!");

            }
        };

        db.executeQuery(query, onSuccess, onFail);
    }

    public void updateEventCategory(int cIndex){

        int cID;
        try{
            cID = categoryIDs.get(cIndex);
        }catch (Exception e){
            e.printStackTrace();
            mPresenter.onUpdateFail("An error has occurred!");
            return;
        }

        String query = "UPDATE Event SET CategoryID = %d WHERE EID = %d";
        query = String.format(Locale.ENGLISH, query, cID, eventID);


        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("true"))
                    mPresenter.onUpdateSuccess("Category updated successfully!");
                else
                    mPresenter.onUpdateFail("An error has occurred!");
            }

        };

        Response.ErrorListener onFail = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mPresenter.onUpdateFail("Connection error!");

            }
        };

        db.executeQuery(query, onSuccess, onFail);
    }


}
