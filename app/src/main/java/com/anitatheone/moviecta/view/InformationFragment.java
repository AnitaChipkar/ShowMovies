package com.anitatheone.moviecta.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.anitatheone.moviecta.BaseFragment;
import com.anitatheone.moviecta.R;

public class InformationFragment extends BaseFragment {
    private View view;
    MainActivity mainActivity;




    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.information_fragment, container, false);
       if (getArguments() != null)
       {
           String string = getArguments().getString("MOVIE_INFO");
       }
        return view;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) context;
    }

    @Override
    public void onResume() {
        super.onResume();
        mainActivity.comeBackToPreviousFragment();

    }
}
