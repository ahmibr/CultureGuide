package com.ghorabaa.cultureguide.OrganizationEventPage;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;


import com.ghorabaa.cultureguide.R;

public class RemoveEventActivity extends EventMainActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_event);
        mpresenter=new EventPresenter( this,super.Appcontext);
        int ID=2;

        mpresenter.RemoveEventFun(2);

    }




    @Override
    public void onFail() {
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
