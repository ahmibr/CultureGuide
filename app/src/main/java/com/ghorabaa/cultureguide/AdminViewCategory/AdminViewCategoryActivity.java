package com.ghorabaa.cultureguide.AdminViewCategory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ghorabaa.cultureguide.R;

import java.util.ArrayList;

public class AdminViewCategoryActivity extends AppCompatActivity implements AdminViewCategoryContract.View {

    private AdminViewCategoryContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_category);

        mPresenter = new AdminViewCategoryPresenter(this, getApplicationContext());
        mPresenter.retrieveCategories();
    }

    public void onRetrieve(ArrayList<Pair<Integer, String> > categoriesList){

        for (int i=0; i<categoriesList.size(); i++)
        {
            Toast.makeText(getApplicationContext(),categoriesList.get(i).second,Toast.LENGTH_LONG).show();
        }
    }

    public void onSuccess(String success){

        //Toast.makeText(getApplicationContext(),,Toast.LENGTH_LONG).show();
        //TODO Add locking remove button
    }

    public void onFail(String errorMessage){

        Toast.makeText(getApplicationContext(),errorMessage,Toast.LENGTH_LONG).show();
    }

}
