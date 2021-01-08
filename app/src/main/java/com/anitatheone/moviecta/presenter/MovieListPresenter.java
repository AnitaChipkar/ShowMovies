package com.anitatheone.moviecta.presenter;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.anitatheone.moviecta.Movie;
import com.anitatheone.moviecta.application.MovieApplication;
import com.anitatheone.moviecta.utils.CommonUtils;
import com.anitatheone.moviecta.utils.VolleyErrorHelper;
import com.anitatheone.moviecta.view.MainActivity;
import com.anitatheone.moviecta.view.MovieListFragment;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.anitatheone.moviecta.utils.APIConstants.GET_UPCOMING_MOVIE_LIST;

public class MovieListPresenter {
    private MainActivity mainActivity;
    private MovieListFragment fragment;
    private static final String TAG = MovieListPresenter.class.getSimpleName();
    private ArrayList<Movie> movieArrayList = new ArrayList<>();

    /**
     * Constructor.
     */
    public MovieListPresenter(MovieListFragment fragment) {
        mainActivity = (MainActivity) fragment.getActivity();
        this.fragment = fragment;
    }

    /**
     * Call get appliances API.
     */

   /* public void callGetMovieListAPI() {
        Movie movie = new Movie("Mad Max: Fury Road", "Action & Adventure", "2015", "abc");
        movieArrayList.add(movie);

        movie = new Movie("Inside Out", "Animation, Kids & Family", "2015", "adf");
        movieArrayList.add(movie);

        movie = new Movie("Star Wars: Episode VII - The Force Awakens", "Action", "2015", "aasss");
        movieArrayList.add(movie);

        movie = new Movie("Shaun the Sheep", "Animation", "2015", "aaa");
        movieArrayList.add(movie);

        movie = new Movie("The Martian", "Science Fiction & Fantasy", "2015", "ssss");
        movieArrayList.add(movie);

        movie = new Movie("Mission: Impossible Rogue Nation", "Action", "2015", "ssss");
        movieArrayList.add(movie);

        movie = new Movie("Up", "Animation", "2009", "sddf");
        movieArrayList.add(movie);
        fragment.setMovieArrayList(movieArrayList);
        fragment.setMovieArrayListOnUI();
    }*/

    public void callGetMovieListAPI() {
        String url = "https://api.themoviedb.org/3/movie/upcoming?api_key=b7cd3340a794e5a2f35e3abb820b497f";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        CommonUtils.hideProgressDialog();
                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray newsArray = obj.getJSONArray("results");
                            for (int i = 0; i < newsArray.length(); i++) {
                                JSONObject moviesObject = newsArray.getJSONObject(i);
                                movieArrayList.add(new Movie(moviesObject.getString("title"), moviesObject.getString("poster_path"),
                                        moviesObject.getString("release_date"),moviesObject.getString("adult")));
                               // newsModelList.add(newsModel);
                                //newsAdaptor.notifyDataSetChanged();
                                fragment.setMovieArrayList(movieArrayList);
                                fragment.setMovieArrayListOnUI();
                                VolleyLog.d("Data", response);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                String msg = VolleyErrorHelper.getMessage(error.toString(), mainActivity);
                //Hide Progress bar
                CommonUtils.hideProgressDialog();
                CommonUtils.showAlertDialog(mainActivity, msg);
            }


        });
        RequestQueue requestQueue = Volley.newRequestQueue(mainActivity);
        stringRequest.setShouldCache(false);
        requestQueue.add(stringRequest);

        // add the request object to the queue to be executed
       // MovieApplication.getInstance().addToRequestQueue(jsonArrayRequest);
    }
}




