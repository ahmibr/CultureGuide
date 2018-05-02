package com.ghorabaa.cultureguide.AdminViewEvent;

import android.content.Context;
import android.util.Pair;

import com.ghorabaa.cultureguide.AdminViewOrganization.AdminViewOrganizationModel;

import java.util.ArrayList;

/**
 * Created by megem on 5/1/2018.
 */

public class AdminViewEventPresenter implements AdminViewEventContract.Presenter {

    private AdminViewEventContract.View mView;
    private AdminViewEventModel mModel;

    public AdminViewEventPresenter(AdminViewEventContract.View view, Context context) {

        mView = view;
        mModel = new AdminViewEventModel(this, context);
    }

    @Override
    public void retrieveEvents() {

        mModel.retrieveEvents();
    }

    @Override
    public void onRetrieve(ArrayList<Pair<Integer, String>> events) {

        mView.onRetrieve(events);
    }

    @Override
    public void onFail(String errorMessage) {

        mView.onFail(errorMessage);
    }
}
