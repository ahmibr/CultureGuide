package com.ghorabaa.cultureguide.EventPage;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;

import android.widget.Toast;

import com.ghorabaa.cultureguide.MEvent;
import com.ghorabaa.cultureguide.R;

import java.util.List;


public class EventMainActivity extends AppCompatActivity implements EventContract.EventView {
    private static final String TAG = "EventActivity";

     protected EventPresenter mpresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_main_activty);
    }



    @Override
    public void onSuccess() {

    }

    @Override
    public void onFail(Exception e) {

    }

    @Override
    public void onRetrieve(List<MEvent> Events) {

    }
}
