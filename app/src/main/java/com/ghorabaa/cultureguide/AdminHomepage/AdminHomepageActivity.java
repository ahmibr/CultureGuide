package com.ghorabaa.cultureguide.AdminHomepage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ghorabaa.cultureguide.AdminViewAdmins.AdminViewAdminActivity;
import com.ghorabaa.cultureguide.AdminViewCategory.AdminViewCategoryActivity;
import com.ghorabaa.cultureguide.AdminViewOrganization.AdminViewOrganizationActivity;
import com.ghorabaa.cultureguide.AdminViewUser.AdminViewUserActivity;
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

    public void onViewUsersClicked(View view) {

        startActivity(new Intent(this, AdminViewUserActivity.class));
        finish();
    }

    public void onViewAdminsClicked(android.view.View view){

        startActivity(new Intent(this, AdminViewAdminActivity.class ));
        finish();
    }


    public void onViewOrganizationsClicked(android.view.View view){

        startActivity(new Intent(this, AdminViewOrganizationActivity.class));
        finish();
    }
}
