package com.ghorabaa.cultureguide;

/**
 * Created by ruba on 18/03/18.
 */

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

public class MEvent {

    private String description;
    private String title;
    private String location;
    private int rating;
    private Date eventDate;
    private int ID;
    private int orgID;
    private int catID;
    private String catName;
    private String orgName;

    public MEvent() {
    }

    public MEvent(JSONObject Event) throws JSONException {

        String title = Event.getString("Title");
        String description = Event.getString("Description");
        String location = Event.getString("Location");
        long date = Event.getLong("Date");
        int categoryID = Event.getInt("CatID");
        String categoryName = Event.getString("CatName");
        int orgID = Event.getInt("OrgID");
        String orgName = Event.getString("Name");
        int eventID = Event.getInt("EID");

        setID(eventID);
        setTitle(title);
        setDescription(description);
        setLocation(location);
        setDate(date);
        setCatID(categoryID);
        setCatName(categoryName);
        setOrgName(orgName);
        setOrgID(orgID);

    }

    /*
    *   Checks if date is after now
    *
     */
    public static boolean isValidDate(Date date) {
        Date now = new Date();
        if (date.after(now))
            return true;
        else
            return false;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String Title) {
        this.title = Title;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String Location) {
        this.location = Location;
    }

    public int getRating() {
        return this.rating;
    }

    public void setRating(int rating) {
        if (rating >= 0 && rating <= 5)
            this.rating = rating;
    }

    public Date getDate(){
        return eventDate;
    }

    public long getDateLong() {
        return eventDate.getTime();
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setDate(long eventDate) {
        this.eventDate = new Date(eventDate);
    }

    public int getOrgID() {
        return orgID;
    }

    public void setOrgID(int ID) {
        this.orgID = ID;
    }

    public int getCatID() {
        return catID;
    }

    public void setCatID(int ID) {
        this.catID = ID;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public void setDate(Date date) {
        this.eventDate = date;
    }

    public String getOrgName() {
        return this.orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
