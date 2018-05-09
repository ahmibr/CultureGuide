package com.ghorabaa.cultureguide.OrganizationEvent.EventPage;

import com.ghorabaa.cultureguide.MEvent;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Roba Gamal on 4/5/18.
 *
 */

public interface OrgEventPageContract {
//the contract between view and presenter
    interface View{
        void onRetrieveEvent(MEvent mEvent);
        void onRetrieveFail(String errorMessage);
        void onRemoveEventSuccess();
        void onRemoveEventFail(String errorMessage);
        void onUpdateSuccess(String message);
        void onUpdateFail(String errorMessage);
        void onRetrieveCategories(ArrayList<String> categoryList);
    }
//the contract between presenter and model
    interface Presenter{
        void retrieveEvent();
        void retrieveCategories();
        void removeEvent();
        void updateEventTitle(String title);
        void updateEventLocation(String location);
        void updateEventDescription(String description);
        void updateEventDate(Date date);
        void updateEventCategory(int cIndex);
    }
}
