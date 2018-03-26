package com.ghorabaa.cultureguide;

/**
 * Created by ruba on 18/03/18.
 */
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ghorabaa.cultureguide.MEvent;

public class EventManager {

    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();



    private static final EventManager ourInstance = new EventManager();

    public static EventManager getInstance() {
        return ourInstance;
    }

    private EventManager() {

    }

    public  void AddEvent( MEvent Event)
    {
        Event.SetID(mDatabase.push().getKey()) ;
        mDatabase.child("events").child(Event.GetID()).setValue(Event);
    }



    public  void RemoveEvent(MEvent Event)
    {
        mDatabase.child("events").child(Event.GetID()).setValue(null);
    }

   public void UpdateEvent(MEvent Event,String ParameterToChange,Object value)
   {
       mDatabase.child("events").child(Event.GetID()).child(ParameterToChange).setValue(value);
   }



}
