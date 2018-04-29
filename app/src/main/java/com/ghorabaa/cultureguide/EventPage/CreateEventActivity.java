package com.ghorabaa.cultureguide.EventPage;

import android.content.Context;
import android.os.Bundle;

import android.widget.Toast;

import com.ghorabaa.cultureguide.MEvent;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateEventActivity extends EventMainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_create_event_activty);
       mpresenter=new EventPresenter(this,super.Appcontext) ;
        MEvent Event=new MEvent();

        Event.SetDescription("art event");

        try {
            Event.SetEventDate( "20181030122618");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Event.SetLocation("Giza");
        Event.SetTitle("Applied art");
        Event.SetCatName("ARTS");
        mpresenter.CreatePresenterFun(Event);







    }




    @Override
    public void onSuccess() {
        Context context = getApplicationContext();
        CharSequence text = " Event is created";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }

    @Override
    public void onFail() {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        String text="failed to add event";

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}


