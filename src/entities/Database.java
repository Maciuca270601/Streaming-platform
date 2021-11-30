package entities;

import actor.ActorsAwards;
import entertainment.Genre;
import fileio.ActorInputData;
import fileio.MovieInputData;
import fileio.SerialInputData;
import fileio.UserInputData;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.HashMap;


public final class Database {
    private final List<Movie> movies;
    private final List<Serial> serials;
    private final List<User> users;
    private final List<Actor> actors;
    private final List<Video> videos;

    public Database() {
        this.movies = new ArrayList<>();
        this.serials = new ArrayList<>();
        this.users = new ArrayList<>();
        this.actors = new ArrayList<>();
        this.videos = new ArrayList<>();
    }

    /**
     * addMovies
     * -> designed to add all Movies from MovieInputData to my database.
     */
    public void addMovies(final List<MovieInputData> moviesInput) {
        for (MovieInputData movieInput: moviesInput) {
            Movie movie = new Movie(movieInput);
            this.movies.add(movie);
        }
    }

    /**
     * addSerials
     * -> designed to add all Serials from SerialInputData to my database.
     */
    public void addSerials(final List<SerialInputData> serialsInput) {
        for (SerialInputData serialInput: serialsInput) {
            Serial serial = new Serial(serialInput);
            this.serials.add(serial);
        }
    }

    /**
     * addUsers
     * -> designed to add all Users from UserInputData to my database.
     */
    public void addUsers(final List<UserInputData> usersInput) {
        for (UserInputData userInput: usersInput) {
            User user = new User(userInput);
            this.users.add(user);
        }
    }

    /**
     * addActors
     * -> designed to add all Actors from ActorInputData to my database.
     */
    public void addActors(final List<ActorInputData> actorsInput) {
        for (ActorInputData actorInput: actorsInput) {
            Actor actor = new Actor(actorInput);
            this.actors.add(actor);
        }
    }

    /**
     * addVideos
     * -> designed to add all Videos (both Movies and Serials).
     */
    public void addVideos() {
        this.videos.addAll(movies);
        this.videos.addAll(serials);
    }

    /**
     * findMovie
     * -> designed to return the movie with the given name.
     */
    public Movie findMovie(final String movieName) {
        for (Movie m: this.getMovies()) {
            if (Objects.equals(m.getTitle(), movieName)) {
                return m;
            }
        }
        return null;
    }

    /**
     * findSerials
     * -> designed to return the serial with the given name.
     */
    public User findUser(final String userName) {
        for (User u: this.getUsers()) {
            if (Objects.equals(u.getUsername(), userName)) {
                return u;
            }
        }
        return null;
    }

    /**
     * findSeason
     * -> designed to return the season with the given name and current season.
     */
    public Season findSeason(final String serialName, final int currentSeason) {
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

    /**
     * findSerial
     * -> designed to return the serial with the given name.
     */
    public Serial findSerial(final String serialName) {
        for (Serial s: this.getSerials()) {
            if (Objects.equals(s.getTitle(), serialName)) {
                return s;
            }
        }
        return null;
    }

    /**
     * filterActorByWords
     * -> designed to return the list of actors that have those specified words
     * in their description.
     */
    public ArrayList<Actor> filterActorByWords(final ArrayList<Actor> toFilter,
                                               final List<String> filters) {
        ArrayList<Actor> filteredActors = new ArrayList<>();
        // if there is no filter to apply then return the original list
        if (filters == null || filters.get(0) == null) {
            filteredActors.addAll(toFilter);
            return filteredActors;
        }
        for (Actor a: toFilter) {
            int counter = 0; // how many keywords have been found
            String[] phrase = a.getCareerDescription().split("\\W+");
            for (String s: filters) {
                for (String s2: phrase) {
                    if (Objects.equals(s2.toLowerCase(), s.toLowerCase())) {
                       counter = counter + 1;
                       break;
                    }
                }
            }
            // if all keywords have been found in the actor description
            if (counter == filters.size()) {
                filteredActors.add(a);
            }
        }
        return filteredActors;
    }

    /**
     * filterActorByAwards
     * -> designed to return the list of actors that have all the awards
     * specified.
     */
    public ArrayList<Actor> filterActorByAwards(final ArrayList<Actor> toFilter,
                                                final List<String> filters) {
        ArrayList<Actor> filteredActors = new ArrayList<>();
        // if there is no filter to apply then return the original list
        if (filters == null || filters.get(0) == null) {
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
            // if all keywords have been found in the actor description
            if (counter == filters.size()) {
                filteredActors.add(a);
            }
        }
        return filteredActors;
    }

    /**
     * filterMoviesByYear
     * -> designed to return the list of movies that have at least one year
     * specified.
     */
    public ArrayList<Movie> filterMoviesByYear(final ArrayList<Movie> toFilter,
                                               final List<String> filters) {
        ArrayList<Movie> filteredMovies = new ArrayList<>();
        // if there is no filter to apply then return the original list
        if (filters == null || filters.get(0) == null) {
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

    /**
     * filterMoviesByGenre
     * -> designed to return the list of movies that have at least one genre
     * specified.
     */
    public ArrayList<Movie> filterMoviesByGenre(final ArrayList<Movie> toFilter,
                                                final List<String> filters) {
        ArrayList<Movie> filteredMovies = new ArrayList<>();
        // if there is no filter to apply then return the original list
        if (filters == null || filters.get(0) == null) {
            filteredMovies.addAll(toFilter);
            return filteredMovies;
        }
        for (Movie m: toFilter) {
            for (String s: filters) {
                for (String genre: m.getGenres()) {
                    if (Objects.equals(genre.toLowerCase(), s.toLowerCase())) {
                        filteredMovies.add(m);
                    }
                }
            }
        }
        return filteredMovies;
    }

    /**
     * filterSerialByYear
     * -> designed to return the list of serials that have at least one year
     * specified.
     */
    public ArrayList<Serial> filterSerialsByYear(final ArrayList<Serial> toFilter,
                                                 final List<String> filters) {
        ArrayList<Serial> filteredSerials = new ArrayList<>();
        // if there is no filter to apply then return the original list
        if (filters == null || filters.get(0) == null) {
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

    /**
     * filterSerialByGenre
     * -> designed to return the list of serials that have at least one genre
     * specified.
     */
    public ArrayList<Serial> filterSerialsByGenre(final ArrayList<Serial> toFilter,
                                                  final List<String> filters) {
        ArrayList<Serial> filteredSerials = new ArrayList<>();
        // if there is no filter to apply then return the original list
        if (filters == null || filters.get(0) == null) {
            filteredSerials.addAll(toFilter);
            return filteredSerials;
        }
        for (Serial s: toFilter) {
            for (String filter: filters) {
                for (String genre: s.getGenres()) {
                    if (Objects.equals(genre.toLowerCase(), filter.toLowerCase())) {
                        filteredSerials.add(s);
                    }
                }
            }
        }
        return filteredSerials;
    }

    /**
     * sortActorsByGrade
     * -> designed to sort actors based on the overall rating of the videos in
     * which they have played.
     * -> if applicable, both filterActorByWords and filterActorByAwards are used.
     * -> the ArrayList is sorted in ascending order.
     */
    public ArrayList<Actor> sortActorsByGrade(final List<List<String>> filters) {
        ArrayList<Actor> filteredActors = filterActorByWords((ArrayList<Actor>) this.actors,
                                                             filters.get(2));
        filteredActors = filterActorByAwards(filteredActors, filters.get(2 + 1));

        // remove actors with  the grade equal to 0
        filteredActors.removeIf(a -> a.actorGrade((ArrayList<Movie>) movies,
                                                  (ArrayList<Serial>) serials) == 0);

        filteredActors.sort((o1, o2) -> {
            int gradeCompare = o1.actorGrade((ArrayList<Movie>) movies,
                    (ArrayList<Serial>) serials).compareTo(o2.actorGrade((ArrayList<Movie>) movies,
                    (ArrayList<Serial>) serials));
            int nameCompare = o1.getName().compareTo(o2.getName());
            return (gradeCompare == 0) ? nameCompare : gradeCompare;
        });
        return filteredActors;
    }

    /**
     * sortActorsByAwards
     * -> designed to sort actors based on the number of awards that they have received.
     * -> if applicable, filterActorByAwards is used.
     * -> the ArrayList is sorted in ascending order.
     */
    public ArrayList<Actor> sortActorsByAwards(final List<List<String>> filters) {
        ArrayList<Actor> filteredActors = filterActorByAwards((ArrayList<Actor>) this.actors,
                                                              filters.get(2 + 1));

        filteredActors.sort((o1, o2) -> {
            int awardsCompare = o1.actorAwards().compareTo(o2.actorAwards());
            int nameCompare = o1.getName().compareTo(o2.getName());
            return (awardsCompare == 0) ? nameCompare : awardsCompare;
        });
        return filteredActors;
    }

    /**
     * sortActorsByWords
     * -> designed to sort actors based on the description they have.
     * -> if applicable, filterActorByWords is used.
     * -> the ArrayList is sorted in ascending order.
     */
    public ArrayList<Actor> sortActorsByWords(final List<List<String>> filters) {
        ArrayList<Actor> filteredActors = filterActorByWords((ArrayList<Actor>) this.actors,
                                                             filters.get(2));

        filteredActors.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
        return filteredActors;
    }

    /**
     * sortUsersByRating
     * -> designed to sort users based on the overall rating they have sent.
     * -> add users only if they have rated at least one video.
     * -> the ArrayList is sorted in ascending order.
     */
    public ArrayList<User> sortUsersByRating() {
        ArrayList<User> filteredUsers = new ArrayList<>();
        for (User u: this.users) {
            if (u.countRatings((ArrayList<Movie>) movies, (ArrayList<Serial>) serials) != 0) {
                filteredUsers.add(u);
            }
        }
        filteredUsers.sort((o1, o2) -> {
            int counterCompare = o1.countRatings((ArrayList<Movie>) movies,
                    (ArrayList<Serial>) serials).compareTo(o2.countRatings(
                            (ArrayList<Movie>) movies, (ArrayList<Serial>) serials));
            int nameCompare = o1.getUsername().compareTo(o2.getUsername());
            return (counterCompare == 0) ? nameCompare : counterCompare;
        });
        return filteredUsers;
    }

    /**
     * sortMoviesByRating
     * -> designed to sort movies based on the overall rating received from all users.
     * -> if applicable, both filterMoviesByYear and filterMoviesByGenre are used.
     * -> the ArrayList is sorted in ascending order.
     */
    public ArrayList<Movie> sortMoviesByRating(final List<List<String>> filters) {
        ArrayList<Movie> filteredMovies = filterMoviesByYear((ArrayList<Movie>) this.movies,
                                                             filters.get(0));
        filteredMovies = filterMoviesByGenre(filteredMovies, filters.get(1));
        // remove movie from the list if it has 0 ratings
        filteredMovies.removeIf(m -> m.ratingVideo() == 0);

        filteredMovies.sort((o1, o2) -> {
            int ratingCompare = o1.ratingVideo().compareTo(o2.ratingVideo());
            int nameCompare = o1.getTitle().compareTo(o2.getTitle());
            return (ratingCompare == 0) ? nameCompare : ratingCompare;
        });
        return filteredMovies;
    }

    /**
     * sortSerialsByRating
     * -> designed to sort serials based on the overall rating received from all users.
     * -> if applicable, both filterSerialsByYear and filterSerialsByGenre are used.
     * -> the ArrayList is sorted in ascending order.
     */
    public ArrayList<Serial> sortSerialsByRating(final List<List<String>> filters) {
        ArrayList<Serial> filteredSerials = filterSerialsByYear((ArrayList<Serial>) this.serials,
                                                                filters.get(0));
        filteredSerials = filterSerialsByGenre(filteredSerials, filters.get(1));
        // remove if there is a serial with 0 ratings
        filteredSerials.removeIf(s -> s.ratingVideo() == 0);

        filteredSerials.sort((o1, o2) -> {
            int ratingCompare = o1.ratingVideo().compareTo(o2.ratingVideo());
            int nameCompare = o1.getTitle().compareTo(o2.getTitle());
            return (ratingCompare == 0) ? nameCompare : ratingCompare;
        });
        return filteredSerials;
    }

    /**
     * sortMoviesByFavorite
     * -> designed to sort movies based on the overall numbers of favorite nominations.
     * -> if applicable, both filterMoviesByYear and filterMoviesByGenre are used.
     * -> the ArrayList is sorted in ascending order.
     */
    public ArrayList<Movie> sortMoviesByFavorite(final List<List<String>> filters) {
        ArrayList<Movie> filteredMovies = filterMoviesByYear((ArrayList<Movie>) this.movies,
                                                             filters.get(0));
        filteredMovies = filterMoviesByGenre(filteredMovies, filters.get(1));
        // remove if any movie has 0 favorite nominations
        filteredMovies.removeIf(m -> m.favoriteVideo((ArrayList<User>) this.users) == 0);

        filteredMovies.sort((o1, o2) -> {
            int favoriteCompare = o1.favoriteVideo((ArrayList<User>) users)
                                  .compareTo(o2.favoriteVideo((ArrayList<User>) users));
            int nameCompare = o1.getTitle().compareTo(o2.getTitle());
            return (favoriteCompare == 0) ? nameCompare : favoriteCompare;
        });
        return filteredMovies;
    }

    /**
     * sortSerialsByFavorite
     * -> designed to sort serials based on the overall numbers of favorite nominations.
     * -> if applicable, both filterSerialsByYear and filterSerialsByGenre are used.
     * -> the ArrayList is sorted in ascending order.
     */
    public ArrayList<Serial> sortSerialsByFavorite(final List<List<String>> filters) {
        ArrayList<Serial> filteredSerials = filterSerialsByYear((ArrayList<Serial>) this.serials,
                                                                filters.get(0));
        filteredSerials = filterSerialsByGenre(filteredSerials, filters.get(1));
        // remove if any serial has 0 favorite nominations
        filteredSerials.removeIf(s -> s.favoriteVideo((ArrayList<User>) this.users) == 0);

        filteredSerials.sort((o1, o2) -> {
            int favoriteCompare = o1.favoriteVideo((ArrayList<User>) users)
                                  .compareTo(o2.favoriteVideo((ArrayList<User>) users));
            int nameCompare = o1.getTitle().compareTo(o2.getTitle());
            return (favoriteCompare == 0) ? nameCompare : favoriteCompare;
        });
        return filteredSerials;
    }

    /**
     * sortMoviesByLongest
     * -> designed to sort movies based on the duration.
     * -> if applicable, both filterMoviesByYear and filterMoviesByGenre are used.
     * -> the ArrayList is sorted in ascending order.
     */
    public ArrayList<Movie> sortMoviesByLongest(final List<List<String>> filters) {
        ArrayList<Movie> filteredMovies = filterMoviesByYear((ArrayList<Movie>) this.movies,
                                                             filters.get(0));
        filteredMovies = filterMoviesByGenre(filteredMovies, filters.get(1));

        filteredMovies.sort((o1, o2) -> {
            int longestCompare = o1.getDuration().compareTo(o2.getDuration());
            int nameCompare = o1.getTitle().compareTo(o2.getTitle());
            return (longestCompare == 0) ? nameCompare : longestCompare;
        });
        return filteredMovies;
    }

    /**
     * sortSerialsByLongest
     * -> designed to sort serials based on the duration.
     * -> if applicable, both filterSerialsByYear and filterSerialsByGenre are used.
     * -> the ArrayList is sorted in ascending order.
     */
    public ArrayList<Serial> sortSerialsByLongest(final List<List<String>> filters) {
        ArrayList<Serial> filteredSerials = filterSerialsByYear((ArrayList<Serial>) this.serials,
                                                                filters.get(0));
        filteredSerials = filterSerialsByGenre(filteredSerials, filters.get(1));

        filteredSerials.sort((o1, o2) -> {
            int longestCompare = o1.durationSerial().compareTo(o2.durationSerial());
            int nameCompare = o1.getTitle().compareTo(o2.getTitle());
            return (longestCompare == 0) ? nameCompare : longestCompare;
        });
        return filteredSerials;
    }

    /**
     * sortMoviesByViews
     * -> designed to sort movies based on the overall number of views.
     * -> if applicable, both filterMoviesByYear and filterMoviesByGenre are used.
     * -> the ArrayList is sorted in ascending order.
     */
    public ArrayList<Movie> sortMoviesByViews(final List<List<String>> filters) {
        ArrayList<Movie> filteredMovies = filterMoviesByYear((ArrayList<Movie>) this.movies,
                                                             filters.get(0));
        filteredMovies = filterMoviesByGenre(filteredMovies, filters.get(1));
        // remove if any movie has 0 views
        filteredMovies.removeIf(m -> m.viewsVideo((ArrayList<User>) this.users) == 0);

        filteredMovies.sort((o1, o2) -> {
            int viewCompare = o1.viewsVideo((ArrayList<User>) users)
                              .compareTo(o2.viewsVideo((ArrayList<User>) users));
            int nameCompare = o1.getTitle().compareTo(o2.getTitle());
            return (viewCompare == 0) ? nameCompare : viewCompare;
        });
        return filteredMovies;
    }

    /**
     * sortSerialsByViews
     * -> designed to sort serials based on the overall number of views.
     * -> if applicable, both filterSerialsByYear and filterSerialsByGenre are used.
     * -> the ArrayList is sorted in ascending order.
     */
    public ArrayList<Serial> sortSerialsByViews(final List<List<String>> filters) {
        ArrayList<Serial> filteredSerials = filterSerialsByYear((ArrayList<Serial>) this.serials,
                                                                filters.get(0));
        filteredSerials = filterSerialsByGenre(filteredSerials, filters.get(1));
        // remove if any serial has 0 views
        filteredSerials.removeIf(s -> s.viewsVideo((ArrayList<User>) this.users) == 0);

        filteredSerials.sort((o1, o2) -> {
            int viewCompare = o1.viewsVideo((ArrayList<User>) users)
                              .compareTo(o2.viewsVideo((ArrayList<User>) users));
            int nameCompare = o1.getTitle().compareTo(o2.getTitle());
            return (viewCompare == 0) ? nameCompare : viewCompare;
        });
        return filteredSerials;
    }

    /**
     * sortVideoByViews
     * -> designed to return the best video unseen from the database by a certain user.
     * -> filteredVideos contains all videos that have not been seen by the user.
     * -> the ArrayList is sorted in ascending order.
     */
    public ArrayList<Video> sortVideosByViews(final String username) {
        ArrayList<Video> filteredVideos = new ArrayList<>();
        User u = findUser(username);
        for (Video v: this.videos) {
            assert u != null;
            if (u.isVideo(v.getTitle()) == 0) {
                filteredVideos.add(v);
            }
        }

        filteredVideos.sort((o1, o2) -> {
            int ratingCompare = o2.ratingVideo().compareTo(o1.ratingVideo());
            int secondCompare = o1.positionVideo((ArrayList<Video>) videos)
                                .compareTo(o2.positionVideo((ArrayList<Video>) videos));
            return (ratingCompare == 0) ? secondCompare : ratingCompare;
        });
        return filteredVideos;
    }

    /**
     * sortVideoBySearch
     * -> designed to return all videos from a genre that have not been seen by the user.
     * -> filteredVideos contains all videos that have not been seen by the user.
     * -> the ArrayList is sorted in ascending order.
     */
    public ArrayList<Video> sortVideosBySearch(final String username, final String genre) {
        ArrayList<Video> filteredVideos = new ArrayList<>();
        User u = findUser(username);
        for (Video v: this.videos) {
            int seen = 0; // video has not been seen
            assert u != null;
            for (Map.Entry<String, Integer> entry : u.getHistory().entrySet()) {
                if (Objects.equals(entry.getKey(), v.getTitle())) {
                    seen = 1; // video has been seen
                    break;
                }
            }
            if (seen == 0) {
                for (String s: v.getGenres()) {
                    if (Objects.equals(genre, s)) {
                        filteredVideos.add(v);
                    }
                }
            }
        }

        filteredVideos.sort((o1, o2) -> {
            int ratingCompare = o1.ratingVideo().compareTo(o2.ratingVideo());
            int nameCompare = o1.getTitle().compareTo(o2.getTitle());
            return (ratingCompare == 0) ? nameCompare : ratingCompare;
        });
        return filteredVideos;
    }

    /**
     * bestFavorite
     * -> designed to return the video with the most favorite nominations without being seen.
     * -> filteredVideos contains all videos that have not been seen by the user.
     * -> the ArrayList is sorted in ascending order.
     */
    public ArrayList<Video> bestFavorite(final User u) {
        ArrayList<Video> filteredVideos = new ArrayList<>();
        for (Video v: this.videos) {
            if (u.isVideo(v.getTitle()) == 0 && v.favoriteVideo((ArrayList<User>) users) != 0) {
               filteredVideos.add(v);
            }
        }

        filteredVideos.sort((o1, o2) -> {
            int favoriteCompare = o1.favoriteVideo((ArrayList<User>) users)
                                  .compareTo(o2.favoriteVideo((ArrayList<User>) users));
            int positionCompare = o1.positionVideo((ArrayList<Video>) videos)
                                  .compareTo(o2.positionVideo((ArrayList<Video>) videos));
            return (favoriteCompare == 0) ? positionCompare : favoriteCompare;
        });
        return filteredVideos;
    }

    /**
     * sortedGenres
     * -> designed to return a list of genres sorted by the number of views.
     * -> allGenres is a hashmap that contains every genre with its total number of views.
     * -> sortedGenres is an array that contains all genre from the hashmap and sorts them.
     * -> the function returns sortedGenres(arrayList<String>).
     */
    public ArrayList<String> sortedGenres() {
        HashMap<String, Integer> allGenres = new HashMap<>();
        ArrayList<String> sortedGenres = new ArrayList<>();

        for (Genre genre: Genre.values()) {
            int counter = 0;
            for (Video v: this.videos) {
                for (String videoGenre: v.getGenres()) {
                    if (Objects.equals(videoGenre.toLowerCase(), genre.toString().toLowerCase())) {
                        counter = counter + v.viewsVideo((ArrayList<User>) users);
                    }
                }
            }
            allGenres.put(genre.toString(), counter);
        }
        // create a list from elements of HashMap
        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<>(allGenres.entrySet());

        // sort the list
        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        for (Map.Entry<String, Integer> entry: list) {
            sortedGenres.add(entry.getKey());
        }
        return sortedGenres;
    }

    /**
     * bestPopular
     * -> designed to return the most viewed video from the most viewed genre(not seen by the user)
     */
    public Video bestPopular(final User u) {
        ArrayList<String> sortedGenres = sortedGenres();

        for (String genre: sortedGenres) {
            for (Video v: this.videos) {
                for (String videoGenre: v.getGenres()) {
                    if (Objects.equals(videoGenre.toLowerCase(), genre.toLowerCase())
                                       && u.isVideo(v.getTitle()) == 0) {
                        return v;
                    }
                }
            }
        }
        return null;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public List<Serial> getSerials() {
        return serials;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public List<Video> getVideos() {
        return videos;
    }
}
