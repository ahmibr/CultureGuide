package com.ghorabaa.cultureguide.SignIn;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.ghorabaa.cultureguide.AdminHomepage.AdminHomepageActivity;
import com.ghorabaa.cultureguide.OrganizationEvent.Homepage.OrgHomepageActivity;
import com.ghorabaa.cultureguide.R;
import com.ghorabaa.cultureguide.SignUp.SignUpActivity;
import com.ghorabaa.cultureguide.UserHomepage.UserHomepage;

public class MainActivity extends AppCompatActivity implements SignInContract.View {


    private static final String TAG = "SignInActivity";

    private SignInContract.Presenter mPresenter;

    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new SignInPresenter(this,getApplicationContext());

        progressBar = new ProgressDialog(this);

    }


    //Checking login Function(Success)
    public void onSignInSuccess(){
        
        progressBar.dismiss();
        String text = getResources().getString(R.string.sign_in_success);
        int duration = Toast.LENGTH_LONG;

        printToast(text, duration);

    }

    //Checking login Function(Fail)
    public void onSignInFail(String errorMessage){

        progressBar.dismiss();
        int duration = Toast.LENGTH_LONG;

        printToast(errorMessage,duration);
    }

    /**
     * CallBack function from BackEnd
     */
    @Override
    public void routeRegular() {
        startActivity(new Intent(this, UserHomepage.class));
        finish();
    }

    /**
     * CallBack function from BackEnd
     */
    @Override
    public void routeOrganization() {
        startActivity(new Intent(MainActivity.this, OrgHomepageActivity.class));
        finish();
    }

    /**
     * CallBack function from BackEnd
     */
    @Override
    public void routeAdmin() {
        startActivity(new Intent(MainActivity.this, AdminHomepageActivity.class));
        finish();
    }

    /**
     * After sign in is clicked
     * handling empty text
     */
    public void onSignInClicked(android.view.View view){

        String email = ((EditText)findViewById(R.id.sign_in_email)).getText().toString();

        String password = ((EditText)findViewById(R.id.sign_in_password)).getText().toString();


        //(Completed) add validations
        String blankFieldError = getResources().getString(R.string.blank_field);
        if(email.isEmpty() || password.isEmpty())
        {
            printToast(blankFieldError,Toast.LENGTH_LONG);
            return;
        }

        showProgressBar(getResources().getString(R.string.signing_in),getResources().getString(R.string.wait));

        mPresenter.signIn(email,password);
    }

    /**
     * CallBack function from BackEnd
     */
    public void onSignUpClicked(android.view.View view){

        startActivity(new Intent(MainActivity.this, SignUpActivity.class));
        finish();
    }

    private void printToast(String message,int duration){
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }

    private void showProgressBar(String title,String message){
        progressBar.setTitle(title);
        progressBar.setMessage(message);
        progressBar.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progressBar.show();
    }

}
