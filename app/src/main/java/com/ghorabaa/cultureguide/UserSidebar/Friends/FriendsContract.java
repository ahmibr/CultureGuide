package com.ghorabaa.cultureguide.UserSidebar.Friends;

import com.ghorabaa.cultureguide.Friend;

import java.util.ArrayList;

/**
 * Created by Ahmed Ibrahim on 5/6/18.
 */

public interface FriendsContract {
    interface View{
        void onRetrieveFriendsList(ArrayList<Friend> friendsList);
        void onRetrieveFriendsListFail(String errorMessage);
        void onRemoveSuccess();
        void onRemoveFail(String errorMessage);
        void onAddSuccess();
        void onAddFail(String errorMessage);
    }

    interface Presenter{
        void retrieveFriendsList();
        void removeFriend(int index);
        void addFriend(String email);
    }
}
