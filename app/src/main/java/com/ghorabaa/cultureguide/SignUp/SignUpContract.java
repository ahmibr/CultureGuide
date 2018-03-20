package com.ghorabaa.cultureguide.SignUp;

import com.ghorabaa.cultureguide.UserType;

/**
 * Created by Ahmed Ibrahim on 3/19/18.
 */

public interface SignUpContract {
    interface SignUpView {
        void onSignUpSuccess();
        void onSignUpFail();
    };

    interface SignUpPresenter{
        void signUp(String name,String email, String password,UserType type);
    }
}
