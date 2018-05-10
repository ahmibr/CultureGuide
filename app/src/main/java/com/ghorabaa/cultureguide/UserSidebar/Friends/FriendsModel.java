package com.ghorabaa.cultureguide.UserSidebar.Friends;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ghorabaa.cultureguide.Friend;
import com.ghorabaa.cultureguide.Utilities.Authenticator;
import com.ghorabaa.cultureguide.Utilities.DBConnection;
import com.ghorabaa.cultureguide.Utilities.EmailValidator;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Ahmed Ibrahim on 5/6/18.
 */

public class FriendsModel {

    private FriendsPresenter mPresenter;
    private Context mContext;
    private DBConnection db;
    private ArrayList<Friend> friendsList;

    /**
     * Constructor of SignUp Model
     * @param presenter The presenter attached to the model, to handle callbacks
     * @param context Application context to sync with
     */
    public FriendsModel(FriendsPresenter presenter, Context context) {
        mPresenter = presenter;
        mContext = context;
        db = DBConnection.getInstance(context);
        friendsList = new ArrayList<>();
    }


    public void retrieveFriendsList() {
        String query = "SELECT * FROM Friend,AppUser WHERE UID = %d AND FUID = AppUser.ID";
        query = String.format(Locale.ENGLISH,query, Authenticator.getID());

        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //Forever alone, again
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

    public void removeFriend(int index) {
        String query = "DELETE FROM Friend WHERE UID = %d AND FUID = %d";
        try {
            query = String.format(Locale.ENGLISH, query, Authenticator.getID(), friendsList.get(index).getID());
        } catch (Exception e) {
            mPresenter.onRemoveFail("An error has occurred!");
            return;
        }

        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("true")){
                    mPresenter.onRemoveSuccess();
                }
                else{
                    mPresenter.onRemoveFail("An error has occurred!");
                }
            }
        };

        Response.ErrorListener onFail = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mPresenter.onRemoveFail("Connection Error!");
            }
        };

        db.executeQuery(query, onSuccess, onFail);
    }

    public void addFriend(String email) {
        if(!EmailValidator.validate(email)){
            mPresenter.onAddFail("Please enter valid email form!");
            return;
        }
        if(email.toLowerCase().equals(Authenticator.getEmail())){
            //forever alone
            mPresenter.onAddFail("You can't add yourself!");
            return;
        }
        String query = "INSERT INTO Friend(UID,FUID) VALUES(%d,(SELECT ID FROM AppUser WHERE Email = '%s'))";
        query = String.format(Locale.ENGLISH,query,Authenticator.getID(),email);

        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("true")){
                    mPresenter.onAddSuccess();
                }
                else if(response.equals("false")){
                    mPresenter.onAddFail("User not found");
                }
                else{
                    mPresenter.onAddFail("An error has occurred!");
                }
            }
        };

        Response.ErrorListener onFail = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mPresenter.onAddFail("Connection Error!");
            }
        };

        db.executeQuery(query, onSuccess, onFail);
    }
}
