package com.ghorabaa.cultureguide;

/**
 * Created by Ahmed Ibrahim on 3/16/18.
 */

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


class InvalidLogin extends Exception {
    InvalidLogin(String errorMessage) {
        super(errorMessage);
    }
}

enum UserType{
    Regular,Organization,Admin;
}

public class LoginManager {

    private FirebaseAuth mAuth; //reference to authentication module in firebase
    private final static String TAG = "Login Module"; //Tag for log

    public   LoginManager(){
        mAuth = FirebaseAuth.getInstance();
    }

    public UserType login(String email,String password)throws InvalidLogin{

        mAuth.signInWithEmailAndPassword(email, password);

        FirebaseUser user = mAuth.getCurrentUser();

        if(user!=null){

            //ToDo (1) return user type from database
            return UserType.Regular;
        }
        else {
            //ToDo (2) add new Exception if email not found
            throw new InvalidLogin("Email and Password doesn't match");
        }

    }

    public void signOut(){
        mAuth.signOut();
    }
}
