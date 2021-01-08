package com.anitatheone.moviecta.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.anitatheone.moviecta.BaseFragment;
import com.anitatheone.moviecta.Movie;
import com.anitatheone.moviecta.R;
import com.anitatheone.moviecta.model.IMovieClickListener;
import com.anitatheone.moviecta.presenter.MovieListPresenter;
import com.anitatheone.moviecta.utils.CommonUtils;
import com.anitatheone.moviecta.view.adapter.MoviesAdapter;

import java.util.ArrayList;

public class MovieListFragment extends BaseFragment {
    private MainActivity mainActivity;


   private ArrayList<Movie> movieArrayList = new ArrayList<>();
     private IMovieClickListener movieClickListener;
    private MovieListPresenter movieListPresenter;
    private MoviesAdapter moviesAdapter;
    RecyclerView recyclerView;
   private View view;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.movie_list_fragment, container, false);
        recyclerView = view.findViewById(R.id.movie_recycler_view);

      //  prepareMovieData();
       movieListPresenter = new MovieListPresenter(this);
        //Show progress bar
        CommonUtils.showProgressDialog(mainActivity);
        // call API to get list of categories of appliances.
        movieListPresenter.callGetMovieListAPI();

        return view;

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) context;
    }

    /**
     * set my appliances list
     * @param movies
     */
    public void setMovieArrayList( ArrayList<Movie> movies) {
        movieArrayList= movies;
    }

    /**
     * Set appliance list on UI.
     */
    public void setMovieArrayListOnUI(){
        moviesAdapter = new MoviesAdapter(movieArrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mainActivity);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);

        movieClickListener = new IMovieClickListener() {
            @Override
            public void onApplianceClicked(View view, int position) {
                //Hide keypad if open
                CommonUtils.hideKeypad(mainActivity);
               // openAddedApplianceFragment(view,position);
            }

        };
        moviesAdapter.setMovieCLickListener(movieClickListener);
        //Set adapter after setting listener, if the adapter is set first,
        // can get NPE on click of any row because the listener is not yet set.
        recyclerView.setAdapter(moviesAdapter);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        inflater.inflate(R.menu.menu_info, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) { switch(item.getItemId()) {
        case R.id.menu_info:
            //add the function to perform here
            Bundle bundle = new Bundle();
            bundle.putString("MOVIE_INFO", "1");
            mainActivity.addFragment(new InformationFragment(), getString(R.string.movie_info), bundle, false);
            return(true);

    }
        return(super.onOptionsItemSelected(item));
    }
}
