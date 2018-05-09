package com.ghorabaa.cultureguide.AdminViewUser;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ghorabaa.cultureguide.R;

import java.util.ArrayList;

public class AdminViewUserActivity extends AppCompatActivity implements AdminViewUserContract.View {

    private AdminViewUserContract.Presenter mPresenter;

    private LinearLayout mainLinLay;
    private LinearLayout linLay;

    private int previousViewsCnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_user);

        previousViewsCnt = 0;

        mPresenter = new AdminViewUserPresenter(this, getApplicationContext());
        //mPresenter.retrieveUsers();
    }

    @Override
    public void onRetrieve(final ArrayList<Pair<String, String>> users) {

        mainLinLay = findViewById(R.id.view_user);
        mainLinLay.removeViews(1,previousViewsCnt);

        for(int i=0; i<users.size(); i++){

            //Toast.makeText(getApplicationContext(), users.get(i).first + " " + users.get(i).second, Toast.LENGTH_LONG).show();
            linLay = (LinearLayout) View.inflate(this, R.layout.content_admin_view, null);
            ((TextView) linLay.findViewById(R.id.entity)).setText("E-mail: " + users.get(i).first + System.lineSeparator() + "Name: " + users.get(i).second);
            mainLinLay.addView(linLay);

            Button removeButton = (Button)linLay.findViewById(R.id.remove_button);
            removeButton.setTag(i);
            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), users.get(Integer.parseInt(view.getTag().toString())).first ,Toast.LENGTH_LONG).show();
                    mPresenter.removeUser(users.get(Integer.parseInt(view.getTag().toString())).first);
                }
            });
        }

        previousViewsCnt = users.size();
    }

    @Override
    public void onFail(String errorMessage) {

        Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccess() {

        Toast.makeText(getApplicationContext(),"User Removed Successfully",Toast.LENGTH_LONG).show();
        mPresenter.retrieveUsers();
    }

    public void onSearchClicked(View view) {

        String email = ((EditText) findViewById(R.id.user_email)).getText().toString();
        mPresenter.retrieveUser(email);
    }

}