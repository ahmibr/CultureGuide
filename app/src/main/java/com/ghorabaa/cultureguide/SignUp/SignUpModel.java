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

import java.util.Locale;

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

    final Response.ErrorListener onFail = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            mPresenter.onSignUpFail("Connection error");
        }
    };

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

        if(!EmailValidator.validate(email)){
            mPresenter.onSignUpFail("Please enter valid email form!");
            return;
        }
        if(password.length()<6)
        {
            mPresenter.onSignUpFail("Password should be at least 6 characters!");
            return;
        }




        Response.Listener<String> onCheckEmail = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                switch (response){
                    case "true":
                        insertByType(name,email,type);
                        break;
                    case "false":
                        mPresenter.onSignUpFail("This email is already registered!");
                        break;
                    default:
                        mPresenter.onSignUpFail("An error has occurred!");
                        break;
                }
            }
        };

        String query = "INSERT INTO Users VALUES ('%s','%s','%s')";
        query = String.format(Locale.ENGLISH,query,email, PasswordEncrypter.encrypt(password),type.toString());

        db.executeQuery(query,onCheckEmail,onFail);


    }

    private void insertByType(String name,String email,UserType type) {
        switch (type) {
            case Regular:
                registerRegular(name, email);
                break;

            case Organization:
                registerOrganization(name, email);
                break;

            default:
                break;
        }
    }


    /**
     * Saves new Regular user info into the database
     * @param name  name of the user
     *
     * @return none
     */
    private void registerRegular(final String name,final String email){
        String query = "INSERT INTO AppUser(Name,Email) VALUES('%s','%s')";
        query = String.format(Locale.ENGLISH,query,name,email);
        db.executeQuery(query);

        String getInfo = "SELECT * FROM AppUser WHERE Email = '%s'";
        getInfo = String.format(getInfo,email);
        Log.d("Model",getInfo);
        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(response,response);
                try {
                    JSONArray result = new JSONArray(response);

                    String name = result.getJSONObject(0).getString("Name");
                    int id = result.getJSONObject(0).getInt("ID");
                    String email = result.getJSONObject(0).getString("Email");

                    cacheUserData(id,name,email);
                    mPresenter.onSignUpSuccess(UserType.Regular);
                } catch (JSONException e) {
                    mPresenter.onSignUpFail("Error");
                    e.printStackTrace();
                }
            }
        };

        db.executeQuery(getInfo, onSuccess,onFail);

    }
    /**
     * Saves new Organization user info into the database
     * @param name  name of the user
     *
     * @return none
     */
    private void registerOrganization(final String name,final String email){
        String query = "INSERT INTO Organization(Name,Email) VALUES('%s','%s')";
        query = String.format(Locale.ENGLISH,query,name,email);
        db.executeQuery(query);

        String getInfo = "SELECT * FROM Organization WHERE Email = '%s'";
        getInfo = String.format(getInfo,email);

        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray result = new JSONArray(response);

                    String name = result.getJSONObject(0).getString("Name");
                    int id = result.getJSONObject(0).getInt("ID");
                    String email = result.getJSONObject(0).getString("Email");
                    cacheUserData(id,name,email);
                    mPresenter.onSignUpSuccess(UserType.Organization);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        db.executeQuery(getInfo, onSuccess, onFail);

    }

    private void cacheUserData(final int id,final String name,final String email){
        Authenticator.setEmail(email);
        Authenticator.setID(id);
        Authenticator.setName(name);
        Authenticator.setLoggedIn(true);
    }

}
