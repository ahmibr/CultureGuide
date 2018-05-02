package com.ghorabaa.cultureguide.UserEventPage;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ghorabaa.cultureguide.Friend;
import com.ghorabaa.cultureguide.MEvent;
import com.ghorabaa.cultureguide.Utilities.Authenticator;
import com.ghorabaa.cultureguide.Utilities.DBConnection;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by Ahmed Ibrahim on 4/28/18.
 */

public class UserEventPageModel {

    private UserEventPagePresenter mPresenter;
    private Context mContext;
    private DBConnection db;
    private MEvent mEvent;
    private int eventID;

    ArrayList<Friend> friendsList;

    UserEventPageModel(UserEventPagePresenter presenter,Context context,int eventID){
        mPresenter = presenter;
        mContext = context;
        db = DBConnection.getInstance(context);
        this.eventID = eventID;
        friendsList = new ArrayList<>();
    }

    void retrieveEvent(){
        String query = "SELECT Event.* , Category.Name AS cName , Organization.Name AS orgName FROM Event,Organization,Category WHERE EID = %d AND Organization.ID = Event.OID AND Event.CategoryID = Category.ID";
        query = String.format(query, eventID);

        //TODO add it with Roba's part

    }


    void rateEvent(int rate){
        String query = "INSERT INTO Rate(UID, EID, Rate) VALUES(%d, %d, %d) ON DUPLICATE KEY UPDATE Rate = %d";
        query = String.format(query, Authenticator.getID(),mEvent.GetID(),rate);

        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("true"))
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

        db.executeQuery(query,onSuccess,onFail);
    }

    void attendEvent(){
        String query = "INSERT INTO ATTEND(UID,EID) VALUES(?,?)";
        query = String.format(query,Authenticator.getID(),mEvent.GetID());

        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("true"))
                    mPresenter.onAttendSuccess();
                else if(response.equals("false"))
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

        db.executeQuery(query,onSuccess,onFail);
    }

    void retrieveFriendsList(){
        String query = "SELECT * FROM Friend,AppUser WHERE UID = %d AND FUID = AppUser.ID";
        query = String.format(query,Authenticator.getID());

        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //Forever alone
                friendsList.clear();

                try {
                    JSONArray result = new JSONArray(response);

                    for(int i=0;i<result.length();++i){
                        int id = result.getJSONObject(i).getInt("FUID");
                        String email = result.getJSONObject(0).getString("Email");
                        String name = result.getJSONObject(0).getString("Name");
                        Friend friend = new Friend(id,email,name);
                        friendsList.add(friend);
                    }

                    mPresenter.onRetrieveFriendsList(friendsList);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        Response.ErrorListener onFail = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mPresenter.onFailFriendsList("Connection error!");
            }
        };
        db.executeQuery(query, onSuccess, onFail);

    }
    public void inviteFriend(int index){

        int id = friendsList.get(index).getId();
        String query = "INSERT INTO Invite(UID,IUID,EID) VALUES(%d,%d,%d)";
        query = String.format(query,Authenticator.getID(),id,mEvent.GetID());

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

    public void retrieveRate(){
        String query = "SELECT AVG(Rate) AS Average FROM Rate WHERE EID = %d";
        query = String.format(query, eventID);

        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray result = new JSONArray(response);
                    int rate = result.getJSONObject(0).getInt("Average");
                    mPresenter.onRetrieveRate(rate);
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

        db.executeQuery(query,onSuccess,onFail);
    }

    public void addOrgToFavorite(){
        String query = "INSERT INTO Favorite(UID,OID) VALUES(%d,%d)";
        query = String.format(query,Authenticator.getID(),mEvent.GetOrgID());

        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("true"))
                    mPresenter.onAddOrgSuccess();
                else if(response.equals("false"))
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

        db.executeQuery(query,onSuccess,onFail);
    }

    public void checkOrgState() {
        String query = "SELECT * FROM Favorite WHERE UID = %d AND OID = %d";
        query = String.format(query,Authenticator.getID(),mEvent.GetOrgID());

        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray result = new JSONArray(response);
                    if(result.length()==0)
                        mPresenter.showAddOrg();
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

        db.executeQuery(query,onSuccess,onFail);
    }
}
