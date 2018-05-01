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
import org.json.JSONObject;

import java.sql.PreparedStatement;


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

        String query="INSERT INTO Event( OID, Title, CategoryID, Date, Description, Location)"+ "values(%s,%s,%s,%s,%s,%s)";
        query=String.format(query,Event.getOrgID(),Event.getTitle(),Event.getCatID(),Event.getDate(),Event.getDescrption(),Event.getLocation());

        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response=="true")

                    mpresenter.onSuccess("Event created");

                else if(response=="false")
                    mpresenter.onFail(" Insert a valid  category or organization");

                else
                    mpresenter.onFail("معرفش");
            }

        };

        DBManger.executeQuery(query,onSuccess, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });







    }



    public  void RemoveEvent(int ID)
    {
        String query="Delete from Event where EID = "+ID;
        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response=="true")

                    mpresenter.onSuccess("event deleted");

                else if(response=="false")
                    mpresenter.onFail("there is no event with this ID");
                else
                    mpresenter.onFail("معرفش");


            }

        };

        DBManger.executeQuery(query,onSuccess, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

    }

   public void UpdateEventTitle(String Title ,int ID)
   {
       String query="UPDATE Event SET Title = %s WHERE EID = %d";
       query=String.format(query,Title,ID);

       Response.Listener<String> onSuccess = new Response.Listener<String>() {
           @Override
           public void onResponse(String response) {
               if(response=="true")

                   mpresenter.onSuccess("event updated");

               else if (response=="false")
                   mpresenter.onFail("please enter a valid event ID");
               else
                   mpresenter.onFail("معرفش");


           }

       };

       DBManger.executeQuery(query,onSuccess, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {

           }
       });


   }

    public void UpdateEventLocation(String location ,int ID)
    {
        String query="UPDATE Event SET Location = "+location+"WHERE EID ="+ID;
        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response=="true")

                    mpresenter.onSuccess();

                else
                    mpresenter.onFail();


            }

        };

        DBManger.executeQuery(query,onSuccess, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }

    public void UpdateEventCat(String category ,int ID)
    {
        String query="UPDATE Event SET CategoryID = "+category+"WHERE EID ="+ID;
        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response=="true")

                    mpresenter.onSuccess();

                else
                    mpresenter.onFail();


            }

        };

        DBManger.executeQuery(query,onSuccess, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


    }

    public void UpdateEventDes(String describtion ,int ID)
    {
        String query="UPDATE Event SET Description = "+describtion+"WHERE ID ="+ID;
        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response=="true")

                    mpresenter.onSuccess();

                else
                    mpresenter.onFail();


            }

        };

        DBManger.executeQuery(query,onSuccess, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }
    public void UpdateEventDate(Long date ,int ID) {
        final String query = "UPDATE Event SET Date = " + date + "WHERE ID =" + ID;

        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response=="true")

                    mpresenter.onSuccess();

                else
                    mpresenter.onFail();


            }

        };

        DBManger.executeQuery(query,onSuccess, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }

            public void GetCatName(int ID) {

                String query = "SELECT Name from Category where ID =" + ID;
                Response.Listener<String> onSuccess = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            String CatName;
                            MEvent Event = new MEvent();

                            JSONArray result = new JSONArray(response);

                            CatName = result.getJSONObject(0).getString("Name");
                            Event.setCatName(CatName);
                            mpresenter.onRetrive(Event);

                        } catch (JSONException e) {
                            Log.w("error msg", e.getMessage());
                        }


                    }
                };
                DBManger.executeQuery(query, onSuccess, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });


            }

            public void GetEventRate(final int EventID) {


                String query = "select rate from AVG(Rate) where EID= " + EventID;
                Response.Listener<String> onSuccess = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            MEvent Event = new MEvent();
                            Event.SetID(EventID);
                            JSONArray result = new JSONArray(response);
                            int rate;

                            rate = Integer.parseInt(result.getJSONObject(0).getString("Rate"));
                            Event.setRating(rate);
                            mpresenter.onRetrive(Event);

                        } catch (JSONException e) {
                            Log.w("error msg", e.getMessage());
                        }


                    }
                };
                DBManger.executeQuery(query, onSuccess, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
            }


            public void GetEvent(final int index) {
                String query = " select * from Event where EID = " + index;


                Response.Listener<String> onSuccess = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray result = new JSONArray(response);
                            JSONObject objectResult = result.getJSONObject(0);
                            MEvent Event = new MEvent(objectResult);
                            //GetEventRate(index);
                           // GetCatName(index);
                           // Event.setCatName(mpresenter.pEvent.getCatName());
                            //Event.setRating(mpresenter.pEvent.getRating());
                            mpresenter.onRetrive(Event);


                        } catch (JSONException e) {
                            Log.w("json error msg", e.getMessage());
                            mpresenter.onFail(" event not found");
                        }

                    }


                };

                DBManger.executeQuery(query, onSuccess, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });



            }

}


