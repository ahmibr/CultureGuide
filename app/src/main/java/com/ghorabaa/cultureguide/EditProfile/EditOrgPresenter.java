package com.ghorabaa.cultureguide.EditProfile;

/**
 * Created by Ahmed Ibrahim on 4/6/18.
 */

public class EditOrgPresenter extends EditProfilePresenter{

    public EditOrgPresenter(EditProfileContract.EditProfileView view){
        super(view);

        mModel = new EditOrgModel(this);
    }


}
