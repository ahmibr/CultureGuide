package com.ghorabaa.cultureguide.UserEventPage;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

    public void onRetrieveFriendsList(final ArrayList<Friend> friendsList){
        for (int i = 0; i < friendsList.size() ; i++)
        {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.invite_friend_row, null);

            ((TextView)(((LinearLayout)rowView).getChildAt(0))).setText(friendsList.get(i).getName());

            final Button inviteButton = (Button) ((LinearLayout)rowView).getChildAt(1);
            inviteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if((LinearLayout)view.getParent() != null)
                    {
                        LinearLayout row = (LinearLayout)view.getParent();
                        LinearLayout rowParent = (LinearLayout)row.getParent();

                        for (int i = 0 ; i < rowParent.getChildCount(); i++){
                            if(rowParent.getChildAt(i) == row) {
                                Toast.makeText(view.getContext(), "Invited " + friendsList.get(i).getName() , Toast.LENGTH_SHORT).show();
                                mPresenter.inviteFriend(i);

                                inviteButton.setEnabled(false);
                            }
                        }
                        return;
                    }

                    int index = ((LinearLayout)view.getParent()).indexOfChild(view);
                    Toast.makeText(view.getContext(), index, Toast.LENGTH_LONG).show();
                }
            });

            //TextView friendName = (TextView) findViewById(R.id.invite_friends_name);
            //friendName.setText(friendsList.get(i).getName());

            mFriendParent.addView(rowView, mFriendParent.getChildCount());


            //TODO complete add invite button logic here
//            Button inviteButton = (Button) findViewById(R.id.invite_friends_invite_button);
//            inviteButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(view.getContext(),view.getId() + " test " , Toast.LENGTH_LONG).show();
//                }
//            });
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
