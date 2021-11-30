package solver;

import entities.Actor;
import entities.Database;
import entities.Movie;
import entities.Serial;
import entities.User;
import tasks.Query;
import java.util.ArrayList;
import java.util.Objects;

public final class QuerySolver {
    private StringBuilder message = new StringBuilder();
    private int id;

    /**
     * solveQueries
     * -> designed to solve each and every type of query received from input.
     */
    public void solveQueries(final Query q, final Database database) {
        this.id = q.getActionId();
        this.message = new StringBuilder();

        // solve average query
        if (Objects.equals(q.getCriteria(), "average")) {
            message.append("Query result: [");
            if (Objects.equals(q.getObjectType(), "actors")) {
                // sort actors by their grade
                ArrayList<Actor> sortedActors = database.sortActorsByGrade(q.getFilters());
                // if there are less actors than the number specified in the query,show all of them
                if (sortedActors.size() < q.getNumber()) {
                    q.setNumber(sortedActors.size());
                }
                // show the actors in ascending order
                if (Objects.equals(q.getSortType(), "asc") && sortedActors.size() != 0) {
                    for (int i = 0; i < q.getNumber() - 1; i++) {
                        message.append(sortedActors.get(i).getName());
                        message.append(", ");
                    }
                    message.append(sortedActors.get(q.getNumber() - 1).getName());
                } else if (Objects.equals(q.getSortType(), "desc") && sortedActors.size() != 0) {
                    // show the actors in descending order
                    int counter = sortedActors.size() - q.getNumber();
                    for (int i = sortedActors.size() - 1; i > counter; i--) {
                        message.append(sortedActors.get(i).getName());
                        message.append(", ");
                    }
                    message.append(sortedActors.get(sortedActors.size() - q.getNumber()).getName());
                }
            }
            message.append("]");
        }
        // solve awards query
        if (Objects.equals(q.getCriteria(), "awards")) {
            message.append("Query result: [");
            if (Objects.equals(q.getObjectType(), "actors")) {
                // sort actors based on their total number of awards
                ArrayList<Actor> sortedActors = database.sortActorsByAwards(q.getFilters());
                // show the actors in ascending order
                if (Objects.equals(q.getSortType(), "asc") && sortedActors.size() != 0) {
                    for (int i = 0; i < sortedActors.size() - 1; i++) {
                        message.append(sortedActors.get(i).getName());
                        message.append(", ");
                    }
                    message.append(sortedActors.get(sortedActors.size() - 1).getName());
                } else if (Objects.equals(q.getSortType(), "desc") && sortedActors.size() != 0) {
                    // show the actors in descending order
                    for (int i = sortedActors.size() - 1; i > 0; i--) {
                        message.append(sortedActors.get(i).getName());
                        message.append(", ");
                    }
                    message.append(sortedActors.get(0).getName());
                }
            }
            message.append("]");
        }
        // solve the filter_description query
        if (Objects.equals(q.getCriteria(), "filter_description")) {
            message.append("Query result: [");
            if (Objects.equals(q.getObjectType(), "actors")) {
                // sort actors based on their description
                ArrayList<Actor> sortedActors = database.sortActorsByWords(q.getFilters());
                // show the actors in ascending order
                if (Objects.equals(q.getSortType(), "asc") && sortedActors.size() != 0) {
                    for (int i = 0; i < sortedActors.size() - 1; i++) {
                        message.append(sortedActors.get(i).getName());
                        message.append(", ");
                    }
                    message.append(sortedActors.get(sortedActors.size() - 1).getName());
                } else if (Objects.equals(q.getSortType(), "desc") && sortedActors.size() != 0) {
                    // show the actors in descending order
                    for (int i = sortedActors.size() - 1; i > 0; i--) {
                        message.append(sortedActors.get(i).getName());
                        message.append(", ");
                    }
                    message.append(sortedActors.get(0).getName());
                }
            }
            message.append("]");
        }
        // solve num_ratings query
        if (Objects.equals(q.getCriteria(), "num_ratings")) {
            message.append("Query result: [");
            if (Objects.equals(q.getObjectType(), "users")) {
                // sort users by rating
                ArrayList<User> sortedUsers = database.sortUsersByRating();
                // if there are less actors than the number specified in the query, show them all
                if (sortedUsers.size() < q.getNumber()) {
                    q.setNumber(sortedUsers.size());
                }
                // show actors in ascending order
                if (Objects.equals(q.getSortType(), "asc") && sortedUsers.size() != 0) {
                    for (int i = 0; i < q.getNumber() - 1; i++) {
                        message.append(sortedUsers.get(i).getUsername());
                        message.append(", ");
                    }
                    message.append(sortedUsers.get(q.getNumber() - 1).getUsername());
                } else if (Objects.equals(q.getSortType(), "desc") && sortedUsers.size() != 0) {
                    // show actors in descending order
                    int counter = sortedUsers.size() - q.getNumber();
                    for (int i = sortedUsers.size() - 1; i > counter; i--) {
                        message.append(sortedUsers.get(i).getUsername());
                        message.append(", ");
                    }
                    message.append(sortedUsers.get(counter).getUsername());
                }
            }
            message.append("]");
        }
        // solve ratings query
        if (Objects.equals(q.getCriteria(), "ratings")) {
            message.append("Query result: [");
            // check if it is movie
            if (Objects.equals(q.getObjectType(), "movies")) {
                // sort movies by their rating
                ArrayList<Movie> sortedMovies = database.sortMoviesByRating(q.getFilters());
                // show all movies if there are less than the number specified
                if (sortedMovies.size() < q.getNumber()) {
                    q.setNumber(sortedMovies.size());
                }
                // show movies in ascending order
                if (Objects.equals(q.getSortType(), "asc") && sortedMovies.size() != 0) {
                    for (int i = 0; i < q.getNumber() - 1; i++) {
                        message.append(sortedMovies.get(i).getTitle());
                        message.append(", ");
                    }
                    message.append(sortedMovies.get(q.getNumber() - 1).getTitle());
                } else if (Objects.equals(q.getSortType(), "desc") && sortedMovies.size() != 0) {
                    // show movies in descending order
                    int counter = sortedMovies.size() - q.getNumber();
                    for (int i = sortedMovies.size() - 1; i > counter; i--) {
                        message.append(sortedMovies.get(i).getTitle());
                        message.append(", ");
                    }
                    message.append(sortedMovies.get(counter).getTitle());
                }
            }
            // check if it is serial
            if (Objects.equals(q.getObjectType(), "shows")) {
                // sort serials based on their rating
                ArrayList<Serial> sortedSerials = database.sortSerialsByRating(q.getFilters());
                // show all serials if there are less than the specified number
                if (sortedSerials.size() < q.getNumber()) {
                    q.setNumber(sortedSerials.size());
                }
                // show all serials in ascending order
                if (Objects.equals(q.getSortType(), "asc") && sortedSerials.size() != 0) {
                    for (int i = 0; i < q.getNumber() - 1; i++) {
                        message.append(sortedSerials.get(i).getTitle());
                        message.append(", ");
                    }
                    message.append(sortedSerials.get(q.getNumber() - 1).getTitle());
                } else if (Objects.equals(q.getSortType(), "desc") && sortedSerials.size() != 0) {
                    // show all serials in descending order
                    int counter = sortedSerials.size() - q.getNumber();
                    for (int i = sortedSerials.size() - 1; i > counter; i--) {
                        message.append(sortedSerials.get(i).getTitle());
                        message.append(", ");
                    }
                    message.append(sortedSerials.get(counter).getTitle());
                }
            }
            message.append("]");
        }
        // solve favorite query
        if (Objects.equals(q.getCriteria(), "favorite")) {
            message.append("Query result: [");
            // check if there are movies
            if (Objects.equals(q.getObjectType(), "movies")) {
                // sort movies by the favorite number
                ArrayList<Movie> sortedMovies = database.sortMoviesByFavorite(q.getFilters());
                // show all movies if there are less than the number specified
                if (sortedMovies.size() < q.getNumber()) {
                    q.setNumber(sortedMovies.size());
                }
                // show movies in ascending order
                if (Objects.equals(q.getSortType(), "asc") && sortedMovies.size() != 0) {
                    for (int i = 0; i < q.getNumber() - 1; i++) {
                        message.append(sortedMovies.get(i).getTitle());
                        message.append(", ");
                    }
                    message.append(sortedMovies.get(q.getNumber() - 1).getTitle());
                } else if (Objects.equals(q.getSortType(), "desc") && sortedMovies.size() != 0) {
                    // show movies in descending order
                    int counter = sortedMovies.size() - q.getNumber();
                    for (int i = sortedMovies.size() - 1; i > counter; i--) {
                        message.append(sortedMovies.get(i).getTitle());
                        message.append(", ");
                    }
                    message.append(sortedMovies.get(counter).getTitle());
                }
            }
            // check if it is serial
            if (Objects.equals(q.getObjectType(), "shows")) {
                // sort serials by the number of favorites
                ArrayList<Serial> sortedSerials = database.sortSerialsByFavorite(q.getFilters());
                // show all of them if there are less than the number specified
                if (sortedSerials.size() < q.getNumber()) {
                    q.setNumber(sortedSerials.size());
                }
                // show serials in ascending order
                if (Objects.equals(q.getSortType(), "asc") && sortedSerials.size() != 0) {
                    for (int i = 0; i < q.getNumber() - 1; i++) {
                        message.append(sortedSerials.get(i).getTitle());
                        message.append(", ");
                    }
                    message.append(sortedSerials.get(q.getNumber() - 1).getTitle());
                } else if (Objects.equals(q.getSortType(), "desc") && sortedSerials.size() != 0) {
                    // show serials in descending order
                    int counter = sortedSerials.size() - q.getNumber();
                    for (int i = sortedSerials.size() - 1; i > counter; i--) {
                        message.append(sortedSerials.get(i).getTitle());
                        message.append(", ");
                    }
                    message.append(sortedSerials.get(counter).getTitle());
                }
            }
            message.append("]");
        }
        // solve longest query
        if (Objects.equals(q.getCriteria(), "longest")) {
            message.append("Query result: [");
            // check if it is movie
            if (Objects.equals(q.getObjectType(), "movies")) {
                // sort movies by their duration
                ArrayList<Movie> sortedMovies = database.sortMoviesByLongest(q.getFilters());
                // show all movies if there are less than the number specified
                if (sortedMovies.size() < q.getNumber()) {
                    q.setNumber(sortedMovies.size());
                }
                // show all movies in ascending order
                if (Objects.equals(q.getSortType(), "asc") && sortedMovies.size() != 0) {
                    for (int i = 0; i < q.getNumber() - 1; i++) {
                        message.append(sortedMovies.get(i).getTitle());
                        message.append(", ");
                    }
                    message.append(sortedMovies.get(q.getNumber() - 1).getTitle());
                } else if (Objects.equals(q.getSortType(), "desc") && sortedMovies.size() != 0) {
                    // show all movies in descending order
                    int counter = sortedMovies.size() - q.getNumber();
                    for (int i = sortedMovies.size() - 1; i > counter; i--) {
                        message.append(sortedMovies.get(i).getTitle());
                        message.append(", ");
                    }
                    message.append(sortedMovies.get(counter).getTitle());
                }
            }
            // check if it is serial
            if (Objects.equals(q.getObjectType(), "shows")) {
                // sort serials by their duration
                ArrayList<Serial> sortedSerials = database.sortSerialsByLongest(q.getFilters());
                // show all serials if there are less than the specified number
                if (sortedSerials.size() < q.getNumber()) {
                    q.setNumber(sortedSerials.size());
                }
                // show all serials in ascending order
                if (Objects.equals(q.getSortType(), "asc") && sortedSerials.size() != 0) {
                    for (int i = 0; i < q.getNumber() - 1; i++) {
                        message.append(sortedSerials.get(i).getTitle());
                        message.append(", ");
                    }
                    message.append(sortedSerials.get(q.getNumber() - 1).getTitle());
                } else if (Objects.equals(q.getSortType(), "desc") && sortedSerials.size() != 0) {
                    // show all serials in descending order
                    int counter = sortedSerials.size() - q.getNumber();
                    for (int i = sortedSerials.size() - 1; i > counter; i--) {
                        message.append(sortedSerials.get(i).getTitle());
                        message.append(", ");
                    }
                    message.append(sortedSerials.get(counter).getTitle());
                }
            }
            message.append("]");
        }
        // solve most_viewed query
        if (Objects.equals(q.getCriteria(), "most_viewed")) {
            message.append("Query result: [");
            // check if it is movie
            if (Objects.equals(q.getObjectType(), "movies")) {
                // sort movies by their total amount of views
                ArrayList<Movie> sortedMovies = database.sortMoviesByViews(q.getFilters());
                // show all movies if there are less than the specified number
                if (sortedMovies.size() < q.getNumber()) {
                    q.setNumber(sortedMovies.size());
                }
                // sort movies in ascending order
                if (Objects.equals(q.getSortType(), "asc") && sortedMovies.size() != 0) {
                    for (int i = 0; i < q.getNumber() - 1; i++) {
                        message.append(sortedMovies.get(i).getTitle());
                        message.append(", ");
                    }
                    message.append(sortedMovies.get(q.getNumber() - 1).getTitle());
                } else if (Objects.equals(q.getSortType(), "desc") && sortedMovies.size() != 0) {
                    // sort movies in descending order
                    int counter = sortedMovies.size() - q.getNumber();
                    for (int i = sortedMovies.size() - 1; i > counter; i--) {
                        message.append(sortedMovies.get(i).getTitle());
                        message.append(", ");
                    }
                    message.append(sortedMovies.get(counter).getTitle());
                }
            }
            // check if it is serial
            if (Objects.equals(q.getObjectType(), "shows")) {
                // sort serials by their total amount of views
                ArrayList<Serial> sortedSerials = database.sortSerialsByViews(q.getFilters());
                // show all serials if there are less than the number specified
                if (sortedSerials.size() < q.getNumber()) {
                    q.setNumber(sortedSerials.size());
                }
                // show all serials in ascending order
                if (Objects.equals(q.getSortType(), "asc") && sortedSerials.size() != 0) {
                    for (int i = 0; i < q.getNumber() - 1; i++) {
                        message.append(sortedSerials.get(i).getTitle());
                        message.append(", ");
                    }
                    message.append(sortedSerials.get(q.getNumber() - 1).getTitle());
                } else if (Objects.equals(q.getSortType(), "desc") && sortedSerials.size() != 0) {
                    // show all serials in descending order
                    int counter = sortedSerials.size() - q.getNumber();
                    for (int i = sortedSerials.size() - 1; i > counter; i--) {
                        message.append(sortedSerials.get(i).getTitle());
                        message.append(", ");
                    }
                    message.append(sortedSerials.get(counter).getTitle());
                }
            }
            message.append("]");
        }

    }

    public StringBuilder getMessage() {
        return message;
    }

    public int getId() {
        return id;
    }
}
