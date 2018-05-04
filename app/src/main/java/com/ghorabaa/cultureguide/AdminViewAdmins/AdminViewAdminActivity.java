package com.ghorabaa.cultureguide.AdminViewAdmins;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ghorabaa.cultureguide.R;

import java.util.ArrayList;

public class AdminViewAdminActivity extends AppCompatActivity implements AdminViewAdminContract.View{

    AdminViewAdminContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_admin);

        mPresenter = new AdminViewAdminPresenter(this, getApplicationContext());
        mPresenter.retrieveAdmins();
    }

    @Override
    public void onRetrieve(ArrayList<String> admins) {

        for(int i=0; i<admins.size(); i++)
        {
            Toast.makeText(getApplicationContext(), admins.get(i), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onFail(String errorMessage) {

        Toast.makeText(getApplicationContext(),errorMessage,Toast.LENGTH_LONG).show();
    }

    public void onSearchClicked(View view) {

        String email = ((EditText)findViewById(R.id.admin_email)).getText().toString();
        mPresenter.retrieveAdmin(email);
    }
}
