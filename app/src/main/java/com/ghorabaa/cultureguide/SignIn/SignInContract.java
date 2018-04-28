package com.ghorabaa.cultureguide.SignIn;

/**
 * Created by Ahmed Ibrahim on 3/19/18.
 */

public interface SignInContract {
    interface View {
        void onSignInSuccess();
        void onSignInFail(String errorMessage);
        void routeRegular();
        void routeOrganization();
        void routeAdmin();
    };

    interface Presenter {
        void signIn(String email,String password);
    }
}
