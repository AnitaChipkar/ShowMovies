package com.anitatheone.moviecta;

import java.io.Serializable;

public class Movie implements Serializable {
    private String title;
    private String poster_path;
    private String release_date;
    private String adult;

    public Movie ()
    {}
    public Movie(String title,String poster_path,String release_date,String adult)
    {
        this.title = title;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.adult = adult;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getAdult() {
        return adult;
    }

    public void setAdult(String adult) {
        this.adult = adult;
    }



}
