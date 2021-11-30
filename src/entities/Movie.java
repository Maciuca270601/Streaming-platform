package entities;

import fileio.MovieInputData;

import java.util.ArrayList;

public final class Movie extends Video {
    private int duration;
    private ArrayList<String> eligibleRaters;
    private ArrayList<Double> ratings;

    public Movie() {

    }

    public Movie(final MovieInputData movieInput) {
        super(movieInput);
        this.duration = movieInput.getDuration();
        this.eligibleRaters = new ArrayList<>();
        this.ratings = new ArrayList<>();
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(final int duration) {
        this.duration = duration;
    }

    public ArrayList<String> getEligibleRaters() {
        return eligibleRaters;
    }

    public ArrayList<Double> getRatings() {
        return ratings;
    }

    /**
     * ratingVideo
     * -> returns the average rating obtained by a movie.
     */
    public Double ratingVideo() {
        double sumGrade = 0d;
        for (Double rating : this.ratings) {
            sumGrade = sumGrade + rating;
        }
        if (this.ratings.size() == 0) {
            return 0d;
        }
        return sumGrade / this.ratings.size();
    }
}
