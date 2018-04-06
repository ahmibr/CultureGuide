package com.ghorabaa.cultureguide.EditProfile;

/**
 * Created by Ahmed Ibrahim on 4/6/18.
 */

public class EditOrgPresenter {

    EditOrgModel mModel;

    EditOrgContract.EditOrgView mView;

    public EditOrgPresenter(EditOrgContract.EditOrgView view){
        mView = view;

        mModel = new EditOrgModel(this);
    }

    public void changeEmail(String email){
        mModel.changeEmail(email);
    }

    public void changePassword(String password){
        mModel.changePassword(password);
    }

    public void changeName(String name){
        mModel.changeName(name);
    }

    public void onSuccess(String successMessage){
        mView.onSuccess(successMessage);
    }

    public void onFail(String errorMessage){
        mView.onFail(errorMessage);
    }
}
