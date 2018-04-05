package com.ghorabaa.cultureguide.SignIn;

/**
 * Created by Ahmed Ibrahim on 3/19/18.
 */

public interface SignInContract {
    interface SignInView {
        void onSignInSuccess();
        void onSignInFail(String text);
    };

}
