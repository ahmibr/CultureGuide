package com.ghorabaa.cultureguide.Utilities;

import android.content.Context;
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

import com.ghorabaa.cultureguide.R;

import java.sql.Struct;
import java.util.ArrayList;
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

    //private OrganizationEventCardInfo[] organizationEventCardsInfo;

    List<OrganizationEventCardInfo> organizationsEventsInfo;


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
        organizationsEventsInfo = new ArrayList<OrganizationEventCardInfo>();

        OrganizationEventCardInfo o = new OrganizationEventCardInfo();
        o.organizationName = "XYZ";
        o.eventName = "ABC";
        organizationsEventsInfo.add( o );
        o = new OrganizationEventCardInfo();
        o.organizationName = "سصط";
        o.eventName = "ابت";
        organizationsEventsInfo.add( o );
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

    class EventPost extends  RecyclerView.ViewHolder
            implements View.OnClickListener {

        //The views in the frameLayout
        TextView postTitle;

        ImageButton postAppearance;

        Button attendButton;


        int position;
        String initialContent;

        public EventPost(View itemView) {
            super(itemView);

            postTitle = (TextView) itemView.findViewById(R.id.postTitle);
            postAppearance = (ImageButton) itemView.findViewById(R.id.postImage);
            attendButton = (Button) itemView.findViewById(R.id.postAttend);

            initialContent = postTitle.getText().toString();

            Log.d("Bassel","Initial Content is " + initialContent);

            itemView.setOnClickListener(this);

            attendButton.setOnClickListener( new View.OnClickListener(){
                @Override
                public void onClick(View view) {

                    String toastText = organizationsEventsInfo.get(position%2).organizationName + " created " + organizationsEventsInfo.get(position%2).eventName;
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
        public void setPostValues(String orgnizationName,String eventName){
            postTitle.setText(orgnizationName + " " + initialContent + " " + eventName);
        }

        public void bindValue ( int position ) {
            this.position = position;

            setPostValues( organizationsEventsInfo.get(position%2).organizationName + Integer.toString(position)
                    , organizationsEventsInfo.get(position%2).eventName + Integer.toString(position * 3));
        }

        //TODO please implement the onClick of the imageButton and attend + find a way of saving the event in the view


        @Override
        public void onClick(View v) {
            Log.d("Bassel","Click happened");
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }

    public class OrganizationEventCardInfo {
        public String organizationName;
        public String eventName;

        public String time;
    }
}
