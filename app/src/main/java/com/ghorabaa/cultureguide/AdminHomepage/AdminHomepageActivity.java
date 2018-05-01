package com.ghorabaa.cultureguide.AdminHomepage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ghorabaa.cultureguide.AdminViewCategory.AdminViewCategoryActivity;
import com.ghorabaa.cultureguide.R;

public class AdminHomepageActivity extends AppCompatActivity implements AdminHomepageContract.View {

    private AdminHomepageContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_homepage);

        mPresenter = new AdminHomepagePresenter(this,getApplicationContext());
    }

    public void onViewCategoriesClicked(android.view.View view){

        startActivity(new Intent(this, AdminViewCategoryActivity.class));
        finish();

    }

    /*public void onViewAdminsClicked(android.view.View view){

        startActivity(new Intent(this,ViewAdmins.class ));
        finish();
    }

    public void onViewUsersClicked(android.view.View view){

        startActivity(new Intent(this, ViewUsers.class));
        finish();
    }

    public void onViewOrganizations(anroid.view.View view){

        startActivity(new Intent(this, ViewOrganizations.class));
        finish();
    }*/
}
