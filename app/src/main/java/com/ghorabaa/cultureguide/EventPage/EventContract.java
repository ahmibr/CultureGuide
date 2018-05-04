package com.ghorabaa.cultureguide.OrganizationEventPage;

/**
 * Created by ruba on 26/03/18.
 */
import com.ghorabaa.cultureguide.MEvent;

public interface EventContract {
    interface EventView{



        void onSuccess();
        void onSuccess(String msg);
        void onFail(String msg);
        void onFail();
        void onRetrive(MEvent event);




    }


}
