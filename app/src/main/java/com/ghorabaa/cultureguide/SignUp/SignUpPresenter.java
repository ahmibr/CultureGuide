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
     * Registers new user in authentication web service and database
     * @param name name of the registered user
     * @param email email of the registered user
     * @param password password of the registered user
     * @param type type of user (Regular,Organization,Admin)
     * @see UserType Enum
     *
     * @callback view.onSuccessRegister: In case of Success
     * @callback presenter.onFailRegister: In case of Failure, with Error message
     * @return none
     */
    public void signUp(final String name,final String email, String password,final UserType type){

        mModel.register(name, email, password, type);
    }

    /**
     * Callback from Model, when sign up succeeds
     */
    public void onSignUpSuccess(){
        //notify view
        mView.onSignUpSuccess();
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
