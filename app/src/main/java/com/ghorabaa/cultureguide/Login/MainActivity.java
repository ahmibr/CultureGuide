package com.ghorabaa.cultureguide.Login;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.ghorabaa.cultureguide.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements LoginContract.LoginView {


    private static final String TAG = "MainActivity";

    private LoginPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new LoginPresenter(this);

        //Testing Login
        mPresenter.signIn("ahmed@gmail.com","password");
    }

    //Testing Functions
    public void onLoginSuccess(){
        Context context = getApplicationContext();
        CharSequence text = "Login Success";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void onLoginFail(){
        Context context = getApplicationContext();
        CharSequence text = "Login Failed";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
