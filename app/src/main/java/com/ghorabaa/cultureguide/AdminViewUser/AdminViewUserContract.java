package com.ghorabaa.cultureguide.AdminViewUser;

import android.util.Pair;

import java.util.ArrayList;

/**
 * Created by megem on 5/1/2018.
 */

public interface AdminViewUserContract {
    interface View{

        void onRetrieve(ArrayList<Pair<String, String>> users);
        void onFail(String errorMessage);
    }

    interface Presenter{

        void retrieveUsers();
        void onRetrieve(ArrayList<Pair<String, String>> users);
        void onFail(String errorMessage);
    }
}
