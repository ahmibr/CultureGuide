package com.ghorabaa.cultureguide.AdminViewAdmins;

public interface AddAdminContract {

    interface View{

        void onSuccess();
        void onFail(String errorMesage);
    }

    interface Presenter{

        void onInsertion();
        void onFail(String errorMesage);
        void addAdmin(String email, String password);
    }
}
