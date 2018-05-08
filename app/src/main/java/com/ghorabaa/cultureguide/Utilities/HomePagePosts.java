package com.ghorabaa.cultureguide.Utilities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ghorabaa.cultureguide.MEvent;
import com.ghorabaa.cultureguide.R;
import com.ghorabaa.cultureguide.UserEventPage.EventPage.UserEventPage;

import java.text.SimpleDateFormat;
import java.util.List;

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

    boolean mIsPastEvent;

    boolean mIsOrgView = false;

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

        organizationsEventsInfo = cardsInfo;
    }

    public HomePagePosts(List<MEvent> cardsInfo , ListItemClickListener listener, boolean pastEvent) {
        mNumberItems = cardsInfo.size();
        mOnClickListener = listener;
        viewHolderCount = 0;

        mIsPastEvent = pastEvent;

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
        TextView mPostTitle;
        TextView mPostTime;

        ImageButton mPostAppearance;

        int id;

        int position;
        String initialContent;

        public EventPost(View itemView) {
            super(itemView);

            mPostTitle = (TextView) itemView.findViewById(R.id.post_title);
            mPostAppearance = (ImageButton) itemView.findViewById(R.id.post_image);
            mPostTime = (TextView) itemView.findViewById(R.id.post_time);

            initialContent = mPostTitle.getText().toString();

            itemView.setOnClickListener(this);

            mPostAppearance.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext() , UserEventPage.class);
                    Log.d("Bassel" , id + " of event");
                    intent.putExtra("eventId", id);
                    intent.putExtra("isPast", mIsPastEvent);
                    view.getContext().startActivity(intent);
                }
            } );
        }

        /**
         * Use this function to set the values of the view
         * @param orgnizationName
         * @param eventName
         */
        public void setPostValues(String orgnizationName, String eventName, String eventTime){
            mPostTitle.setText(/*orgnizationName + " " + initialContent + " " +*/eventName);
            mPostTime.setText(eventTime);
        }

        public void bindValue ( int position ) {
            this.position = position;

            id = organizationsEventsInfo.get(position).getID();

            String date = "";//new SimpleDateFormat("MM/dd/yyyy").format( organizationsEventsInfo.get(position).getDate() );
            /*Long dateLong = (Long)organizationsEventsInfo.get(position).getDate();
            if(dateLong != null){
                date = dateLong.toString();
            }*/

            setPostValues( Authenticator.getName()
                    , organizationsEventsInfo.get(position).getTitle()
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
