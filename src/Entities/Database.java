package Entities;

import fileio.ActorInputData;
import fileio.MovieInputData;
import fileio.SerialInputData;
import fileio.UserInputData;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Database {
    protected List<Movie> movies = new ArrayList<>();
    protected List<Serial> serials = new ArrayList<>();
    protected List<User> users = new ArrayList<>();
    protected List<Actor> actors = new ArrayList<>();
    protected List<Video> videos = new ArrayList<>();

    private Database() {}
    private final static Database instance = new Database();

    public static Database getDatabase() {
            return instance;
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


    public List<Movie> getMovies() { return movies; }

    public List<Serial> getSerials() { return serials; }

    public List<User> getUsers() { return users; }

    public List<Actor> getActors() { return actors; }

    public List<Video> getVideos() { return videos; }
}
