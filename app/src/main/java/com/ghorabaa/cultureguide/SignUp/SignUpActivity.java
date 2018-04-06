package com.ghorabaa.cultureguide.SignUp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ghorabaa.cultureguide.HomePage;
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

        startActivity(new Intent(SignUpActivity.this, HomePage.class));

    }

    public void onSignUpFail(String errorMessage){
        Context context = getApplicationContext();
        CharSequence text = errorMessage;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void doneSignUp(View view){

        String name = ((EditText)findViewById(R.id.organization_name)).getText().toString();

        String email = ((EditText)findViewById(R.id.organization_email)).getText().toString();

        String password = ((EditText)findViewById(R.id.organization_password)).getText().toString();

        String confirmPassword = ((EditText)findViewById(R.id.organization_password_confirm)).getText().toString();

        if(name.isEmpty()||email.isEmpty()||password.isEmpty()||confirmPassword.isEmpty()) {
            String errorMessage = "Please fill blank fields";
            Toast.makeText(view.getContext(), errorMessage, Toast.LENGTH_LONG).show();
            return;
        }

        if(!password.equals(confirmPassword)){
            String errorMessage = "Passwords doesn't match";
            Toast.makeText(view.getContext(), errorMessage, Toast.LENGTH_LONG).show();
            return;
        }

        mPresenter.signUp(name,email,password,UserType.Organization);
    }
}
