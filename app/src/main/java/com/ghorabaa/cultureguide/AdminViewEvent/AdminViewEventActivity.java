package com.ghorabaa.cultureguide.AdminViewEvent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.widget.Toast;

import com.ghorabaa.cultureguide.R;

import java.util.ArrayList;

public class AdminViewEventActivity extends AppCompatActivity implements AdminViewEventContract.View{

    private AdminViewEventContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_event);

        mPresenter = new AdminViewEventPresenter(this, getApplicationContext());
        mPresenter.retrieveEvents();
    }

    @Override
    public void onRetrieve(ArrayList<Pair<Integer, String>> events) {

        for(int i=0; i<events.size(); i++)
        {
            Toast.makeText(getApplicationContext(), events.get(i).first + " " + events.get(i).second, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onFail(String errorMessage) {

        Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_LONG).show();
    }
}
