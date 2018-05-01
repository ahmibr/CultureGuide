package com.ghorabaa.cultureguide.AdminViewCategory;

import android.content.Context;
import android.util.Pair;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ghorabaa.cultureguide.AdminHomepage.AdminHomepagePresenter;
import com.ghorabaa.cultureguide.Utilities.DBConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Ahmed Ibrahim on 4/30/18.
 */

public class AdminViewCategoryModel {

    private Context mContext;
    private DBConnection db;
    private AdminViewCategoryPresenter mPresenter;

    public AdminViewCategoryModel(AdminViewCategoryPresenter presenter, Context context) {

        mContext= context;
        mPresenter = presenter;
        db = DBConnection.getInstance(mContext);
    }

    public void getAvailableCategories() {

        String query = "SELECT * FROM Category";

        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                ArrayList<Pair<Integer, String> > categories = new ArrayList<>();

                try {

                    JSONArray result = new JSONArray(response);

                    for(int i=0; i<result.length(); i++)
                    {
                        JSONObject category = result.getJSONObject(i);
                        int id = category.getInt("ID");
                        String name = category.getString("Name");
                        Pair<Integer, String> p = new Pair<>(id,name);
                        categories.add(p);
                    }
                    mPresenter.onRetrieve(categories);
                }

                catch (JSONException e) {
                    e.printStackTrace();
                    mPresenter.onFail("An error has occurred!");
                }
//                switch (response) {
//                    case "true":
//                        //operation done
//                        break;
//                    case "false":
//                        //query succeeded, but with no change
//                        break;
//                    default:
//                        //wrong query
//                        break;
//                }


            }
        };

        Response.ErrorListener onFail = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mPresenter.onFail("Connection Error");

            }
        };

        db.executeQuery(query, onSuccess, onFail);

    }
}
