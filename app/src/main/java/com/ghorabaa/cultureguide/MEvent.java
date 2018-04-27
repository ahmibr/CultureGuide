package com.ghorabaa.cultureguide;

/**
 * Created by ruba on 18/03/18.
 */

import java.util.Date;
public class MEvent {

   private String description;
   private String title;
   private String location;
    private int rating;
    private Long EventDate;
    private String ID;
    private String OrgID;
    private String CatID;
    private String CatName;



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
    public String GetDate()
    {

       String year=Long.toString(this.EventDate/10000);
       String Month =Long.toString((this.EventDate%10000)/100);
       String Day=Long.toString(((this.EventDate%10000)/100)/10);


        return year+"-"+Month+"-"+Day;
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
    public void SetEventDate(Long EventDate)
    {
        this.EventDate=EventDate;
    }
    public String  GetOrgID(){return OrgID;}
    public String  GetCatID(){return CatID;}
    public void SetCatName(String CatName)
    {
        this.CatName=CatName;
    }

}
