package com.ghorabaa.cultureguide.OrganizationEvent.CreateEvent;

import android.content.Context;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Ahmed Ibrahim on 5/8/18.
 */

public class CreateEventPresenter implements CreateEventContract.Presenter {

    private CreateEventContract.View mView;
    private CreateEventModel mModel;

    CreateEventPresenter(CreateEventContract.View view, Context context){
        mView = view;
        mModel = new CreateEventModel(this,context);
    }
    @Override
    public void createEvent(String title, String description, String location, Date date, int cIndex) {
        mModel.createEvent(title,description,location,date,cIndex);
    }

    @Override
    public void retrieveCategories() {
        mModel.retrieveCategories();
    }

    void onCreateEventSuccess(){
        mView.onCreateEventSuccess();
    }

    void onCreateEventFail(String errorMessage){
        mView.onCreateEventFail(errorMessage);
    }

    void onRetrieveCategories(ArrayList<String> categories){
        mView.onRetrieveCategories(categories);
    }

    void onRetrieveFail(String errorMessage){
        mView.onRetrieveFail(errorMessage);
    }
}
