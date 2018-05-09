package com.ghorabaa.cultureguide.Utilities;

/**
 * Created by Ahmed Ibrahim on 4/27/18.
 */

/**
 * Helper class to cache user data to be used allover the application
 */
public class Authenticator {

    private static int id;
    private static String email = "";
    private static String name = "";
    private static boolean loggedIn = false;

    //Don't instantiate
    private Authenticator(){};

    public static void setID(int newID){
        id = newID;
    }

    public static void setEmail(String newEmail){
        email = newEmail;
    }

    public static void setLoggedIn(boolean newLoggedIn){
        loggedIn = newLoggedIn;
    }

    public static int getID(){
        return id;
    }

    public static String getEmail(){
        return email;
    }

    public static boolean isLoggedIn(){
        return loggedIn;
    }

    public static void logOut(){
        loggedIn = false;
        email = "";
        name = "";
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Authenticator.name = name;
    }
}
