package entities;

import java.util.ArrayList;
import java.util.List;

public final class Season {
    private int currentSeason;
    private int duration;
    private List<Double> ratings;
    private ArrayList<String> eligibleRaters;


    public Season() {

    }

    public Season(final int currentSeason, final int duration) {
        this.currentSeason = currentSeason;
        this.duration = duration;
        this.ratings = new ArrayList<>();
        this.eligibleRaters = new ArrayList<>();
    }

    public int getCurrentSeason() {
        return currentSeason;
    }

    public int getDuration() {
        return duration;
    }

    public List<Double> getRatings() {
        return ratings;
    }

    public ArrayList<String> getEligibleRaters() {
        return eligibleRaters;
    }
}
