package com.ghorabaa.cultureguide.OrganizationEvent.CreateEvent;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Ahmed Ibrahim on 5/8/18.
 */

public interface CreateEventContract {
    interface View{
        void onCreateEventSuccess();
        void onCreateEventFail(String errorMessage);
        void onRetrieveCategories(ArrayList<String> categories);
        void onRetrieveFail(String errorMessage);
    }

    interface Presenter{
        void createEvent(String title, String description, String location, Date date, int cIndex);
        void retrieveCategories();
    }
}
