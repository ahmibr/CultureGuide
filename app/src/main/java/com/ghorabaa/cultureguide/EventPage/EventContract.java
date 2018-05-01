package com.ghorabaa.cultureguide.EventPage;

/**
 * Created by ruba on 26/03/18.
 */
import com.ghorabaa.cultureguide.MEvent;
import java.util.List;
public interface EventContract {
    interface EventView{



        void onSuccess();
        void onSuccess(String msg);
        void onFail(String msg);
        void onFail();
        void onRetrive(MEvent event);




    }


}
