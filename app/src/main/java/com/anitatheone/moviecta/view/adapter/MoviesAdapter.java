package com.anitatheone.moviecta.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.anitatheone.moviecta.Movie;
import com.anitatheone.moviecta.R;
import com.anitatheone.moviecta.model.IMovieClickListener;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.AppliancesViewHolder>
{

    ArrayList<Movie> movieArrayList;
    IMovieClickListener iMovieClickListener;
    private Context context;

    public MoviesAdapter(ArrayList<Movie> myAppliancesArrayList) {
        movieArrayList = myAppliancesArrayList;
    }

    public void setMovieCLickListener(IMovieClickListener listener) {
        iMovieClickListener = listener;
    }

    @Override
    public MoviesAdapter.AppliancesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.movie_list_row_layout, null);
        context = parent.getContext();
        // create ViewHolder
        MoviesAdapter.AppliancesViewHolder appliancesViewHolder = new MoviesAdapter.AppliancesViewHolder(itemLayoutView);
        return appliancesViewHolder;
    }

    @Override
    public void onBindViewHolder(MoviesAdapter.AppliancesViewHolder holder, int position) {
        holder.tvMovieTitle.setText(movieArrayList.get(position).getTitle());
        Picasso.with(context)
                .load("http://image.tmdb.org/t/p/w185/" + movieArrayList.get(position).getPoster_path())
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.imgPosterPath);
        String strDate = movieArrayList.get(position).getRelease_date();
        SimpleDateFormat sd1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        Date dt = new Date();
        try {
            dt = sd1.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sd2 = new SimpleDateFormat("dd-MM-yyyy");
        String newDate = sd2.format(dt);
        holder.tvMovieReleaseDate.setText(newDate);
        if (movieArrayList.get(position).getAdult().contains("true"))
        {
            holder.tvMovieAdult.setText("(A)");
        }
        else
        {
            holder.tvMovieAdult.setText("(U/A)");
        }

    }


    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }

    public class AppliancesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgPosterPath;
        TextView tvMovieTitle,tvMovieReleaseDate,tvMovieAdult;


        public AppliancesViewHolder(View itemView) {
            super(itemView);
            imgPosterPath = itemView.findViewById(R.id.img_poster_path);
            tvMovieTitle = itemView.findViewById(R.id.tv_movie_title);
            tvMovieReleaseDate = itemView.findViewById(R.id.tv_movie_release_date);
            tvMovieAdult = itemView.findViewById(R.id.tv_movie_adult);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            iMovieClickListener.onApplianceClicked(v, getAdapterPosition() );
        }
    }

}
