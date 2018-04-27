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
                        String query = "INSERT INTO Users(Email,Password,Type) VALUES ('" +
                                email+"','"+
                                pass+"','"+
                                type.toString()+"')";

                        final Response.Listener<String> cacheUser = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject result = new JSONObject(response);

                                    String name = result.getString("Name");
                                    int id = result.getInt("ID");
                                    String email = result.getString("Email");

                                    cacheUserData(id,name,email);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        };

                        Response.Listener<String> dbInsertion = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d("Register",response);
                                if(response.equals("true")) {
                                    switch (type) {
                                        case Regular:
                                            registerRegular(name,email);
                                            break;

                                        case Organization:
                                            registerOrganization(name,email);
                                            break;

                                        default:
                                            break;
                                    }
                                }
                                else
                                    mPresenter.onSignUpFail("Sign Up Failed");
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

        String checkEmailQuery = "SELECT * FROM Users WHERE Email = '"+email+"'";
        db.executeQuery(checkEmailQuery,onCheckEmail,onFail);


    }


    /**
     * Saves new Regular user info into the database
     * @param name  name of the user
     *
     * @return none
     */
    private void registerRegular(final String name,final String email){
        String query = "INSERT INTO AppUser(Name,Email) VALUES('"+name+"','"+email+"')";
        db.executeQuery(query);
        String getInfo = "SELECT * FROM AppUser WHERE Email = '"+email+"'";
        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray result = new JSONArray(response);

                    String name = result.getJSONObject(0).getString("Name");
                    int id = result.getJSONObject(0).getInt("ID");
                    String email = result.getJSONObject(0).getString("Email");


                    cacheUserData(id,name,email);
                    mPresenter.onSignUpSuccess();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        db.executeQuery(getInfo, onSuccess, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });



    }

    /**
     * Saves new Organization user info into the database
     * @param name  name of the user
     *
     * @return none
     */
    private void registerOrganization(final String name,final String email){
        String query = "INSERT INTO Organization(Name,Email) VALUES('"+name+"','"+email+"')";
        db.executeQuery(query);
        String getInfo = "SELECT * FROM Organization WHERE Email = '"+email+"'";
        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray result = new JSONArray(response);

                    String name = result.getJSONObject(0).getString("Name");
                    int id = result.getJSONObject(0).getInt("ID");
                    String email = result.getJSONObject(0).getString("Email");

                    mPresenter.onSignUpSuccess();
                    cacheUserData(id,name,email);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        db.executeQuery(getInfo, onSuccess, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

    }

    private void cacheUserData(final int id,final String name,final String email){
        Authenticator.setEmail(email);
        Authenticator.setID(id);
        Authenticator.setName(name);
        Authenticator.setLoggedIn(true);
    }

}
