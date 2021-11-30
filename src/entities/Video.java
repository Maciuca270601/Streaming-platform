package entities;

import fileio.ShowInput;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class Video {
    private String title;
    private int year;
    private ArrayList<String> cast;
    private ArrayList<String> genres;

    public Video() {

    }

    public Video(final ShowInput show) {
        this.title = show.getTitle();
        this.year = show.getYear();
        this.cast = show.getCast();
        this.genres = show.getGenres();
    }

    public final String getTitle() {
        return title;
    }

    public final int getYear() {
        return year;
    }

    public final ArrayList<String> getCast() {
        return cast;
    }

    public  final ArrayList<String> getGenres() {
        return genres;
    }

    /**
     * findActor
     * -> designed to check if an actor has played in a certain video.
     * -> 0 => has not played in that certain video.
     * -> 1 => has played in that certain video.
     */
    public int findActor(final String name) {
        for (String s: cast) {
            if (Objects.equals(s, name)) {
                return 1;
            }
        }
        return 0;
    }

    /**
     * favoriteVideo
     * -> designed to return the number of favorite nominations that a certain video has.
     */
    public Integer favoriteVideo(final ArrayList<User> users) {
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

    /**
     *  this does nothing because it will be overwritten.
     */
    public Double ratingVideo() {
        return 0d;
    }

    /**
     * positionVideo
     * -> designed to return the index of a video from the existing database.
     */
    public Integer positionVideo(final ArrayList<Video> videos) {
        for (int i = 0; i < videos.size(); i++) {
            if (Objects.equals(videos.get(i).getTitle(), this.title)) {
                return i;
            }
        }
        return 0;
    }

    /**
     * viewsVideo
     * -> designed to return the total amount of views that a certain video has.
     */
    public Integer viewsVideo(final ArrayList<User> users) {
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
