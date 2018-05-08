package com.ghorabaa.cultureguide.OrganizationEvent.Homepage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ghorabaa.cultureguide.EditProfile.EditOrgActivity;
import com.ghorabaa.cultureguide.MEvent;
import com.ghorabaa.cultureguide.OrganizationEvent.CreateEvent.CreateEventActivity;
import com.ghorabaa.cultureguide.R;
import com.ghorabaa.cultureguide.SignIn.MainActivity;
import com.ghorabaa.cultureguide.Utilities.HomePagePosts;

import java.util.ArrayList;
import java.util.List;

public  class OrgHomepageActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener  , HomePagePosts.ListItemClickListener ,OrgHomepageContract.View{


    private Toast mToast;
    RecyclerView mPosts;
    HomePagePosts mAdapter;

    private OrgHomepageContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mPosts = (RecyclerView) findViewById(R.id.HomepageEvents);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mPosts.setLayoutManager(layoutManager);

        /*
         * The HomePagePostsAdapeter is responsible for displaying each item in the list.
         */
        mAdapter = new HomePagePosts(0, this);
        mPosts.setAdapter(mAdapter);


        mPresenter = new OrgHomepagePresenter(this,getApplicationContext());
        mPresenter.retrieveEvents();
    }

    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Log.d("Bassel","choosed " + id);

        if (id == R.id.nav_edit_profile) {
            startActivity(new Intent(OrgHomepageActivity.this, EditOrgActivity.class));
        }
        else if (id == R.id.nav_sign_out){
            //Todo Attach signout in presenter
            startActivity(new Intent(OrgHomepageActivity.this, MainActivity.class));
            finish();
        }else if (id == R.id.nav_create_event){
            createEvent(null);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    void createEvent(View view){
        startActivity(new Intent(OrgHomepageActivity.this, CreateEventActivity.class));
    }

    /**
     * This callback is invoked when you click on an item in the list.
     *
     * @param clickedItemIndex Index in the list of the item that was clicked.
     */
    @Override
    public void onListItemClick(int clickedItemIndex) {

    }

    public void refreshPosts(View view){
        //eventOrgPresenter mpresenter = new eventOrgPresenter(this, getApplicationContext());
        //mpresenter.retrieveEvents();
    }

    /**
     * Created by BasselMostafa
     *
     * call this function to show posts of events on home screen
     *
     * @param cardsInfo array of MEvents classes
     */
    public void showCards(List<MEvent> cardsInfo){
        mAdapter = new HomePagePosts(cardsInfo,this , true, true );
        mPosts.setAdapter(mAdapter);
    }


    @Override
    public void onRetrieveEvents(ArrayList<MEvent> events) {
        showCards(events);
    }

    @Override
    public void onRetrieveFail(String errorMessage) {
        Toast.makeText(getApplicationContext(),errorMessage,Toast.LENGTH_LONG);
    }
}
