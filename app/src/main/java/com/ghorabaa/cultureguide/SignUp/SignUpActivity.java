package com.ghorabaa.cultureguide.SignUp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.ghorabaa.cultureguide.R;
import com.ghorabaa.cultureguide.UserType;

public class SignUpActivity extends AppCompatActivity implements SignUpContract.SignUpView {


    private static final String TAG = "SignUpActivity";

    private SignUpPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mPresenter = new SignUpPresenter(this);
    }

    //Testing Functions
    public void onSignUpSuccess(){
        Context context = getApplicationContext();
        CharSequence text = "Sign Up Success";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void onSignUpFail(){
        Context context = getApplicationContext();
        CharSequence text = "Sign up Failed";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
