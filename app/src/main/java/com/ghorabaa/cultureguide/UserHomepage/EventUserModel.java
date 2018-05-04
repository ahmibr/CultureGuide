package com.ghorabaa.cultureguide.EventPage;

import android.content.Context;

import com.ghorabaa.cultureguide.EventPageBaseModel;
import com.ghorabaa.cultureguide.MEvent;
import com.ghorabaa.cultureguide.Utilities.DBConnection;

/**
 * Created by ruba on 04/05/18.
 */

public class EventUserModel extends EventPageBaseModel {
    private static EventPresenter mpresenter;

    private static Context context;
    private int EventID;


    private static EventUserModel ourInstance;

    public static EventUserModel getInstance(EventPresenter presenter, Context Mcontext, DBConnection db, int eventID) {
        if (ourInstance == null) {
            ourInstance = new EventUserModel( db,  eventID);
            ourInstance.mpresenter = presenter;
            ourInstance.context = Mcontext;
            ourInstance.EventID=eventID;
        }
        return ourInstance;
    }

    private EventUserModel(DBConnection db, int eventID) {
        super(db,eventID,context);



    }

    @Override
    public void passToPresenter(MEvent mEvent) {
        mpresenter.onRetrive(mEvent);

    }

    @Override
    public void onRetrieveFail(String errorMessage) {
        mpresenter.onFail(errorMessage);

    }
}
