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

    UserHomepagePresenter(UserHomepageContract.View view, Context context){
        mView = view;
        mModel = new UserHomepageModel(this,context);
    }

    public void retrieveEvents(EventRetrievalType type){
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

    public void onRetrievingEvents(String events){
        mView.onRetrieve(events);
    }

    public void onFail(String errorMessage){
        mView.onFail(errorMessage);
    }
}
