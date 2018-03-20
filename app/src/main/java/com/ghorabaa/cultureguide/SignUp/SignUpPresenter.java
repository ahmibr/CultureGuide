package com.ghorabaa.cultureguide.SignUp;

/**
 * Created by Ahmed Ibrahim on 3/16/18.
 */

import android.support.annotation.NonNull;
import android.util.Log;

import com.ghorabaa.cultureguide.*;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignUpPresenter {

    private FirebaseAuth mAuth; //reference to authentication module in firebase.
    private SignUpContract.SignUpView mView; //reference to view
    private SignUpModel mModel;

    private final static String TAG = "SignUpModule"; //Tag for log


    public SignUpPresenter(SignUpContract.SignUpView view)
    {
        mView = view;
        mAuth = FirebaseAuth.getInstance();
        mModel = new SignUpModel(this);
    }

    void signUp(final String name,final String email, String password,final UserType type){


        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");

                            mModel.register(new User(mAuth.getCurrentUser().getUid(),name,email,type));


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());

                            mView.onSignUpFail();
                        }

                        // ...
                    }
                });



    }

    public void onCompleteRegister(){
        mView.onSignUpSuccess();
    }

}
