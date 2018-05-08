package com.ghorabaa.cultureguide.OrganizationEvent.CreateEvent;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.ghorabaa.cultureguide.R;

import java.util.ArrayList;

public class CreateEventActivity extends AppCompatActivity implements CreateEventContract.View
{
    CreateEventContract.Presenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event_activty);

        mPresenter = new CreateEventPresenter(this,getApplicationContext());
        mPresenter.retrieveCategories();
    }

    @Override
    public void onCreateEventSuccess() {

    }

    @Override
    public void onCreateEventFail(String errorMessage) {

    }

    @Override
    public void onRetrieveCategories(ArrayList<String> categories) {

        //testing
        for(int i=0;i<categories.size();++i)
            Toast.makeText(getApplicationContext(),categories.get(i),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRetrieveFail(String errorMessage) {

    }
}


