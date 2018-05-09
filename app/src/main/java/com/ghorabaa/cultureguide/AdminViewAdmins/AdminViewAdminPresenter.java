package com.ghorabaa.cultureguide.AdminViewAdmins;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by megem on 5/1/2018.
 */

public class AdminViewAdminPresenter implements AdminViewAdminContract.Presenter {

    private AdminViewAdminContract.View mView;
    private AdminViewAdminModel mModel;

    public AdminViewAdminPresenter(AdminViewAdminContract.View view, Context context) {

        mView = view;
        mModel = new AdminViewAdminModel(this, context);
    }

    @Override
    public void retrieveAdmins() {
        mModel.getAdmins();
    }

    public void onRetrieve(ArrayList<String> admins){
        mView.onRetrieve(admins);
    }

    @Override
    public void onFail(String errorMessage) {
        mView.onFail(errorMessage);
    }

    @Override
    public void retrieveAdmin(String email) {

        mModel.getAdmin(email);
    }

    @Override
    public void removeAdmin(String email) {

        mModel.removeAdmin(email);
    }

    public void onSuccess() {

        mView.onSuccess();
    }
}
