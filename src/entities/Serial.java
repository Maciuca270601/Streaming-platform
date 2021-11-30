package entities;

import fileio.SerialInputData;

import java.util.ArrayList;

public final class Serial extends Video {
    private int numberOfSeasons;
    private final ArrayList<Season> seasons = new ArrayList<>();

    public Serial() {

    }

    public Serial(final SerialInputData serialInput) {
        super(serialInput);
        this.numberOfSeasons = serialInput.getNumberSeason();
        int currentSeason = 1;
        for (entertainment.Season si: serialInput.getSeasons()) {
            Season s = new Season(currentSeason, si.getDuration());
            this.seasons.add(s);
            currentSeason = currentSeason + 1;
        }
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    /**
     * ratingVideo
     * -> designed to return the overall rating of a serial.
     * -> each season has its own average rating.
     * -> the overall rating is an average of each season rating.
     */
    public Double ratingVideo() {
        double seasonGrade;
        double serialGrade = 0d;
        for (Season s: this.seasons) {
            seasonGrade = 0d;
            for (Double score: s.getRatings()) {
                seasonGrade = seasonGrade + score;
            }
            if (s.getRatings().size() != 0) {
                serialGrade = serialGrade + (seasonGrade / s.getRatings().size());
            }
        }
        if (serialGrade == 0) {
           return 0d;
        }
        return serialGrade / numberOfSeasons;
    }

    /**
     * durationSerial
     * -> designed to return the overall duration of a serial.
     * -> each season has its own duration.
     * -> the serial duration is the sum of each season duration.
     */
    public Integer durationSerial() {
        int duration = 0;
        for (Season s: this.getSeasons()) {
            duration = duration + s.getDuration();
        }
        return duration;
    }
}
