package com.ghorabaa.cultureguide.AdminViewCategory;

import android.content.Context;
import android.util.Pair;
import android.widget.Toast;

import com.ghorabaa.cultureguide.AdminHomepage.AdminHomepageModel;

import java.util.ArrayList;

/**
 * Created by Ahmed Ibrahim on 4/30/18.
 */

public class AdminViewCategoryPresenter implements AdminViewCategoryContract.Presenter{
    private AdminViewCategoryContract.View mView;
    private AdminViewCategoryModel mModel;

    AdminViewCategoryPresenter(AdminViewCategoryContract.View view, Context context){
        mView = view;

        mModel = new AdminViewCategoryModel(this,context);
    }

    public void retrieveCategories(){

        mModel.getAvailableCategories();
    }


    public void onRetrieve(ArrayList<Pair<Integer, String> > categoriesList){

        mView.onRetrieve(categoriesList);

    }

    @Override
    public void onFail(String errorMessage) {

        mView.onFail(errorMessage);
    }
}
