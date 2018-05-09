package com.ghorabaa.cultureguide.AdminViewEvent;

import android.content.Context;
import android.util.Pair;

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

        mModel.getEvents();
    }

    @Override
    public void onRetrieve(ArrayList<Pair<Integer, String>> events) {

        mView.onRetrieve(events);
    }

    @Override
    public void onFail(String errorMessage) {

        mView.onFail(errorMessage);
    }

    @Override
    public void retrieveEvent(int id) {

        mModel.getEvent(id);
    }

    @Override
    public void removeEvent(Integer id) {

        mModel.removeEvent(id);
    }

    public void onSuccess() {

        mView.onSuccess();
    }
}
