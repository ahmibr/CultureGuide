package com.ghorabaa.cultureguide.AdminViewUser;

import android.content.Context;

import com.android.volley.Response;
import com.ghorabaa.cultureguide.Utilities.DBConnection;

/**
 * Created by megem on 5/1/2018.
 */

public class AdminViewUserModel {

    Context mContext;
    DBConnection db;
    AdminViewUserContract.Presenter mPresenter;

    public void getUsers() {

        String query = "SELECT Email, Name FROM AppUser";

        Response.Listener onSucccess = new Response.Listener() {


            @Override
            public void onResponse(Object response) {


            }
        }
    }
}
