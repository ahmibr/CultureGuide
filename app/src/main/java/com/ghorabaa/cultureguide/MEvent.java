package com.ghorabaa.cultureguide;

/**
 * Created by ruba on 18/03/18.
 */

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
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

     public MEvent(JSONArray Event)
     {

         try {

             String Title = Event.getJSONObject(0).getString("Title");
             String Description = Event.getJSONObject(0).getString("Description");
             String location = Event.getJSONObject(0).getString("Location");
             String Date = Event.getJSONObject(0).getString("Date");
             String CategoryID=Event.getJSONObject(0).getString("CategoryID");
             SetTitle(Title);
             SetDescription(Description);
             SetLocation(location);

             SetEventDate(Date);
             SetCatName(CatName);



         }
         catch (JSONException e) {
             Log.w("error msg",e.getMessage());
         } catch (ParseException e) {
             e.printStackTrace();
         }
     }

    public String GetDescrption()
    {
     return this.description;
     }

    public String GetTitle()
    {
        return this.title;
    }
    public String GetLocation()
    {
        return this.location;
    }
    public int  GetRating()
    {
        return this.rating;
    }
    public Date GetDate()
    {
         return EventDate;}



    public void SetDescription(String descrption)
    {
        this.description=descrption;
    }
    public void SetID(int ID){this.ID=ID;}
    public int  GetID(){return ID;}
    public void SetTitle(String Title)
    {
        this.title=Title;
    }
    public void SetLocation(String Location)
    {
      this.location=Location;
    }
    public void SetRating(int rating)
    {
       if(rating>=0&&rating<=5)

       {this.rating=rating;}


    }
    public void SetEventDate(String EventDate) throws ParseException {
        DateFormat format =  new SimpleDateFormat(" yyMMddHHmmssZ");
            Date dummy=format.parse(EventDate);
            Date date=new Date();
            if(dummy.after(date) )

            {this.EventDate=format.parse(EventDate);}
    }
    public int  GetOrgID(){return OrgID;}
    public int  GetCatID(){return CatID;}
    public void SetCatName(String CatName)
    {
        this.CatName=CatName;
    }
    public void SetOrgID(int OrgID){this.OrgID=OrgID;}
}
