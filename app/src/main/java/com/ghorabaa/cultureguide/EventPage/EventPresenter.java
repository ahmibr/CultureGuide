package com.ghorabaa.cultureguide.OrganizationEventPage;


import com.ghorabaa.cultureguide.MEvent;


import android.content.Context;
import android.util.Log;

/**
 * Created by ruba on 26/03/18.
 */

public class EventPresenter {


    protected EventContract.EventView mview;
    protected EventModel mModel;
    protected Context PContext;




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
    {  try {
        switch (FuncID) {
            case 1:
                mModel.UpdateEventTitle(Paramter, EventID);
            case 2:
                mModel.UpdateEventCat(Paramter, EventID);
            case 3:
                mModel.UpdateEventDes(Paramter, EventID);
            case 4:
                mModel.UpdateEventLocation(Paramter, EventID);
            case 5:
                mModel.UpdateEventDate(Paramter, EventID);


        }
    }
    catch (Exception e)
    {
        Log.w("presenter error",e.getMessage());
    }
    }

    public void RemoveEventFun(int ID)
    {
        mModel.RemoveEvent(ID);
    }



    public void onRetrive(MEvent event)

    {

        mview.onRetrive(event);

    }





    public void getEventFun(int ID)
    {
        mModel.GetEvent(ID);
    }
    public void GetRate(int ID){mModel.GetEventRate(ID);}




    public void onFail(){
        //notify view
        mview.onFail();
    }

    public void onSuccess(String msg)
    {
        mview.onSuccess(msg);
    }


}

