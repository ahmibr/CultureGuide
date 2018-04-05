package com.ghorabaa.cultureguide.SignIn;

/**
 * Created by Ahmed Ibrahim on 3/16/18.
 */

import android.support.annotation.NonNull;
import android.util.Log;

import com.ghorabaa.cultureguide.Authenticator;
import com.ghorabaa.cultureguide.UserType;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;


public class SignInPresenter {

    private Authenticator mAuth; //reference to authentication module.
    private SignInContract.SignInView mView; //reference to view
    private final static String TAG = "LoginModule"; //Tag for log


    public SignInPresenter(SignInContract.SignInView view)
    {
        mView = view;

       mAuth = new Authenticator(this);
    }


    public void signIn(String email,String password){

      mAuth.signIn(email, password);


    }

    public void onSignInSuccess(UserType userType){
        mView.onSignInSuccess();
    }

    public void onSignInFail(){
        mView.onSignInFail("Email and Password doesn't match");

    }


    public void ha(){};
}
