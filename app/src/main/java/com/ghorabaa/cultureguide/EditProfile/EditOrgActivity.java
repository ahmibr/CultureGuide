package com.ghorabaa.cultureguide.EditProfile;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.ghorabaa.cultureguide.R;

public class EditOrgActivity extends AppCompatActivity
        implements EditProfileContract.EditProfileView {

    private EditProfileContract.EditProfilePresenter mPresenter;

    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_organization);

        mPresenter = new EditOrgPresenter(this,getApplicationContext());

        progressBar = new ProgressDialog(this);
    }




    @Override
    public void onSuccess(String successMessage) {
        progressBar.dismiss();
        printToast(successMessage,Toast.LENGTH_LONG);
    }

    @Override
    public void onFail(String failMessage) {
        progressBar.dismiss();
        printToast(failMessage,Toast.LENGTH_LONG);
    }


    //Todo change name email password

    public void changeOrgName(View view){
        String newName = ((TextView) findViewById(R.id.edit_org_name)).getText().toString();

        if(newName.isEmpty()){
            printToast("Please fill the blank field",Toast.LENGTH_LONG);
            return;
        }

        showProgressBar("Changing name","Please wait while changing name...");
        mPresenter.changeName( newName );
    }

    public void changeOrgEmail(View view){
        String email = ((TextView) findViewById(R.id.edit_org_email)).getText().toString();

        if(email.isEmpty()){
            printToast("Please fill the blank field",Toast.LENGTH_LONG);
            return;
        }

        showProgressBar("Changing email","Please wait while changing email...");
        mPresenter.changeEmail( email );
    }

    public void changeOrgPassword(View view){
        String password = ((TextView) findViewById(R.id.edit_org_password_1)).getText().toString();
        String passwordConfirm = ((TextView) findViewById(R.id.edit_org_password_2)).getText().toString();

        if(!password.equals(passwordConfirm)){
            printToast("Passwords doesn't match",Toast.LENGTH_LONG);
            return;
        }

        if(password.isEmpty()){
            printToast("Please fill the blank field",Toast.LENGTH_LONG);
            return;
        }

        showProgressBar("Changing password","Please wait while changing password...");
        mPresenter.changePassword( password );
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
