package com.ghorabaa.cultureguide.EditProfile;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by Ahmed Ibrahim on 4/21/18.
 */

public class EditUserModel extends EditProfileModel {

    public EditUserModel(EditUserPresenter presenter) {
        super(presenter);
        db = FirebaseDatabase.getInstance().getReference("user");
    }

    public void updateInterest(ArrayList<String> interests){


        Task<Void> addingTask = db.child("0").child("interests").setValue(interests);
        addingTask.addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                mPresenter.onSuccess("Interests updated successfully!");
            }
        });
    }
}
