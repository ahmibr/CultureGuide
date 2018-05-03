package com.ghorabaa.cultureguide.EditProfile;

import android.content.Context;

/**
 * Created by Ahmed Ibrahim on 4/6/18.
 */

public class EditOrgPresenter extends EditProfilePresenter{

    public EditOrgPresenter(EditProfileContract.EditProfileView view, Context context){
        super(view);

        mModel = new EditOrgModel(this,context);
    }


}
