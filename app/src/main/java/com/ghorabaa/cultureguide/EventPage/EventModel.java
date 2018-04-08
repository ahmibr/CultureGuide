package com.ghorabaa.cultureguide.EventPage;

/**
 * Created by ruba on 18/03/18.
 */
import android.support.annotation.NonNull;

import com.ghorabaa.cultureguide.MEvent;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class EventModel {

    private DatabaseReference mDatabase ;
    private static EventPresenter mpresenter;


    private static final EventModel ourInstance = new EventModel();


    public static EventModel getInstance(EventPresenter presenter) { ourInstance.mpresenter=presenter;
        return ourInstance;
    }

    private EventModel() {
        mDatabase = FirebaseDatabase.getInstance().getReference();

    }



    public  void AddEvent( MEvent Event)
    {
        Event.ID=(mDatabase.push().getKey()) ;//give each event a unique id
        mDatabase.child("events").child(Event.GetID()).setValue(Event);


    }



    public  void RemoveEvent(MEvent Event)
    {
        mDatabase.child("events").child(Event.GetID()).removeValue();
    }
/*
  parameter to change:name of parameter to be updated
  value:a generic object with the new value


 */

   public void UpdateEvent(MEvent Event,String ParameterToChange,Object value)
   {
       mDatabase.child("events").child(Event.GetID()).child(ParameterToChange).setValue(value);
   }


  public List<MEvent> GetEvents(String OrganiztionID)
  {
       final List<MEvent>Events=new ArrayList<MEvent>();

      mDatabase.child("events").child("organizationCreatedIt").equalTo(OrganiztionID).addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(DataSnapshot dataSnapshot) {

         Events.add(dataSnapshot.getValue(MEvent.class));


          }

          @Override
          public void onCancelled(DatabaseError databaseError) {

          }


      });


      return Events;
  }
}
