package Entities;

import fileio.ShowInput;

import java.util.ArrayList;

public class Video {
    private String title;
    private int year;
    private ArrayList<String> cast;
    private ArrayList<String> genres;

    public Video() {}

    public Video(ShowInput show) {
        this.title = show.getTitle();
        this.year = show.getYear();
        this.cast = show.getCast();
        this.genres = show.getGenres();
    }

    public String getTitle() { return title; }

    public int getYear() { return year; }

    public ArrayList<String> getCast() { return cast; }

    public ArrayList<String> getGenres() { return genres; }
}
