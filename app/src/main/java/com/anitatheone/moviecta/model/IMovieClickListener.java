package com.anitatheone.moviecta.model;

import android.view.View;


public interface IMovieClickListener {

    /**
     * Used when the appliance row is clicked. Radio buttons are hidden in this case.
     *
     * @param view
     * @param position
     */
    void onApplianceClicked(View view, int position);

}
