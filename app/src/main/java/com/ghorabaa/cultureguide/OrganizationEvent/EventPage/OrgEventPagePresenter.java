package com.ghorabaa.cultureguide.OrganizationEvent.EventPage;

import android.content.Context;

import com.ghorabaa.cultureguide.MEvent;

/**
 * Created by Roba Gamal on 4/5/18.
 */

public class OrgEventPagePresenter implements OrgEventPageContract.Presenter{

    private OrgEventPageContract.View mView;
    private OrgEventPageModel mModel;

    public OrgEventPagePresenter(OrgEventPageContract.View view, Context context, int eventID){
        mView = view;
        mModel = new OrgEventPageModel(this,context,eventID);
    }

    @Override
    public void retrieveEvent() {
        mModel.retrieveEvent();
    }

    @Override
    public void removeEvent() {
        mModel.removeEvent();
    }

    void onRetrieveEvent(MEvent mEvent){
        mView.onRetrieveEvent(mEvent);
    }

    void onRetrieveFail(String errorMessage){
        mView.onRetrieveFail(errorMessage);
    }

    void onRemoveEventSuccess(){
        mView.onRemoveEventSuccess();
    }

    void onRemoveEventFail(String errorMessage){
        mView.onRemoveEventFail(errorMessage);
    }
}
