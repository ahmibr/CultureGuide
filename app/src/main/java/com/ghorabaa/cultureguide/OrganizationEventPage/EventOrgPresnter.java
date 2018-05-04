package com.ghorabaa.cultureguide.OrganizationEventPage;

import android.content.Context;
import android.util.Log;

import com.ghorabaa.cultureguide.MEvent;

/**
 * Created by ruba on 04/05/18.
 */

public class EventOrgPresnter extends EventPresenter {

    public EventOrgPresnter(EventContract.EventView view, Context context) {
        super(view, context);
         mModel=EventOrgModel.getInstance(this,context,getid());

    }




    public void CreatePresenterFun(MEvent Event)
    {
        ( (EventOrgModel)mModel).AddEvent(Event);
    }


    public void UpdatePresenterFun(int FuncID,String Paramter)
    {  try {
        switch (FuncID) {
            case 1:
                ( (EventOrgModel)mModel).UpdateEventTitle(Paramter);
            case 2:
                ( (EventOrgModel)mModel).UpdateEventCat(Paramter);
            case 3:
                ( (EventOrgModel)mModel).UpdateEventDes(Paramter);
            case 4:
                ( (EventOrgModel)mModel).UpdateEventLocation(Paramter);
            case 5:
                ( (EventOrgModel)mModel).UpdateEventDate(Paramter);


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



}
