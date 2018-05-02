package com.ghorabaa.cultureguide.UserHomepage;


import android.content.Context;

import com.ghorabaa.cultureguide.EventRetrievalType;
import com.ghorabaa.cultureguide.MEvent;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Ahmed Ibrahim on 4/28/18.
 */

public class UserHomepagePresenter implements UserHomepageContract.Presenter {

    private UserHomepageContract.View mView;
    private UserHomepageModel mModel;
    private ArrayList<MEvent> currentEvents;
    private EventRetrievalType currentType;

    UserHomepagePresenter(UserHomepageContract.View view, Context context){
        mView = view;
        mModel = new UserHomepageModel(this,context);
        currentType = EventRetrievalType.Upcoming;
    }

    public void retrieveEvents(EventRetrievalType type){
        currentType = type;
        switch (type){
            case Favourite:
                mModel.getFavouriteEvent();
                break;
            case Upcoming:
                mModel.getUpcomingEvents();
                break;
            case Past:
                mModel.getPastEvent();
                break;
            default:
                break;
        }
    }

    public void onRetrievingEvents(ArrayList<MEvent> events){
        mView.onRetrieve(events);
    }

    public void refresh(){
        retrieveEvents(currentType);
    }

    public void onFail(String errorMessage){
        mView.onFail(errorMessage);
    }
}
