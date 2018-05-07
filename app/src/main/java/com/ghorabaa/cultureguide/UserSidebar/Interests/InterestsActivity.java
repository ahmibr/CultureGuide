package com.ghorabaa.cultureguide.UserSidebar.Interests;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.widget.Toast;

import com.ghorabaa.cultureguide.R;

import java.util.ArrayList;

public class InterestsActivity extends AppCompatActivity implements InterestsContract.View{

    InterestsContract.Presenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interests);

        mPresenter = new InterestsPresenter(this,getApplicationContext());
        mPresenter.retrieveInterests();
    }

    @Override
    public void onRetrieveInterests(ArrayList<Pair<String, Boolean>> interestsList) {

        //testing
        for(int i=0;i<interestsList.size();++i) {
            String name = interestsList.get(i).first;
            String checked = interestsList.get(i).second.toString();
            Toast.makeText(getApplicationContext(), name + " " + checked,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRetrieveInterestsFail(String errorMessage) {

    }

    @Override
    public void onAddSuccess() {

    }

    @Override
    public void onAddFail(String errorMessage) {

    }

    @Override
    public void onRemoveSuccess() {

    }

    @Override
    public void onRemoveFail(String errorMessage) {

    }
}
