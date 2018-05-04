package com.ghorabaa.cultureguide.OrganizationEventPage;

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
    private static DBConnection DBManger;



    private static EventUserModel ourInstance;

    public static EventUserModel getInstance(EventPresenter presenter, Context Mcontext, int eventID) {
        if (ourInstance == null) {
            ourInstance = new EventUserModel( DBManger,  eventID);
            ourInstance.mpresenter = presenter;
            ourInstance.context = Mcontext;
            DBManger=DBConnection.getInstance(context);

        }
        return ourInstance;
    }

    private EventUserModel(DBConnection db, int eventID) {
        super(db,eventID);



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
