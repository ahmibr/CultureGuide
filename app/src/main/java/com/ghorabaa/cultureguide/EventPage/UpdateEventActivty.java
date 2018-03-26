package com.ghorabaa.cultureguide.EventPage;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.ghorabaa.cultureguide.R;

public class UpdateEventActivty extends EventMainActivty {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_event_activty);

    }

    @Override
    public boolean IsVerified() {


        // get the parameters to be updated from GUI
        return true;
    }

    @Override
    public void onSuccess() {
        Context context = getApplicationContext();
        CharSequence text = "event data updated successfully";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }

    @Override
    public void onFail() {

        Context context = getApplicationContext();
        CharSequence text = "Failed to update event data";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }
}
