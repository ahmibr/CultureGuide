package com.ghorabaa.cultureguide.SignIn;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.ghorabaa.cultureguide.R;

public class MainActivity extends AppCompatActivity implements SignInContract.SignInView {


    private static final String TAG = "SignInActivity";

    private SignInPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new SignInPresenter(this);

        //Testing Login
        mPresenter.signIn("ahmed@gmail.com","password");
    }

    //Testing Functions
    public void onSignInSuccess(){
        Context context = getApplicationContext();
        CharSequence text = "Login Success";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void onSignInFail(){
        Context context = getApplicationContext();
        CharSequence text = "Login Failed";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void ha(){};
}
