package com.ghorabaa.cultureguide.UserEventPage.EventPage;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ghorabaa.cultureguide.MEvent;
import com.ghorabaa.cultureguide.Utilities.Authenticator;
import com.ghorabaa.cultureguide.Utilities.DBConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

/**
 * Created by Ahmed Ibrahim on 4/28/18.
 */

public class UserEventPageModel {

    private UserEventPagePresenter mPresenter;
    private Context mContext;   //Application context to sync with
    private DBConnection db;    //database reference
    private MEvent mEvent;  //event info after being retrieved
    private int eventID; //event id to be retrieved

    /**
     * Constructor of SignUp Model
     * @param presenter The presenter attached to the model, to handle callbacks
     * @param context Application context to sync with
     */
    UserEventPageModel(UserEventPagePresenter presenter, Context context, int eventID) {
        mPresenter = presenter;
        mContext = context;
        db = DBConnection.getInstance(context);
        this.eventID = eventID;
    }

    /**
     * Retrieves event info
     * @param isPastEvent to know if the event is past, to check if it's expired
     */
    void retrieveEvent(final boolean isPastEvent) {
        String query = "SELECT Event.EID,Title,Description,Event.Date,Location,Category.Name as CatName,Category.ID as CatID,Organization.Name,Organization.ID as OrgID FROM `Event`,`Category`,`Organization` WHERE Event.EID= %d && Event.CategoryID=Category.ID && Event.OID=Organization.ID";
        query = String.format(Locale.ENGLISH, query, eventID);

        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray result = new JSONArray(response);
                    JSONObject objectResult = result.getJSONObject(0);
                    mEvent = new MEvent(objectResult);

                    //if it's expired
                    if (!isPastEvent && !MEvent.isValidDate(mEvent.getDate()))
                        mPresenter.onRetrieveFail("This event is expired, please refresh the page");
                    else
                        mPresenter.onRetrieveSuccess(mEvent);

                } catch (JSONException e) {
                    Log.w("EventRetrieval", e.getMessage());
                    mPresenter.onRetrieveFail("Event not Found!");
                } catch (Exception e) {
                    mPresenter.onRetrieveFail("un supported date");
                }

            }


        };

        db.executeQuery(query, onSuccess, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

    }

    /**
     * Update rate for event in database
     * @param rate
     */
    void rateEvent(int rate) {
        String query = "INSERT INTO Rate(UID, EID, Rate) VALUES(%d, %d, %d) ON DUPLICATE KEY UPDATE Rate = %d";
        query = String.format(Locale.ENGLISH, query, Authenticator.getID(), mEvent.getID(), rate, rate);

        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("true"))
                    mPresenter.onRateSuccess();
                else
                    mPresenter.onRateFail("An error has occurred!");
            }
        };

        Response.ErrorListener onFail = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mPresenter.onRateFail("Connection Error!");
            }
        };

        db.executeQuery(query, onSuccess, onFail);
    }

    /**
     * Registers user in event's attendees
     */
    void attendEvent() {
        String query = "INSERT INTO Attend(UID,EID) VALUES(%d,%d)";
        query = String.format(Locale.ENGLISH, query, Authenticator.getID(), eventID);

        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("true"))
                    mPresenter.onAttendSuccess();
                else if (response.equals("false"))
                    mPresenter.onAttendFail("You are already listed in that event!");
                else
                    mPresenter.onAttendFail("An error has occurred!");
            }
        };

        Response.ErrorListener onFail = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mPresenter.onAttendFail("Connection Error!");
            }
        };

        db.executeQuery(query, onSuccess, onFail);
    }

    /**
     * Get the event rate from database
     */
    public void retrieveRate() {
        String query = "SELECT AVG(Rate) AS Average FROM Rate WHERE EID = %d";
        query = String.format(Locale.ENGLISH, query, eventID);

        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray result = new JSONArray(response);
                    if(!result.getJSONObject(0).isNull("Average")) {
                        int rate = result.getJSONObject(0).getInt("Average");
                        mPresenter.onRetrieveRate(rate);
                    }
                    else
                        mPresenter.onRetrieveRate(0);
                } catch (JSONException e) {
                    e.printStackTrace();
                    mPresenter.onRetrieveFail("Event has been deleted!");
                }

            }
        };

        Response.ErrorListener onFail = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mPresenter.onRateFail("Connection Error!");
            }
        };

        db.executeQuery(query, onSuccess, onFail);
    }

    /**
     * Adds organization to user's favourite list
     */
    public void addOrgToFavorite() {
        String query = "INSERT INTO Favorite(UID,OID) VALUES(%d,%d)";
        query = String.format(Locale.ENGLISH, query, Authenticator.getID(), mEvent.getOrgID());

        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("true"))
                    mPresenter.onAddOrgSuccess();
                else if (response.equals("false"))
                    mPresenter.onAddOrgFail("This organization is already listed in your favourites!");
                else
                    mPresenter.onAddOrgFail("An error has occurred!");
            }
        };

        Response.ErrorListener onFail = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mPresenter.onAddOrgFail("Connection Error!");
            }
        };

        db.executeQuery(query, onSuccess, onFail);
    }

    /**
     * Checks if organization is in users favourites or not
     */
    public void checkOrgState() {
        String query = "SELECT * FROM Favorite WHERE UID = %d AND OID = %d";
        query = String.format(Locale.ENGLISH, query, Authenticator.getID(), mEvent.getOrgID());

        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray result = new JSONArray(response);
                    if (result.length() == 0)
                        mPresenter.showAddOrg();
                } catch (JSONException e) {
                    e.printStackTrace();
                    mPresenter.onAddOrgFail("An error has occurred!");
                }

            }
        };

        Response.ErrorListener onFail = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mPresenter.onAddOrgFail("Connection Error!");
            }
        };

        db.executeQuery(query, onSuccess, onFail);
    }

    /**
     * Checks if user attended the event or not
     */
    void checkAttendState() {
        String query = "SELECT * FROM Attend WHERE UID = %d AND EID = %d";
        query = String.format(Locale.ENGLISH, query, Authenticator.getID(), eventID);

        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray result = new JSONArray(response);
                    if (result.length() == 0)
                        mPresenter.showAttendButton();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };

        Response.ErrorListener onFail = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mPresenter.onAddOrgFail("Connection Error!");
            }
        };

        db.executeQuery(query, onSuccess, onFail);
    }
}
