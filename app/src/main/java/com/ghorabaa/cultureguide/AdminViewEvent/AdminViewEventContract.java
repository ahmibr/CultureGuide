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
        void onSuccess();
    }

    interface Presenter
    {

        void retrieveEvents();
        void retrieveEvent(int id);
        void removeEvent(Integer id);
    }
}
