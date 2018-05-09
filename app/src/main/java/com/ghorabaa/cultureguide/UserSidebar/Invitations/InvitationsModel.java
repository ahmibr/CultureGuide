package com.ghorabaa.cultureguide.UserSidebar.Invitations;

import android.content.Context;
import android.text.format.Time;
import android.util.Pair;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ghorabaa.cultureguide.MEvent;
import com.ghorabaa.cultureguide.Utilities.Authenticator;
import com.ghorabaa.cultureguide.Utilities.DBConnection;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Ahmed Ibrahim on 5/6/18.
 */

public class InvitationsModel {

    private InvitationsPresenter mPresenter;
    private Context mContext;
    private DBConnection db;
    private ArrayList<Pair<String,MEvent>> invitationsList; //First: Sender Name Second:Event

    /**
     * Constructor of Invitation Model
     * @param presenter The presenter attached to the model, to handle callbacks
     * @param context Application context to sync with
     */
    public InvitationsModel(InvitationsPresenter presenter, Context context) {
        mPresenter = presenter;
        mContext = context;
        db = DBConnection.getInstance(context);
        invitationsList = new ArrayList<>();
    }

    /**
     * Retrieve users fresh invitations
     */
    public void retrieveInvitations() {
        final Time currentTime = new Time();
        currentTime.setToNow();
        long lCurrentTime = currentTime.toMillis(false);

        String query = "SELECT Name,Event.EID,Date,Title FROM Invite,Event,AppUser WHERE IUID = %d AND Invite.EID = Event.EID AND UID = ID AND Date > %d ORDER BY Time";
        query = String.format(Locale.ENGLISH,query, Authenticator.getID(),lCurrentTime);

        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                invitationsList.clear();

                try {
                    JSONArray result = new JSONArray(response);

                    for(int i=0;i<result.length();++i){
                        String friendName = result.getJSONObject(i).getString("Name");
                        int eventID = result.getJSONObject(i).getInt("EID");
                        long eventDate = result.getJSONObject(i).getLong("Date");
                        String eventTitle = result.getJSONObject(i).getString("Title");

                        MEvent event = new MEvent();
                        event.setID(eventID);
                        event.setDate(eventDate);
                        event.setTitle(eventTitle);

                        invitationsList.add(new Pair<String, MEvent>(friendName,event));
                    }

                    mPresenter.onRetrieveInvitations(invitationsList);

                }
                catch (Exception e) {
                    e.printStackTrace();
                    mPresenter.onRetrieveInvitationsFail("An error has occurred!");
                }
            }
        };

        Response.ErrorListener onFail = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mPresenter.onRetrieveInvitationsFail("Connection error!");
            }
        };

        db.executeQuery(query, onSuccess, onFail);
    }

    /**
     * Helper method to check if invitation is expired
     * @param index index of invitation
     * @return  true of invitation is expired
     */
    public boolean isExpired(int index) {
        try{
            MEvent event = invitationsList.get(index).second;
            return !MEvent.isValidDate(event.getDate());
        }
        catch (Exception e)
        {
            return true;
        }
    }
}
