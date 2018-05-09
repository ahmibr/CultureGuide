package com.ghorabaa.cultureguide.EditProfile;

import android.content.Context;

/**
 * Created by Ahmed Ibrahim on 4/21/18.
 */

public class EditUserModel extends EditProfileBaseModel {

    /**
     * Constructor of SignUp Model
     * @param presenter The presenter attached to the model, to handle callbacks
     * @param context Application context to sync with
     */
    public EditUserModel(EditUserPresenter presenter, Context context) {
        super(presenter,context,"AppUser");
    }

}
