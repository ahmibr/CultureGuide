package com.ghorabaa.cultureguide.AdminViewUser;

import com.ghorabaa.cultureguide.AdminViewOrganization.AdminViewOrganizationContract;

/**
 * Created by megem on 5/1/2018.
 */

public class AdminViewUserPresenter implements AdminViewUserContract.Presenter {

    AdminViewUserContract.View mView;
    AdminViewUserModel mModel;

    @Override
    public void retrieveUsers() {

        mModel.getUsers();
    }
}
