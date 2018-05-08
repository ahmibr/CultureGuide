package com.ghorabaa.cultureguide.SignIn;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ghorabaa.cultureguide.UserType;
import com.ghorabaa.cultureguide.Utilities.Authenticator;
import com.ghorabaa.cultureguide.Utilities.DBConnection;
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

    public void SignIn(final String email, String password){
        String query = "SELECT * FROM Users WHERE Email = '"+email+"' AND Password = '"
                + PasswordEncrypter.encrypt(password)+"'";

        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray result = new JSONArray(response);
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

    private void routeAdmin(String email){
        Authenticator.setEmail(email);
        mPresenter.onSignInSuccess(UserType.Admin);
    }
    private void cacheUserData(final int id,final String name,final String email){
        Authenticator.setEmail(email);
        Authenticator.setID(id);
        Authenticator.setName(name);
        Authenticator.setLoggedIn(true);
    }
}
