package Entities;

import fileio.SerialInputData;

import java.util.ArrayList;

public class Serial extends Video{
    private int numberOfSeasons;
    private ArrayList<Season> seasons = new ArrayList<>();

    public Serial() {}

    public Serial(SerialInputData serialInput) {
        super(serialInput);
        this.numberOfSeasons = serialInput.getNumberSeason();
        int currentSeason = 1;
        for (entertainment.Season si: serialInput.getSeasons()) {
            Season s = new Season(currentSeason, si.getDuration());
            this.seasons.add(s);
            currentSeason = currentSeason + 1;
        }
    }

    public int getNumberOfSeasons() { return numberOfSeasons; }

    public ArrayList<Season> getSeasons() { return seasons; }

    public Double averageGrade() {
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

}
