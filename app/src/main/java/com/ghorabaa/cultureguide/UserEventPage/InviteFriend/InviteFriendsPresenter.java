package com.ghorabaa.cultureguide.UserEventPage.InviteFriend;

import android.content.Context;

import com.ghorabaa.cultureguide.Friend;

import java.util.ArrayList;

/**
 * Created by Ahmed Ibrahim on 5/4/18.
 */

public class InviteFriendsPresenter implements InviteFriendsContract.Presenter {

    private InviteFriendsModel mModel;
    private InviteFriendsContract.View mView;

    /**
     * Invite Friends presenter
     * @param view The view attached to the presenter, to send updates
     * @param context Application context to sync with
     * @param eventID event id to be invited to
     */
    public InviteFriendsPresenter(InviteFriendsContract.View view, Context context, int eventID) {
        mView = view;
        mModel = new InviteFriendsModel(this, context, eventID);
    }

    /**
     * Asks model to get friends list
     */
    @Override
    public void retrieveFriendsList() {
        mModel.retrieveFriendsList();
    }

    /**
     * Asks model to invite friend to event
     * @param index
     */
    @Override
    public void inviteFriend(int index) {
        mModel.inviteFriend(index);
    }

    /**
     * Call back from model in case invitation success
     */
    public void onInviteSuccess() {
        mView.onInviteSuccess();
    }

    /**
     * Call back from model in case invitation failed
     * @param errorMessage
     */
    public void onInviteFail(String errorMessage) {
        mView.onInviteFail(errorMessage);
    }

    /**
     * Call back from model in case
     * @param friendsList
     */
    public void onRetrieveFriendsList(ArrayList<Friend> friendsList) {
        mView.onRetrieveFriendsList(friendsList);
    }

    /**
     * Call back from model in case retrieval of failure
     * @param errorMessage
     */
    public void onRetrieveFriendsListFail(String errorMessage) {
        mView.onRetrieveFriendsListFail(errorMessage);
    }
}
