package com.ghorabaa.cultureguide.AdminViewUser;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ghorabaa.cultureguide.R;

import java.util.ArrayList;

public class AdminViewUserActivity extends AppCompatActivity implements AdminViewUserContract.View {

    AdminViewUserContract.Presenter mPresenter;

    private LinearLayout mainLinLay;
    private LinearLayout linLay;

    private int previousViewsCnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_user);

        previousViewsCnt = 0;

        mPresenter = new AdminViewUserPresenter(this, getApplicationContext());
        mPresenter.retrieveUsers();
    }

    @Override
    public void onRetrieve(ArrayList<Pair<String, String>> users) {

        mainLinLay = findViewById(R.id.view_user);

        for(int i=0; i<users.size(); i++){

            //Toast.makeText(getApplicationContext(), users.get(i).first + " " + users.get(i).second, Toast.LENGTH_LONG).show();
            linLay = (LinearLayout) View.inflate(this, R.layout.content_admin_view, null);
            ((TextView) linLay.findViewById(R.id.entity)).setText("E-mail: " + users.get(i).first + System.lineSeparator() + "Name: " + users.get(i).second);
            mainLinLay.addView(linLay);
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