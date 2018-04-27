package com.ghorabaa.cultureguide.SignUp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ghorabaa.cultureguide.UserType;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.ghorabaa.cultureguide.Utilities.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Ahmed Ibrahim on 3/20/18.
 */

public class SignUpModel {

    //Reference to sign up presenter
    private SignUpPresenter mPresenter; //handles callbacks

    //Reference to database connection
    private DBConnection db;

    //Tag for Log(Debugging)
    final static private String TAG = "SignUpModel";

    /**
     * Constructor of SignUp Model
     * @param presenter The presenter attached to the model, to handle callbacks
     *
     */
    public SignUpModel(SignUpPresenter presenter, Context context){
        mPresenter = presenter;
        db = DBConnection.getInstance(context);
    }

    /**
     * Registers new user in authentication web service and database
     * @param name name of the registered user
     * @param email email of the registered user
     * @param password password of the registered user
     * @param type type of user (Regular,Organization,Admin)
     * @see UserType Enum
     *
     * @callback presenter.onSuccessRegister: In case of Success
     * @callback presenter.onFailRegister: In case of Failure, with Error message
     * @return none
     */
    public void register(final String name, final String email, final String password, final UserType type){

         //TODO check email form

        if(password.length()<6)
        {
            mPresenter.onSignUpFail("Password should be at least 6 characters!");
            return;
        }


        final Response.ErrorListener onFail = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mPresenter.onSignUpFail("Connection error");
            }
        };

        Response.Listener<String> onCheckEmail = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray result = new JSONArray(response);
                    if(result.length()==0){

                        String pass = PasswordEncrypter.encrypt(password);
                        String query = "INSERT INTO USERS(EMAIL,PASSWORD,TYPE) VALUES ('" +
                                email+"','"+
                                pass+"','"+
                                type.toString()+"')";


                        Response.Listener<String> dbInsertion = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                switch(type) {
                                    case Regular:
                                        registerRegular(name);
                                        break;

                                    case Organization:
                                        registerOrganization(name);
                                        break;

                                    default:
                                        break;
                                }
                            }
                        };
                        db.executeQuery(query,dbInsertion,onFail);
                    }
                    else
                    {
                        mPresenter.onSignUpFail("This email is already registered!");
                    }
                } catch (JSONException e) {
                    mPresenter.onSignUpFail("An error has occurred");
                }
            }
        };

        String checkEmailQuery = "SELECT * FROM USERS WHERE EMAIL = '"+email+"'";
        db.executeQuery(checkEmailQuery,onCheckEmail,onFail);


    }


    /**
     * Saves new Regular user info into the database
     * @param name  name of the user
     *
     * @return none
     */
    private void registerRegular(final String name){
        String query = "INSERT INTO AppUser(NAME) VALUES('"+name+"')";
        db.executeQuery(query);
        mPresenter.onSignUpSuccess();
    }

    /**
     * Saves new Organization user info into the database
     * @param name  name of the user
     *
     * @return none
     */
    private void registerOrganization(final String name){
        String query = "INSERT INTO Organization(NAME) VALUES('"+name+"')";
        db.executeQuery(query);
        mPresenter.onSignUpSuccess();
    }

}
