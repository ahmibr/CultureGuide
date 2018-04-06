package com.ghorabaa.cultureguide.EditProfile;

/**
 * Created by Ahmed Ibrahim on 4/6/18.
 */

public interface EditOrgContract {
    public interface EditOrgView{
        void onSuccess(String SuccessMessage);
        void onFail(String SuccessMessage);
    }
    public interface EditOrgPresenter{
        public void changeEmail(String email);
        public void changePassword(String password);
        public void changeName(String name);
    }
}
