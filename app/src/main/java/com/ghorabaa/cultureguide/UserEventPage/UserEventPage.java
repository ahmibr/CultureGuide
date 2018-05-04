package com.ghorabaa.cultureguide.UserEventPage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ghorabaa.cultureguide.Friend;
import com.ghorabaa.cultureguide.MEvent;
import com.ghorabaa.cultureguide.R;

import java.util.ArrayList;

public class UserEventPage extends AppCompatActivity implements UserEventPageContract.View {

    private UserEventPageContract.Presenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_event_page);
        mPresenter = new UserEventPagePresenter(this,getApplicationContext(),getIntent().getExtras().getInt("id"));
        mPresenter.checkOrgState();
    }

    @Override
    public void onRetrieveSuccess(MEvent mEvent) {
        //TODO show event data
    }

    @Override
    public void onRetrieveFail(String errorMessage) {
        //event is deleted, or connection error. make a toast and finish activity
    }

    @Override
    public void onAttendSuccess() {
        //lock the button
    }

    @Override
    public void onAttendFail(String errorMessage) {
        //event is deleted, or connection error. make a toast
    }

    @Override
    public void onRateSuccess() {
        //make a toast
    }

    @Override
    public void onRateFail(String errorMessage) {
        //event is deleted, or connection error. make a toast
    }

    @Override
    public void onInviteSuccess() {
        //make a toast, lock invite for user
    }

    @Override
    public void onInviteFail(String errorMessage) {
        //make a toast, lock invite for user
    }

    @Override
    public void onRetrieveRate(int rate) {
        //update rate
    }

    @Override
    public void onAddOrgSuccess() {
        //lock the button
    }

    @Override
    public void onAddOrgFail(String errorMessage) {
        //make a toast
    }

    @Override
    public void onRetrieveFriendsList(ArrayList<Friend> friendsList) {
        //show it to user, with invite user next to each one
    }

    @Override
    public void showAddOrgButton() {

    }

    @Override
    public void showAttendButton() {

    }

    @Override
    public void onRetrieveFriendsListFail(String errorMessage) {

    }


}
