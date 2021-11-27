package Entities;

import Tasks.Command;
import fileio.UserInputData;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class User {
    private String username;
    private String subscriptionType;
    private Map<String, Integer> history;
    private ArrayList<String> favoriteMovies;

    public User() {}

    public User(UserInputData userData) {
        this.username = userData.getUsername();
        this.subscriptionType = userData.getSubscriptionType();
        this.history = userData.getHistory();
        this.favoriteMovies = userData.getFavoriteMovies();
    }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getSubscriptionType() { return subscriptionType; }

    public void setSubscriptionType(String subscriptionType) { this.subscriptionType = subscriptionType; }

    public Map<String, Integer> getHistory() { return history; }

    public void setHistory(Map<String, Integer> history) { this.history = history; }

    public ArrayList<String> getFavoriteMovies() { return favoriteMovies; }

    public Integer countRatings(ArrayList<Movie>movies, ArrayList<Serial>serials) {
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

    public Integer isVideo(String videoName) {
        int viewed = 0; // presupunem ca nu l-am vazut
        for (Map.Entry<String, Integer> entry : this.history.entrySet()) {
            if (Objects.equals(entry.getKey(), videoName)) {
                viewed = 1;
                break;
            }
        }
        return viewed;
    }
}
