package com.ghorabaa.cultureguide.AdminViewCategory;

import android.content.Context;

public class AddCategoryPresenter implements AddCategoryContract.Presenter{

    AddCategoryContract.View mView;
    AddCategoryModel mModel;

    public AddCategoryPresenter(AddCategoryContract.View view, Context context) {


        mView = view;
        mModel = new AddCategoryModel(this, context);
    }

    @Override
    public void onInsertion() {

        mView.onSuccess();
    }

    @Override
    public void onFail(String errorMessage) {

        mView.onFail(errorMessage);
    }

    @Override
    public void addCategory(String name) {

        mModel.addCategory(name);
    }
}
