package com.ghorabaa.cultureguide.UserEventPage.InviteFriend;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ghorabaa.cultureguide.Friend;
import com.ghorabaa.cultureguide.Utilities.Authenticator;
import com.ghorabaa.cultureguide.Utilities.DBConnection;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Ahmed Ibrahim on 5/4/18.
 */

public class InviteFriendsModel {
    private InviteFriendsPresenter mPresenter;
    private Context mContext;
    private DBConnection db;
    private int eventID; //eventID to be invited to

    //User's friends
    private ArrayList<Friend> friendsList;

    /**
     * Constructor of Invite friends Model
     * @param presenter The presenter attached to the model, to handle callbacks
     * @param context Application context to sync with
     * @param eventID to be invited to
     */
    InviteFriendsModel(InviteFriendsPresenter presenter,Context context,int eventID){
        mPresenter = presenter;
        mContext = context;
        db = DBConnection.getInstance(context);
        this.eventID = eventID;
        friendsList = new ArrayList<>();
    }

    /**
     * Retrieves users friend
     *
     * Calls presenter.OnRetrieveFriendsList with the list
     */
    void retrieveFriendsList(){
        String query = "SELECT * FROM Friend,AppUser WHERE UID = %d AND FUID = AppUser.ID";
        query = String.format(Locale.ENGLISH,query, Authenticator.getID());

        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //Forever alone
                friendsList.clear();

                try {
                    JSONArray result = new JSONArray(response);

                    for(int i=0;i<result.length();++i){
                        int id = result.getJSONObject(i).getInt("FUID");
                        String email = result.getJSONObject(i).getString("Email");
                        String name = result.getJSONObject(i).getString("Name");
                        Friend friend = new Friend(id,email,name);
                        friendsList.add(friend);
                    }

                    mPresenter.onRetrieveFriendsList(friendsList);

                } catch (JSONException e) {
                    e.printStackTrace();
                    mPresenter.onRetrieveFriendsListFail("An error has occurred!");
                }
            }
        };

        Response.ErrorListener onFail = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mPresenter.onRetrieveFriendsListFail("Connection error!");
            }
        };
        db.executeQuery(query, onSuccess, onFail);

    }

    /**
     * invites user to following event
     * @param index index of friend to be invited
     */
    public void inviteFriend(int index){

        int id;
        try {
            id = friendsList.get(index).getID();
        }catch (Exception e){
            mPresenter.onInviteFail("An error has occurred!");
            return;
        }
        String query = "INSERT INTO Invite(UID,IUID,EID) VALUES(%d,%d,%d)";
        query = String.format(Locale.ENGLISH,query,Authenticator.getID(),id,eventID);

        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("true"))
                    mPresenter.onInviteSuccess();
                else if(response.equals("false"))
                    mPresenter.onInviteFail("You already invited this friend!");
                else
                    mPresenter.onInviteFail("An error has occurred!");
            }
        };

        Response.ErrorListener onFail = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mPresenter.onInviteFail("Connection Error!");
            }
        };

        db.executeQuery(query,onSuccess,onFail);
    }
}
