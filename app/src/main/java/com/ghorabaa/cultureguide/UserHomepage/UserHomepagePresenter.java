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

    /**
     * User homepage constructor
     * @param view The view attached to the presenter, to send updates
     * @param context Application context to sync with
     */
    UserHomepagePresenter(UserHomepageContract.View view, Context context){
        mView = view;
        mModel = new UserHomepageModel(this,context);
    }

    /**
     * Asks model to get events of specific type
     * @param type
     */
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

    /**
     * Call back from model in events retrieval success
     * @param events
     */
    public void onRetrievingEvents(ArrayList<MEvent> events){
        mView.onRetrieve(events);
    }

    /**
     * Call back from model if event retrieval failed
     * @param errorMessage
     */
    public void onFail(String errorMessage){
        mView.onFail(errorMessage);
    }
}
