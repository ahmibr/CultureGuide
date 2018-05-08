package com.ghorabaa.cultureguide.UserSidebar.Favorites;

import java.util.ArrayList;

/**
 * Created by Ahmed Ibrahim on 5/6/18.
 */

public interface FavoritesContract {

    interface View{
        void onRetrieveFavorites(ArrayList<String> favoritesList);
        void onRetrieveFail(String errorMessage);
        void onRemoveSuccess();
        void onRemoveFail(String errorMessage);
    }

    interface Presenter{
        void retrieveFavorites();
        void removeFavorite(int index);
    }
}
