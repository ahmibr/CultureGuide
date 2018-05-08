package com.ghorabaa.cultureguide.OrganizationEvent.Homepage;

import android.content.Context;

import com.ghorabaa.cultureguide.MEvent;

import java.util.ArrayList;

/**
 * Created by Ahmed Ibrahim on 5/8/18.
 */

public class OrgHomepagePresenter implements OrgHomepageContract.Presenter {

    private OrgHomepageContract.View mView;
    private OrgHomepageModel mModel;

    public OrgHomepagePresenter(OrgHomepageContract.View view, Context context){
        mView = view;
        mModel = new OrgHomepageModel(this,context);
    }

    @Override
    public void retrieveEvents() {
        mModel.retrieveEvents();
    }

    void onRetrieveEvents(ArrayList<MEvent> events){
        mView.onRetrieveEvents(events);
    }

    void onRetrieveFail(String errorMessage){
        mView.onRetrieveFail(errorMessage);
    }


}
