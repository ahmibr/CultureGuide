package com.ghorabaa.cultureguide.UserHomepage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.ghorabaa.cultureguide.MEvent;
import com.ghorabaa.cultureguide.R;
import com.ghorabaa.cultureguide.Utilities.HomePagePosts;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bassel Mostafa on 4/30/2018.
 */

public class PastEventsFragment extends Fragment
implements HomePagePosts.ListItemClickListener
{
    private static final String TAG = "PastEventsFragment";

    RecyclerView mPosts;
    HomePagePosts mAdapter;

    Button mRefreshButtons;

    UserHomepage mUserPage;

    public void setUserHomePage(UserHomepage userHomepage) {
        mUserPage = userHomepage;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.user_homepage_past_events, container , false );

        mPosts = (RecyclerView) view.findViewById(R.id.past_events);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mPosts.setLayoutManager(layoutManager);


        mAdapter = new HomePagePosts(0,this);
        mPosts.setAdapter(mAdapter);

        mRefreshButtons = (Button) view.findViewById(R.id.past_events_refresh);

        mRefreshButtons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUserPage.getPastEvents();
            }
        });

        return view;
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {

    }

    public void showCards(List<MEvent> cardsInfo){
        mAdapter = new HomePagePosts(cardsInfo,this);
        mPosts.setAdapter(mAdapter);
    }

    public void refreshPosts(View view){
        Toast.makeText(getActivity(),"Refreshing past views",Toast.LENGTH_LONG).show();
    }
}
