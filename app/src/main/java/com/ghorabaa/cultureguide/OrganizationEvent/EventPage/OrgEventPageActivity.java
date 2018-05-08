package com.ghorabaa.cultureguide.OrganizationEvent.EventPage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.ghorabaa.cultureguide.MEvent;
import com.ghorabaa.cultureguide.R;

import java.util.ArrayList;

public class OrgEventPageActivity extends AppCompatActivity implements OrgEventPageContract.View {

    OrgEventPageContract.Presenter mPresenter;
    int mEventID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org_event_page);
        mEventID = getIntent().getExtras().getInt("eventId");
        mPresenter = new OrgEventPagePresenter(this,getApplicationContext(),mEventID);
        mPresenter.retrieveEvent();
        mPresenter.retrieveCategories();
    }

    @Override
    public void onRetrieveEvent(MEvent mEvent) {
        Toast.makeText(getApplicationContext(),mEvent.getTitle(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRetrieveCategories(ArrayList<String> categoryList) {
        for(int i=0;i<categoryList.size();++i)
            Toast.makeText(getApplicationContext(),categoryList.get(i),Toast.LENGTH_SHORT).show();
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

    @Override
    public void onUpdateSuccess(String message) {

    }

    @Override
    public void onUpdateFail(String errorMessage) {

    }
}
