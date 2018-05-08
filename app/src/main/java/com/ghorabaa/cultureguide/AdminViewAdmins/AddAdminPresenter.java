package com.ghorabaa.cultureguide.AdminViewAdmins;

import android.content.Context;

public class AddAdminPresenter implements AddAdminContract.Presenter{

    private AddAdminContract.View mView;
    private AddAdminModel mModel;

    public AddAdminPresenter(AddAdminContract.View view, Context context) {

        mView = view;
        mModel = new AddAdminModel(this, context);
    }

    @Override
    public void onInsertion() {

        mView.onSuccess();
    }

    @Override
    public void onFail(String errorMesage) {

        mView.onFail(errorMesage);
    }

    @Override
    public void addAdmin(String email, String password) {

        mModel.addAdmin(email, password);
    }
}
