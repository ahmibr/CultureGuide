package com.ghorabaa.cultureguide.UserSidebar.Favorites;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Ahmed Ibrahim on 5/6/18.
 */

public class FavoritesPresenter implements FavoritesContract.Presenter{
    private FavoritesContract.View mView;
    private FavoritesModel mModel;

    FavoritesPresenter(FavoritesContract.View view, Context context){
        mView = view;
        mModel = new FavoritesModel(this,context);
    }

    @Override
    public void retrieveFavorites() {
        mModel.retrieveFavorites();
    }

    @Override
    public void removeFavorite(int index) {
        mModel.removeFavorite(index);
    }

    void onRetrieveFavorites(ArrayList<String> favoritesList){
        mView.onRetrieveFavorites(favoritesList);
    }

    void onRetrieveFail(String errorMessage){
        mView.onRetrieveFail(errorMessage);
    }

    void onRemoveSuccess(){
        mView.onRemoveSuccess();
    }

    void onRemoveFail(String errorMessage){
        mView.onRetrieveFail(errorMessage);
    }
}
