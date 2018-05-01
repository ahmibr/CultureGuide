package com.ghorabaa.cultureguide.AdminViewCategory;

import android.util.Pair;

import java.util.ArrayList;

/**
 * Created by Ahmed Ibrahim on 4/30/18.
 */

public interface AdminViewCategoryContract {
    interface View{
        void onRetrieve(ArrayList<Pair<Integer, String> > categoriesList);
        void onSuccess(String categories);
        void onFail(String errorMessage);
    }
    
    interface Presenter{

        void retrieveCategories();

        void onFail(String errorMessage);
    }
}
