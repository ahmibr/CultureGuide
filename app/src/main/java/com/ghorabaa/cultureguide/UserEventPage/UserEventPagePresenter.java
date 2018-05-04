package com.ghorabaa.cultureguide.UserEventPage;

import android.content.Context;

import com.ghorabaa.cultureguide.Friend;
import com.ghorabaa.cultureguide.MEvent;

import java.util.ArrayList;

/**
 * Created by Ahmed Ibrahim on 4/28/18.
 */

public class UserEventPagePresenter implements UserEventPageContract.Presenter {

    private UserEventPageContract.View mView;
    private UserEventPageModel mModel;

    public UserEventPagePresenter(UserEventPageContract.View view, Context context, int id) {
        mView = view;
        mModel = new UserEventPageModel(this, context, id);
    }

    @Override
    public void retrieveEvent() {
        mModel.retrieveEvent();
    }

    @Override
    public void rate(int rate) {
        mModel.rateEvent(rate);
    }

    @Override
    public void retrieveFriendsList() {
        mModel.retrieveFriendsList();
    }

    @Override
    public void inviteFriend(int index) {

    }

    @Override
    public void updateRate() {
        mModel.retrieveRate();
    }

    void onAttendSuccess() {
        mView.onAttendSuccess();
    }

    void onAttendFail(String errorMessage) {
        mView.onAttendFail(errorMessage);
    }

    public void onRetrieveSuccess(MEvent mEvent) {
        mView.onRetrieveSuccess(mEvent);
    }

    public void onRetrieveFail(String errorMessage) {
        mView.onRetrieveFail(errorMessage);
    }

    public void onRateSuccess() {
        mView.onRateSuccess();
        updateRate();
    }

    public void onRateFail(String errorMessage) {
        mView.onRateFail(errorMessage);
    }

    public void onInviteSuccess() {
        mView.onInviteSuccess();
    }

    public void onInviteFail(String errorMessage) {
        mView.onInviteFail(errorMessage);
    }

    public void onRetrieveRate(int rate) {
        mView.onRetrieveRate(rate);
    }

    public void addOrgToFavorite() {
        mModel.addOrgToFavorite();
    }

    @Override
    public void checkOrgState() {
        mModel.checkOrgState();
    }

    @Override
    public void checkAttendState() {
     mModel.checkAttendState();
    }

    public void onAddOrgSuccess() {
        mView.onAddOrgSuccess();
    }

    public void onAddOrgFail(String errorMessage) {
        mView.onAddOrgFail(errorMessage);
    }

    public void onRetrieveFriendsList(ArrayList<Friend> friendsList) {
        mView.onRetrieveFriendsList(friendsList);
    }


    public void showAddOrg() {
        mView.showAddOrgButton();
    }

    public void onRetrieveFriendsListFail(String errorMessage) {
        mView.onRetrieveFriendsListFail(errorMessage);
    }

    public void showAttendButton() {
        mView.showAttendButton();
    }
}
