package com.ghorabaa.cultureguide.AdminViewCategory;

import android.content.Intent;
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

    public void onRetrieve(final ArrayList<Pair<Integer, String> > categoriesList){

        mainLinLay = findViewById(R.id.view_category);

        mainLinLay.removeViews(2, previousViewsCnt);
        for (int i=0; i<categoriesList.size(); i++)
        {
            //Toast.makeText(getApplicationContext(),categoriesList.get(i).second,Toast.LENGTH_LONG).show();
            linLay = (LinearLayout) View.inflate(this, R.layout.content_admin_view, null);
            ((TextView) linLay.findViewById(R.id.entity)).setText("ID: " + categoriesList.get(i).first + System.lineSeparator() + "Name: " + categoriesList.get(i).second);
            mainLinLay.addView(linLay);

            Button removeButton = (Button)linLay.findViewById(R.id.remove_button);
            removeButton.setTag(i);

            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(getApplicationContext(), organizationList.get(Integer.parseInt(view.getTag().toString())).first ,Toast.LENGTH_LONG).show();
                    mPresenter.removeCategory(categoriesList.get(Integer.parseInt(view.getTag().toString())).first);
                }
            });
        }
        previousViewsCnt = categoriesList.size();
    }

    public void onSuccess(){

        Toast.makeText(getApplicationContext(),"Category Removed Successfully",Toast.LENGTH_LONG).show();
        mPresenter.retrieveCategories();
    }

    public void onFail(String errorMessage){

        Toast.makeText(getApplicationContext(),errorMessage,Toast.LENGTH_LONG).show();
    }

    public void onSearchClicked(View view) {

        try {
            String inputID = ((EditText) findViewById(R.id.category_id)).getText().toString();
            if(inputID.isEmpty()){
                mPresenter.retrieveCategories();
                return;
            }
            int id = Integer.parseInt(inputID);
            mPresenter.retrieveCategory(id);
        }

        catch(Exception e){

            Toast.makeText(getApplication(), "Please enter a valid ID", Toast.LENGTH_LONG).show();
        }
    }


    public void onAddCatClicked(View view) {

        startActivity(new Intent(this, AddCategoryActivity.class));
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        mPresenter.retrieveCategories();
    }
}
