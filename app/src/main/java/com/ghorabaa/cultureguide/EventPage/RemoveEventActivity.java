package com.ghorabaa.cultureguide.EventPage;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;


import com.ghorabaa.cultureguide.MEvent;
import com.ghorabaa.cultureguide.R;

public class RemoveEventActivity extends EventMainActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_event);
        mpresenter=new RemoveEventPresenter(this);
        MEvent Event=new MEvent();
        Event.ID="0";
        mpresenter.RunPresenter(Event);

    }




    @Override
    public void onFail(Exception e) {
        Context context = getApplicationContext();
        CharSequence text = "Failed to remove event";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }

    @Override
    public void onSuccess() {

        Context context = getApplicationContext();
        CharSequence text = "event removed successfully";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }
}
