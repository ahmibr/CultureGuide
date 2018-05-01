package com.ghorabaa.cultureguide.UserHomepage;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.ghorabaa.cultureguide.EventRetrievalType;
import com.ghorabaa.cultureguide.MEvent;
import com.ghorabaa.cultureguide.R;
import com.ghorabaa.cultureguide.Utilities.SectionsPagesAdapter;

import java.util.ArrayList;

public class UserHomepage extends AppCompatActivity implements UserHomepageContract.View{

    private UserHomepageContract.Presenter mPresenter;

    private ViewPager mViewPager;

    private PastEventsFragment mPastEvents = new PastEventsFragment();
    private FavoriteEventsFragment mFavoriteEvents = new FavoriteEventsFragment();
    private UpcomingEventsFragment mUpcomingEvents = new UpcomingEventsFragment();

    private EventRetrievalType requestedType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_homepage);

        mPresenter = new UserHomepagePresenter(this,getApplicationContext());

        mPresenter.retrieveEvents(EventRetrievalType.Upcoming);
        requestedType = EventRetrievalType.Upcoming;

        mPastEvents.setUserHomePage(this);
        mFavoriteEvents.setUserHomePage(this);
        mUpcomingEvents.setUserHomePage(this);

        mViewPager = (ViewPager) findViewById(R.id.user_view_pager);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.user_tab_lay_out);
        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onRetrieve(ArrayList<MEvent> events) {
        switch (requestedType){
            case Past:
                Toast.makeText(this,"Requested Past events " + events.size(),Toast.LENGTH_LONG).show();
                mPastEvents.showCards(events);
                break;
            case Upcoming:
                Toast.makeText(this,"Requested Upcoming events " + events.size(),Toast.LENGTH_LONG).show();
                mUpcomingEvents.showCards(events);
                break;
            case Favourite:
                Toast.makeText(this,"Requested Favorite events " + events.size(),Toast.LENGTH_LONG).show();
                mFavoriteEvents.showCards(events);
                break;
        }
    }

    private void setupViewPager (ViewPager viewPager){
        SectionsPagesAdapter adapter = new SectionsPagesAdapter(getSupportFragmentManager());

        adapter.addFragment(mUpcomingEvents, "Upcoming Events");
        adapter.addFragment(mPastEvents,"Past Events");
        adapter.addFragment(mFavoriteEvents, "Favorite Events");

        viewPager.setAdapter(adapter);
    }

    public void getUpcomingEvents(){
        mPresenter.retrieveEvents( EventRetrievalType.Upcoming );
        requestedType = EventRetrievalType.Upcoming;
    }

    public void getFavoriteEvents(){
        mPresenter.retrieveEvents( EventRetrievalType.Favourite );
        requestedType = EventRetrievalType.Favourite;
    }

    public void getPastEvents(){
        mPresenter.retrieveEvents( EventRetrievalType.Past );
        requestedType = EventRetrievalType.Past;
    }

    @Override
    public void onFail(String errorMessage) {
        Toast.makeText(getApplicationContext(),errorMessage,Toast.LENGTH_LONG).show();
    }
}
