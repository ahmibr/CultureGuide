package com.ghorabaa.cultureguide.EventPage;

import android.util.Log;

import com.ghorabaa.cultureguide.MEvent;

/**
 * Created by ruba on 26/03/18.
 */

public class RemoveEventPresenter extends EventPresenter {


    RemoveEventPresenter(EventContract.EventView view) {
        super(view);

    }

    @Override
    public void RunPresenter(MEvent Event) {


        try

        {
            super.mModel.RemoveEvent(Event);
            mview.onSuccess();

        } catch (Exception e)

        {
            mview.onFail(e);

            Log.w("error:", e);

        }
    }

}
