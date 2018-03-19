package com.ghorabaa.cultureguide.Login;

/**
 * Created by Ahmed Ibrahim on 3/19/18.
 */

public interface LoginContract {
    interface LoginView{
        void onLoginSuccess();
        void onLoginFail();
    };

    interface LoginPresenter{
        void signIn(String email,String password);
        void signOut();
    }
}
