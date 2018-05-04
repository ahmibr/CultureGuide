package com.ghorabaa.cultureguide.UserEventPage;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ghorabaa.cultureguide.Friend;
import com.ghorabaa.cultureguide.R;

import java.util.ArrayList;
import java.util.List;

public class InviteFriends extends AppCompatActivity implements InviteFriendsContract.View{

    LinearLayout mFriendParent;
    private InviteFriendsContract.Presenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_friends);
        int mEventID = getIntent().getExtras().getInt("eventId");
        mPresenter = new InviteFriendsPresenter(this,getApplicationContext(),mEventID);
        mFriendParent = (LinearLayout) findViewById(R.id.invite_friend_parent);
        mPresenter.retrieveFriendsList();
    }

    public void onRetrieveFriendsList(ArrayList<Friend> friendsList){
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.invite_friend_row, null);
        for (int i = 0; i < friendsList.size() ; i++)
        {
            rowView = inflater.inflate(R.layout.invite_friend_row, null);
            mFriendParent.addView(rowView, mFriendParent.getChildCount() - 1);

            TextView friendName = (TextView) findViewById(R.id.invite_friends_name);
            friendName.setText(friendsList.get(i).getName());

            //TODO complete add invite button logic here
            Button inviteButton = (Button) findViewById(R.id.invite_friends_invite_button);
            inviteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }

    @Override
    public void onRetrieveFriendsListFail(String errorMessage) {
        //make a toast and close activity
    }

    @Override
    public void onInviteSuccess() {
        //make a toast, lock invite for user
    }

    @Override
    public void onInviteFail(String errorMessage) {
        //make a toast, lock invite for user
    }

}
