package com.ghorabaa.cultureguide.EventPage;

import com.ghorabaa.cultureguide.MEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruba on 07/04/18.
 */

public class RetrieveEventPresenter extends EventPresenter {


    public RetrieveEventPresenter(EventContract.EventView view)
    {
        super(view);
    }

    @Override
    public void RunPresenter(MEvent Event) {

    };

    public List<MEvent> GetEvent(String organizationID)
    {
         List<MEvent>Events=new ArrayList<MEvent>();
        mModel.GetEvents(organizationID);
        return Events;
    }
}
