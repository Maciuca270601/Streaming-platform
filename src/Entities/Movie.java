package Entities;

import fileio.MovieInputData;

import java.util.ArrayList;
import java.util.Objects;

public class Movie extends Video{
    private int duration;
    private ArrayList<String> eligibleRaters;
    private ArrayList<Double> ratings;

    public Movie() {}

    public Movie(MovieInputData movieInput) {
        super(movieInput);
        this.duration = movieInput.getDuration();
        this.eligibleRaters = new ArrayList<>();
        this.ratings = new ArrayList<>();
    }

    public int getDuration() { return duration; }

    public void setDuration(int duration) { this.duration = duration; }

    public ArrayList<String> getEligibleRaters() { return eligibleRaters; }

    public ArrayList<Double> getRatings() { return ratings; }

    public Double ratingMovie() {
        double sumGrade = 0d;
        for (Double rating : this.ratings) {
            sumGrade = sumGrade + rating;
        }
        if (this.ratings.size() == 0) {
            return 0d;
        }
        return sumGrade / this.ratings.size();
    }

    public Integer durationMovie() {
        return this.duration;
    }
}
