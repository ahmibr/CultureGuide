package com.ghorabaa.cultureguide.AdminViewAdmins;

import java.util.ArrayList;

/**
 * Created by megem on 5/1/2018.
 */

public interface AdminViewAdminContract {
    interface View{

        void onRetrieve(ArrayList<String> admins);
        void onFail(String errorMessage);
        void onSuccess();
    }

    interface Presenter{

        void retrieveAdmins();
        void retrieveAdmin(String email);
        void removeAdmin(String email);
    }
}
