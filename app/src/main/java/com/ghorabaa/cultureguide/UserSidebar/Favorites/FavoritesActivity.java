package com.ghorabaa.cultureguide.UserSidebar.Favorites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.ghorabaa.cultureguide.R;

import java.util.ArrayList;

public class FavoritesActivity extends AppCompatActivity implements FavoritesContract.View{

    FavoritesContract.Presenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        mPresenter = new FavoritesPresenter(this,getApplicationContext());
        mPresenter.retrieveFavorites();
    }


    @Override
    public void onRetrieveFavorites(ArrayList<String> favoritesList) {

        //for testing
        for(int i=0;i<favoritesList.size();++i)
            Toast.makeText(getApplicationContext(),favoritesList.get(i),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRetrieveFail(String errorMessage) {
        Toast.makeText(getApplicationContext(),errorMessage,Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void onRemoveSuccess() {
        Toast.makeText(getApplicationContext(),"Favorite removed successfully!",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRemoveFail(String errorMessage) {
        Toast.makeText(getApplicationContext(),errorMessage,Toast.LENGTH_LONG).show();
    }
}
