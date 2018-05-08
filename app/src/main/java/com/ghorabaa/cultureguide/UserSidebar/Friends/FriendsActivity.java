package com.ghorabaa.cultureguide.UserSidebar.Friends;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ghorabaa.cultureguide.Friend;
import com.ghorabaa.cultureguide.R;

import java.util.ArrayList;

public class FriendsActivity extends AppCompatActivity implements FriendsContract.View , AdapterView.OnItemClickListener{

    private FriendsContract.Presenter mPresenter;

    private TextView mEmailText;
    private Button mAddFriend;
    private ArrayAdapter<String> mArrayAdapter;
    private ListView mFriendListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_list);
        mPresenter = new FriendsPresenter(this,getApplicationContext());
        mPresenter.retrieveFriendsList();

        mEmailText = (TextView) findViewById(R.id.friends_email);
        mAddFriend = (Button) findViewById(R.id.friends_add);
        mFriendListView = (ListView) findViewById(R.id.friends_list);

        mFriendListView.setOnItemClickListener(this);

        mAddFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.addFriend(mEmailText.getText().toString());
            }
        });
    }

    @Override
    public void onRetrieveFriendsList(ArrayList<Friend> friendsList) {

        if(friendsList.isEmpty()){
            Toast.makeText(this, "You have no friends.", Toast.LENGTH_LONG).show();
        }

        String[] friendsListString = new String[friendsList.size()];

        //For testing
        for(int i=0;i<friendsList.size();++i){
            friendsListString[i] = getResources().getString(R.string.remove) +" : " + friendsList.get(i).getName();
        }

        mArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,friendsListString);

        mFriendListView.setAdapter(mArrayAdapter);
    }

    @Override
    public void onRetrieveFriendsListFail(String errorMessage) {
        Toast.makeText(getApplicationContext(),errorMessage,Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void onRemoveSuccess() {
        Toast.makeText(getApplicationContext(),"Friend removed successfully",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRemoveFail(String errorMessage) {
        Toast.makeText(getApplicationContext(),errorMessage,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAddSuccess() {
        Toast.makeText(getApplicationContext(),"Friend added successfully!",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAddFail(String errorMessage) {
        Toast.makeText(getApplicationContext(),errorMessage,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        mPresenter.removeFriend(i);

        mPresenter.retrieveFriendsList();
    }
}
