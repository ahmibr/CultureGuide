package com.ghorabaa.cultureguide;

/**
 * Created by ruba on 18/03/18.
 */

import java.util.Date;
public class MEvent {

   public String description;
    public String title;

    public String location;
    public int rating;
    public Date EventDate;
    public String ID;

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
        return this.EventDate;
    }
    public void SetDescription(String descrption)
    {
        this.description=descrption;
    }
    public void SetID(String ID){this.ID=ID;}
    public String  GetID(){return ID;}
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
      this.rating=rating;
    }
    public void SetEventDate(Date EventDate)
    {
        this.EventDate=EventDate;
    }


}
