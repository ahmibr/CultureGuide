package com.ghorabaa.cultureguide.UserSidebar.Friends;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.ghorabaa.cultureguide.Friend;
import com.ghorabaa.cultureguide.R;

import java.util.ArrayList;

public class FriendsActivity extends AppCompatActivity implements FriendsContract.View{

    private FriendsContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_list);
        mPresenter = new FriendsPresenter(this,getApplicationContext());
        mPresenter.retrieveFriendsList();
    }

    @Override
    public void onRetrieveFriendsList(ArrayList<Friend> friendsList) {

        //For testing
        for(int i=0;i<friendsList.size();++i)
            Toast.makeText(getApplicationContext(),friendsList.get(i).getName(),Toast.LENGTH_LONG).show();
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
}
