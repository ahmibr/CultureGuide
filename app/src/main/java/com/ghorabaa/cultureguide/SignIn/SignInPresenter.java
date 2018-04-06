package com.ghorabaa.cultureguide.SignIn;

/**
 * Created by Ahmed Ibrahim on 3/16/18.
 */

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class SignInPresenter {

    private FirebaseAuth mAuth; //reference to authentication module in firebase.
    private SignInContract.SignInView mView; //reference to view
    private final static String TAG = "LoginModule"; //Tag for log


    public SignInPresenter(SignInContract.SignInView view)
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
                    mView.onSignInSuccess();
                    Log.d(TAG, "signInWithEmail:Done");
                }
                else
                {
                    mView.onSignInFail();
                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                }
            }
        });
    }

}
