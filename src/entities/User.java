package entities;

import fileio.UserInputData;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public final class User {
    private String username;
    private String subscriptionType;
    private Map<String, Integer> history;
    private ArrayList<String> favoriteMovies;

    public User() {

    }

    public User(final UserInputData userData) {
        this.username = userData.getUsername();
        this.subscriptionType = userData.getSubscriptionType();
        this.history = userData.getHistory();
        this.favoriteMovies = userData.getFavoriteMovies();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(final String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public Map<String, Integer> getHistory() {
        return history;
    }

    public void setHistory(final Map<String, Integer> history) {
        this.history = history;
    }

    public ArrayList<String> getFavoriteMovies() {
        return favoriteMovies;
    }

    /**
     * countRatings
     * -> designed to return the number of ratings that a certain user has given.
     */
    public Integer countRatings(final ArrayList<Movie> movies, final ArrayList<Serial> serials) {
        int counter = 0;
        for (Movie m: movies) {
            for (String s: m.getEligibleRaters()) {
                if (Objects.equals(this.username, s)) {
                    counter = counter + 1;
                    break;
                }
            }
        }
        for (Serial serial: serials) {
            for (Season season: serial.getSeasons()) {
                for (String s: season.getEligibleRaters()) {
                    if (Objects.equals(this.username, s)) {
                        counter = counter + 1;
                        break;
                    }
                }
            }
        }
        return counter;
    }

    /**
     * isVideo
     * -> designed to check if a video has been seen by a certain user.
     * -> 0 => has not been seen.
     * -> 1 => has been seen.
     */
    public Integer isVideo(final String videoName) {
        int viewed = 0; // has not been seen
        for (Map.Entry<String, Integer> entry : this.history.entrySet()) {
            if (Objects.equals(entry.getKey(), videoName)) {
                viewed = 1; // has been seen
                break;
            }
        }
        return viewed;
    }
}
