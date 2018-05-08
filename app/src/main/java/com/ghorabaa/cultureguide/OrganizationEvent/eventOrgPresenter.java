package com.ghorabaa.cultureguide.OrganizationEventPage;

import android.content.Context;
import android.util.Log;

import com.ghorabaa.cultureguide.MEvent;

import java.util.ArrayList;

/**
 * Created by ruba on 04/05/18.
 */

public class EventOrgPresnter extends EventPresenter {

    public EventOrgPresnter(EventContract.EventView view, Context context) {
        super(view, context);
         mModel=EventOrgModel.getInstance(this,context,getid());

    }



    public void retrieveEvents(){((EventOrgModel)mModel).retrieveEvents();}
    public void CreatePresenterFun(MEvent Event)
    {
        ( (EventOrgModel)mModel).AddEvent(Event);
    }


    public void UpdatePresenterFun(int FuncID,String Paramter)
    {  try {
        switch (FuncID) {
           case 1:
             ( (EventOrgModel)mModel).UpdateEventTitle(Paramter);
             break;
            case 2:
            ( (EventOrgModel)mModel).UpdateEventCat(Paramter);
                break;
            case 3:
             ( (EventOrgModel)mModel).UpdateEventDes(Paramter);
                break;
            case 4:
              ( (EventOrgModel)mModel).UpdateEventLocation(Paramter);
                break;
            case 5:
              ( (EventOrgModel)mModel).UpdateEventDate(Paramter);
                break;


        }
    }
    catch (Exception e)
    {
        Log.w("presenter error",e.getMessage());
    }
    }

    public void RemoveEventFun()
    {
        ( (EventOrgModel)mModel).RemoveEvent();
    }

    public void getMostCrowded() {  ((EventOrgModel) mModel).getMostcrowded();}
    public void getEventFun(){((EventOrgModel) mModel).retrieveEvent();}
    public void getMostrated() {  ((EventOrgModel) mModel).getmostrated();}
    public void getRate(){((EventOrgModel) mModel).GetEventRate();}

    public void onRetrive(int ID)
    {
        mview.onRetrieve(ID);
    }

    public void onRetrieve(MEvent mEvent){
        mview.onRetrieve(mEvent);
    }

    public void onRetrieve(ArrayList<MEvent> mEvents){
        mview.onRetrieve(mEvents);
    }


}
