package com.ghorabaa.cultureguide;

/**
 * Created by ruba on 18/03/18.
 */

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

public class MEvent {

   private String description;
   private String title;
   private String location;
    private int rating;
    private Date EventDate;
    private int ID;
    private int OrgID;
    private int CatID;
    private String CatName;
    public MEvent(){};

     public MEvent(JSONObject Event)
     {

         try {

             String Title = Event.getString("Title");
             String Description = Event.getString("Description");
             String location = Event.getString("Location");
             String Date = Event.getString("Date");
             String CategoryID=Event.getString("CategoryID");
             SetTitle(Title);
             setDescription(Description);
             setLocation(location);

             SetEventDate(Long.parseLong(Date));
             setCatName(CatName);



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
    public Date getDate()
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
    public void SetEventDate(long EventDate) {

        Date dummy = new Date(EventDate);
        SimpleDateFormat dateformat = new SimpleDateFormat("MMM dd, yyyy HH:mm");
        Date date= new Date();
            if(dummy.after(date) )

            {this.EventDate=dummy;}
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
}
