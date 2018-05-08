package com.ghorabaa.cultureguide.OrganizationEvent.EventPage;

import com.ghorabaa.cultureguide.MEvent;

/**
 * Created by Roba Gamal on 4/5/18.
 *
 */

public interface OrgEventPageContract {

    interface View{
        void onRetrieveEvent(MEvent mEvent);
        void onRetrieveFail(String errorMessage);
        void onRemoveEventSuccess();
        void onRemoveEventFail(String errorMessage);
    }

    interface Presenter{
        void retrieveEvent();
        void removeEvent();
    }
}
