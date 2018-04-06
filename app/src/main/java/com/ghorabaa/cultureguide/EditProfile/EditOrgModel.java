package com.ghorabaa.cultureguide.EditProfile;

/**
 * Created by Ahmed Ibrahim on 4/5/18.
 */

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditOrgModel {

    FirebaseAuth mAuth;

    EditOrgPresenter mPresenter;

    FirebaseUser mUser;

    public EditOrgModel(EditOrgPresenter presenter){

        mAuth = FirebaseAuth.getInstance();

        mUser = mAuth.getCurrentUser();

        mPresenter = presenter;


    }

    public void changeEmail(final String newEmail){

        if(mUser==null)
            return;

        mUser.updateEmail(newEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()) {
                    DatabaseReference db = FirebaseDatabase.getInstance().getReference("organization");
                    db.child(mUser.getUid()).child("email").setValue(newEmail);
                    mPresenter.onSuccess("Email Changed Successfully");
                }
                else
                {

                    //Invalid email format
                    if(task.getException() instanceof FirebaseAuthInvalidCredentialsException)
                        mPresenter.onFail("Invalid email format!");

                    //Email already used
                     else if(task.getException() instanceof FirebaseAuthUserCollisionException)
                        mPresenter.onFail("This email is already registered!");

                     else
                         mPresenter.onFail("Email Change Failed");
                }
            }
        });
    }

    public void changeName(final String newName){
        if(mUser==null)
            return;

        DatabaseReference db = FirebaseDatabase.getInstance().getReference("organization");
        db.child(mUser.getUid()).child("name").setValue(newName);
    }

    public void changePassword(final String newPassword){

        if(mUser==null)
            return;
        mUser.updatePassword(newPassword).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
               if(task.isSuccessful())
               {
                   mPresenter.onSuccess("Password Changed Successfully");
               }
               else
               {
                   //Weak Password (Less than 6 Characters)
                   if(task.getException() instanceof FirebaseAuthWeakPasswordException)
                       mPresenter.onFail("Password should be at least 6 characters!");
                   else
                       mPresenter.onFail("Change Password Failed");
               }
            }
        });
    }

}
