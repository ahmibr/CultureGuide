package com.ghorabaa.cultureguide.OrganizationEventPage;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ghorabaa.cultureguide.EventPageBaseModel;
import com.ghorabaa.cultureguide.MEvent;
import com.ghorabaa.cultureguide.Utilities.DBConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ruba on 04/05/18.
 */

public class EventUserModel extends EventPageBaseModel {
    private static EventPresenter mpresenter;

    private static Context context;
    private static DBConnection DBManger;



    private static EventUserModel ourInstance;

    public static EventUserModel getInstance(EventPresenter presenter, Context Mcontext, int eventID) {
        if (ourInstance == null) {
            ourInstance = new EventUserModel( DBManger,  eventID);
            ourInstance.mpresenter = presenter;
            ourInstance.context = Mcontext;
            DBManger=DBConnection.getInstance(context);

        }
        return ourInstance;
    }

    private EventUserModel(DBConnection db, int eventID) {
        super(db,eventID);



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



    @Override
    public void passToPresenter(MEvent mEvent) {
        mpresenter.onRetrive(mEvent);

    }

    @Override
    public void onRetrieveFail(String errorMessage) {
        mpresenter.onFail(errorMessage);

    }
}
