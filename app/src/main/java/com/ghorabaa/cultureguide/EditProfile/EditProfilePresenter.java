package com.ghorabaa.cultureguide.EditProfile;

/**
 * Created by Ahmed Ibrahim on 4/21/18.
 */

abstract class EditProfilePresenter implements EditProfileContract.EditProfilePresenter {

    //base edit profile model
    protected EditProfileBaseModel mModel;

    //attached view to send update
    private EditProfileContract.EditProfileView mView;

    /**
     * Constructor of EditProfile Presenter
     * @param view The presenter attached to the model, to handle callbacks
     *
     */

    public EditProfilePresenter(EditProfileContract.EditProfileView view) {
        mView = view;
    }

    /**
     * Asks model to change email
     * @param email the new email address
     */
    public void changeEmail(String email) {
        mModel.changeEmail(email);
    }

    /**
     * Asks model to change password
     * @param password the new password
     */
    public void changePassword(String password) {
        mModel.changePassword(password);
    }

    /**
     * Asks model to change name
     * @param name  the new name
     */
    public void changeName(String name) {
        mModel.changeName(name);
    }

    /**
     * Call back from model in case of success
     * @param successMessage which field is update
     */
    public void onSuccess(String successMessage) {
        mView.onSuccess(successMessage);
    }

    /**
     * Call back from model in case of failure
     * @param errorMessage the error that occurred
     */
    public void onFail(String errorMessage) {
        mView.onFail(errorMessage);
    }
}
