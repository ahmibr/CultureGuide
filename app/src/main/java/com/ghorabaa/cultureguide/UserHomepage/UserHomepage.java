package com.ghorabaa.cultureguide.UserHomepage;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.ghorabaa.cultureguide.EditProfile.EditUserActivity;
import com.ghorabaa.cultureguide.EventRetrievalType;
import com.ghorabaa.cultureguide.MEvent;
import com.ghorabaa.cultureguide.R;
import com.ghorabaa.cultureguide.SignIn.MainActivity;
import com.ghorabaa.cultureguide.UserSidebar.Favorites.FavoritesActivity;
import com.ghorabaa.cultureguide.UserSidebar.Friends.FriendsActivity;
import com.ghorabaa.cultureguide.UserSidebar.Invitations.InvitationsActivity;
import com.ghorabaa.cultureguide.Utilities.SectionsPagesAdapter;

import java.util.ArrayList;

public class UserHomepage extends AppCompatActivity implements UserHomepageContract.View,
        NavigationView.OnNavigationItemSelectedListener {

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

        Toolbar toolbar = (Toolbar) findViewById(R.id.user_tool_bar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.user_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.user_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onRetrieve(ArrayList<MEvent> events) {
        switch (requestedType){
            case Past:
                mPastEvents.showCards(events);
                break;
            case Upcoming:
                mUpcomingEvents.showCards(events);
                break;
            case Favourite:
                mFavoriteEvents.showCards(events);
                break;
        }
    }

    private void setupViewPager (ViewPager viewPager){
        SectionsPagesAdapter adapter = new SectionsPagesAdapter(getSupportFragmentManager());

        adapter.addFragment(mUpcomingEvents, getResources().getString(R.string.upcoming_events));
        adapter.addFragment(mPastEvents,getResources().getString(R.string.past_events));
        adapter.addFragment(mFavoriteEvents, getResources().getString(R.string.favorite_events));

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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        
        if (id == R.id.nav_edit_profile) {
            startActivity(new Intent(UserHomepage.this, EditUserActivity.class));
        }
        else if(id == R.id.nav_friends) {
            startActivity(new Intent(UserHomepage.this, FriendsActivity.class));
        }
        else if (id == R.id.nav_sign_out){
            startActivity(new Intent(UserHomepage.this, MainActivity.class));
            finish();
        }
        else if (id == R.id.nav_invitations){
            startActivity(new Intent(UserHomepage.this, InvitationsActivity.class));
        }
        else if (id == R.id.nav_favorites){
            startActivity(new Intent(UserHomepage.this, FavoritesActivity.class));
        }
        else if (id == R.id.nav_interests){

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.user_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
