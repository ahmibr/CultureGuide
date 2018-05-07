package com.ghorabaa.cultureguide.UserSidebar.Interests;

import android.util.Pair;

import java.util.ArrayList;

/**
 * Created by Ahmed Ibrahim on 5/7/18.
 */

public interface InterestsContract {
    interface View{
        void onRetrieveInterests(ArrayList<Pair<String,Boolean>> interestsList);
        void onRetrieveInterestsFail(String errorMessage);
        void onAddSuccess();
        void onAddFail(String errorMessage);
        void onRemoveSuccess();
        void onRemoveFail(String errorMessage);
    }

    interface Presenter{
        void retrieveInterests();
        void addInterest(int index);
        void removeInterest(int index);
    }
}
