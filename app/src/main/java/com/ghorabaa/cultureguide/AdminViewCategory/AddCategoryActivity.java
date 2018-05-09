package com.ghorabaa.cultureguide.AdminViewCategory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ghorabaa.cultureguide.R;

public class AddCategoryActivity extends AppCompatActivity implements AddCategoryContract.View{

    private AddCategoryContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        mPresenter = new AddCategoryPresenter(this, getApplicationContext());
    }

    @Override
    public void onSuccess() {

        Toast.makeText(getApplicationContext(), "Added Successfully", Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void onFail(String errorMesage) {

        Toast.makeText(getApplicationContext(), errorMesage, Toast.LENGTH_LONG).show();

    }


    public void onAddCategoryClicked(View view) {

        String category = ((EditText) findViewById(R.id.category_name)).getText().toString();

        if(category.isEmpty())
        {
            String errorMessage = "Please Fill Empty Blank";
            Toast.makeText(view.getContext(), errorMessage, Toast.LENGTH_LONG).show();
            return;
        }


        mPresenter.addCategory(category);
    }
}
