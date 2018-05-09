package com.ghorabaa.cultureguide.AdminViewOrganization;

import android.util.Pair;

import java.util.ArrayList;

/**
 * Created by megem on 5/1/2018.
 */

public interface AdminViewOrganizationContract {
    interface View{

        void onRetrieve(ArrayList<Pair<String, String>> organizations);
        void onFail(String errorMessage);
        void onSuccess();
    }

    interface Presenter{

        void retrieveOrganizations();
        void retrieveOrganization(String email);
        void removeOrganization(String first);
    }
}
