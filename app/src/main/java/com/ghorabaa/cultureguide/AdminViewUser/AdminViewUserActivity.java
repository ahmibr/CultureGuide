package com.ghorabaa.cultureguide.AdminViewUser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ghorabaa.cultureguide.R;

import java.util.ArrayList;

public class AdminViewUserActivity extends AppCompatActivity implements AdminViewUserContract.View {

    AdminViewUserContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_user);

        mPresenter = new AdminViewUserPresenter(this, getApplicationContext());
        mPresenter.retrieveUsers();
    }

    @Override
    public void onRetrieve(ArrayList<Pair<String, String>> users) {

        for(int i=0; i<users.size(); i++){

            Toast.makeText(getApplicationContext(), users.get(i).first + " " + users.get(i).second, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onFail(String errorMessage) {

        Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_LONG).show();
    }

    public void onSearchClicked(View view) {

        String email = ((EditText) findViewById(R.id.user_email)).getText().toString();
        mPresenter.retrieveUser(email);
    }
}
