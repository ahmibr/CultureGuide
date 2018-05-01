package com.ghorabaa.cultureguide.AdminHomepage;

import android.content.Context;

/**
 * Created by megem on 4/30/18.
 */

public class AdminHomepagePresenter implements AdminHomepageContract.Presenter{

    private AdminHomepageContract.View mView;
    private AdminHomepageModel mModel;

    AdminHomepagePresenter(AdminHomepageContract.View view, Context context){
        mView = view;

        mModel = new AdminHomepageModel(this,context);
    }
    

}
