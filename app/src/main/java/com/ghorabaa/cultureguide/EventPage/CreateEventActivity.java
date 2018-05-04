package com.ghorabaa.cultureguide.OrganizationEventPage;

import android.content.Context;
import android.os.Bundle;

import android.widget.Toast;

import com.ghorabaa.cultureguide.MEvent;

public class CreateEventActivity extends EventMainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_create_event_activty);
       mpresenter=new EventPresenter(this,super.Appcontext) ;
        MEvent Event=new MEvent();

        Event.setDescription("art event");


       // Event.SetEventDate( (long)2018103012);

        Event.setLocation("Giza");
        Event.SetTitle("Applied art");
        Event.setCatName("ARTS");
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


