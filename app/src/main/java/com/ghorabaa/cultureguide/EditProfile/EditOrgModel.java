package com.ghorabaa.cultureguide.EditProfile;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Ahmed Ibrahim on 4/21/18.
 */

public class EditOrgModel extends EditProfileModel {

    public EditOrgModel(EditOrgPresenter presenter) {
        super(presenter);
        db = db = FirebaseDatabase.getInstance().getReference("organization");
    }

}
