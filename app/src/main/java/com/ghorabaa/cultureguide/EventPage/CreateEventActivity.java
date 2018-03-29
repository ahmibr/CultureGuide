package com.ghorabaa.cultureguide.EventPage;

import android.content.Context;
import android.os.Bundle;

import android.widget.Toast;

import com.ghorabaa.cultureguide.MEvent;
import com.ghorabaa.cultureguide.R;
import java.util.Date;
public class CreateEventActivity extends EventMainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event_activty);
        mpresenter=new CreateEventPresenter(this);
        MEvent Event=new MEvent();
        Date MyDate= new Date();

        Event.description="art event";

        Event.EventDate=MyDate;
        Event.location="Giza";
        Event.rating=5;
        Event.title="ARTS";


        mpresenter.RunPresenter(Event);

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
    public void onFail(Exception e) {
        Context context = getApplicationContext();
        CharSequence text = e.getMessage();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}


