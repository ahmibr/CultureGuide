package com.ghorabaa.cultureguide.Utilities;

import android.content.Context;
import android.os.Handler;
import android.service.notification.NotificationListenerService;
import android.support.design.widget.Snackbar;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ghorabaa.cultureguide.MEvent;
import com.ghorabaa.cultureguide.R;

import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import static android.content.ContentValues.TAG;

/**
 * Created by Bassel Mostafa on 4/5/2018.
 *
 * This adapter class controls the recycler view in the home page
 */

public class HomePagePosts extends RecyclerView.Adapter<HomePagePosts.EventPost> {


    private int mNumberItems;

    /*
     * An on-click handler that we've defined to make it easy for an Activity to interface with
     * our RecyclerView
     */
    final private ListItemClickListener mOnClickListener;

    /**
     * The interface that receives onClick messages.
     */
    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }


    private static int viewHolderCount;


    List<MEvent> organizationsEventsInfo;


    /**
     * Constructor for HomeAdapter that accepts a number of items to display and the specification
     * for the ListItemClickListener.
     *
     * @param numberOfItems Number of items to display in list
     * @param listener Listener for list item clicks
     */
    public HomePagePosts(int numberOfItems, ListItemClickListener listener) {
        mNumberItems = numberOfItems;
        mOnClickListener = listener;
        viewHolderCount = 0;

        //TODO delete this chunk of test code after testing and merging with back
    }

    public HomePagePosts(List<MEvent> cardsInfo , ListItemClickListener listener) {
        mNumberItems = cardsInfo.size();
        mOnClickListener = listener;
        viewHolderCount = 0;

        //TODO delete this chunk of test code after testing and merging with back
        organizationsEventsInfo = cardsInfo;
    }

    @Override
    public HomePagePosts.EventPost onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.activity_home_page_card;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        EventPost viewHolder = new EventPost(view);

        viewHolderCount++;
        Log.d(TAG, "onCreateViewHolder: number of ViewHolders created: "
                + viewHolderCount);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(EventPost holder, int position) {
        Log.d("onBindViewHolder", "#" + position);

        holder.bindValue(position);
    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }

    public void setNumberOfPosts(int count){
        mNumberItems = count;
    }

    class EventPost extends  RecyclerView.ViewHolder
            implements View.OnClickListener {

        //The views in the frameLayout
        TextView postTitle;
        TextView postTime;

        ImageButton postAppearance;

        Button attendButton;

        int id;

        int position;
        String initialContent;

        public EventPost(View itemView) {
            super(itemView);

            postTitle = (TextView) itemView.findViewById(R.id.post_title);
            postAppearance = (ImageButton) itemView.findViewById(R.id.post_image);
            attendButton = (Button) itemView.findViewById(R.id.post_attend);
            postTime = (TextView) itemView.findViewById(R.id.post_time);

            attendButton.setClickable(false);

            initialContent = postTitle.getText().toString();

            itemView.setOnClickListener(this);

            attendButton.setOnClickListener( new View.OnClickListener(){
                @Override
                public void onClick(View view) {

                    String toastText = organizationsEventsInfo.get(position).GetTitle() + " created " + organizationsEventsInfo.get(position).GetTitle();
                    Toast.makeText(view.getContext(),toastText,Toast.LENGTH_LONG).show();
                }
            } );
        }

        //Call this if the user of the app is an organization
        public void ViewerIsOrganiztion(){
            attendButton.setClickable(false);
        }

        /**
         * Use this function to set the values of the view
         * @param orgnizationName
         * @param eventName
         */
        public void setPostValues(String orgnizationName, String eventName, String eventTime){
            postTitle.setText(orgnizationName + " " + initialContent + " " + eventName);
            postTime.setText(eventTime);
        }

        public void bindValue ( int position ) {
            this.position = position;

            id = organizationsEventsInfo.get(position).GetID();

            String date = "";
            if(organizationsEventsInfo.get(position).GetDate() != null){
                date = organizationsEventsInfo.get(position).GetDate().toString();
            }

            setPostValues( Authenticator.getName()
                    , organizationsEventsInfo.get(position).GetTitle()
                    , date);
        }

        //TODO please implement the onClick of the imageButton and attend + find a way of saving the event in the view


        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }
}
