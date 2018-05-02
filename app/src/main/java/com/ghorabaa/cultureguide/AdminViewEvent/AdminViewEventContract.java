package com.ghorabaa.cultureguide.AdminViewEvent;

import android.util.Pair;

import java.util.ArrayList;

/**
 * Created by megem on 5/1/2018.
 */

public interface AdminViewEventContract {
    interface View
    {
        void onRetrieve(ArrayList<Pair<Integer, String>> events);
        void onFail(String errorMessage);
    }

    interface Presenter
    {

        void retrieveEvents();
        void onRetrieve(ArrayList<Pair<Integer, String>> events);
        void onFail(String errorMessage);
    }
}
