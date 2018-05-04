package com.ghorabaa.cultureguide.OrganizationEventPage;

/**
 * Created by ruba on 18/03/18.
 */

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ghorabaa.cultureguide.EventPageBaseModel;
import com.ghorabaa.cultureguide.MEvent;

import com.ghorabaa.cultureguide.Utilities.DBConnection;
import com.google.firebase.database.DatabaseReference;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;


public class EventOrgModel extends EventPageBaseModel {

    private DatabaseReference mDatabase;
    private static EventPresenter mpresenter;
    private static DBConnection DBManger;
    private static Context context;
   


    private static EventOrgModel ourInstance;

    public static EventOrgModel getInstance(EventPresenter presenter, Context Mcontext, int eventID) {
        if (ourInstance == null) {
            ourInstance = new EventOrgModel(DBManger,eventID);
            ourInstance.mpresenter = presenter;
            ourInstance.context = Mcontext;
        }
        return ourInstance;
    }

    private EventOrgModel(DBConnection db, int eventID) {
        super(db,eventID);
        DBManger = DBConnection.getInstance(context);


    }


    public void AddEvent(MEvent Event) {

        String query = "INSERT INTO Event( OID, Title, CategoryID, Date, Description, Location) Values(%d,'%s',%d,%tQ,'%s','%s')";
        query = String.format(Locale.ENGLISH,query, Event.getOrgID(), Event.getTitle(), Event.getCatID(), Event.getDate(), Event.getDescrption(), Event.getLocation());

        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response=="true")

                    mpresenter.onSuccess("Event created");

                else if(response=="false")
                    mpresenter.onFail(" Insert a valid  category or organization");

                else
                    mpresenter.onFail("no response");
            }

        };


        DBManger.executeQuery(query,onSuccess, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mpresenter.onFail("connection error");

            }
        });


        //DBManger.executeQuery(query);
        mpresenter.onSuccess("event created");


    }


    public void RemoveEvent() {
        String query = "Delete from Event where EID = " + eventID;
       Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response=="true")

                    mpresenter.onSuccess("event deleted");

                else if(response=="false")
                    mpresenter.onFail("there is no event with this ID");
                else
                    mpresenter.onFail("no response");


            }

        };

        DBManger.executeQuery(query,onSuccess, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mpresenter.onFail("connection error");
            }
        });
        //DBManger.executeQuery(query);
        mpresenter.onSuccess("event removed");

    }

    public void UpdateEventTitle(String Title) {

        String query = "UPDATE Event SET Title ='%s' WHERE EID = %d";
        query = String.format(Locale.ENGLISH,query, Title, eventID);

        //DBManger.executeQuery(query);




      Response.Listener<String> onSuccess = new Response.Listener<String>() {
           @Override
           public void onResponse(String response) {
               if(response=="true")

                   mpresenter.onSuccess("event updated");

               else if (response=="false")
                   mpresenter.onFail("please enter a valid event ID");
               else
                   mpresenter.onFail("no response");


           }

       };

       DBManger.executeQuery(query,onSuccess, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {

           }
       });

        mpresenter.onSuccess("event updated");
    }

    public void UpdateEventLocation(String location) {
        String query = "UPDATE Event SET Location ='%s' WHERE EID = %d";
        query=String.format(Locale.ENGLISH,query,location, eventID);
        //DBManger.executeQuery(query);


       Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response=="true")

                    mpresenter.onSuccess();

                else if(response=="false")
                    mpresenter.onFail("there is no event with this ID");
                else
                    mpresenter.onFail("no response");
            }

        };

        DBManger.executeQuery(query,onSuccess, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mpresenter.onFail("connection error");
            }
        });

        mpresenter.onSuccess("event updated");
    }

    public void UpdateEventCat(String category) {
        String query = "UPDATE Event SET CategoryID =%s WHERE EID = %d";
        query = String.format(Locale.ENGLISH,query, category,  eventID);
        //DBManger.executeQuery(query);

        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response=="true")

                    mpresenter.onSuccess();

                else if(response=="false")
                    mpresenter.onFail("there is no event with this ID");
                else
                    mpresenter.onFail("no response");


            }

        };

        DBManger.executeQuery(query,onSuccess, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mpresenter.onFail("connection error");

            }
        });


        mpresenter.onSuccess("event updated");

    }

    public void UpdateEventDes(String describtion) {
        String query = "UPDATE Event SET Description = '%s' WHERE EID = %d";
        query = String.format(Locale.ENGLISH,query, describtion,  eventID);
        //DBManger.executeQuery(query);


        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response=="true")

                    mpresenter.onSuccess();

                else if(response=="false")
                    mpresenter.onFail("there is no event with this ID");
                else
                    mpresenter.onFail("no response ");


            }

        };

        DBManger.executeQuery(query,onSuccess, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mpresenter.onFail("connection error ");

            }
        });

        mpresenter.onSuccess("event updated");
    }


    public void UpdateEventDate(String date ) throws Exception {

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        try {
            java.util.Date dummy = format.parse(date);

              Long validDate= MEvent.validateDate(dummy);
            String query = "UPDATE Event SET Date = %tQ WHERE EID = %d ";
            query=String.format(Locale.ENGLISH,query,validDate, eventID);
            //DBManger.executeQuery(query);

       Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response=="true")

                    mpresenter.onSuccess();

                else if(response=="false")
                    mpresenter.onFail("there is no event with this ID");
                else
                    mpresenter.onFail("no response ");


            }

        };

        DBManger.executeQuery(query,onSuccess, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mpresenter.onFail("connection error ");

            }
        });

        mpresenter.onSuccess("event updated");
        } catch (ParseException e) {
            Log.w("setEvent string ",e.getMessage());
            mpresenter.onFail("wrong date format");
        }

    }


    public void GetEventRate() {


                String query = "SELECT AVG(Rate) FROM Rate WHERE Rate.EID= " + eventID;
                Response.Listener<String> onSuccess = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            MEvent Event = new MEvent();
                            Event.SetID(eventID);
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


            public void GetEvent() {
                String query = " SELECT Event.EID,Title,Description,Event.Date,Location,Category.Name as CatName,Category.ID as CatID,Organization.Name,Organization.ID as OrgID FROM `Event`,`Category`,`Organization` WHERE Event.CategoryID=Category.ID&& Event.OID=Organization.ID&& Event.EID= " + eventID;


                Response.Listener<String> onSuccess = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray result = new JSONArray(response);
                            JSONObject objectResult = result.getJSONObject(0);
                            MEvent Event = new MEvent(objectResult);

                            mpresenter.onRetrive(Event);




                        } catch (JSONException e) {
                            Log.w("json error msg", e.getMessage());
                            mpresenter.onFail(" event not found");
                        } catch (Exception e) {
                            Log.w("json error msg", e.getMessage());
                            mpresenter.onFail("un supported date");
                        }

                    }


                };

                DBManger.executeQuery(query, onSuccess, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });



            }

            public void getMostcrowded()
            {

                String query = " SELECT A.eventID, A.AttendTimes" +
                        "FROM (SELECT EID as eventID,COUNT(EID) as AttendTimes FROM Attend GROUP BY EID)as A " +
                        "HAVING A.AttendTimes=(SELECT MAX(A.AttendTimes) FROM   (SELECT EID as eventID,COUNT(EID) as AttendTimes FROM Attend GROUP BY EID) as A)" ;


                Response.Listener<String> onSuccess = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray result = new JSONArray(response);
                            JSONObject objectResult = result.getJSONObject(0);
                            MEvent Event = new MEvent(objectResult);

                            mpresenter.onRetrive(Event);




                        } catch (JSONException e) {
                            Log.w("json error msg", e.getMessage());
                            mpresenter.onFail(" event not found");
                        } catch (Exception e) {
                            Log.w("json error msg", e.getMessage());
                            mpresenter.onFail("un supported date");
                        }

                    }


                };

                DBManger.executeQuery(query, onSuccess, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });


            }


    public void getmostrated()
    {
        String query = " SELECT A.eventID, A.AttendTimes" +
                "FROM (SELECT EID as eventID,COUNT(EID) as AttendTimes FROM Attend GROUP BY EID)as A " +
                "HAVING A.AttendTimes=(SELECT MAX(A.AttendTimes) FROM   (SELECT EID as eventID,COUNT(EID) as AttendTimes FROM Attend GROUP BY EID) as A)" ;


        Response.Listener<String> onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray result = new JSONArray(response);
                    JSONObject objectResult = result.getJSONObject(0);
                    MEvent Event = new MEvent(objectResult);

                    mpresenter.onRetrive(Event);




                } catch (JSONException e) {
                    Log.w("json error msg", e.getMessage());
                    mpresenter.onFail(" event not found");
                } catch (Exception e) {
                    Log.w("json error msg", e.getMessage());
                    mpresenter.onFail("un supported date");
                }

            }


        };

        DBManger.executeQuery(query, onSuccess, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }

    @Override
    public void passToPresenter(MEvent mEvent) {
        mpresenter.onRetrive(mEvent);
        
    }

    @Override
    public void onRetrieveFail(String errorMessage) {
        mpresenter.onFail(errorMessage);
    }

}


