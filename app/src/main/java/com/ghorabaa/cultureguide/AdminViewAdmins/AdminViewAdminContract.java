package com.ghorabaa.cultureguide.AdminViewAdmins;

import java.util.ArrayList;

/**
 * Created by megem on 5/1/2018.
 */

public interface AdminViewAdminContract {
    interface View{

        void onRetrieve(ArrayList<String> admins);
        void onFail(String errorMessage);
    }

    interface Presenter{

        void retrieveAdmins();
        void onRetrieve(ArrayList<String> admins);
        void onFail(String s);
        void retrieveAdmin(String email);
    }
}
