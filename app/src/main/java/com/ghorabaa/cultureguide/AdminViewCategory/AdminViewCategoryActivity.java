package com.ghorabaa.cultureguide.AdminViewCategory;

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

public class AdminViewCategoryActivity extends AppCompatActivity implements AdminViewCategoryContract.View {

    private AdminViewCategoryContract.Presenter mPresenter;

    private LinearLayout mainLinLay;
    private LinearLayout linLay;

    private int previousViewsCnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_category);

        previousViewsCnt = 0;

        mPresenter = new AdminViewCategoryPresenter(this, getApplicationContext());
        mPresenter.retrieveCategories();
    }

    public void onRetrieve(ArrayList<Pair<Integer, String> > categoriesList){

        mainLinLay = findViewById(R.id.view_category);

        for (int i=0; i<categoriesList.size(); i++)
        {
            //Toast.makeText(getApplicationContext(),categoriesList.get(i).second,Toast.LENGTH_LONG).show();
            linLay = (LinearLayout) View.inflate(this, R.layout.content_admin_view, null);
            ((TextView) linLay.findViewById(R.id.entity)).setText("ID: " + categoriesList.get(i).first + System.lineSeparator() + "Name: " + categoriesList.get(i).second);
            mainLinLay.addView(linLay);
        }
    }

    public void onSuccess(String success){

        //Toast.makeText(getApplicationContext(),,Toast.LENGTH_LONG).show();
        //TODO Add locking remove button
    }

    public void onFail(String errorMessage){

        Toast.makeText(getApplicationContext(),errorMessage,Toast.LENGTH_LONG).show();
    }

    public void onSearchClicked(View view) {

        try {

            int id = Integer.parseInt(((EditText) findViewById(R.id.category_id)).getText().toString());
            mPresenter.retrieveCategory(id);
        }

        catch(Exception e){

            Toast.makeText(getApplication(), "Please enter a valid ID", Toast.LENGTH_LONG).show();
        }
    }
}
