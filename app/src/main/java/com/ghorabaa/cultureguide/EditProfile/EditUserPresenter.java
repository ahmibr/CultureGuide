package com.ghorabaa.cultureguide.EditProfile;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Ahmed Ibrahim on 4/21/18.
 */

public class EditUserPresenter extends EditProfilePresenter {

    /**
     * Constructor of SignUp Model
     * @param view The view attached to the model, to handle callbacks
     * @param context Application context to sync with
     */
    public EditUserPresenter(EditProfileContract.EditProfileView view,Context context){
        super(view);

        mModel = new EditUserModel(this,context);
    }

}
