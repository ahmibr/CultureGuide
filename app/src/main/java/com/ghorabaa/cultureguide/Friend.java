package com.ghorabaa.cultureguide;

/**
 * Created by Ahmed Ibrahim on 5/1/18.
 */

public class Friend {

    private int id;
    private String email;
    private String name;


    public Friend(int id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
