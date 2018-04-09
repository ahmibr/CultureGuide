package com.ghorabaa.cultureguide.EventPage;

/**
 * Created by ruba on 26/03/18.
 */
import com.ghorabaa.cultureguide.MEvent;
import java.util.List;
public interface EventContract {
    interface EventView{



        void onSuccess();
        void onFail(Exception e);
        void onRetrieve(List<MEvent>Events);



    }


}
