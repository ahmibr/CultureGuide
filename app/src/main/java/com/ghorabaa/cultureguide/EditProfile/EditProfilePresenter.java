package com.ghorabaa.cultureguide.EditProfile;

/**
 * Created by Ahmed Ibrahim on 4/21/18.
 */

abstract class EditProfilePresenter implements EditProfileContract.EditProfilePresenter {

    protected EditProfileModel mModel;

    private EditProfileContract.EditProfileView mView;

    public EditProfilePresenter(EditProfileContract.EditProfileView view) {
        mView = view;
    }

    public void changeEmail(String email) {
        mModel.changeEmail(email);
    }

    public void changePassword(String password) {
        mModel.changePassword(password);
    }

    public void changeName(String name) {
        mModel.changeName(name);
    }

    public void onSuccess(String successMessage) {
        mView.onSuccess(successMessage);
    }

    public void onFail(String errorMessage) {
        mView.onFail(errorMessage);
    }
}
