package com.ghorabaa.cultureguide.EventPage;

import android.content.Context;
import android.os.Bundle;

import android.widget.Toast;

import com.ghorabaa.cultureguide.R;

public class CreateEventActivty extends EventMainActivty {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event_activty);
        mpresenter=new CreateEventPresenter(this);

            }

    @Override
    public boolean IsVerified() {
        //Do verification on data entered through GUI


        return true;
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
        CharSequence text = "Failed to create event";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}


