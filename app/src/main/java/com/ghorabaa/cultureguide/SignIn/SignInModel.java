package com.ghorabaa.cultureguide.SignIn;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ghorabaa.cultureguide.UserType;
import com.ghorabaa.cultureguide.Utilities.Authenticator;
import com.ghorabaa.cultureguide.Utilities.DBConnection;
import com.ghorabaa.cultureguide.Utilities.EmailValidator;
import com.ghorabaa.cultureguide.Utilities.PasswordEncrypter;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by Ahmed Ibrahim on 4/27/18.
 */

public class SignInModel {

    //Reference to sign in presenter
    private SignInPresenter mPresenter; //handles callbacks

    //Reference to database connection
    private DBConnection db;

    //Tag for Log(Debugging)
    final static private String TAG = "SignInModel";

    /**
     * Constructor of SignIn Model
     * @param presenter The presenter attached to the model, to handle callbacks
     *
     */
    public SignInModel(SignInPresenter presenter, Context context){
        mPresenter = presenter;
        db = DBConnection.getInstance(context);
    }

    /**
     * Sign in user with given email and password
     * @param email user email
     * @param password user password
     */
    public void SignIn(final String email, String password){

        if(!EmailValidator.validate(email)){
            mPresenter.onSignInFail("Please enter valid email form!");
            return;
        }

        //hash the password and login with it
        String query = "SELECT * FROM Users WHERE Email = '"+email+"' AND Password = '"
                + PasswordEncrypter.encrypt(password)+"'";

        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray result = new JSONArray(response);

                    //this email is not registered before
                    if(result.length()!=0){
                        String type = result.getJSONObject(0).getString("Type");
                        switch (type){
                            case "Regular":
                                routeRegular(email);
                                break;
                            case "Organization":
                                routeOrganization(email);
                                break;
                            case "Admin":
                                routeAdmin(email);
                                break;
                            default:
                                break;
                        }
                    }

                    else
                        mPresenter.onSignInFail("Wrong Email or Wrong Password!");
                } catch (JSONException e) {
                    mPresenter.onSignInFail("An error has occurred");

                }

            }
        };

        Response.ErrorListener onFail = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mPresenter.onSignInFail("Connection Error");
            }
        };

        db.executeQuery(query,onSuccess,onFail);
    }


    /**
     * Helper function to insert regular user in it's table
     * @param email email of the user
     */
    private void routeRegular(String email){
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
                    mPresenter.onSignInSuccess(UserType.Regular);

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
     * Helper function to insert organization user in it's table
     * @param email email of the user
     */
    private void routeOrganization(String email){
        String getInfo = "SELECT * FROM Organization WHERE Email = '"+email+"'";
        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray result = new JSONArray(response);

                    String name = result.getJSONObject(0).getString("Name");
                    int id = result.getJSONObject(0).getInt("ID");
                    String email = result.getJSONObject(0).getString("Email");
                    cacheUserData(id,name,email);
                    mPresenter.onSignInSuccess(UserType.Organization);

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
     * Helper function to insert admin user in it's table
     * @param email email of the user
     */
    private void routeAdmin(String email){
        Authenticator.setEmail(email);
        mPresenter.onSignInSuccess(UserType.Admin);
    }

    /**
     * Helper function to cache user info to user it in the app
     * @param id user's id in database
     * @param name user's name
     * @param email user's email
     */
    private void cacheUserData(final int id,final String name,final String email){
        Authenticator.setEmail(email);
        Authenticator.setID(id);
        Authenticator.setName(name);
        Authenticator.setLoggedIn(true);
    }
}
