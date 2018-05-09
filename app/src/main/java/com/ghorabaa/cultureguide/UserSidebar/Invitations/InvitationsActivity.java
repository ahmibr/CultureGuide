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

    /**
     * Activity That handles Invitations.
     * Created by Bassel
     */

    InvitationsContract.Presenter mPresenter;

    ArrayAdapter<String> mArrayAdapter;
    ListView mListView;
    private ArrayList<Pair<String, MEvent>> invitations;

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

    /**
     * CallBack function from BackEnd
     */
    @Override
    public void onRetrieveInvitations(ArrayList<Pair<String, MEvent>> invitations) {

        if (invitations.isEmpty()){
            Toast.makeText(this,"No Invitations",Toast.LENGTH_LONG).show();

            return;
        }

        this.invitations = invitations;
        String[] listItems = new String[invitations.size()];

        //testing
        for(int i=0;i<invitations.size();++i) {
            listItems[i] = invitations.get(i).first + " " + getResources().getString(R.string.invited) + " " + invitations.get(i).second.getTitle() ;
        }

        mArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listItems);

        mListView.setAdapter(mArrayAdapter);
    }

    /**
     * CallBack function from BackEnd
     */
    @Override
    public void onRetrieveInvitationsFail(String errorMessage) {
        Toast.makeText(getApplicationContext(),errorMessage,Toast.LENGTH_LONG).show();
        finish();
    }

    /**
     * CallBack function from BackEnd
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        if(mPresenter.isExpired(i))
            Toast.makeText(this, "Invitation is expired, please refresh the page",Toast.LENGTH_LONG).show();
        else
        {
            Intent intent = new Intent(InvitationsActivity.this, UserEventPage.class);
            MEvent event = invitations.get(i).second;
            int eventID = event.getID();
            intent.putExtra("eventId",eventID);
            startActivity(intent);
        }

    }
}
