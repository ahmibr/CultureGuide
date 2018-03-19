package com.ghorabaa.cultureguide.Login;

/**
 * Created by Ahmed Ibrahim on 3/16/18.
 */

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


class InvalidLogin extends Exception {
    InvalidLogin(String errorMessage) {
        super(errorMessage);
    }
}

enum UserType{
    Regular,Organization,Admin;
}

public class LoginPresenter {

    private FirebaseAuth mAuth; //reference to authentication module in firebase.
    private LoginContract.LoginView mView; //reference to view
    private final static String TAG = "LoginModule"; //Tag for log


    public LoginPresenter(LoginContract.LoginView view)
    {
        mView = view;
        mAuth = FirebaseAuth.getInstance();
    }


    public void signIn(String email,String password){

        //Listener on Authentication process
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    mView.onLoginSuccess();
                    Log.d(TAG, "signInWithEmail:Done");
                }
                else
                {
                    mView.onLoginFail();
                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                }
            }
        });
    }

    public void signOut(){
        mAuth.signOut();
    }
}
