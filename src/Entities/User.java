package Entities;

import Tasks.Command;
import fileio.UserInputData;

import java.util.ArrayList;
import java.util.Map;

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
}
