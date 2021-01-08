package com.anitatheone.moviecta.view;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.anitatheone.moviecta.BaseActivity;
import com.anitatheone.moviecta.R;

public class MainActivity extends BaseActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        addFragment(new MovieListFragment(), getString(R.string.movie_list), null, false);

    }
    /**
     * Add fragment to this activity.
     *
     * @param fragment       class to add
     * @param tag            tag for the fragment
     * @param bundle         bundle to pass data to fragment
     * @param addToBackStack true if need to add to backStack, false otherwise.
     */
    public void addFragment(Fragment fragment, String tag, Bundle bundle, boolean addToBackStack) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        ft.add(R.id.container, fragment, tag);
        if (addToBackStack) {
            ft.addToBackStack(tag);
        }
        ft.commit();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void comeBackToPreviousFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        for(int i = 0; i < fragmentManager.getBackStackEntryCount(); ++i) {
            fragmentManager.popBackStack();
        }
    }

}
