package com.ghorabaa.cultureguide;

/**
 * Created by ruba on 18/03/18.
 */

import android.util.Log;

import com.ghorabaa.cultureguide.EventPage.EventPresenter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

public class MEvent {

   private String description;
   private String title;
   private String location;
    private int rating;
    private Long EventDate;
    private int ID;
    private int OrgID;
    private int CatID;
    private String CatName;
    private  String OrgName;

    public MEvent(){};

     public MEvent(JSONObject Event) throws Exception {

         try {

             String Title = Event.getString("Title");
             String Description = Event.getString("Description");
             String location = Event.getString("Location");
             String Date = Event.getString("Date");
             String CategoryID=Event.getString("CategoryID");
             SetTitle(Title);
             setDescription(Description);
             setLocation(location);

            setEventDate(Date);
            setCatID(Integer.parseInt(CategoryID));


         }
         catch (JSONException e) {
             Log.w("error msg",e.getMessage());
         }
     }

    public String getDescrption()
    {
     return this.description;
     }

    public String getTitle()
    {
        return this.title;
    }
    public String getLocation()
    {
        return this.location;
    }
    public int getRating()
    {
        return this.rating;
    }
    public Long getDate()
    {

         return EventDate;}



    public void setDescription(String descrption)
    {
        this.description=descrption;
    }
    public void SetID(int ID){this.ID=ID;}
    public int getID(){return ID;}
    public void SetTitle(String Title)
    {
        this.title=Title;
    }
    public void setLocation(String Location)
    {
      this.location=Location;
    }
    public void setRating(int rating)
    {
       if(rating>=0&&rating<=5)

       {this.rating=rating;}


    }
    public void SetEventDate(long EventDate) throws Exception {


        Date dummy= new Date(EventDate);
        this.EventDate= validateDate(dummy);



    }
    public int getOrgID(){return OrgID;}
    public int getCatID(){return CatID;}
    public void setCatName(String CatName)
    {
        this.CatName=CatName;
    }
    public void SetOrgID(int OrgID){this.OrgID=OrgID;}
    public String getCatName(){ return CatName;}
    public void setCatID(int ID)
    {
        this.CatID=ID;
    }
    public void  setEventDate(String Date) throws Exception {

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        try {
            Date date = format.parse(Date);

              this.EventDate= validateDate(date);

        } catch (ParseException e) {
            Log.w("setEventDate error ",e.getMessage());
        }




    }

    public   static  Long  validateDate(Date date) throws Exception {

        Long dummyLong=date.getTime();
        Date dummy =new Date(dummyLong);
        Date now= new Date();
        if(dummy.after(now))

        {
           return dummyLong ;

        }
        else
            throw (new Exception("this date has passed") );

    }

    public void setOrgName(String OrgName)
    {
        this.OrgName=OrgName;
    }

    public String getOrgName ()
    {
        return this.OrgName;
    }
}
