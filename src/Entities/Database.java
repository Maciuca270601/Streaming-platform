package Entities;

import actor.ActorsAwards;
import fileio.ActorInputData;
import fileio.MovieInputData;
import fileio.SerialInputData;
import fileio.UserInputData;

import java.util.*;

public class Database {
    private List<Movie> movies;
    private List<Serial> serials;
    private List<User> users;
    private List<Actor> actors;
    private List<Video> videos;

    public Database() {
        this.movies = new ArrayList<>();
        this.serials = new ArrayList<>();
        this.users = new ArrayList<>();
        this.actors = new ArrayList<>();
        this.videos = new ArrayList<>();
    }

    public void addMovies(List<MovieInputData> moviesInput) {
        for(MovieInputData movieInput: moviesInput) {
            Movie movie = new Movie(movieInput);
            this.movies.add(movie);
        }
    }

    public void addSerials(List<SerialInputData> serialsInput) {
        for(SerialInputData serialInput: serialsInput) {
            Serial serial = new Serial(serialInput);
            this.serials.add(serial);
        }
    }

    public void addUsers(List<UserInputData> usersInput) {
        for(UserInputData userInput: usersInput) {
            User user = new User(userInput);
            this.users.add(user);
        }
    }

    public void addActors(List<ActorInputData> actorsInput) {
        for(ActorInputData actorInput: actorsInput) {
            Actor actor = new Actor(actorInput);
            this.actors.add(actor);
        }
    }

    public void addVideos() {
        this.videos.addAll(movies);
        this.videos.addAll(serials);
    }

    public Movie findMovie(String movieName) {
        for (Movie m: this.getMovies()) {
            if (Objects.equals(m.getTitle(), movieName)) {
                return m;
            }
        }
        return null;
    }

    public User findUser(String userName) {
        for (User u: this.getUsers()) {
            if(Objects.equals(u.getUsername(), userName)) {
                return u;
            }
        }
        return null;
    }

    public Season findSeason(String serialName, int currentSeason) {
        for (Serial s: this.getSerials()) {
            if (Objects.equals(s.getTitle(), serialName)) {
                for (int i = 0; i < s.getNumberOfSeasons(); i++) {
                    if (s.getSeasons().get(i).getCurrentSeason() == currentSeason) {
                        return s.getSeasons().get(i);
                    }
                }
            }
        }
        return null;
    }

    public Serial findSerial(String serialName) {
        for (Serial s: this.getSerials()) {
            if (Objects.equals(s.getTitle(), serialName)) {
                return s;
            }
        }
        return null;
    }

    public ArrayList<Actor> filterActorByWords(ArrayList<Actor> toFilter, List<String> filters) {
        if (filters == null) {
           return toFilter;
        }
        ArrayList<Actor> filteredActors = new ArrayList<>();
        for (Actor a: toFilter) {
            for (String s: filters) {
                if (Objects.equals(s, a.getCarrerDescription())) {
                    filteredActors.add(a);
                }
            }
        }
        return filteredActors;
    }

    public ArrayList<Actor> filterActorByAwards(ArrayList<Actor> toFilter, List<String> filters) {
        if (filters == null) {
            return toFilter;
        }
        ArrayList<Actor> filteredActors = new ArrayList<>();
        for (Actor a: toFilter) {
            for (String s: filters) {
                if (Objects.equals(s, a.getAwards().toString())) {
                    filteredActors.add(a);
                }
            }
        }
        return filteredActors;
    }



    public ArrayList<Actor> sortActorsByGrade (List<List<String>> filters) {
        ArrayList<Actor> filteredActors = filterActorByWords((ArrayList<Actor>)this.actors, filters.get(2));
        filteredActors = filterActorByAwards(filteredActors, filters.get(3));

        filteredActors.removeIf(a -> a.actorGrade((ArrayList<Movie>) movies, (ArrayList<Serial>) serials) == 0);

        filteredActors.sort(new Comparator<Actor>() {

            @Override
            public int compare(Actor o1, Actor o2) {
                int gradeCompare = o1.actorGrade((ArrayList<Movie>)movies, (ArrayList<Serial>)serials).compareTo(o2.actorGrade((ArrayList<Movie>)movies, (ArrayList<Serial>)serials));
                int nameCompare = o1.getName().compareTo(o2.getName());
                return (gradeCompare == 0) ? nameCompare : gradeCompare;
            }
        });
        return filteredActors;
    }


    public List<Movie> getMovies() { return movies; }

    public List<Serial> getSerials() { return serials; }

    public List<User> getUsers() { return users; }

    public List<Actor> getActors() { return actors; }

    public List<Video> getVideos() { return videos; }
}
