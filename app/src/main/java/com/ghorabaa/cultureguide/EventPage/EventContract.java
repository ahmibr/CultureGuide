package com.ghorabaa.cultureguide.EventPage;

/**
 * Created by ruba on 26/03/18.
 */
import com.ghorabaa.cultureguide.MEvent;
public interface EventContract {
    interface EventView{


        boolean IsVerified();
        void onSuccess();
        void onFail();




    };


}
