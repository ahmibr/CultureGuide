package com.ghorabaa.cultureguide.UserEventPage.EventPage;

import com.ghorabaa.cultureguide.Friend;
import com.ghorabaa.cultureguide.MEvent;

import java.util.ArrayList;

/**
 * Created by Ahmed Ibrahim on 4/28/18.
 */

public interface UserEventPageContract {
    interface View{
        void onRetrieveSuccess(MEvent mEvent);
        void onRetrieveFail(String errorMessage);
        void onAttendSuccess();
        void onAttendFail(String errorMessage);
        void onRateSuccess();
        void onRateFail(String errorMessage);
        void onRetrieveRate(int rate);
        void onAddOrgSuccess();
        void onAddOrgFail(String errorMessage);
        void showAddOrgButton();
        void showAttendButton();
    }

    interface Presenter{
        void retrieveEvent();
        void rate(int rate);
        void updateRate();
        void addOrgToFavorite();
        void checkOrgState();
        void checkAttendState();

    }
}
