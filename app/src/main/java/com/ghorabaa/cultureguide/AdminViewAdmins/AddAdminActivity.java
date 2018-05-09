package com.ghorabaa.cultureguide.AdminViewAdmins;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ghorabaa.cultureguide.R;

public class AddAdminActivity extends AppCompatActivity implements AddAdminContract.View{

    private AddAdminContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_admin);

        mPresenter = new AddAdminPresenter(this, getApplicationContext());
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

    public void onAddAdminClicked(View view) {

        String email = ((EditText) findViewById(R.id.admin_email)).getText().toString();
        String password = ((EditText) findViewById(R.id.admin_password)).getText().toString();
        String confirmPassword = ((EditText) findViewById(R.id.admin_password_confirm)).getText().toString();

        if(email.isEmpty()||password.isEmpty()||confirmPassword.isEmpty()) {
            String errorMessage = getResources().getString(R.string.blank_field);
            Toast.makeText(view.getContext(), errorMessage, Toast.LENGTH_LONG).show();
            return;
        }

        if(!password.equals(confirmPassword)){
            String errorMessage = getResources().getString(R.string.password_not_matching);
            Toast.makeText(view.getContext(), errorMessage, Toast.LENGTH_LONG).show();
            return;
        }

        mPresenter.addAdmin(email, password);
    }
}

