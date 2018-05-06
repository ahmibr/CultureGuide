package com.ghorabaa.cultureguide.UserSidebar.Invitations;

import android.util.Pair;

import com.ghorabaa.cultureguide.MEvent;

import java.util.ArrayList;

/**
 * Created by Ahmed Ibrahim on 5/6/18.
 */

public interface InvitationsContract {

    interface View{
        void onRetrieveInvitations(ArrayList<Pair<String,MEvent> > invitations);
        void onRetrieveInvitationsFail(String errorMessage);
    }

    interface Presenter{
        void retrieveInvitations();
    }
}
