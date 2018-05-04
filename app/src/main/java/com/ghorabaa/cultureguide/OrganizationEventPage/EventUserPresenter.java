package com.ghorabaa.cultureguide.OrganizationEventPage;

import android.content.Context;

import com.ghorabaa.cultureguide.EventPageBaseModel;

/**
 * Created by ruba on 04/05/18.
 */

public class EventUserPresenter extends EventPresenter {

    public EventUserPresenter(EventContract.EventView view, Context context)
    {
        super( view,  context);
        mModel=EventUserModel.getInstance(this,context,getid());
    }






}




