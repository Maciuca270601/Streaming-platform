package Entities;

import fileio.ShowInput;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

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

    public int findActor(String name) {
        for (String s: cast) {
            if (Objects.equals(s, name)) {
                return 1;
            }
        }
        return 0;
    }

    public Integer favoriteVideo(ArrayList<User> users) {
        int counter = 0;
        for (User u: users) {
            for (String favorite: u.getFavoriteMovies()) {
                if (Objects.equals(favorite, this.getTitle())) {
                    counter = counter + 1;
                    break;
                }
            }
        }
        return counter;
    }

    public Double ratingVideo() {
        return 0d;
    }

    public Integer positionVideo(ArrayList<Video> videos) {
        for (int i = 0; i < videos.size(); i++) {
            if (Objects.equals(videos.get(i).getTitle(), this.title)) {
                return i;
            }
        }
        return 0;
    }

    public Integer viewsVideo(ArrayList<User> users) {
        int counter = 0;
        for (User u : users) {
            for (Map.Entry<String, Integer> entry : u.getHistory().entrySet()) {
                if (Objects.equals(entry.getKey(), this.getTitle())) {
                    counter = counter + entry.getValue();
                }
            }
        }
        return counter;
    }
}
