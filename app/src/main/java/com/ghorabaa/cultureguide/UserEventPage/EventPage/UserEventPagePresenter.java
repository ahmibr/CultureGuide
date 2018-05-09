package com.ghorabaa.cultureguide.UserEventPage.EventPage;

import android.content.Context;

import com.ghorabaa.cultureguide.MEvent;

/**
 * Created by Ahmed Ibrahim on 4/28/18.
 */

public class UserEventPagePresenter implements UserEventPageContract.Presenter {

    private UserEventPageContract.View mView;
    private UserEventPageModel mModel;

    /**
     * User Event Page constructor
     * @param view The view attached to the presenter, to send updates
     * @param context Application context to sync with
     * @param id    event id
     */
    public UserEventPagePresenter(UserEventPageContract.View view, Context context, int id) {
        mView = view;
        mModel = new UserEventPageModel(this, context, id);
    }

    /**
     * Asks model to retrieve events info
     * @param isPastEvent to know if the event is past, to check if it's expired
     */
    @Override
    public void retrieveEvent(boolean isPastEvent) {
        mModel.retrieveEvent(isPastEvent);
    }

    /**
     * Asks model to rate event
     * @param rate
     */
    @Override
    public void rate(int rate) {
        mModel.rateEvent(rate);
    }

    /**
     * Asks model to register user in attendees
     */
    @Override
    public void attendEvent() {
        mModel.attendEvent();
    }

    /**
     * Asks model to get new rate
     */
    @Override
    public void updateRate() {
        mModel.retrieveRate();
    }

    /**
     * Callback from model if attending is success
     */
    public void onAttendSuccess() {
        mView.onAttendSuccess();
    }

    /**
     * Callback from model in case attending failed
     * @param errorMessage
     */
    public void onAttendFail(String errorMessage) {
        mView.onAttendFail(errorMessage);
    }

    /**
     * Call back from model in retrieval success
     * @param mEvent event info
     */
    public void onRetrieveSuccess(MEvent mEvent) {
        mView.onRetrieveSuccess(mEvent);
    }

    /**
     * Call back from model if retrieval failed
     * @param errorMessage
     */
    public void onRetrieveFail(String errorMessage) {
        mView.onRetrieveFail(errorMessage);
    }

    /**
     * Call back from model if rated success
     */
    public void onRateSuccess() {
        mView.onRateSuccess();
        updateRate();
    }

    /**
     * Call back from model if rate fail
     * @param errorMessage
     */
    public void onRateFail(String errorMessage) {
        mView.onRateFail(errorMessage);
    }

    /**
     * Call back from model if rate retrieval success
     * @param rate
     */
    public void onRetrieveRate(int rate) {
        mView.onRetrieveRate(rate);
    }

    /**
     * Call back from model if organization add success
     */
    public void addOrgToFavorite() {
        mModel.addOrgToFavorite();
    }

    /**
     * Asks model to check if user added org in favourites
     */
    @Override
    public void checkOrgState() {
        mModel.checkOrgState();
    }

    /**
     * Asks model to check if user attended this event
     */
    @Override
    public void checkAttendState() {
     mModel.checkAttendState();
    }

    /**
     * Call back from model if organization is added to favourite successfully
     */
    public void onAddOrgSuccess() {
        mView.onAddOrgSuccess();
    }

    /**
     * Call back from model if add organization failed
     * @param errorMessage
     */
    public void onAddOrgFail(String errorMessage) {
        mView.onAddOrgFail(errorMessage);
    }

    /**
     * Call back from model if organization is not users favorites
     */
    public void showAddOrg() {
        mView.showAddOrgButton();
    }

    /**
     * Call back from model if users didn't attend file
     */
    public void showAttendButton() {
        mView.showAttendButton();
    }
}
