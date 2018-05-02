package com.ghorabaa.cultureguide.EditProfile;

import android.content.Context;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Ahmed Ibrahim on 4/21/18.
 */

public class EditOrgModel extends EditProfileModel {

    public EditOrgModel(EditOrgPresenter presenter, Context context) {
        super(presenter,context);
        tableName = "Organization";
    }

}
