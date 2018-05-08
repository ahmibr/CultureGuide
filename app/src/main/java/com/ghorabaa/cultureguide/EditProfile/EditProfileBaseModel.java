package com.ghorabaa.cultureguide.EditProfile;

/**
 * Created by Ahmed Ibrahim on 4/5/18.
 */

import android.content.Context;
import android.support.annotation.NonNull;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ghorabaa.cultureguide.Utilities.Authenticator;
import com.ghorabaa.cultureguide.Utilities.DBConnection;
import com.ghorabaa.cultureguide.Utilities.EmailValidator;
import com.ghorabaa.cultureguide.Utilities.PasswordEncrypter;
import com.ghorabaa.cultureguide.Utilities.SQLInjectionEscaper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;

abstract class EditProfileBaseModel {


    protected EditProfilePresenter mPresenter;

    protected String tableName;

    protected Context mContext;

    protected DBConnection db;

    public EditProfileBaseModel(EditProfilePresenter presenter, Context context, String tableName){

        mPresenter = presenter;

        mContext = context;

        db = DBConnection.getInstance(context);

        this.tableName = tableName;
    }

    public void changeEmail(final String newEmail){

        if(!EmailValidator.validate(newEmail)){
            mPresenter.onFail("Please enter valid email form!");
            return;
        }
        String query = "UPDATE Users SET Email = '%s' WHERE Email = '%s'";
        query = String.format(Locale.ENGLISH,query,newEmail, Authenticator.getEmail());

        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("true"))
                {
                    mPresenter.onSuccess("Email changed successfully");
                    Authenticator.setEmail(newEmail);
                }
                else if(response.equals("false"))
                    mPresenter.onFail("This email is already registered!");
                else
                    mPresenter.onFail("An error has occurred!");
            }
        };

        Response.ErrorListener onFail = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mPresenter.onFail("Connection Error!");
            }
        };

        db.executeQuery(query,onSuccess,onFail);
    }

    public void changeName(final String newName){
        String escapedNewName = SQLInjectionEscaper.escapeString(newName);
        String query = "UPDATE %s SET Name = '%s' WHERE ID = %d";
        query = String.format(Locale.ENGLISH,query,tableName,escapedNewName, Authenticator.getID());

        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("true"))
                {
                    mPresenter.onSuccess("Name changed successfully");
                    Authenticator.setName(newName);
                }
                else
                    mPresenter.onFail("An error has occurred!");
            }
        };

        Response.ErrorListener onFail = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mPresenter.onFail("Connection Error!");
            }
        };

        db.executeQuery(query,onSuccess,onFail);

    }

    public void changePassword(final String newPassword){

        if(newPassword.length()<6){
            mPresenter.onFail("Password should be at least 6 characters!");
            return;
        }
        String query = "UPDATE Users SET Password = '%s' WHERE Email = '%s'";
        query = String.format(Locale.ENGLISH,query, PasswordEncrypter.encrypt(newPassword), Authenticator.getEmail());

        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("true"))
                    mPresenter.onSuccess("Password changed successfully");
                else
                    mPresenter.onFail("An error has occurred!");
            }
        };

        Response.ErrorListener onFail = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mPresenter.onFail("Connection Error!");
            }
        };

        db.executeQuery(query,onSuccess,onFail);
    }

}
