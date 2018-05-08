package com.ghorabaa.cultureguide.OrganizationEvent.Homepage;

import com.ghorabaa.cultureguide.MEvent;

import java.util.ArrayList;

/**
 * Created by Ahmed Ibrahim on 5/8/18.
 */

public interface OrgHomepageContract {

    interface View{
        void onRetrieveEvents(ArrayList<MEvent> events);
        void onRetrieveFail(String errorMessage);
    }

    interface Presenter{
        void retrieveEvents();
    }
}
