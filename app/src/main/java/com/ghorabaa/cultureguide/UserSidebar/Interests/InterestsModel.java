package com.ghorabaa.cultureguide.UserSidebar.Interests;

import android.content.Context;
import android.util.Pair;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ghorabaa.cultureguide.Utilities.Authenticator;
import com.ghorabaa.cultureguide.Utilities.DBConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Ahmed Ibrahim on 5/7/18.
 */

public class InterestsModel {

    private Context mContext;
    private InterestsPresenter mPresenter;
    private DBConnection db;
    private ArrayList<Integer> categoryIDs; //IDs of categories in db
    private ArrayList<Pair<String, Boolean>> categoryList; //First: Name Second: subscribed

    /**
     * Constructor of Interests Model
     * @param presenter The presenter attached to the model, to handle callbacks
     * @param context Application context to sync with
     */
    public InterestsModel(InterestsPresenter presenter, Context context) {
        mContext = context;
        mPresenter = presenter;
        categoryIDs = new ArrayList<>();
        categoryList = new ArrayList<>();
        db = DBConnection.getInstance(context);
    }

    /**
     * Retrieve user's interests from database
     */
    public void retrieveInterests() {

        String query = "SELECT Category.ID,Category.Name,Subscription.UID FROM Category LEFT JOIN Subscription ON Category.ID = Subscription.CID AND Subscription.UID = %d";

        query = String.format(Locale.ENGLISH, query, Authenticator.getID());

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
                        Boolean subscribed = !category.isNull("UID");
                        String categoryName = category.getString("Name");
                        categoryIDs.add(categoryID);
                        categoryList.add(new Pair<String, Boolean>(categoryName, subscribed));
                    }
                    mPresenter.onRetrieveInterests(categoryList);
                } catch (JSONException e) {
                    e.printStackTrace();
                    mPresenter.onRetrieveInterestsFail("An error has occurred!");
                }

            }
        };

        Response.ErrorListener onFail = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mPresenter.onRetrieveInterestsFail("Connection Error!");
            }
        };

        db.executeQuery(query, onSuccess, onFail);
    }

    /**
     * Add category to user's interests
     * @param index category index in array list
     */
    public void addInterests(int index) {
        String query = "INSERT INTO Subscription(UID,CID) VALUES(%d,%d)";
        try {
            query = String.format(Locale.ENGLISH, query, Authenticator.getID(), categoryIDs.get(index));
        } catch (Exception e) {
            mPresenter.onAddFail("An error has occurred!");
            return;
        }
        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("true")) {
                    mPresenter.onAddSuccess();
                } else {
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

    /**
     * Removes category from user's interests
     * @param index index of category in array list
     */
    public void removeInterest(int index) {

        String query = "DELETE FROM Subscription WHERE UID = %d AND CID = %d";
        try {
            query = String.format(Locale.ENGLISH, query, Authenticator.getID(), categoryIDs.get(index));
        } catch (Exception e) {
            mPresenter.onRemoveFail("An error has occurred!");
            return;
        }

        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("true")) {
                    mPresenter.onRemoveSuccess();
                } else {
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


}
