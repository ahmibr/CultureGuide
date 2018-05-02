package com.ghorabaa.cultureguide.EditProfile;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by Ahmed Ibrahim on 4/21/18.
 */

public class EditUserModel extends EditProfileModel {

    public EditUserModel(EditUserPresenter presenter, Context context) {
        super(presenter,context);
        tableName = "AppUser";
    }

    public void getInterests(){

    }
    public void addInterest(int index){

    }
}
