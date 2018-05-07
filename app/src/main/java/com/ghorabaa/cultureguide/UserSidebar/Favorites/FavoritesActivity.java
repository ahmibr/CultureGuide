package com.ghorabaa.cultureguide.UserSidebar.Favorites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.ghorabaa.cultureguide.R;

import java.util.ArrayList;

public class FavoritesActivity extends AppCompatActivity implements FavoritesContract.View, AdapterView.OnItemClickListener{

    FavoritesContract.Presenter mPresenter;

    private ListView mFavoriteList;
    private ArrayAdapter<String> mArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        mPresenter = new FavoritesPresenter(this,getApplicationContext());
        mPresenter.retrieveFavorites();

        mFavoriteList = (ListView) findViewById(R.id.favorites_list);

        mFavoriteList.setOnItemClickListener(this);
    }


    @Override
    public void onRetrieveFavorites(ArrayList<String> favoritesList) {

        if(favoritesList.isEmpty())
        {
            Toast.makeText(this,"You have no favorites", Toast.LENGTH_LONG).show();
            return;
        }

        String[] favoritesListStrings = new String[favoritesList.size()];

        //for testing
        for(int i=0;i<favoritesList.size();++i){
            favoritesListStrings[i] = getResources().getString(R.string.remove) + " " + favoritesList.get(i);
        }

        mArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, favoritesListStrings);

        mFavoriteList.setAdapter(mArrayAdapter);
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        mPresenter.removeFavorite(i);

        mPresenter.retrieveFavorites();
    }
}
