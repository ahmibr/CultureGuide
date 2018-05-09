package com.ghorabaa.cultureguide.UserSidebar.Invitations;

import android.content.Context;
import android.util.Pair;

import com.ghorabaa.cultureguide.MEvent;

import java.util.ArrayList;

/**
 * Created by Ahmed Ibrahim on 5/6/18.
 */

public class InvitationsPresenter implements InvitationsContract.Presenter {

    private InvitationsContract.View mView;
    private InvitationsModel mModel;

    /**
     * Invitation Presenter Constructor
     * @param view The view attached to the presenter, to send updates
     * @param context Application context to sync with
     */
    InvitationsPresenter(InvitationsContract.View view, Context context){
        mView = view;
        mModel = new InvitationsModel(this,context);
    }

    /**
     * Asks model to retrieve invitations
     */
    @Override
    public void retrieveInvitations() {
        mModel.retrieveInvitations();
    }

    /**
     * Asks model if invitation is expired
     * @param index index of the invitation
     * @return  true if invitation is expired
     */
    @Override
    public boolean isExpired(int index) {
        return mModel.isExpired(index);
    }

    /**
     * Call back from model if invitations retrieved is success
     * @param invitations First: Sender Name Second: Event
     */
    void onRetrieveInvitations(ArrayList<Pair<String,MEvent> > invitations){
        mView.onRetrieveInvitations(invitations);
    }

    /**
     * Call back from model if invitations retrieval failed
     * @param errorMessage
     */
    void onRetrieveInvitationsFail(String errorMessage){
        mView.onRetrieveInvitationsFail(errorMessage);
    }
}
