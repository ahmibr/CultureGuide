package com.ghorabaa.cultureguide.SignIn;

/**
 * Created by Ahmed Ibrahim on 3/16/18.
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.ghorabaa.cultureguide.UserType;


public class SignInPresenter implements SignInContract.SignInPresenter{

    private SignInModel mModel;
    private SignInContract.SignInView mView; //reference to view
    private final static String TAG = "LoginModule"; //Tag for log


    public SignInPresenter(SignInContract.SignInView view, Context context)
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
