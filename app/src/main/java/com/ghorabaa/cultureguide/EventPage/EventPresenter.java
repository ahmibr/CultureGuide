package com.ghorabaa.cultureguide.EventPage;


import com.ghorabaa.cultureguide.MEvent;

/**
 * Created by ruba on 26/03/18.
 */

abstract class EventPresenter {


    protected EventContract.EventView mview;
    protected EventModel mModel;

    public EventPresenter(EventContract.EventView view) {

        mview = view;
        mModel = EventModel.getInstance(this);

    }



    public abstract void RunPresenter(MEvent Event) ;




}