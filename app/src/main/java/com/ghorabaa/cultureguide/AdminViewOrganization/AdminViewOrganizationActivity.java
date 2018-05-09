package com.ghorabaa.cultureguide.AdminViewOrganization;

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

    public void onRetrieve(final ArrayList<Pair<String, String> > organizationList){

        mainLay = findViewById(R.id.view_organization);
        mainLay.removeViews(1, previousViewsCnt);

        for(int i=0; i<organizationList.size(); i++)
        {
            //Toast.makeText(getApplicationContext(),organizationList.get(i).first +" " + organizationList.get(i).second,Toast.LENGTH_LONG).show();
            linLay = (LinearLayout) View.inflate(this, R.layout.content_admin_view, null);
            ((TextView) linLay.findViewById(R.id.entity)).setText("E-mail: " + organizationList.get(i).first + System.lineSeparator() + "Name: " + organizationList.get(i).second);
            mainLay.addView(linLay);

            Button removeButton = (Button)linLay.findViewById(R.id.remove_button);
            removeButton.setTag(i);

            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(getApplicationContext(), organizationList.get(Integer.parseInt(view.getTag().toString())).first ,Toast.LENGTH_LONG).show();
                    mPresenter.removeOrganization(organizationList.get(Integer.parseInt(view.getTag().toString())).first);
                }
            });
        }

        previousViewsCnt = organizationList.size();

    }

    @Override
    public void onFail(String errorMessage) {

        Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccess() {
        Toast.makeText(getApplicationContext(),"Organization Removed Successfully",Toast.LENGTH_LONG).show();
        mPresenter.retrieveOrganizations();
    }

    public void onSearchClicked(View view) {

        String email = ((EditText) findViewById(R.id.organization_email)).getText().toString();
        mPresenter.retrieveOrganization(email);
    }

}
