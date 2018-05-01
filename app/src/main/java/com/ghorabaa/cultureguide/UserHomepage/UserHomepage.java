package com.ghorabaa.cultureguide.UserHomepage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.ghorabaa.cultureguide.EventRetrievalType;
import com.ghorabaa.cultureguide.MEvent;
import com.ghorabaa.cultureguide.R;

import java.util.ArrayList;

public class UserHomepage extends AppCompatActivity implements UserHomepageContract.View{

    private UserHomepageContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_homepage);

        mPresenter = new UserHomepagePresenter(this,getApplicationContext());

        mPresenter.retrieveEvents(EventRetrievalType.Favourite);
    }

    @Override
    public void onRetrieve(ArrayList<MEvent> events) {

    }


    @Override
    public void onFail(String errorMessage) {
        Toast.makeText(getApplicationContext(),errorMessage,Toast.LENGTH_LONG).show();
    }
}
