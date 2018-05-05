package com.ghorabaa.cultureguide.OrganizationEventPage;


import com.ghorabaa.cultureguide.EventPageBaseModel;
import com.ghorabaa.cultureguide.MEvent;


import android.content.Context;

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

        mview.onRetrieve(event);

    }









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


    return mview.geteventID();
}

}

