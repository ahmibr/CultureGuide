package com.ghorabaa.cultureguide;

/**
 * Created by ruba on 18/03/18.
 */

import java.util.Date;
public class MEvent {

    private String description;
    private String ID;
    private String title;
    private String location;
    private int rating;
    private Date EventDate;

    public String GetDescrption()
    {
     return this.description;
     };
    public String GetID()
    {
       return  this.ID;
    };
    public String GetTitle()
    {
        return this.title;
    };
    public String GetLocation()
    {
        return this.location;
    };
    public int  GetRating()
    {
        return this.rating;
    };
    public Date GetDate()
    {
        return this.EventDate;
    };
    public void SetDescription(String descrption)
    {
        this.description=descrption;
    };
    public void SetID(String ID)
    {
        this.ID=ID;
    };
    public void SetTitle(String Title)
    {
        this.title=title;
    };
    public void SetLocation(String Location)
    {
      this.location=location;
    };
    public void SetRating(int rating)
    {
      this.rating=rating;
    };
    public void SetEventDate(Date EventDate)
    {
        this.EventDate=EventDate;
    };


}
