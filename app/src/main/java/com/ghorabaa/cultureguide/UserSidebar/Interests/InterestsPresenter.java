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

    /**
     * Constrictor of interests presenter
     * @param view The view attached to the presenter, to send updates
     * @param context Application context to sync with
     */
    public InterestsPresenter(InterestsContract.View view, Context context){
        mView = view;
        mModel = new InterestsModel(this,context);
    }

    /**
     * Asks model to retrieve interests
     */
    @Override
    public void retrieveInterests() {
        mModel.retrieveInterests();
    }

    /**
     * Asks model to add category in interests
     * @param index
     */
    @Override
    public void addInterest(int index) {
        mModel.addInterests(index);
    }

    /**
     * Asks model to remove category from interests
     * @param index
     */
    @Override
    public void removeInterest(int index) {
        mModel.removeInterest(index);
    }

    /**
     * Call back from model if retrieval success
     * @param interestsList First: Name Second: Subscribed
     */
    void onRetrieveInterests(ArrayList<Pair<String,Boolean>> interestsList){
        mView.onRetrieveInterests(interestsList);
    }

    /**
     * Call back from model if removing failed
     * @param errorMessage
     */
    void onRetrieveInterestsFail(String errorMessage){
        mView.onRetrieveInterestsFail(errorMessage);
    }

    /**
     * Call back from model if adding category success
     */
    void onAddSuccess(){
        mView.onAddSuccess();
    }

    /**
     * Call back from model if adding failed
     * @param errorMessage
     */
    void onAddFail(String errorMessage){
        mView.onAddFail(errorMessage);
    }

    /**
     * Call back from model if removing success
     */
    void onRemoveSuccess(){
        mView.onRemoveSuccess();
    }

    /**
     * Call back from model if removing failed
     * @param errorMessage
     */
    void onRemoveFail(String errorMessage){
        mView.onRemoveFail(errorMessage);
    }
}
