package com.ghorabaa.cultureguide.EventPage;
import android.util.Log;
import com.ghorabaa.cultureguide.MEvent;
import com.google.android.gms.tasks.OnCompleteListener;

/**
 * Created by ruba on 26/03/18.
 */

public class CreateEventPresenter extends EventPresenter {


    CreateEventPresenter(EventContract.EventView view)

    {
        super(view);


    }




    @Override
    public void RunPresenter(MEvent Event) {
        try

        {
            super.mModel.AddEvent(Event);
            mview.onSuccess();

        }
        catch(Exception e)

        { mview.onFail(e);

        Log.w("error:",e);}







    }
}
