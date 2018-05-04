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

import java.util.List;

public class InviteFriends extends AppCompatActivity {

    LinearLayout mFriendParent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_friends);
        //id sent by name "eventId"
        mFriendParent = (LinearLayout) findViewById(R.id.invite_friend_parent);

    }

    void showFriendsRows(List<Friend> friends){
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.invite_friend_row, null);
        for (int i = 0; i < friends.size() ; i++)
        {
            rowView = inflater.inflate(R.layout.invite_friend_row, null);
            mFriendParent.addView(rowView, mFriendParent.getChildCount() - 1);

            TextView friendName = (TextView) findViewById(R.id.invite_friends_name);
            friendName.setText(friends.get(i).getName());

            //TODO complete add invite button logic here
            Button inviteButton = (Button) findViewById(R.id.invite_friends_invite_button);
            inviteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }

}
