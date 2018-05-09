package com.ghorabaa.cultureguide.EditProfile;

import android.content.Context;

/**
 * Created by Ahmed Ibrahim on 4/6/18.
 */

public class EditOrgPresenter extends EditProfilePresenter{

    /**
     * Constructor of EditOrg Presenter
     * @param view The view attached to the presenter, to send updates
     * @param context Application context to sync with
     */
    EditOrgPresenter(EditProfileContract.EditProfileView view, Context context){
        super(view);

        mModel = new EditOrgModel(this,context);
    }


}
