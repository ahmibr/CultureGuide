package com.ghorabaa.cultureguide.EditProfile;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Ahmed Ibrahim on 4/21/18.
 */

public class EditUserPresenter extends EditProfilePresenter {


    public EditUserPresenter(EditProfileContract.EditProfileView view){
        super(view);

        mModel = new EditUserModel(this);
    }

    public void updateInterests(ArrayList<String> interests){
        ((EditUserModel)mModel).updateInterest(interests);
    }

}
