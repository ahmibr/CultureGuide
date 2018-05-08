package com.ghorabaa.cultureguide.OrganizationEvent.CreateEvent;

import android.os.Build;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.ghorabaa.cultureguide.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CreateEventActivity extends AppCompatActivity implements CreateEventContract.View
{
    CreateEventContract.Presenter mPresenter;

    private EditText mEventTitle;
    private EditText mEventAbout;
    private EditText mEventLocation;

    private Spinner mCategoriesSpinner;

    private Button mEventCreate;

    private DatePicker mEventDate;

    private TimePicker mEventTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event_activty);

        mPresenter = new CreateEventPresenter(this,getApplicationContext());
        mPresenter.retrieveCategories();

        mEventTitle = (EditText) findViewById(R.id.create_event_title);
        mEventAbout = (EditText) findViewById(R.id.create_event_about);
        mEventLocation = (EditText) findViewById(R.id.create_event_location);

        mEventCreate = (Button) findViewById(R.id.create_event_button);
        mEventCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createEventWithValidations();
            }
        });

        mEventDate = (DatePicker) findViewById(R.id.create_event_date);
        Date currentDate = new Date();
        mEventDate.setMinDate(currentDate.getTime());

        mEventTime = (TimePicker) findViewById(R.id.create_event_time);

        mCategoriesSpinner = (Spinner) findViewById(R.id.create_event_categories);


    }

    private void createEventWithValidations(){

        String eventTitle = mEventTitle.getText().toString();
        String  eventAbout = mEventAbout.getText().toString();
        String eventLocation = mEventLocation.getText().toString();

        if(eventTitle.isEmpty() || eventAbout.isEmpty() || eventLocation.isEmpty()){
            Toast.makeText(this,getResources().getString(R.string.blank_field),Toast.LENGTH_LONG).show();
            return;
        }

        Calendar calender = Calendar.getInstance();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            calender.set(Calendar.HOUR, mEventTime.getHour() - 12);
            calender.set(Calendar.MINUTE, mEventTime.getMinute());
        }
        else {
            calender.set(Calendar.HOUR, mEventTime.getCurrentHour() - 12);
            calender.set(Calendar.MINUTE, mEventTime.getCurrentMinute());
        }
        calender.set(Calendar.DAY_OF_MONTH, mEventDate.getDayOfMonth());
        calender.set(Calendar.MONTH, mEventDate.getMonth());
        calender.set(Calendar.YEAR, mEventDate.getYear());



        Date eventDate = calender.getTime();

        mPresenter.createEvent(eventTitle,eventAbout,eventLocation,eventDate,mCategoriesSpinner.getSelectedItemPosition());

    }

    @Override
    public void onCreateEventSuccess() {
        Toast.makeText(this, "Success", Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void onCreateEventFail(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRetrieveCategories(ArrayList<String> categories) {

        String[] categoriesStrings = new String[categories.size()];

        //testing
        for(int i=0;i<categories.size();++i) {
            Toast.makeText(getApplicationContext(), categories.get(i), Toast.LENGTH_LONG);
            categoriesStrings[i] = categories.get(i);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.spinner_layout_style, categoriesStrings);

        mCategoriesSpinner.setAdapter(adapter);
    }

    @Override
    public void onRetrieveFail(String errorMessage) {

    }
}


