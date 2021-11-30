package entities;

import actor.ActorsAwards;
import fileio.ActorInputData;

import java.util.ArrayList;
import java.util.Map;

public final class Actor {
    private String name;
    private String careerDescription;
    private ArrayList<String> filmography;
    private Map<ActorsAwards, Integer> awards;

    public Actor() {

    }

    public Actor(final ActorInputData actorData) {
        this.name = actorData.getName();
        this.careerDescription = actorData.getCareerDescription();
        this.filmography = actorData.getFilmography();
        this.awards = actorData.getAwards();
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getCareerDescription() {
        return careerDescription;
    }

    public ArrayList<String> getFilmography() {
        return filmography;
    }

    public void setFilmography(final ArrayList<String> filmography) {
        this.filmography = filmography;
    }

    public Map<ActorsAwards, Integer> getAwards() {
        return awards;
    }

    public void setAwards(final Map<ActorsAwards, Integer> awards) {
        this.awards = awards;
    }

    /**
     * actorGrade
     * -> designed to determine a sum of ratings for every video in which
     * this actor has played. The sum follows the indications provided on OCW.
     */
    public Double actorGrade(final ArrayList<Movie> movies, final ArrayList<Serial> serials) {
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

    /**
     * actorAwards
     * -> designed to determine how many awards this actor has won,
     * iterating through its hashmap for awards.
     */
    public Integer actorAwards() {
        int counter = 0;
        for (Map.Entry<ActorsAwards, Integer> entry : this.getAwards().entrySet()) {
            counter = counter + entry.getValue();
        }
        return counter;
    }
}
