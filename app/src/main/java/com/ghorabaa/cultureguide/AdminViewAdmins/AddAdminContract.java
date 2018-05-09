package com.ghorabaa.cultureguide.AdminViewAdmins;

public interface AddAdminContract {

    interface View{

        void onSuccess();
        void onFail(String errorMesage);
    }

    interface Presenter{

        void onInsertion();
        void onFail(String errorMessage);
        void addAdmin(String email, String password);
    }
}
