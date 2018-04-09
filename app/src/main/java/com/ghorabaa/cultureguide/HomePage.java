package com.ghorabaa.cultureguide;

import android.content.Context;
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
import com.ghorabaa.cultureguide.EventPage.EventContract;
import com.ghorabaa.cultureguide.EventPage.RetrieveEventPresenter;
import com.ghorabaa.cultureguide.Utilities.HomePagePosts;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public  class HomePage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener  , HomePagePosts.ListItemClickListener ,EventContract.EventView{

    //TODO remove this toast after testing
    //Used for Testing Processes
    private Toast mToast;
    RecyclerView mPosts;
    HomePagePosts mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

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
         * Use this setting to improve performance if you know that changes in content do not
         * change the child layout size in the RecyclerView
         */
        //mPosts.setHasFixedSize(true);

        /*
         * The HomePagePostsAdapeter is responsible for displaying each item in the list.
         */
        mAdapter = new HomePagePosts(0, this);
        mPosts.setAdapter(mAdapter);

        RetrieveEventPresenter mpresenter = new RetrieveEventPresenter( this);
        String Id="dxZCPcOYXuTaMYumAy58pPGMdiC3";
        mpresenter.RunRetrival(Id);



    }

    @Override
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
            startActivity(new Intent(HomePage.this, EditOrgActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * This callback is invoked when you click on an item in the list.
     *
     * @param clickedItemIndex Index in the list of the item that was clicked.
     */
    @Override
    public void onListItemClick(int clickedItemIndex) {
        Log.d("Bassel","Click happened");
        /*
         * Even if a Toast isn't showing, it's okay to cancel it. Doing so
         * ensures that our new Toast will show immediately, rather than
         * being delayed while other pending Toasts are shown.
         *
         * Comment out these three lines, run the app, and click on a bunch of
         * different items if you're not sure what I'm talking about.
         */
        if (mToast != null) {
            mToast.cancel();
        }

        // COMPLETED (12) Show a Toast when an item is clicked, displaying that item number that was clicked
        /*
         * Create a Toast and store it in our Toast field.
         * The Toast that shows up will have a message similar to the following:
         *
         *                     Item #42 clicked.
         */
        String toastMessage = "Item #" + clickedItemIndex + " clicked.";
        mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);

        mToast.show();
    }


    public void testPosts(View view){
        String toastMessage = "Go to create event";
        mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);

        mToast.show();
    }

    /**
     * Created by BasselMostafa
     *
     * call this function to show posts of events on home screen
     *
     * @param cardsInfo array of MEvents classes
     */

    public void showCards(List<MEvent> cardsInfo){


        mAdapter = new HomePagePosts(cardsInfo,this);
        mPosts.setAdapter(mAdapter);
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFail(Exception e) {

    }

    @Override
    public void onRetrieve(List<MEvent> Events) {
        //showCards(Events);


    }
}
