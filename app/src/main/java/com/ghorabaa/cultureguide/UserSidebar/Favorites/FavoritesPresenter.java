package com.ghorabaa.cultureguide.UserSidebar.Favorites;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Ahmed Ibrahim on 5/6/18.
 */

public class FavoritesPresenter implements FavoritesContract.Presenter{
    private FavoritesContract.View mView;
    private FavoritesModel mModel;

    /**
     * Favorites presenter constructor
     * @param view The view attached to the presenter, to send updates
     * @param context Application context to sync with
     */
    FavoritesPresenter(FavoritesContract.View view, Context context){
        mView = view;
        mModel = new FavoritesModel(this,context);
    }

    /**
     * Asks model to retrieves favourite list
     */
    @Override
    public void retrieveFavorites() {
        mModel.retrieveFavorites();
    }

    /**
     * Asks model to remove organization from favourites
     * @param index index of organization in favourites
     */
    @Override
    public void removeFavorite(int index) {
        mModel.removeFavorite(index);
    }

    /**
     * Call back from model in retrieval of favourites
     * @param favoritesList list of organizations in user's favourite
     */
    void onRetrieveFavorites(ArrayList<String> favoritesList){
        mView.onRetrieveFavorites(favoritesList);
    }

    /**
     * Call back from model if retrieval failed
     * @param errorMessage
     */
    void onRetrieveFail(String errorMessage){
        mView.onRetrieveFail(errorMessage);
    }

    /**
     * Call back from model if organization removed from favourites
     */
    void onRemoveSuccess(){
        mView.onRemoveSuccess();
    }

    /**
     * Call back from model if removing failed
     * @param errorMessage
     */
    void onRemoveFail(String errorMessage){
        mView.onRetrieveFail(errorMessage);
    }
}
