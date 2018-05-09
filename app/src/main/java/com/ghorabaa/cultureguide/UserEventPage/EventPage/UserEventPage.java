package com.ghorabaa.cultureguide.UserEventPage.EventPage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.ghorabaa.cultureguide.MEvent;
import com.ghorabaa.cultureguide.R;
import com.ghorabaa.cultureguide.UserEventPage.InviteFriend.InviteFriends;

public class UserEventPage extends AppCompatActivity implements UserEventPageContract.View {

    private UserEventPageContract.Presenter mPresenter;

    private boolean mIsPastEvent;

    private boolean hasAttended;

    NumberPicker mRatePicker;

    TextView mOrganization;
    TextView mTitle;
    TextView mDescription;
    TextView mLocation;
    TextView mDate;
    TextView mRating;
    TextView mRatingTitle;

    LinearLayout mRatingBlock;

    Button mAttend;
    Button mInviteFriend;
    Button mAddFavorites;

    private int mEventID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_event_page);
        mEventID = getIntent().getExtras().getInt("eventId");
        mIsPastEvent = getIntent().getExtras().getBoolean("isPast");

        mPresenter = new UserEventPagePresenter(this, getApplicationContext(), mEventID);
        mPresenter.retrieveEvent(mIsPastEvent);

        mRatePicker = (NumberPicker) findViewById(R.id.event_page_rate);

        mTitle = (TextView) findViewById(R.id.event_page_name);
        mDescription = (TextView) findViewById(R.id.event_page_description);
        mLocation = (TextView) findViewById(R.id.event_page_location);
        mDate = (TextView) findViewById(R.id.event_page_date);
        mRating = (TextView) findViewById(R.id.event_page_rating);
        mOrganization = (TextView) findViewById(R.id.event_page_organiztion);
        mRatingTitle = (TextView) findViewById(R.id.event_page_rating_title);

        mRatingBlock = (LinearLayout) findViewById(R.id.event_page_rating_view);

        mInviteFriend = (Button) findViewById(R.id.event_page_invite_friend);
        mInviteFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),InviteFriends.class);
                intent.putExtra("eventId" , mEventID);
                view.getContext().startActivity(intent);
            }
        });

        mAttend = (Button) findViewById(R.id.event_page_attend);
        mAttend.setVisibility(View.GONE);

        mAddFavorites = (Button) findViewById(R.id.event_page_add_favorites);
        mAddFavorites.setVisibility(View.GONE);

        if(mIsPastEvent){
            mPresenter.updateRate();

            mInviteFriend.setVisibility(View.GONE);
        }
        else
        {
            mRating.setVisibility(View.GONE);
            mRatingTitle.setVisibility(View.GONE);

            mRatingBlock.setVisibility(View.GONE);

            mPresenter.checkAttendState();
        }


        mRatePicker.setMaxValue(5);
        mRatePicker.setMinValue(1);
        mRatePicker.setWrapSelectorWheel(false);

        mAttend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.attendEvent();
            }
        });

        mAddFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.addOrgToFavorite();
            }
        });

        mRatePicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                mPresenter.rate(i);
            }
        });
    }

    @Override
    public void onRetrieveSuccess(MEvent mEvent) {

        mTitle.setText( mEvent.getTitle() );
        mDescription.setText( mEvent.getDescription() );
        mLocation.setText( mEvent.getLocation() );
        mDate.setText( mEvent.getDate().toString() );
        mOrganization.setText( mEvent.getOrgName() );

        mPresenter.checkOrgState();
    }

    @Override
    public void onRetrieveFail(String errorMessage) {
        Toast.makeText(getApplicationContext(),errorMessage,Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void onAttendSuccess() {
        mAttend.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_clear_black_24dp,0);
    }

    @Override
    public void onAttendFail(String errorMessage) {
        //event is deleted, or connection error. make a toast
        Toast.makeText(this,errorMessage,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRateSuccess() {
        Toast.makeText(this, "Rated successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRateFail(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }


    @Override
    public void onRetrieveRate(int rate) {
        //update rate
        mRating.setText( rate + "" );
    }

    @Override
    public void onAddOrgSuccess() {
        mAddFavorites.setEnabled(false);
    }

    @Override
    public void onAddOrgFail(String errorMessage) {
        Toast.makeText(this,errorMessage,Toast.LENGTH_LONG).show();
    }


    @Override
    public void showAddOrgButton() {
        mAddFavorites.setVisibility(View.VISIBLE);
    }

    @Override
    public void showAttendButton() {
        mAttend.setVisibility(View.VISIBLE);
    }

}
