package com.ghorabaa.cultureguide.SignUp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ghorabaa.cultureguide.OrganizationEvent.Homepage.OrgHomepageActivity;
import com.ghorabaa.cultureguide.R;
import com.ghorabaa.cultureguide.SignIn.MainActivity;
import com.ghorabaa.cultureguide.UserHomepage.UserHomepage;
import com.ghorabaa.cultureguide.UserType;

public class SignUpActivity extends AppCompatActivity implements SignUpContract.View {


    private static final String TAG = "SignUpActivity";

    private SignUpContract.Presenter mPresenter;

    private ProgressDialog progressBar;

    Spinner mSignUpOptions;

    TextView mSignUpName;

    UserType mUserType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mPresenter = new SignUpPresenter(this,getApplicationContext());

        progressBar = new ProgressDialog(this);

        mSignUpOptions = (Spinner) findViewById(R.id.sign_up_options);
        String[] signUpOptions = { getResources().getString(R.string.user) , getResources().getString(R.string.organization)};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.spinner_layout_style, signUpOptions);
        //adapter.setDropDownViewResource(android.R.layout.);
        mSignUpOptions.setAdapter(adapter);

        mSignUpName = (TextView)findViewById(R.id.sign_up_name);

        mSignUpOptions.setSelection(0);
        mUserType = UserType.Regular;

        mSignUpOptions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        mUserType = UserType.Regular;
                        mSignUpName.setText(R.string.userName);
                        break;
                    case 1:
                        mUserType = UserType.Organization;
                        mSignUpName.setText(R.string.organizationName);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    public void onSignUpSuccess(){
        progressBar.dismiss();
        Context context = getApplicationContext();
        CharSequence text = "Sign Up Success";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        startActivity(new Intent(SignUpActivity.this, OrgHomepageActivity.class));
        finish();
    }

    public void onSignUpFail(String errorMessage){
        progressBar.dismiss();
        Context context = getApplicationContext();
        CharSequence text = errorMessage;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Override
    public void onBackPressed() {
       startActivity(new Intent(SignUpActivity.this,MainActivity.class));
       finish();
    }

    public void doneSignUp(android.view.View view){

        String name = ((EditText)findViewById(R.id.organization_name)).getText().toString();

        String email = ((EditText)findViewById(R.id.organization_email)).getText().toString();

        String password = ((EditText)findViewById(R.id.organization_password)).getText().toString();

        String confirmPassword = ((EditText)findViewById(R.id.organization_password_confirm)).getText().toString();

        if(name.isEmpty()||email.isEmpty()||password.isEmpty()||confirmPassword.isEmpty()) {
            String errorMessage = getResources().getString(R.string.blank_field);
            Toast.makeText(view.getContext(), errorMessage, Toast.LENGTH_LONG).show();
            return;
        }

        if(!password.equals(confirmPassword)){
            String errorMessage = getResources().getString(R.string.password_not_matching);
            Toast.makeText(view.getContext(), errorMessage, Toast.LENGTH_LONG).show();
            return;
        }

        showProgressBar(getResources().getString(R.string.signing_up),getResources().getString(R.string.wait));
        mPresenter.signUp(name,email,password,mUserType);
    }

    private void showProgressBar(String title,String message){
        progressBar.setTitle(title);
        progressBar.setMessage(message);
        progressBar.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progressBar.show();
    }

    @Override
    public void routeRegular() {
        startActivity(new Intent(this, UserHomepage.class));
        finish();
    }

    @Override
    public void routeOrganization() {
        startActivity(new Intent(this, OrgHomepageActivity.class));
        finish();
    }
}
