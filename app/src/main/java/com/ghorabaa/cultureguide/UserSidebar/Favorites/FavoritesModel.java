package com.ghorabaa.cultureguide.UserSidebar.Favorites;

import android.content.Context;

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
 * Created by Ahmed Ibrahim on 5/6/18.
 */

public class FavoritesModel {
    private FavoritesPresenter mPresenter;
    private Context mContext;
    private DBConnection db;
    private ArrayList<Integer> favoritesIDs; //organization ids


    /**
     * Constructor of Favorites Model
     * @param presenter The presenter attached to the model, to handle callbacks
     * @param context Application context to sync with
     */
    public FavoritesModel(FavoritesPresenter presenter, Context context) {
        mPresenter = presenter;
        mContext = context;
        db = DBConnection.getInstance(context);
        favoritesIDs = new ArrayList<>();
    }

    /**
     * Retrieve favourites from database
     */
    public void retrieveFavorites() {
        String query = "SELECT Name,OID FROM Favorite,Organization WHERE UID = %d AND OID = ID";
        query = String.format(Locale.ENGLISH, query, Authenticator.getID());

        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                favoritesIDs.clear();
                ArrayList<String> favoritesNames = new ArrayList<>();
                try {
                    JSONArray result = new JSONArray(response);
                    for (int i = 0; i < result.length(); ++i) {
                        JSONObject organization = result.getJSONObject(i);
                        favoritesIDs.add(organization.getInt("OID"));
                        favoritesNames.add(organization.getString("Name"));
                    }
                    mPresenter.onRetrieveFavorites(favoritesNames);
                } catch (JSONException e) {
                    e.printStackTrace();
                    mPresenter.onRetrieveFail("An error has occurred!");
                }
            }
        };

        Response.ErrorListener onFail = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mPresenter.onRetrieveFail("Connection Error");
            }
        };

        db.executeQuery(query, onSuccess, onFail);
    }

    /**
     * remove organization from favourites
     * @param index index of organization in the array list
     */
    public void removeFavorite(int index) {
        String query = "DELETE FROM Favorite WHERE UID = %d AND OID = %d";
        try {
            query = String.format(Locale.ENGLISH, query, Authenticator.getID(), favoritesIDs.get(index));
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
}
