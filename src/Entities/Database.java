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
        ArrayList<Actor> filteredActors = new ArrayList<>();
        if (filters == null) {
            filteredActors.addAll(toFilter);
            return filteredActors;
        }
        for (Actor a: toFilter) {
            int counter = 0; // how many keywords have been found
            String[] phrase = a.getCarrerDescription().split("\\W+");
            for (String s: filters) {
                for (String s2: phrase) {
                    if (Objects.equals(s2, s)) {
                       counter = counter + 1;
                       break;
                    }
                }
            }
            if (counter == filters.size()) {
                filteredActors.add(a);
            }
        }
        return filteredActors;
    }

    public ArrayList<Actor> filterActorByAwards(ArrayList<Actor> toFilter, List<String> filters) {
        ArrayList<Actor> filteredActors = new ArrayList<>();
        if (filters == null) {
            filteredActors.addAll(toFilter);
            return filteredActors;

        }
        for (Actor a : toFilter) {
            int counter = 0; // how many filters have been found
            for (String s : filters) {
                for (Map.Entry<ActorsAwards, Integer> entry : a.getAwards().entrySet()) {
                    if (Objects.equals(entry.getKey().toString(), s)) {
                        counter = counter + 1;
                    }
                }
            }
            if (counter == filters.size()) {
                filteredActors.add(a);
            }
        }
        return filteredActors;
    }

    public ArrayList<Movie> filterMoviesByYear(ArrayList<Movie> toFilter, List<String> filters) {
        ArrayList<Movie> filteredMovies = new ArrayList<>();
        if (filters == null) {
            filteredMovies.addAll(toFilter);
            return filteredMovies;
        }
        for (Movie m: toFilter) {
            for (String s : filters) {
                if (Objects.equals(s, String.valueOf(m.getYear()))) {
                    filteredMovies.add(m);
                }
            }
        }
        return filteredMovies;
    }

    public ArrayList<Movie> filterMoviesByGenre(ArrayList<Movie> toFilter, List<String> filters) {
        ArrayList<Movie> filteredMovies = new ArrayList<>();
        if (filters == null) {
            filteredMovies.addAll(toFilter);
            return filteredMovies;
        }
        for (Movie m: toFilter) {
            for (String s: filters) {
                for (String genre: m.getGenres()) {
                    if (Objects.equals(genre, s)) {
                        filteredMovies.add(m);
                    }
                }
            }
        }
        return filteredMovies;
    }

    public ArrayList<Serial> filterSerialsByYear(ArrayList<Serial> toFilter, List<String> filters) {
        ArrayList<Serial> filteredSerials = new ArrayList<>();
        if (filters == null) {
            filteredSerials.addAll(toFilter);
            return filteredSerials;
        }
        for (Serial s: toFilter) {
            for (String filter : filters) {
                if (Objects.equals(filter, String.valueOf(s.getYear()))) {
                    filteredSerials.add(s);
                }
            }
        }
        return filteredSerials;
    }

    public ArrayList<Serial> filterSerialsByGenre(ArrayList<Serial> toFilter, List<String> filters) {
        ArrayList<Serial> filteredSerials = new ArrayList<>();
        if (filters == null) {
            filteredSerials.addAll(toFilter);
            return filteredSerials;
        }
        for (Serial s: toFilter) {
            for (String filter: filters) {
                for (String genre: s.getGenres()) {
                    if (Objects.equals(genre, filter)) {
                        filteredSerials.add(s);
                    }
                }
            }
        }
        return filteredSerials;
    }


    public ArrayList<Actor> sortActorsByGrade(List<List<String>> filters) {
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

    public ArrayList<Actor> sortActorsByAwards(List<List<String>> filters) {
        ArrayList<Actor> filteredActors = filterActorByAwards((ArrayList<Actor>)this.actors, filters.get(3));

        filteredActors.sort(new Comparator<Actor>() {
            @Override
            public int compare(Actor o1, Actor o2) {
                int awardsCompare = o1.actorAwards(filters.get(3)).compareTo(o2.actorAwards(filters.get(3)));
                int nameCompare = o1.getName().compareTo(o2.getName());
                return (awardsCompare == 0) ? nameCompare : awardsCompare;
            }
        });
        return filteredActors;
    }

    public ArrayList<Actor> sortActorsByWords(List<List<String>> filters) {
        ArrayList<Actor> filteredActors = filterActorByWords((ArrayList<Actor>)this.actors, filters.get(2));

        filteredActors.sort(new Comparator<Actor>() {
            @Override
            public int compare(Actor o1, Actor o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        return filteredActors;
    }

    public ArrayList<User> sortUsersByRating() {
        ArrayList<User> filteredUsers = new ArrayList<>();
        for(User u: this.users) {
            if(u.countRatings((ArrayList<Movie>)movies, (ArrayList<Serial>)serials) != 0) {
                filteredUsers.add(u);
            }
        }
        filteredUsers.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int counterCompare = o1.countRatings((ArrayList<Movie>)movies, (ArrayList<Serial>)serials).compareTo(o2.countRatings((ArrayList<Movie>)movies, (ArrayList<Serial>)serials));
                int nameCompare = o1.getUsername().compareTo(o2.getUsername());
                return (counterCompare == 0) ? nameCompare : counterCompare;
            }
        });
        return filteredUsers;
    }

    public ArrayList<Movie> sortMoviesByRating(List<List<String>> filters) {
        ArrayList<Movie> filteredMovies = filterMoviesByYear((ArrayList<Movie>)this.movies, filters.get(0));
        filteredMovies = filterMoviesByGenre(filteredMovies, filters.get(1));

        filteredMovies.removeIf(m -> m.ratingMovie() == 0);

        filteredMovies.sort(new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                int ratingCompare = o1.ratingMovie().compareTo(o2.ratingMovie());
                int nameCompare = o1.getTitle().compareTo(o2.getTitle());
                return (ratingCompare == 0) ? nameCompare : ratingCompare;
            }
        });
        return filteredMovies;
    }

    public ArrayList<Serial> sortSerialsByRating(List<List<String>> filters) {
        ArrayList<Serial> filteredSerials = filterSerialsByYear((ArrayList<Serial>)this.serials, filters.get(0));
        filteredSerials = filterSerialsByGenre(filteredSerials, filters.get(1));

        filteredSerials.removeIf(s -> s.ratingSerial() == 0);

        filteredSerials.sort(new Comparator<Serial>() {
            @Override
            public int compare(Serial o1, Serial o2) {
                int ratingCompare = o1.ratingSerial().compareTo(o2.ratingSerial());
                int nameCompare = o1.getTitle().compareTo(o2.getTitle());
                return (ratingCompare == 0) ? nameCompare : ratingCompare;
            }
        });
        return filteredSerials;
    }

    public ArrayList<Movie> sortMoviesByFavorite(List<List<String>> filters) {
        ArrayList<Movie> filteredMovies = filterMoviesByYear((ArrayList<Movie>)this.movies, filters.get(0));
        filteredMovies = filterMoviesByGenre(filteredMovies, filters.get(1));

        filteredMovies.removeIf(m -> m.favoriteVideo((ArrayList<User>)this.users) == 0);

        filteredMovies.sort(new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                int favoriteCompare = o1.favoriteVideo((ArrayList<User>)users).compareTo(o2.favoriteVideo((ArrayList<User>)users));
                int nameCompare = o1.getTitle().compareTo(o2.getTitle());
                return (favoriteCompare == 0) ? nameCompare : favoriteCompare;
            }
        });
        return filteredMovies;
    }

    public ArrayList<Serial> sortSerialsByFavorite(List<List<String>> filters) {
        ArrayList<Serial> filteredSerials = filterSerialsByYear((ArrayList<Serial>)this.serials, filters.get(0));
        filteredSerials = filterSerialsByGenre(filteredSerials, filters.get(1));

        filteredSerials.removeIf(s -> s.favoriteVideo((ArrayList<User>)this.users) == 0);

        filteredSerials.sort(new Comparator<Serial>() {
            @Override
            public int compare(Serial o1, Serial o2) {
                int favoriteCompare = o1.favoriteVideo((ArrayList<User>)users).compareTo(o2.favoriteVideo((ArrayList<User>)users));
                int nameCompare = o1.getTitle().compareTo(o2.getTitle());
                return (favoriteCompare == 0) ? nameCompare : favoriteCompare;
            }
        });
        return filteredSerials;
    }

    public ArrayList<Movie> sortMoviesByLongest(List<List<String>> filters) {
        ArrayList<Movie> filteredMovies = filterMoviesByYear((ArrayList<Movie>)this.movies, filters.get(0));
        filteredMovies = filterMoviesByGenre(filteredMovies, filters.get(1));

        filteredMovies.sort(new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                int longestCompare = o1.durationMovie().compareTo(o2.durationMovie());
                int nameCompare = o1.getTitle().compareTo(o2.getTitle());
                return (longestCompare == 0) ? nameCompare : longestCompare;
            }
        });
        return filteredMovies;
    }

    public ArrayList<Serial> sortSerialsByLongest(List<List<String>> filters) {
        ArrayList<Serial> filteredSerials = filterSerialsByYear((ArrayList<Serial>)this.serials, filters.get(0));
        filteredSerials = filterSerialsByGenre(filteredSerials, filters.get(1));

        filteredSerials.sort(new Comparator<Serial>() {
            @Override
            public int compare(Serial o1, Serial o2) {
                int longestCompare = o1.durationSerial().compareTo(o2.durationSerial());
                int nameCompare = o1.getTitle().compareTo(o2.getTitle());
                return (longestCompare == 0) ? nameCompare : longestCompare;
            }
        });
        return filteredSerials;
    }




    public List<Movie> getMovies() { return movies; }

    public List<Serial> getSerials() { return serials; }

    public List<User> getUsers() { return users; }

    public List<Actor> getActors() { return actors; }

    public List<Video> getVideos() { return videos; }
}
