package com.ghorabaa.cultureguide.OrganizationEventPage;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.content.Context;

import com.ghorabaa.cultureguide.MEvent;
import com.ghorabaa.cultureguide.R;


public class EventMainActivity extends AppCompatActivity implements EventContract.EventView {
    private static final String TAG = "EventActivity";

     protected EventPresenter mpresenter;
     protected Context Appcontext=getApplicationContext();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_main_activty);

        mpresenter=new EventPresenter(this,Appcontext);
    }

@Override
public void  onRetrive(MEvent event)
{


}

    @Override
    public int geteventID() {
        return 0;
    }

    @Override
    public void onRetrive(int ID) {

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onSuccess(String msg) {

    }

    @Override
    public void onFail(String msg) {

    }
   public void onFail(){};


}