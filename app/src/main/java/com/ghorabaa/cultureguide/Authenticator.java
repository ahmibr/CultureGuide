package com.ghorabaa.cultureguide;

import android.support.annotation.NonNull;
import android.util.Log;

import com.ghorabaa.cultureguide.SignIn.SignInPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Ahmed Ibrahim on 3/29/18.
 */

public class Authenticator {

    private FirebaseAuth mAuth;
    private SignInPresenter mPresenter;
    private static FirebaseUser mUser = null;

    public Authenticator(SignInPresenter presenter){
        mAuth = FirebaseAuth.getInstance();
        mPresenter = presenter;
    }

    public void signIn(String email,String password){

        Task<AuthResult> signInTask = mAuth.signInWithEmailAndPassword(email,password);

        signInTask.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            /****************Start Authentication********************/
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                /***********Connected to Server*************/
                if(task.isSuccessful()){
                    /***********User Found in DB****************/

                   mUser = mAuth.getCurrentUser();
                    DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
                    DatabaseReference organizations = dbRef.child("organization");

                    //Check if User is Organization
                    organizations.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(dataSnapshot.hasChild(mUser.getUid()))
                            {
                                mPresenter.onSignInSuccess(UserType.Organization);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                    //TODO Add Regular User Check and Admin Check in Sign In

                /***********SignInDone********************/

                }

                else{ //Login Failed
                    mPresenter.onSignInFail();
                }

                /***********Connection Closed*************/
            }
        });
    }


    public static String getUserID(){
        if(mUser==null)
            return null;

        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }



}
