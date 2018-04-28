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


    public SignInPresenter(SignInContract.View view, Context context)
    {
        mView = view;

        mModel = new SignInModel(this,context);
    }


    public void signIn(String email,String password){
        mModel.SignIn(email,password);
    }

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

    public void onSignInFail(String errorMessage){
        mView.onSignInFail(errorMessage);
    }

}
