package com.ghorabaa.cultureguide.AdminViewAdmins;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
        mainLinLay.removeViews(2, previousViewsCnt);

        for(int i=0; i<admins.size(); i++)
        {
            //Toast.makeText(getApplicationContext(), admins.get(i), Toast.LENGTH_LONG).show();
            linLay = (LinearLayout) View.inflate(this, R.layout.content_admin_view, null);
            ((TextView) linLay.findViewById(R.id.entity)).setText(admins.get(i));
            mainLinLay.addView(linLay);

            Button removeButton = (Button)linLay.findViewById(R.id.remove_button);
            removeButton.setTag(admins.get(i));

            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(getApplicationContext(), view.getTag().toString() ,Toast.LENGTH_LONG).show();
                    mPresenter.removeAdmin(view.getTag().toString());
                }
            });
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
        mPresenter.retrieveAdmins();
    }

    public void onSearchClicked(View view) {

        String email = ((EditText) findViewById(R.id.admin_email)).getText().toString();

    }

    public void onAddAdminClicked(View view) {

        //startActivity(new Intent(this, AddAdminActivity.class));
        startActivity(new Intent(this, AddAdminActivity.class));
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        mPresenter.retrieveAdmins();
    }
}