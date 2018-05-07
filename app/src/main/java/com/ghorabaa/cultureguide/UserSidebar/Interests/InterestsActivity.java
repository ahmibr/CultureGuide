package com.ghorabaa.cultureguide.UserSidebar.Interests;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.ghorabaa.cultureguide.R;

import java.util.ArrayList;

public class InterestsActivity extends AppCompatActivity implements InterestsContract.View {

    InterestsContract.Presenter mPresenter;

    private ListView mInterestListView;
    private InterestsAdapter mAdapter;

    private ArrayList<Pair<String, Boolean>> mInterestsList = new ArrayList<Pair<String, Boolean>>();

    private int chosenIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interests);

        mPresenter = new InterestsPresenter(this,getApplicationContext());
        mPresenter.retrieveInterests();

        mInterestListView = (ListView) findViewById(R.id.interests_list);
    }

    @Override
    public void onRetrieveInterests(ArrayList<Pair<String, Boolean>> interestsList) {

        mInterestsList = interestsList;

        mAdapter = new InterestsAdapter();
        mInterestListView.setAdapter(mAdapter);
    }

    @Override
    public void onRetrieveInterestsFail(String errorMessage) {

    }

    @Override
    public void onAddSuccess() {
        Toast.makeText(this,"Interest added successfully",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAddFail(String errorMessage) {

    }

    @Override
    public void onRemoveSuccess() {
        Toast.makeText(this,"Interest removed successfully",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRemoveFail(String errorMessage) {

    }

    class InterestsAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mInterestsList.size();
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.interest_list_item,null);

            TextView name = (TextView)view.findViewById(R.id.interest_list_name);
            name.setText( mInterestsList.get(i).first );

            ToggleButton interestedToggle = (ToggleButton) view.findViewById(R.id.interests_toggle);

            interestedToggle.setChecked( mInterestsList.get(i).second );

            interestedToggle.setTag(i);

            interestedToggle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int id = chosenIndex;

                    //Toast.makeText(getApplicationContext(), view.getTag().toString(),Toast.LENGTH_LONG).show();

                    Log.d("Bassel" , view.getTag() + " ");

                    if(((ToggleButton)view).isChecked())
                    {
                        mPresenter.addInterest((int)view.getTag());
                    }
                    else
                    {
                        mPresenter.removeInterest((int)view.getTag());
                    }
                }
            });

            return view;
        }
    }
}