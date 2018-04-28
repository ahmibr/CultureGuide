package com.ghorabaa.cultureguide.SignUp;

import com.ghorabaa.cultureguide.UserType;

/**
 * Created by Ahmed Ibrahim on 3/19/18.
 */

public interface SignUpContract {
    interface View {
        void onSignUpSuccess();
        void onSignUpFail(String errorMessage);
    };

    interface Presenter {
        void signUp(String name,String email, String password,UserType type);
    }
}
