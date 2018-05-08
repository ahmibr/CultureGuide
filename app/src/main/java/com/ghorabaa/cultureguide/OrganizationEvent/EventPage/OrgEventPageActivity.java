package com.ghorabaa.cultureguide.OrganizationEvent.EventPage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ghorabaa.cultureguide.MEvent;
import com.ghorabaa.cultureguide.R;

public class OrgEventPageActivity extends AppCompatActivity implements OrgEventPageContract.View{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org_event_page);
    }

    @Override
    public void onRetrieveEvent(MEvent mEvent) {

    }

    @Override
    public void onRetrieveFail(String errorMessage) {

    }

    @Override
    public void onRemoveEventSuccess() {

    }

    @Override
    public void onRemoveEventFail(String errorMessage) {

    }
}
