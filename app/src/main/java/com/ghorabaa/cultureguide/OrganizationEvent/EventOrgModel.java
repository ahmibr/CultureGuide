//package com.ghorabaa.cultureguide.OrganizationEvent;
//
///**
// * Created by ruba on 18/03/18.
// */
//
//import android.util.Log;
//
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.ghorabaa.cultureguide.MEvent;
//
//import com.ghorabaa.cultureguide.Utilities.Authenticator;
//import com.ghorabaa.cultureguide.Utilities.DBConnection;
//
//import android.content.Context;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.Locale;
//
//
//public class EventOrgModel{
//
//
//    private static eventOrgPresenter mPresenter;
//    private static DBConnection db;
//    private static Context mContext;
//    private static ArrayList<MEvent> mEvents;
//    private static int eventID;
//    private static EventOrgModel ourInstance;
//
//    public static EventOrgModel getInstance(eventOrgPresenter presenter, Context Mcontext, int eventID) {
//        if (ourInstance == null) {
//            ourInstance = new EventOrgModel();
//            ourInstance.db = DBConnection.getInstance(mContext);
//            ourInstance.mPresenter = presenter;
//            ourInstance.mContext = Mcontext;
//            mEvents = new ArrayList<>();
//        }
//        return ourInstance;
//    }
//
//    private EventOrgModel() {
//    }
//

//
//
//

//
//    public void UpdateEventTitle(String Title) {
//
//        String query = "UPDATE Event SET Title ='%s' WHERE EID = %d";
//        query = String.format(Locale.ENGLISH, query, Title, eventID);
//
//
//        Response.Listener<String> onSuccess = new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                if (response.equals("true"))
//
//                    mPresenter.onSuccess("event updated");
//
//                else if (response.equals("false"))
//                    mPresenter.onFail("please enter a valid event ID");
//                else
//                    mPresenter.onFail("no response");
//
//
//            }
//
//        };
//
//        db.executeQuery(query, onSuccess, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                mPresenter.onFail("connection error");
//
//            }
//        });
//
//
//    }
//
//
//    public void UpdateEventLocation(String location) {
//        String query = "UPDATE Event SET Location ='%s' WHERE EID = %d";
//        query = String.format(Locale.ENGLISH, query, location, eventID);
//
//
//        Response.Listener<String> onSuccess = new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                if (response.equals("true"))
//
//                    mPresenter.onSuccess("event updated");
//
//                else if (response.equals("false"))
//                    mPresenter.onFail("there is no event with this ID");
//                else
//                    mPresenter.onFail("no response");
//            }
//
//        };
//
//        db.executeQuery(query, onSuccess, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                mPresenter.onFail("connection error");
//            }
//        });
//
//
//    }
//
//
//    public void UpdateEventCat(String category) {
//        String query = "UPDATE Event SET CategoryID =%s WHERE EID = %d";
//        query = String.format(Locale.ENGLISH, query, category, eventID);
//
//        Response.Listener<String> onSuccess = new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                if (response.equals("true"))
//
//                    mPresenter.onSuccess("event updated");
//
//                else if (response.equals("false"))
//                    mPresenter.onFail("there is no event with this ID");
//                else
//                    mPresenter.onFail("no response");
//
//
//            }
//
//        };
//
//        db.executeQuery(query, onSuccess, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                mPresenter.onFail("connection error");
//
//            }
//        });
//
//
//    }
//
//    public void UpdateEventDes(String description) {
//        String query = "UPDATE Event SET Description = '%s' WHERE EID = %d";
//        query = String.format(Locale.ENGLISH, query, description, eventID);
//
//
//        Response.Listener<String> onSuccess = new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                if (response.equals("true"))
//
//                    mPresenter.onSuccess("event updated");
//
//                else if (response.equals("false"))
//                    mPresenter.onFail("there is no event with this ID");
//                else
//                    mPresenter.onFail("no response ");
//
//
//            }
//
//        };
//
//        db.executeQuery(query, onSuccess, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                mPresenter.onFail("connection error ");
//
//            }
//        });
//
//
//    }
//
//
//    public void UpdateEventDate(String sDate){
//
//        DateFormat df = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);
//        Date date;
//
//        try {
//            date = df.parse(sDate);
//        } catch (ParseException e) {
//            e.printStackTrace();
//            mPresenter.onFail("Invalid date form!");
//            return;
//        }
//
//        if (!MEvent.isValidDate(date))
//        {
//            mPresenter.onFail("Invalid date!");
//            return;
//        }
//
//        String query = "UPDATE Event SET Date = %d WHERE EID = %d ";
//        query = String.format(Locale.ENGLISH, query, date.getTime(), eventID);
//
//
//        Response.Listener<String> onSuccess = new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                if (response.equals("true"))
//
//                    mPresenter.onSuccess("event updated");
//
//                else if (response.equals("false"))
//                    mPresenter.onFail("there is no event with this ID");
//                else
//                    mPresenter.onFail("no response ");
//
//
//            }
//
//        };
//
//        db.executeQuery(query, onSuccess, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                mPresenter.onFail("connection error ");
//
//            }
//        });
//    }
//
//
//    public void GetEventRate() {
//
//
//        String query = "SELECT AVG(Rate) FROM Rate WHERE Rate.EID= " + eventID;
//        Response.Listener<String> onSuccess = new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//
//                try {
//
//                    JSONArray result = new JSONArray(response);
//                    int rate;
//
//                    rate = Integer.parseInt(result.getJSONObject(0).getString("Rate"));
//                    passToPresenter(rate);
//                } catch (JSONException e) {
//                    Log.w("error msg", e.getMessage());
//                }
//
//
//            }
//        };
//        db.executeQuery(query, onSuccess, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//
//    }
//
//
//    public void getMostcrowded() {
//
//        String query = "SELECT * \n" +
//                "FROM( \n" +
//                " SELECT Organization.ID as OrganizationID,Event.EID as EventID,COUNT(Event.EID) as Attendees FROM Event JOIN Organization ON Event.OID=Organization.ID JOIN Attend on Event.EID=Attend.EID GROUP BY Organization.ID,Event.EID) as A\n" +
//                "WHERE A.OrganizationID=%d\n" +
//                "HAVING A.Attendees= (SELECT MAX(A.Attendees) FROM (SELECT Organization.ID as OrganizationID,Event.EID as EventID,COUNT(Event.EID) as Attendees FROM Event JOIN Organization ON Event.OID=Organization.ID JOIN Attend on Event.EID=Attend.EID GROUP BY Organization.ID,Event.EID) as A)";
//        query = String.format(Locale.ENGLISH,query,Authenticator.getID());
//        Response.Listener<String> onSuccess = new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//
//                try {
//                    JSONArray result = new JSONArray(response);
//                    JSONObject objectResult = result.getJSONObject(0);
//                    int EID = Integer.parseInt(objectResult.getString("EventID"));
//
//
//                    passToPresenter(EID);
//
//
//                } catch (JSONException e) {
//                    Log.w("json error msg", e.getMessage());
//                    mPresenter.onFail(" event not found");
//                } catch (Exception e) {
//                    Log.w("json error msg", e.getMessage());
//                    mPresenter.onFail("un supported date");
//                }
//
//            }
//
//
//        };
//
//        db.executeQuery(query, onSuccess, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//
//
//    }
//
//
//    public void getmostrated() {
//        String query = "SELECT A.EID ,A.average\n" +
//                "FROM (SELECT AVG(Rate) as average ,Rate.EID From Rate  GROUP BY EID) as A join Event on Event.EID=A.EID\n" +
//                "WHERE Event.OID=%d\n" +
//                "HAVING A.average=(SELECT MAX(A.average))\n";
//
//        query = String.format(Locale.ENGLISH,query,Authenticator.getID());
//        Response.Listener<String> onSuccess = new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//
//                try {
//                    JSONArray result = new JSONArray(response);
//                    JSONObject objectResult = result.getJSONObject(0);
//
//                    int ID = (Integer.parseInt(objectResult.getString("EID")));
//
//                    passToPresenter(ID);
//
//
//                } catch (JSONException e) {
//                    Log.w("json error msg", e.getMessage());
//                    onRetrieveFail(" event not found");
//                } catch (Exception e) {
//                    Log.w("json error msg", e.getMessage());
//                    onRetrieveFail("un supported date");
//                }
//
//            }
//
//
//        };
//
//        db.executeQuery(query, onSuccess, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//    }
//
//

//
//
//    @Override
//    public void passToPresenter(MEvent mEvent) {
//        mPresenter.onRetrive(mEvent);
//
//
//    }
//
//
//    public void passToPresenter(int ID) {
//        mPresenter.onRetrive(ID);
//    }
//
//    @Override
//    public void onRetrieveFail(String errorMessage) {
//        mPresenter.onFail(errorMessage);
//    }
//
//}
//
//
