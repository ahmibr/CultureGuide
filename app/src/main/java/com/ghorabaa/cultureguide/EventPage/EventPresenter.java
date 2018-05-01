package com.ghorabaa.cultureguide.EventPage;


import com.ghorabaa.cultureguide.MEvent;


import android.content.Context;
/**
 * Created by ruba on 26/03/18.
 */

public class EventPresenter {


    protected EventContract.EventView mview;
    protected EventModel mModel;
    protected Context PContext;
    protected MEvent pEvent;

    public EventPresenter(EventContract.EventView view,Context context) {

        mview = view;
        PContext=context;
        mModel = EventModel.getInstance(this,PContext);

    }







    public void onSuccess(){
        //notify view
        mview.onSuccess();
    }


    public void onFail(String msg){
        //notify view
        mview.onFail(msg);
    }

    public void CreatePresenterFun(MEvent Event)
    {
        mModel.AddEvent(Event);
    }


    public void UpdatePresenterFun(int FuncID,String Paramter,int EventID)
    {
        switch (FuncID) {
            case 1:
               mModel.UpdateEventTitle(Paramter,EventID);
            case 2:
                mModel.UpdateEventCat(Paramter,EventID);
            case 3:
               mModel.UpdateEventDes(Paramter,EventID);
            case 4:
                mModel.UpdateEventLocation(Paramter,EventID);
            case 5:
                mModel.UpdateEventDate(Long.parseLong(Paramter),EventID);



        }

    }

    public void RemoveEventFun(int ID)
    {
        mModel.RemoveEvent(ID);
    }



    public void onRetrive(MEvent event)
    {
        mview.onRetrive(event);
        pEvent=event;
    }


    public void getEventFun(int ID)
    {
        mModel.GetEvent(ID);
    }


    public void onFail(){
        //notify view
        mview.onFail();
    }

    public void onSuccess(String msg)
    {
        mview.onSuccess(msg);
    }

}

