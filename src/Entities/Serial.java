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
        double sumGrade = 0d;
        int numberOfGrades = 0;
        for (Season s: this.seasons) {
            for (Double score: s.getRatings()) {
                if (score != 0) {
                    sumGrade = sumGrade + score;
                    numberOfGrades = numberOfGrades + 1;
                }
            }
        }
        if (numberOfGrades == 0) {
           return 0d;
        }
        return sumGrade / numberOfGrades;
    }

}
