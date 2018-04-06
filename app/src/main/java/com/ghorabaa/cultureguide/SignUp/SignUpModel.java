package com.ghorabaa.cultureguide.SignUp;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.ghorabaa.cultureguide.*;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Ahmed Ibrahim on 3/20/18.
 */

public class SignUpModel {

    //Reference to sign up presenter
    private SignUpPresenter mPresenter; //handles callbacks

    //Reference to Firebase Database
    private DatabaseReference mRef;

    //Reference to Firebase Authentecation web service
    private FirebaseAuth mAuth;

    //Tag for Log(Debugging)
    final static private String TAG = "SignUpModel";

    /**
     * Constructor of SignUp Model
     * @param presenter The presenter attached to the model, to handle callbacks
     *
     */
    public SignUpModel(SignUpPresenter presenter){
        mPresenter = presenter;
        mRef = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
    }

    /**
     * Registers new user in authentication web service and database
     * @param name name of the registered user
     * @param email email of the registered user
     * @param password password of the registered user
     * @param type type of user (Regular,Organization,Admin)
     * @see UserType Enum
     *
     * @callback presenter.onSuccessRegister: In case of Success
     * @callback presenter.onFailRegister: In case of Failure, with Error message
     * @return none
     */
    public void register(final String name,final String email, String password,final UserType type){

        //Register user at Firebase Authentication
        Task<AuthResult> signUpTask = mAuth.createUserWithEmailAndPassword(email,password);


        signUpTask.addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign up success
                            Log.d(TAG, "createUserWithEmail:success");

                            //Unique id, to avoid collision
                            String id = mAuth.getCurrentUser().getUid();

                            //Check type of user to save in database
                            switch(type) {
                                case Regular:
                                    registerRegular(id,name,email);
                                    break;

                                case Organization:
                                    registerOrganization(id,name,email);
                                    break;

                                case Admin:
                                    registerAdmin(id,name,email);
                                    break;

                                default:
                                    break;
                            }

                            //notify presenter
                            mPresenter.onSignUpSuccess();

                        } else {
                            // Sign Up Failed
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());

                            //Weak Password (Less than 6 Characters)
                            if(task.getException() instanceof FirebaseAuthWeakPasswordException)
                                mPresenter.onSignUpFail("Password should be at least 6 characters!");

                            //Invalid email format
                            else if(task.getException() instanceof FirebaseAuthInvalidCredentialsException)
                                mPresenter.onSignUpFail("Invalid email format!");

                            //Email already used
                            else if(task.getException() instanceof FirebaseAuthUserCollisionException)
                                mPresenter.onSignUpFail("This email is already registered!");

                            //Any other reason
                            else
                                mPresenter.onSignUpFail("Sign Up Failed");
                        }

                        // ...
                    }
                });
    }


    /**
     * Saves new Regular user info into the database
     * @param id unique id of the user
     * @param name  name of the user
     * @param email email of the user
     *
     * @return none
     */
    private void registerRegular(final String id,final String name,final String email){

        mRef.child("user").child(id).child("email").setValue(email);
        mRef.child("user").child(id).child("name").setValue(name);

    }

    /**
     * Saves new Organization user info into the database
     * @param id unique id of the user
     * @param name  name of the user
     * @param email email of the user
     *
     * @return none
     */
    private void registerOrganization(final String id,final String name,final String email){

        mRef.child("organization").child(id).child("email").setValue(email);
        mRef.child("organization").child(id).child("name").setValue(name);

    }

    /**
     * Saves new Admin user info into the database
     * @param id unique id of the user
     * @param name  name of the user
     * @param email email of the user
     *
     * @return none
     */
    private void registerAdmin(final String id,final String name,final String email){

        mRef.child("Admin").child(id).child("email").setValue(email);
        mRef.child("Admin").child(id).child("name").setValue(email);
        mRef.child("Admin").child(id).child("role").setValue("admin");

    }
}
