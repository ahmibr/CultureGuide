package com.ghorabaa.cultureguide.EventPage;

import android.util.Log;

import com.ghorabaa.cultureguide.MEvent;

/**
 * Created by ruba on 26/03/18.
 */

public class UpdateEventPresenter extends EventPresenter {
    private  String Prameter;
    private Object value;
    UpdateEventPresenter(EventContract.EventView view,String parametertochange,Object value)
    {
        super(view);
        Prameter=parametertochange;
        this.value=value;
    }


    @Override
    public void RunPresenter(MEvent Event) {

        try

        {
            super.mModel.UpdateEvent(Event,Prameter,value);
            mview.onSuccess();

        }
        catch(Exception e)

        { mview.onFail(e);

            Log.w("error:",e);}







    }

    }

