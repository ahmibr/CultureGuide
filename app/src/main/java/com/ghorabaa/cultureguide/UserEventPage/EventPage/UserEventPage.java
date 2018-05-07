package com.ghorabaa.cultureguide.UserEventPage.EventPage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.ghorabaa.cultureguide.MEvent;
import com.ghorabaa.cultureguide.R;
import com.ghorabaa.cultureguide.UserEventPage.InviteFriend.InviteFriends;

public class UserEventPage extends AppCompatActivity implements UserEventPageContract.View {

    private UserEventPageContract.Presenter mPresenter;

    NumberPicker mRatePicker;

    TextView mTitle;
    TextView mDescription;
    TextView mLocation;
    TextView mDate;
    TextView mRating;

    Button mInviteFriend;

    private int mEventID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_event_page);
        mEventID = getIntent().getExtras().getInt("eventId");
        mPresenter = new UserEventPagePresenter(this, getApplicationContext(), mEventID);
        mPresenter.retrieveEvent();

        mRatePicker = (NumberPicker) findViewById(R.id.event_page_rate);

        mTitle = (TextView) findViewById(R.id.event_page_name);
        mDescription = (TextView) findViewById(R.id.event_page_description);
        mLocation = (TextView) findViewById(R.id.event_page_location);
        mDate = (TextView) findViewById(R.id.event_page_date);
        mRating = (TextView) findViewById(R.id.event_page_rating);

        mInviteFriend = (Button) findViewById(R.id.event_page_invite_friend);

        mRatePicker.setMaxValue(5);
        mRatePicker.setMinValue(0);
        mRatePicker.setWrapSelectorWheel(false);


        mRatePicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {

            }
        });

        mInviteFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),InviteFriends.class);
                intent.putExtra("eventId" , mEventID);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public void onRetrieveSuccess(MEvent mEvent) {
        //TODO show event data

        mTitle.setText( mEvent.getTitle() );
        mDescription.setText( mEvent.getDescription() );
        mLocation.setText( mEvent.getLocation() );
        mDate.setText( mEvent.getDate().toString() );
        mRating.setText( mEvent.getRating() );
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
    public void showAddOrgButton() {

    }

    @Override
    public void showAttendButton() {

    }

}
