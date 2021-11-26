package Entities;

import actor.ActorsAwards;
import fileio.ActorInputData;

import java.util.ArrayList;
import java.util.Map;

public class Actor {
    private String name;
    private String carrerDescription;
    private ArrayList<String> filmography;
    private Map<ActorsAwards, Integer> awards;

    public Actor() {}

    public Actor(ActorInputData actorData) {
        this.name = actorData.getName();
        this.carrerDescription = actorData.getCareerDescription();
        this.filmography = actorData.getFilmography();
        this.awards = actorData.getAwards();
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getCarrerDescription() { return carrerDescription; }

    public void setCarrerDescription(String carrerDescription) { this.carrerDescription = carrerDescription; }

    public ArrayList<String> getFilmography() { return filmography; }

    public void setFilmography(ArrayList<String> filmography) { this.filmography = filmography; }

    public Map<ActorsAwards, Integer> getAwards() { return awards; }

    public void setAwards(Map<ActorsAwards, Integer> awards) { this.awards = awards; }

    public Double actorGrade(ArrayList<Movie> movies, ArrayList<Serial> serials) {
        double sumGrade = 0d;
        int count = 0;
        for (Movie m: movies) {
            if (m.findActor(this.name) == 1) {
                for (Double rating: m.getRatings()) {
                    sumGrade = sumGrade + rating;
                }
                if (sumGrade != 0) {
                    count = count + m.getRatings().size();
                }
            }
        }
        for (Serial s: serials) {
            if (s.findActor(this.name) == 1) {
                double newSum = 0d;
                for (Season season : s.getSeasons()) {
                    for (Double rating : season.getRatings()) {
                        newSum = newSum + rating;
                    }
                    if (season.getRatings().size() == 0) {
                        count = count + 1;
                    } else if (season.getRatings().size() != 0) {
                        count = count + season.getRatings().size();
                    }
                }
                if (newSum == 0) {
                    count = count - s.getNumberOfSeasons();
                }
                sumGrade = sumGrade + newSum;
            }
        }
        if (count == 0) {
            return 0d;
        }
        return sumGrade / count;
    }
}
