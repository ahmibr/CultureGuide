package com.ghorabaa.cultureguide.OrganizationEventPage;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.ghorabaa.cultureguide.R;

public class UpdateEventActivity extends EventMainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_event_activty);
        mpresenter=new EventPresenter(this,super.Appcontext) ;

        int funcid=1;
        String title="art";
        int eventid=1;
        ((EventOrgPresnter)mpresenter).UpdatePresenterFun(funcid,title);


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
