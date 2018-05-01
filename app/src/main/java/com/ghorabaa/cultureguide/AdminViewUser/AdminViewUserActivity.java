package com.ghorabaa.cultureguide.AdminViewUser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ghorabaa.cultureguide.R;

public class AdminViewUserActivity extends AppCompatActivity implements AdminViewUserContract.View {

    AdminViewUserContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_user);

        mPresenter = new AdminViewUserPresenter(this, getApplicationContext());
        mPresenter.retrieveUsers();
    }
}
