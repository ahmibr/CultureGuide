package com.ghorabaa.cultureguide.UserSidebar.Friends;

import android.content.Context;

import com.ghorabaa.cultureguide.Friend;

import java.util.ArrayList;

/**
 * Created by Ahmed Ibrahim on 5/6/18.
 */

public class FriendsPresenter implements FriendsContract.Presenter{

    private FriendsContract.View mView;
    private FriendsModel mModel;

    public FriendsPresenter(FriendsContract.View view, Context context) {
        mModel = new FriendsModel(this,context);
        mView = view;
    }

    @Override
    public void retrieveFriendsList() {
        mModel.retrieveFriendsList();
    }

    @Override
    public void removeFriend(int index) {
        mModel.removeFriend(index);
    }

    @Override
    public void addFriend(String email) {
        mModel.addFriend(email);
    }

    void onRetrieveFriendsList(ArrayList<Friend> friendsList){
        mView.onRetrieveFriendsList(friendsList);
    }

    void onRetrieveFriendsListFail(String errorMessage){
        mView.onRetrieveFriendsListFail(errorMessage);
    }

    void onRemoveSuccess(){
        mView.onRemoveSuccess();
    }

    void onRemoveFail(String errorMessage){
        mView.onRemoveFail(errorMessage);
    }

    void onAddSuccess(){
        mView.onAddSuccess();
    }

    void onAddFail(String errorMessage){
        mView.onAddFail(errorMessage);
    }
}
