package com.ghorabaa.cultureguide.AdminViewEvent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ghorabaa.cultureguide.R;

import java.util.ArrayList;

public class AdminViewEventActivity extends AppCompatActivity implements AdminViewEventContract.View{

    private AdminViewEventContract.Presenter mPresenter;

    private LinearLayout mainLay;
    private LinearLayout linLay;

    private int previousViewsCnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_event);

        previousViewsCnt = 0;

        mPresenter = new AdminViewEventPresenter(this, getApplicationContext());
        mPresenter.retrieveEvents();
    }

    @Override
    public void onRetrieve(ArrayList<Pair<Integer, String>> events) {

        mainLay = findViewById(R.id.view_event);

        for(int i=0; i<events.size(); i++)
        {
            //Toast.makeText(getApplicationContext(), events.get(i).first + " " + events.get(i).second, Toast.LENGTH_LONG).show();
            linLay = (LinearLayout) View.inflate(this, R.layout.content_admin_view, null);
            ((TextView) linLay.findViewById(R.id.entity)).setText("ID: " + events.get(i).first + System.lineSeparator() + "Name: " + events.get(i).second);
            mainLay.addView(linLay);
        }
    }

    @Override
    public void onFail(String errorMessage) {

        Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_LONG).show();
    }

    public void onSearchClicked(View view) {

        try{
            int id = Integer.parseInt(((EditText) findViewById(R.id.event_id)).getText().toString());
            mPresenter.retrieveEvent(id);
        }

        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),"Please enter a valid ID", Toast.LENGTH_LONG).show();
        }
    }
}
