package com.ghorabaa.cultureguide.OrganizationEventPage;

/**
 * Created by ruba on 26/03/18.
 */
import com.ghorabaa.cultureguide.MEvent;

import java.util.ArrayList;

public interface EventContract {
    interface EventView{



        void onSuccess();
        void onSuccess(String msg);
        void onFail(String msg);
        void onFail();
        void onRetrieve(MEvent event);
        void onRetrieve(ArrayList<MEvent> events);
        int geteventID();
        void onRetrieve(int ID);




    }


}
