package com.ghorabaa.cultureguide;

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
import com.ghorabaa.cultureguide.OrganizationEventPage.*;
import com.ghorabaa.cultureguide.SignIn.MainActivity;
import com.ghorabaa.cultureguide.Utilities.Authenticator;
import com.ghorabaa.cultureguide.Utilities.HomePagePosts;

import java.text.ParseException;
import java.util.ArrayList;
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
        Toast.makeText(getApplicationContext(), Authenticator.getEmail(), Toast.LENGTH_LONG).show();

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
        EventOrgPresnter mpresenter = new EventOrgPresnter(this, getApplicationContext());
        mpresenter.retrieveEvents();
// for testing
      /*  MEvent Event = new MEvent();
        Event.setDescription("Animation movie ");
        try {
            Event.setEventDate("2018-06-27 09:31:00 GMT-04:00");
        } catch (Exception e) {

            Log.w("home page error:", e.getMessage());
        }

        Event.setLocation("Cairo");
        Event.SetTitle("the princess and the frog");
        Event.setCatID(5);
        Event.SetOrgID(Authenticator.getID());
        mpresenter.CreatePresenterFun(Event);

       mpresenter.RemoveEventFun();


        mpresenter.UpdatePresenterFun(1, "HunchBack of NotreDame  ");
        mpresenter.UpdatePresenterFun(2,"4");
        mpresenter.UpdatePresenterFun(3,"Disney Animation movie ");
        mpresenter.UpdatePresenterFun(4,"Luxor");
        mpresenter.UpdatePresenterFun(5,"2018-06-27 09:31:00 GMT-04:00");
        mpresenter.UpdatePresenterFun(1, "وبكينا يوم غني الاخرون    ");*/


        //mpresenter.getMostCrowded();
        //mpresenter.getMostrated();
        //mpresenter.getRate();
         //mpresenter.getEventFun();




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
            startActivity(new Intent(HomePage.this, EditOrgActivity.class));
        }
        else if (id == R.id.nav_sign_out){
            //Todo Attach signout in presenter
            startActivity(new Intent(HomePage.this, MainActivity.class));
            finish();
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


    /**
     * This function tests show cards.
     * TODO delete after merging with back
     * @param view
     */
    public void testPosts(View view) throws ParseException {
        String toastMessage = "Hard Coded refresh posts";
        mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);

        mToast.show();

        List<MEvent> eventsTests = new ArrayList<>();
      /*  MEvent event =new MEvent();
        event.SetEventDate("2018/10/30 12:26:18");
        event.SetTitle( "Event_Test_1");
        event.SetOrgID(1);
        eventsTests.add(event);

        event = new MEvent();
        event.SetEventDate("2018/11/30 12:26:18");
        event.SetTitle( "Event_Test_2");
        event.SetOrgID(2);
        eventsTests.add(event);


        event = new MEvent();
        event.SetEventDate("2018/12/30 12:26:18");
        event.SetTitle( "Event_Test_3");
        event.SetOrgID(3);
        eventsTests.add(event);

        showCards(eventsTests);*/

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
    public void onSuccess(String msg) {
        mToast=Toast.makeText(this,msg, Toast.LENGTH_LONG);

        mToast.show();


    }

    @Override
    public void onFail(String msg) {

        mToast=Toast.makeText(this,msg, Toast.LENGTH_LONG);

        mToast.show();

    }

    @Override
    public void onFail() {

    }

    @Override
    public void onRetrieve(MEvent event) {
    mToast=Toast.makeText(this,event.getCatName() , Toast.LENGTH_LONG);

        mToast.show();
        mToast=Toast.makeText(this,event.getOrgName() , Toast.LENGTH_LONG);
        mToast.show();

        mToast=Toast.makeText(this,event.getTitle() , Toast.LENGTH_LONG);
        mToast.show();


    }

    @Override
    public void onRetrieve(ArrayList<MEvent> events) {
        //TODO fill this, Bassel
        //Toast.makeText(getApplicationContext(),Integer.toString(events.size()),Toast.LENGTH_LONG).show();
    }

    // To be exchanged with the EventID through intents and passed to presenter
    @Override
    public int geteventID() {

        return 16;
    }

    @Override
    public void onRetrieve(int ID) {


        mToast=Toast.makeText(this,"the most crowded event is"+ID , Toast.LENGTH_LONG);
        mToast.show();



    }



}
