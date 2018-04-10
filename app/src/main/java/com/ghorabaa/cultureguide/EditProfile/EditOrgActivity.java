package com.ghorabaa.cultureguide.EditProfile;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.ghorabaa.cultureguide.R;

public class EditOrgActivity extends AppCompatActivity
        implements EditOrgContract.EditOrgView{

    private EditOrgContract.EditOrgPresenter mPresenter;

    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_organization);

        mPresenter = new EditOrgPresenter(this);

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
        showProgressBar("Changing name","Please wait while changing name...");
        mPresenter.changeName( newName );
    }

    public void changeOrgEmail(View view){
        String email = ((TextView) findViewById(R.id.edit_org_email)).getText().toString();
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
