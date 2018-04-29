package com.ghorabaa.cultureguide.SignUp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.ghorabaa.cultureguide.HomePage;
import com.ghorabaa.cultureguide.R;
import com.ghorabaa.cultureguide.SignIn.MainActivity;
import com.ghorabaa.cultureguide.UserHomepage.UserHomepage;
import com.ghorabaa.cultureguide.UserType;

public class SignUpActivity extends AppCompatActivity implements SignUpContract.View {


    private static final String TAG = "SignUpActivity";

    private SignUpContract.Presenter mPresenter;

    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mPresenter = new SignUpPresenter(this,getApplicationContext());

        progressBar = new ProgressDialog(this);
    }


    public void onSignUpSuccess(){
        progressBar.dismiss();
        Context context = getApplicationContext();
        CharSequence text = "Sign Up Success";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        startActivity(new Intent(SignUpActivity.this, HomePage.class));
        finish();
    }

    public void onSignUpFail(String errorMessage){
        progressBar.dismiss();
        Context context = getApplicationContext();
        CharSequence text = errorMessage;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Override
    public void onBackPressed() {
       startActivity(new Intent(SignUpActivity.this,MainActivity.class));
       finish();
    }

    public void doneSignUp(android.view.View view){

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

        showProgressBar("Signing up","Please wait while signing up...");
        mPresenter.signUp(name,email,password,UserType.Organization);
    }

    private void showProgressBar(String title,String message){
        progressBar.setTitle(title);
        progressBar.setMessage(message);
        progressBar.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progressBar.show();
    }

    @Override
    public void routeRegular() {
        startActivity(new Intent(this, UserHomepage.class));
        finish();
    }

    @Override
    public void routeOrganization() {
        startActivity(new Intent(this, HomePage.class));
        finish();
    }
}
