package com.ghorabaa.cultureguide.EventPage;

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
    public void CallModel(MEvent Event) {
        mModel.UpdateEvent(Event,Prameter,value);
    }
}
