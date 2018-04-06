package com.ghorabaa.cultureguide.SignIn;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ghorabaa.cultureguide.HomePage;
import com.ghorabaa.cultureguide.R;

public class MainActivity extends AppCompatActivity implements SignInContract.SignInView {


    private static final String TAG = "SignInActivity";

    private SignInPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new SignInPresenter(this);
    }


    //Testing Functions
    public void onSignInSuccess(){
        String text = getResources().getString(R.string.sign_in_success);
        int duration = Toast.LENGTH_LONG;

        printToast(text, duration);

        startActivity(new Intent(MainActivity.this, HomePage.class));
    }

    public void onSignInFail(){
        String text = getResources().getString(R.string.sign_in_fail);
        int duration = Toast.LENGTH_LONG;

        printToast(text,duration);
    }

    public void onSignInClicked(View view){

        //TODo add loading spinner until sign in Sucssess or fail

        String email = ((EditText)findViewById(R.id.sign_in_email)).getText().toString();

        String password = ((EditText)findViewById(R.id.sign_in_password)).getText().toString();


        //(Completed) add validations
        String blankFieldError = getResources().getString(R.string.blank_field);
        if(email.isEmpty() || password.isEmpty())
        {
            printToast(blankFieldError,Toast.LENGTH_LONG);
            return;
        }

        mPresenter.signIn(email,password);
    }

    public void printToast(String message,int duration){
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }

    public void onSignUpClicked(View view){

    }
}
