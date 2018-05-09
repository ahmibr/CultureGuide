package com.ghorabaa.cultureguide.AdminViewAdmins;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ghorabaa.cultureguide.Utilities.DBConnection;
import com.ghorabaa.cultureguide.Utilities.EmailValidator;
import com.ghorabaa.cultureguide.Utilities.PasswordEncrypter;
import com.ghorabaa.cultureguide.Utilities.SQLInjectionEscaper;

import java.util.Locale;

public class AddAdminModel {

    private Context mContext;
    private DBConnection db;
    private AddAdminContract.Presenter mPresenter;

    public AddAdminModel(AddAdminPresenter presenter, Context context) {

        mContext = context;
        mPresenter = presenter;
        db = DBConnection.getInstance(mContext);
    }


    public void addAdmin(String email, String password) {
        if(password.length()<6){
            mPresenter.onFail("Password should be at least 6 characters!");
            return;
        }
        if(!EmailValidator.validate(email)){
            mPresenter.onFail("Please enter valid email form!");
            return;


        }

        password = SQLInjectionEscaper.escapeString(password);
        String query = "INSERT INTO Users VALUES('%s', '%s', 'Admin')";
        query = String.format(Locale.ENGLISH,query, email, PasswordEncrypter.encrypt(password));

        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                switch (response) {

                    case "true":
                        mPresenter.onInsertion();
                        break;

                    case "false":
                        mPresenter.onFail("This email is already registered!");
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