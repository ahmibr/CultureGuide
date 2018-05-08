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
    InvitationsPresenter(InvitationsContract.View view, Context context){
        mView = view;
        mModel = new InvitationsModel(this,context);
    }
    @Override
    public void retrieveInvitations() {
        mModel.retrieveInvitations();
    }

    @Override
    public boolean isExpired(int index) {
        return mModel.isExpired(index);
    }

    void onRetrieveInvitations(ArrayList<Pair<String,MEvent> > invitations){
        mView.onRetrieveInvitations(invitations);
    }

    void onRetrieveInvitationsFail(String errorMessage){
        mView.onRetrieveInvitationsFail(errorMessage);
    }
}
