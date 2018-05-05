package com.ghorabaa.cultureguide.UserEventPage;

import com.ghorabaa.cultureguide.Friend;

import java.util.ArrayList;

/**
 * Created by Ahmed Ibrahim on 5/4/18.
 */

public interface InviteFriendsContract {
    interface View{
        void onRetrieveFriendsList(ArrayList<Friend> friendsList);
        void onRetrieveFriendsListFail(String errorMessage);
        void onInviteSuccess();
        void onInviteFail(String errorMessage);
    }
    interface Presenter{
        void retrieveFriendsList();
        void inviteFriend(int index);
    }
}
