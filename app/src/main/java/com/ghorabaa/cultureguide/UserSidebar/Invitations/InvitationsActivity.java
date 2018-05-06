package com.ghorabaa.cultureguide.UserSidebar.Invitations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.widget.Toast;

import com.ghorabaa.cultureguide.MEvent;
import com.ghorabaa.cultureguide.R;

import java.util.ArrayList;

public class InvitationsActivity extends AppCompatActivity implements InvitationsContract.View{

    InvitationsContract.Presenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitations);
        mPresenter = new InvitationsPresenter(this,getApplicationContext());
        mPresenter.retrieveInvitations();
    }

    @Override
    public void onRetrieveInvitations(ArrayList<Pair<String, MEvent>> invitations) {

        //testing
        for(int i=0;i<invitations.size();++i) {
            Toast.makeText(getApplicationContext(), invitations.get(i).first, Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(), invitations.get(i).second.getTitle(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRetrieveInvitationsFail(String errorMessage) {
        Toast.makeText(getApplicationContext(),errorMessage,Toast.LENGTH_LONG).show();
        finish();
    }
}
