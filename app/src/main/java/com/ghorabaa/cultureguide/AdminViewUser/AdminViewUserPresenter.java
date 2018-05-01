package com.ghorabaa.cultureguide.AdminViewUser;

import android.content.Context;
import android.util.Pair;

import java.util.ArrayList;

/**
 * Created by megem on 5/1/2018.
 */

public class AdminViewUserPresenter implements AdminViewUserContract.Presenter {

    AdminViewUserContract.View mView;
    AdminViewUserModel mModel;

    public AdminViewUserPresenter(AdminViewUserActivity view, Context context) {

        mView = view;
        mModel = new AdminViewUserModel(this, context);
    }

    @Override
    public void retrieveUsers() {

        mModel.getUsers();
    }

    @Override
    public void onRetrieve(ArrayList<Pair<String, String>> users) {

        mView.onRetrieve(users);
    }

    @Override
    public void onFail(String errorMessage) {

        mView.onFail(errorMessage);

    }
}
