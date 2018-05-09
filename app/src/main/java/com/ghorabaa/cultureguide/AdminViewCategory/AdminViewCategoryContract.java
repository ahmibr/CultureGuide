package com.ghorabaa.cultureguide.AdminViewCategory;

import android.util.Pair;

import java.util.ArrayList;

/**
 * Created by megem on 4/30/18.
 */

public interface AdminViewCategoryContract {
    interface View{
        void onRetrieve(ArrayList<Pair<Integer, String> > categoriesList);
        void onSuccess();
        void onFail(String errorMessage);
    }
    
    interface Presenter{

        void retrieveCategories();
        void onRetrieve(ArrayList<Pair<Integer, String> > categoriesList);
        void onFail(String errorMessage);
        void retrieveCategory(int id);
        void removeCategory(Integer first);
    }
}
