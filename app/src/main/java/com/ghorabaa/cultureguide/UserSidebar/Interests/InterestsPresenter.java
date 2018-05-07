package com.ghorabaa.cultureguide.UserSidebar.Interests;

import android.content.Context;
import android.util.Pair;

import java.util.ArrayList;

/**
 * Created by Ahmed Ibrahim on 5/7/18.
 */

public class InterestsPresenter implements InterestsContract.Presenter {

    private InterestsContract.View mView;
    private InterestsModel mModel;

    public InterestsPresenter(InterestsContract.View view, Context context){
        mView = view;
        mModel = new InterestsModel(this,context);
    }

    @Override
    public void retrieveInterests() {
        mModel.retrieveInterests();
    }

    @Override
    public void addInterest(int index) {
        mModel.addInterests(index);
    }

    @Override
    public void removeInterest(int index) {
        mModel.removeInterest(index);
    }

    void onRetrieveInterests(ArrayList<Pair<String,Boolean>> interestsList){
        mView.onRetrieveInterests(interestsList);
    }

    void onRetrieveInterestsFail(String errorMessage){
        mView.onRetrieveInterestsFail(errorMessage);
    }

    void onAddSuccess(){
        mView.onAddSuccess();
    }

    void onAddFail(String errorMessage){
        mView.onAddFail(errorMessage);
    }

    void onRemoveSuccess(){
        mView.onRemoveSuccess();
    }

    void onRemoveFail(String errorMessage){
        mView.onRemoveFail(errorMessage);
    }
}
