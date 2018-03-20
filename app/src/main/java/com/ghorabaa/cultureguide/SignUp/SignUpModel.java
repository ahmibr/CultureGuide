package com.ghorabaa.cultureguide.SignUp;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.ghorabaa.cultureguide.*;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Ahmed Ibrahim on 3/20/18.
 */

public class SignUpModel {

    private SignUpPresenter mPresenter;
    private DatabaseReference mRef;

    SignUpModel(SignUpPresenter presenter){
        mPresenter = presenter;
        mRef = FirebaseDatabase.getInstance().getReference();
    }

    public void register(User user){
        switch(user.getType()) {
            case Regular:
                registerRegular(user);
                break;

            case Organization:
                registerOrganization(user);
                break;

            case Admin:
                registerAdmin(user);
                break;

            default:
                return;
        }
    }


    public void registerRegular(User user){

        mRef.child("user").child(user.getId()).child("email").setValue(user.getEmail());
        mRef.child("user").child(user.getId()).child("name").setValue(user.getName());

        mPresenter.onCompleteRegister();

    }

    public void registerOrganization(User user){

        mRef.child("organization").child(user.getId()).child("email").setValue(user.getEmail());
        mRef.child("organization").child(user.getId()).child("name").setValue(user.getName());

        mPresenter.onCompleteRegister();
    }

    public void registerAdmin(User user){

        mRef.child("Admin").child(user.getId()).child("email").setValue(user.getEmail());
        mRef.child("Admin").child(user.getId()).child("name").setValue(user.getName());
        mRef.child("Admin").child(user.getId()).child("role").setValue("admin");

        mPresenter.onCompleteRegister();
    }
}
