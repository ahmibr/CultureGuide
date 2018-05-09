package com.ghorabaa.cultureguide.AdminViewEvent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
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
    public void onRetrieve(final ArrayList<Pair<Integer, String>> events) {

        mainLay = findViewById(R.id.view_event);
        mainLay.removeViews(1, previousViewsCnt);

        for(int i=0; i<events.size(); i++)
        {
            //Toast.makeText(getApplicationContext(), events.get(i).first + " " + events.get(i).second, Toast.LENGTH_LONG).show();
            linLay = (LinearLayout) View.inflate(this, R.layout.content_admin_view, null);
            ((TextView) linLay.findViewById(R.id.entity)).setText("ID: " + events.get(i).first + System.lineSeparator() + "Name: " + events.get(i).second);
            mainLay.addView(linLay);

            Button removeButton = (Button)linLay.findViewById(R.id.remove_button);
            removeButton.setTag(i);

            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(getApplicationContext(), organizationList.get(Integer.parseInt(view.getTag().toString())).first ,Toast.LENGTH_LONG).show();
                    mPresenter.removeEvent(events.get(Integer.parseInt(view.getTag().toString())).first);
                }
            });
        }

        previousViewsCnt = events.size();
    }

    @Override
    public void onFail(String errorMessage) {

        Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccess() {
        Toast.makeText(getApplicationContext(),"Event Removed Successfully",Toast.LENGTH_LONG).show();
        mPresenter.retrieveEvents();
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
