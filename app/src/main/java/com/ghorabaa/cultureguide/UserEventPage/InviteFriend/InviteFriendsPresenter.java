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

    public InviteFriendsPresenter(InviteFriendsContract.View view, Context context, int eventID) {
        mView = view;
        mModel = new InviteFriendsModel(this, context, eventID);
    }

    @Override
    public void retrieveFriendsList() {
        mModel.retrieveFriendsList();
    }

    @Override
    public void inviteFriend(int index) {
        mModel.inviteFriend(index);
    }

    public void onInviteSuccess() {
        mView.onInviteSuccess();
    }

    public void onInviteFail(String errorMessage) {
        mView.onInviteFail(errorMessage);
    }

    public void onRetrieveFriendsList(ArrayList<Friend> friendsList) {
        mView.onRetrieveFriendsList(friendsList);
    }

    public void onRetrieveFriendsListFail(String errorMessage) {
        mView.onRetrieveFriendsListFail(errorMessage);
    }
}
