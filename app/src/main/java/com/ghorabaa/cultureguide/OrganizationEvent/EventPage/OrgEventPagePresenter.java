package com.ghorabaa.cultureguide.OrganizationEvent.EventPage;

import android.content.Context;

import com.ghorabaa.cultureguide.MEvent;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Roba Gamal on 4/5/18.
 */

public class OrgEventPagePresenter implements OrgEventPageContract.Presenter {

    private OrgEventPageContract.View mView;
    private OrgEventPageModel mModel;

    public OrgEventPagePresenter(OrgEventPageContract.View view, Context context, int eventID) {
        mView = view;
        mModel = new OrgEventPageModel(this, context, eventID);
    }

    @Override
    public void retrieveEvent() {
        mModel.retrieveEvent();
    }

    @Override
    public void retrieveCategories() {
        mModel.retrieveCategories();
    }

    @Override
    public void removeEvent() {
        mModel.removeEvent();
    }

    @Override
    public void updateEventTitle(String title) {
        mModel.updateEventTitle(title);
    }

    @Override
    public void updateEventLocation(String location) {
        mModel.updateEventLocation(location);
    }

    @Override
    public void updateEventDescription(String description) {
        mModel.updateEventDescription(description);
    }

    @Override
    public void updateEventDate(Date date) {
        mModel.updateEventDate(date);
    }

    @Override
    public void updateEventCategory(int cIndex) {
        mModel.updateEventCategory(cIndex);
    }

    void onRetrieveEvent(MEvent mEvent) {
        mView.onRetrieveEvent(mEvent);
    }

    void onRetrieveFail(String errorMessage) {
        mView.onRetrieveFail(errorMessage);
    }

    void onRemoveEventSuccess() {
        mView.onRemoveEventSuccess();
    }

    void onRemoveEventFail(String errorMessage) {
        mView.onRemoveEventFail(errorMessage);
    }

    void onUpdateSuccess(String message) {
        mView.onUpdateSuccess(message);
    }

    void onUpdateFail(String errorMessage) {
        mView.onUpdateFail(errorMessage);
    }

    public void onRetrieveCategories(ArrayList<String> categoryList) {
        mView.onRetrieveCategories(categoryList);
    }
}
