package com.ghorabaa.cultureguide.UserSidebar.Invitations;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ghorabaa.cultureguide.MEvent;
import com.ghorabaa.cultureguide.R;
import com.ghorabaa.cultureguide.UserEventPage.EventPage.UserEventPage;

import java.util.ArrayList;

public class InvitationsActivity extends AppCompatActivity implements InvitationsContract.View, AdapterView.OnItemClickListener{

    InvitationsContract.Presenter mPresenter;

    ArrayAdapter<String> mArrayAdapter;
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitations);
        mPresenter = new InvitationsPresenter(this,getApplicationContext());
        mPresenter.retrieveInvitations();

        mListView = (ListView) findViewById(R.id.invitations_list);
        mListView.setOnItemClickListener(this);
        //mArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
    }

    @Override
    public void onRetrieveInvitations(ArrayList<Pair<String, MEvent>> invitations) {

        if (invitations.size() == 0){
            Toast.makeText(this,"No Invitaions",Toast.LENGTH_LONG).show();

            return;
        }

        String[] listItems = new String[invitations.size()];

        //testing
        for(int i=0;i<invitations.size();++i) {
            listItems[i] = invitations.get(i).first + " " + getResources().getString(R.string.invited) + " " + invitations.get(i).second.getTitle() ;
        }

        mArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listItems);

        mListView.setAdapter(mArrayAdapter);
    }

    @Override
    public void onRetrieveInvitationsFail(String errorMessage) {
        Toast.makeText(getApplicationContext(),errorMessage,Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        //TODO use i as index in array
        Toast.makeText(getApplicationContext(), i + "", Toast.LENGTH_LONG).show();
        if(mPresenter.isExpired(i))
            Toast.makeText(this, "Invitation is expired, please refresh the page",Toast.LENGTH_LONG).show();
        else
        {
            Intent intent = new Intent(InvitationsActivity.this, UserEventPage.class);
            intent.putExtra("eventID",0);
            startActivity(intent);
        }

    }
}
