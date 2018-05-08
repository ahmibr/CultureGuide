package com.ghorabaa.cultureguide.AdminViewOrganization;

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

public class AdminViewOrganizationActivity extends AppCompatActivity implements AdminViewOrganizationContract.View{

    private AdminViewOrganizationContract.Presenter mPresenter;

    private LinearLayout mainLay;
    private LinearLayout linLay;

    private int previousViewsCnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_organization);

        previousViewsCnt = 0;

        mPresenter = new AdminViewOrganizationPresenter(this, getApplicationContext());
        mPresenter.retrieveOrganizations();
    }

    public void onRetrieve(ArrayList<Pair<String, String> > organizationList){

        mainLay = findViewById(R.id.view_organization);

        for(int i=0; i<organizationList.size(); i++)
        {
            //Toast.makeText(getApplicationContext(),organizationList.get(i).first +" " + organizationList.get(i).second,Toast.LENGTH_LONG).show();
            linLay = (LinearLayout) View.inflate(this, R.layout.content_admin_view, null);
            ((TextView) linLay.findViewById(R.id.entity)).setText("E-mail: " + organizationList.get(i).first + System.lineSeparator() + "Name: " + organizationList.get(i).second);
            mainLay.addView(linLay);
        }

    }

    @Override
    public void onFail(String errorMessage) {

        Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_LONG).show();
    }

    public void onSearchClicked(View view) {

        String email = ((EditText) findViewById(R.id.organization_email)).getText().toString();
        mPresenter.retrieveOrganization(email);
    }
}
