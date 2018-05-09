package com.ghorabaa.cultureguide.AdminViewAdmins;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ghorabaa.cultureguide.R;

import java.util.ArrayList;

public class AdminViewAdminActivity extends AppCompatActivity implements AdminViewAdminContract.View{

    private AdminViewAdminContract.Presenter mPresenter;

    private LinearLayout mainLinLay;
    private LinearLayout linLay;

    private int previousViewsCnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_admin);


        previousViewsCnt = 0;
        mPresenter = new AdminViewAdminPresenter(this, getApplicationContext());
        mPresenter.retrieveAdmins();
    }

    public void onRetrieve(ArrayList<String> admins) {

        mainLinLay = findViewById(R.id.view_admin);
        //mainLinLay.removeViews(2, previousViewsCnt);

        for(int i=0; i<admins.size(); i++)
        {
            //Toast.makeText(getApplicationContext(), admins.get(i), Toast.LENGTH_LONG).show();
            linLay = (LinearLayout) View.inflate(this, R.layout.content_admin_view, null);
            ((TextView) linLay.findViewById(R.id.entity)).setText(admins.get(i));
            //linLay.setId(i);
            mainLinLay.addView(linLay);
            //linLay = mInflater.inflate(R.layout.content_admin_view, (ViewGroup) findViewById(R.id.viewAdmin));//(RelativeLayout) View.inflate(this, R.id.includedEntity, null);
            //mainLinLay.addView(linLay);
        }

        previousViewsCnt = admins.size();
    }

    @Override
    public void onFail(String errorMessage)
    {

        Toast.makeText(getApplicationContext(),errorMessage,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccess() {

        Toast.makeText(getApplicationContext(),"Admin Removed Successfully",Toast.LENGTH_LONG).show();
    }

    public void onSearchClicked(View view) {

        String email = ((EditText) findViewById(R.id.admin_email)).getText().toString();
        mPresenter.retrieveAdmin(email);
    }

    public void onRemoveClicked(View view) {

        String email = ((TextView) view.findViewById(R.id.entity)).getText().toString(); //((EditText)findViewById(R.id.organization_password)).getText().toString();
        //mPresenter.removeAdmin(email);
    }

    public void onAddAdminClicked(View view) {

        //startActivity(new Intent(this, AddAdminActivity.class));
        startActivity(new Intent(this, AddAdminActivity.class));
    }
}