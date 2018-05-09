package com.ghorabaa.cultureguide.EditProfile;

/**
 * Created by Ahmed Ibrahim on 4/6/18.
 */

public interface EditProfileContract {
    interface EditProfileView {
        void onSuccess(String successMessage);
        void onFail(String failMessage);
    }
    interface EditProfilePresenter {
        public void changeEmail(String email);
        public void changePassword(String password);
        public void changeName(String name);
    }
}
