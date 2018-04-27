package com.ghorabaa.cultureguide.EventPage;

/**
 * Created by ruba on 18/03/18.
 */

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ghorabaa.cultureguide.MEvent;

import com.ghorabaa.cultureguide.Utilities.Authenticator;
import com.ghorabaa.cultureguide.Utilities.DBConnection;
import com.google.firebase.database.DatabaseReference;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;


public class EventModel {

    private DatabaseReference mDatabase ;
    private static EventPresenter mpresenter;
    private DBConnection DBManger;
    private static Context context;


    private static  EventModel ourInstance;

    public static EventModel getInstance(EventPresenter presenter,Context Mcontext) {
        if(ourInstance==null) {
            ourInstance = new EventModel();
            ourInstance.mpresenter = presenter;
            ourInstance.context = Mcontext;
        }
        return ourInstance;
    }

    private EventModel() {

        DBManger=DBConnection.getInstance(context);


    }



    public  void AddEvent( MEvent Event)
    {

        String query="Insert into Event (OID,Title,CategoryID,Date,Description,Location)"+ "values("+
               "'" +Authenticator.getID()+"'"+","+"'"+Event.GetTitle()+"'"+","+"'"+Event.GetCatID()+
                "'"+","+Event.GetDate()+"'"+","+"'"+Event.GetDescrption()+"'"+","+"'"+Event.GetLocation()+"'"+")";

       DBManger.executeQuery(query);




    }



    public  void RemoveEvent(int ID)
    {
        String query="Delete from Event where EID ="+ID;
        DBManger.executeQuery(query);
    }

   public void UpdateEventTitle(String Title ,int ID)
   {
       String query="UPDATE Event SET Title = "+Title+"WHERE ID ="+ID;
       DBManger.executeQuery(query);

   }

    public void UpdateEventLocation(String location ,int ID)
    {
        String query="UPDATE Event SET Location = "+location+"WHERE ID ="+ID;
        DBManger.executeQuery(query);

    }

    public void UpdateEventCat(String category ,int ID)
    {
        String query="UPDATE Event SET CategoryID = "+category+"WHERE ID ="+ID;
        DBManger.executeQuery(query);


    }

    public void UpdateEventDes(String describtion ,int ID)
    {
        String query="UPDATE Event SET Description = "+describtion+"WHERE ID ="+ID;
        DBManger.executeQuery(query);

    }
    public void UpdateEventDate(Long date ,int ID)
    {
        String query="UPDATE Event SET Date = "+date+"WHERE ID ="+ID;
        DBManger.executeQuery(query);
    }

    public String GetCatName(int ID)
    {
        final String[] CatName = new String[1];

        String query="SELECT Name from Category where ID ="+ID;
        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONArray result = new JSONArray(response);

                    CatName[0] = result.getJSONObject(0).getString("Name");


                } catch (JSONException e) {
                    Log.w("error msg",e.getMessage());
                }


            }
        };

        return CatName[0];
    }


    public MEvent GetEvent(int index)
    {  final MEvent Event =new MEvent();
        String query =" select * from Event where ID = "+index;

        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray result = new JSONArray(response);

                    String Title = result.getJSONObject(0).getString("Title");
                    String Description = result.getJSONObject(0).getString("Description");
                    String location = result.getJSONObject(0).getString("Location");
                    String Date = result.getJSONObject(0).getString("Date");
                    String CategoryID=result.getJSONObject(0).getString("CategoryID");
                    String CatName=GetCatName(Integer.parseInt(CategoryID));
                    Event.SetTitle(Title);
                    Event.SetDescription(Description);
                    Event.SetLocation(location);
                    //Event.SetEventDate(Date);
                    Event.SetCatName(CatName);
                    mpresenter.onSuccess();


                }
                catch (JSONException e) {
                   Log.w("error msg",e.getMessage());
                }

            }


        };

DBManger.executeQuery(query,onSuccess, new Response.ErrorListener() {
    @Override
    public void onErrorResponse(VolleyError error) {

    }
});


     return Event;

    }





}
