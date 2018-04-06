package com.ghorabaa.cultureguide.EditProfile;

/**
 * Created by Ahmed Ibrahim on 4/5/18.
 */

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditOrganizationModel {

    public EditOrganizationModel(){

    }

    public void changeEmail(final String newEmail){
        final FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();
        if(mUser==null)return;
        mUser.updateEmail(newEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                DatabaseReference db = FirebaseDatabase.getInstance().getReference("organization");
                db.child(mUser.getUid()).child("email").setValue(newEmail);
            }
        });
    }

    public void changeName(final String newName){
        final FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();
        if(mUser==null)return;
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("organization");
        db.child(mUser.getUid()).child("name").setValue(newName);
    }

    public void changePassword(final String newPassword){
        final FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();
        if(mUser==null)return;
        mUser.updatePassword(newPassword).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
               //TODO Add callback
            }
        });
    }

}
