package com.ghorabaa.cultureguide.SignIn;

/**
 * Created by Ahmed Ibrahim on 3/16/18.
 */

import android.content.Context;

import com.ghorabaa.cultureguide.UserType;


public class SignInPresenter implements SignInContract.Presenter {

    private SignInModel mModel;
    private SignInContract.View mView; //reference to view
    private final static String TAG = "LoginModule"; //Tag for log

    /**
     * Constructor of SignIn Presenter
     * @param view The view attached to the presenter, to send updates
     * @param context Application context to sync with
     */
    public SignInPresenter(SignInContract.View view, Context context)
    {
        mView = view;

        mModel = new SignInModel(this,context);
    }

    /**
     * Asks the model to check user's credentials
     * @param email user's email
     * @param password  user's password
     */
    public void signIn(String email,String password){
        mModel.SignIn(email,password);
    }

    /**
     * Call back from model in case sign in success
     * Routes view to the appropriate homepage
     * @param type which user type has logged in
     */
    public void onSignInSuccess(UserType type){
        mView.onSignInSuccess();
        switch (type){
            case Regular:
                mView.routeRegular();
                break;
            case Organization:
                mView.routeOrganization();
                break;
            case Admin:
                mView.routeAdmin();
                break;
            default:
                break;
        }
    }

    /**
     * Call back from model in case sign in fail
     * Send the view the error message
     * @param errorMessage
     */
    public void onSignInFail(String errorMessage){
        mView.onSignInFail(errorMessage);
    }

}
