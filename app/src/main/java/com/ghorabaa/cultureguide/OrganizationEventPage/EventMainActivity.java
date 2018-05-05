package com.ghorabaa.cultureguide.OrganizationEventPage;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.widget.Toast;

import com.ghorabaa.cultureguide.MEvent;
import com.ghorabaa.cultureguide.R;

import java.util.ArrayList;


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
public void onRetrieve(MEvent event)
{


}

    @Override
    public void onRetrieve(ArrayList<MEvent> events) {
        Toast.makeText(getApplicationContext(),Integer.toString(events.size()),Toast.LENGTH_LONG).show();
    }

    @Override
    public int geteventID() {
        return 0;
    }

    @Override
    public void onRetrieve(int ID) {

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
