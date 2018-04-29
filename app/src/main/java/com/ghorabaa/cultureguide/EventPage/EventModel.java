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

        try {
            DBManger.executeQuery(query);


        } catch (Exception e) {
            Log.w("error msg", e.getMessage());
        }




    }



    public  void RemoveEvent(int ID)
    {
        String query="Delete from Event where EID ="+ID;
        try {
            DBManger.executeQuery(query);


        } catch (Exception e) {
            Log.w("error msg", e.getMessage());
        }
    }

   public void UpdateEventTitle(String Title ,int ID)
   {
       String query="UPDATE Event SET Title = "+Title+"WHERE ID ="+ID;
       try {
           DBManger.executeQuery(query);


       } catch (Exception e) {
           Log.w("error msg", e.getMessage());
       }

   }

    public void UpdateEventLocation(String location ,int ID)
    {
        String query="UPDATE Event SET Location = "+location+"WHERE ID ="+ID;
        try {
            DBManger.executeQuery(query);


        } catch (Exception e) {
            Log.w("error msg", e.getMessage());
        }
    }

    public void UpdateEventCat(String category ,int ID)
    {
        String query="UPDATE Event SET CategoryID = "+category+"WHERE ID ="+ID;
        try {
            DBManger.executeQuery(query);


        } catch (Exception e) {
            Log.w("error msg", e.getMessage());
        }


    }

    public void UpdateEventDes(String describtion ,int ID)
    {
        String query="UPDATE Event SET Description = "+describtion+"WHERE ID ="+ID;
        try {
            DBManger.executeQuery(query);


        } catch (Exception e) {
            Log.w("error msg", e.getMessage());
        }
    }
    public void UpdateEventDate(Long date ,int ID)
    {
        final String query="UPDATE Event SET Date = "+date+"WHERE ID ="+ID;


                try {
                    DBManger.executeQuery(query);


                } catch (Exception e) {
                    Log.w("error msg", e.getMessage());
                }





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
        DBManger.executeQuery(query,onSuccess, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        return CatName[0];
    }

    public int GetEventRate(int EventID) {

        final String[] rate = new String[1];
        String query = "select rate from AVG(Rate) where EID= " + EventID;
        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONArray result = new JSONArray(response);

               rate[0] = result.getJSONObject(0).getString("Rate");


                } catch (JSONException e) {
                    Log.w("error msg", e.getMessage());
                }



            }
        };
        DBManger.executeQuery(query,onSuccess, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        return Integer.parseInt(rate[0]);
    }

    public MEvent GetEvent(final int index)
    {
        String query =" select * from Event where ID = "+index;
        final MEvent[] Event = new MEvent[1];

        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray result = new JSONArray(response);


                    Event[0] =new MEvent(result);
                    String CatName=GetCatName(Event[0].GetCatID());


                    Event[0].SetCatName(CatName);
                    Event[0].SetRating(GetEventRate(index));
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


     return Event[0];

    }


    private class Event {
    }
}
