package com.ghorabaa.cultureguide.OrganizationEvent.EventPage;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.ghorabaa.cultureguide.MEvent;
import com.ghorabaa.cultureguide.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class OrgEventPageActivity extends AppCompatActivity implements OrgEventPageContract.View {

    OrgEventPageContract.Presenter mPresenter;
    int mEventID;

    TextView mEventTitle;
    TextView mEventAbout;
    TextView mEventLocation;

    TimePicker mEventTime;

    DatePicker mEventDate;

    Button mEventUpdate;
    Button mEventButton;

    Spinner mCategoriesSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org_event_page);
        mEventID = getIntent().getExtras().getInt("eventId");
        mPresenter = new OrgEventPagePresenter(this,getApplicationContext(),mEventID);
        mPresenter.retrieveEvent();
        mPresenter.retrieveCategories();

        mEventTitle = (TextView) findViewById(R.id.update_event_title);
        mEventAbout = (TextView) findViewById(R.id.update_event_description);
        mEventLocation = (TextView) findViewById(R.id.update_event_location);

        mEventTime = (TimePicker) findViewById(R.id.update_event_time);

        mEventDate = (DatePicker) findViewById(R.id.update_event_date);

        mEventUpdate = (Button) findViewById(R.id.update_page_button);
        mEventUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateEventWithValidations();
            }
        });

        mCategoriesSpinner = (Spinner)findViewById(R.id.update_event_categories);

        mEventButton = (Button)findViewById(R.id.update_event_remove);
        mEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.removeEvent();
            }
        });
    }

    private void updateEventWithValidations(){

        String eventTitle = mEventTitle.getText().toString();
        String  eventAbout = mEventAbout.getText().toString();
        String eventLocation = mEventLocation.getText().toString();

        if(eventTitle.isEmpty() || eventAbout.isEmpty() || eventLocation.isEmpty()){
            Toast.makeText(this,getResources().getString(R.string.blank_field),Toast.LENGTH_LONG).show();
            return;
        }

        Calendar calender = Calendar.getInstance();

        calender.set(Calendar.HOUR, mEventTime.getCurrentHour() - 12);
        calender.set(Calendar.MINUTE, mEventTime.getCurrentMinute());

        calender.set(Calendar.DAY_OF_MONTH, mEventDate.getDayOfMonth());
        calender.set(Calendar.MONTH, mEventDate.getMonth());
        calender.set(Calendar.YEAR, mEventDate.getYear());



        Date eventDate = calender.getTime();

        mPresenter.updateEventDate(eventDate);
        mPresenter.updateEventDescription(eventAbout);
        mPresenter.updateEventLocation(eventLocation);
        mPresenter.updateEventTitle(eventTitle);
        mPresenter.updateEventCategory( mCategoriesSpinner.getSelectedItemPosition() );
    }

    @Override
    public void onRetrieveEvent(MEvent mEvent) {
        //Toast.makeText(getApplicationContext(),mEvent.getTitle(),Toast.LENGTH_SHORT).show();
        mEventTitle.setText(mEvent.getTitle());
        mEventAbout.setText(mEvent.getDescription());
        mEventLocation.setText(mEvent.getLocation());

        Calendar calender = Calendar.getInstance();
        calender.setTime(mEvent.getDate());

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            mEventTime.setHour(calender.get(Calendar.HOUR));
            mEventTime.setMinute(calender.get(Calendar.MINUTE));
        }
        else {
            mEventTime.setCurrentHour(calender.get(Calendar.HOUR));
            mEventTime.setCurrentMinute(calender.get(Calendar.MINUTE));
        }
        mEventDate.updateDate(calender.get(Calendar.YEAR),calender.get(Calendar.MONTH),calender.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void onRetrieveCategories(ArrayList<String> categoryList) {

        String[] categoriesStrings = new String[categoryList.size()];

        //testing
        for(int i=0;i<categoryList.size();++i) {
            Toast.makeText(getApplicationContext(), categoryList.get(i), Toast.LENGTH_LONG);
            categoriesStrings[i] = categoryList.get(i);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.spinner_layout_style, categoriesStrings);

        mCategoriesSpinner.setAdapter(adapter);
    }

    @Override
    public void onRetrieveFail(String errorMessage) {

    }

    @Override
    public void onRemoveEventSuccess() {
        Toast.makeText(this,"Removed Success" , Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void onRemoveEventFail(String errorMessage) {
        Toast.makeText(this,errorMessage , Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpdateSuccess(String message) {
        Toast.makeText(this,"Success" , Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpdateFail(String errorMessage) {
        Toast.makeText(this,errorMessage , Toast.LENGTH_LONG).show();
    }
}
