package com.ghorabaa.cultureguide.UserHomepage;

import com.ghorabaa.cultureguide.EventRetrievalType;
import com.ghorabaa.cultureguide.MEvent;

import java.util.ArrayList;

/**
 * Created by Ahmed Ibrahim on 4/27/18.
 */

public interface UserHomepageContract {
    interface View{
        void onRetrieve(ArrayList<MEvent> events);
        void onRetrieve(String events);
        void onFail(String errorMessage);
    }
    interface Presenter{
        void retrieveEvents(EventRetrievalType type);
    }
}
