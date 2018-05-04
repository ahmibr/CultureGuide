package com.ghorabaa.cultureguide.AdminViewOrganization;

import android.content.Context;
import android.util.Pair;

import java.util.ArrayList;

/**
 * Created by megem on 5/1/2018.
 */

public class AdminViewOrganizationPresenter implements AdminViewOrganizationContract.Presenter {

    private AdminViewOrganizationModel mModel;
    private AdminViewOrganizationContract.View mView;

    public AdminViewOrganizationPresenter(AdminViewOrganizationContract.View view, Context context) {

        mView = view;
        mModel = new AdminViewOrganizationModel(this, context);
    }

    @Override
    public void retrieveOrganizations() {

        mModel.getOrganizations();
    }

    public void onRetrieve(ArrayList<Pair<String, String>> organizations) {

        mView.onRetrieve(organizations);
    }

    @Override
    public void onFail(String errorMessage) {

        mView.onFail(errorMessage);
    }

    @Override
    public void retrieveOrganization(String email) {
        mModel.getOrganization(email);
    }
}
