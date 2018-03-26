package com.ghorabaa.cultureguide.EventPage;

import com.ghorabaa.cultureguide.MEvent;

/**
 * Created by ruba on 26/03/18.
 */

public class CreateEventPresenter extends EventPresenter {


    CreateEventPresenter(EventContract.EventView view)

    {
        super(view);


    }

    @Override
    public void CallModel(MEvent Event)
    {
        super.mModel.AddEvent(Event);
    }

}
