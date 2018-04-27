package com.ghorabaa.cultureguide.EventPage;


import com.ghorabaa.cultureguide.MEvent;


import android.content.Context;
/**
 * Created by ruba on 26/03/18.
 */

abstract class EventPresenter {


    protected EventContract.EventView mview;
    protected EventModel mModel;
    protected Context PContext;

    public EventPresenter(EventContract.EventView view) {

        mview = view;
        mModel = EventModel.getInstance(this,PContext);

    }


    public void SetContext(Context context)
    {
        PContext=context;
    }

    public abstract void RunPresenter(MEvent Event) ;


    public void onSuccess(){
        //notify view
        mview.onSuccess();
    }


    public void onFail(){
        //notify view
        mview.onFail();
    }




}

