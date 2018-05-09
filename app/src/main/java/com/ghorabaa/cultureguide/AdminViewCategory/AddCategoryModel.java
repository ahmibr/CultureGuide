package com.ghorabaa.cultureguide.AdminViewCategory;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ghorabaa.cultureguide.Utilities.DBConnection;
import com.ghorabaa.cultureguide.Utilities.SQLInjectionEscaper;

import java.util.Locale;

public class AddCategoryModel {
    private Context mContext;
    private DBConnection db;
    private AddCategoryPresenter mPresenter;

    public AddCategoryModel(AddCategoryPresenter presenter, Context context) {

        mContext = context;
        mPresenter = presenter;
        db = DBConnection.getInstance(mContext);
    }

    public void addCategory(String name)

    {
        name = SQLInjectionEscaper.escapeString(name);
        String query = "INSERT INTO Category(Name) VALUES('%s')";
        query = String.format(Locale.ENGLISH,query, name);

        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                switch (response) {

                    case "true":
                        mPresenter.onInsertion();
                        break;

                    case "false":
                        mPresenter.onFail("Database Not Affected");
                        break;

                    default:
                        mPresenter.onFail("An error has occurred");
                        break;
                }
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
