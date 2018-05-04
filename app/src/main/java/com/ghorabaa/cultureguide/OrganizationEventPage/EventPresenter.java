package com.ghorabaa.cultureguide.OrganizationEventPage;


import com.ghorabaa.cultureguide.EventPageBaseModel;
import com.ghorabaa.cultureguide.MEvent;
import com.ghorabaa.cultureguide.Utilities.DBConnection;


import android.content.Context;
import android.util.Log;

/**
 * Created by ruba on 26/03/18.
 */

public class EventPresenter {


    protected EventContract.EventView mview;
    
    protected EventPageBaseModel mModel;
    protected Context PContext;




    public EventPresenter(EventContract.EventView view, Context context) {

        mview = view;
        PContext=context;



        

    }







    public void onSuccess(){
        //notify view
        mview.onSuccess();
    }


    public void onFail(String msg){
        //notify view
        mview.onFail(msg);
    }



    public void onRetrive(MEvent event)

    {

        mview.onRetrive(event);

    }





    public void getEventFun()
    {
        mModel.retrieveEvent();
    }
    public void GetRate(){mModel.GetEventRate();}




    public void onFail(){
        //notify view
        mview.onFail();
    }

    public void onSuccess(String msg)
    {
        mview.onSuccess(msg);
    }




public int getid()
{
    return 0;
}

}

