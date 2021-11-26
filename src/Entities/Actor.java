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
}
