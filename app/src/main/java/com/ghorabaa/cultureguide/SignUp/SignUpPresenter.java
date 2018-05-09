package com.ghorabaa.cultureguide.SignUp;

/**
 * Created by Ahmed Ibrahim on 3/16/18.
 */


import android.content.Context;

import com.ghorabaa.cultureguide.UserType;


public class SignUpPresenter implements SignUpContract.Presenter {

    private SignUpContract.View mView; //Reference to View
    private SignUpModel mModel; //Sign Up Model object

    private final static String TAG = "SignUpModule"; //Tag for Log(Debugging)


    /**
     * SignUp Presenter Constructor
     * @param view Activity that handles callbacks
     */
    public SignUpPresenter(SignUpContract.View view, Context context)
    {
        mView = view;
        mModel = new SignUpModel(this,context);
    }

    /**
     * Asks model to register user
     * @param name name of the registered user
     * @param email email of the registered user
     * @param password password of the registered user
     * @param type type of user (Regular,Organization,Admin)
     * @see UserType Enum
     *
     */
    public void signUp(final String name,final String email, String password,final UserType type){

        mModel.register(name, email, password, type);
    }

    /**
     * Callback from Model, when sign up succeeds
     * @param type type of user
     */
    public void onSignUpSuccess(UserType type){
        mView.onSignUpSuccess();
        switch (type){
            case Regular:
                mView.routeRegular();
                break;
            case Organization:
                mView.routeOrganization();
                break;
            default:
                break;
        }
    }

    /**
     * Callback from Model, when sign up fails
     * @param errorMessage Error occurred during sign up process
     */
    public void onSignUpFail(String errorMessage){
        //notify view
        mView.onSignUpFail(errorMessage);
    }

}
