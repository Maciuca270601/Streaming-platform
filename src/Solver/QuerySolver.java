package Solver;

import Entities.*;
import Tasks.Query;
import java.util.ArrayList;
import java.util.Objects;

public class QuerySolver {
    private StringBuilder message = new StringBuilder();
    private int id;

    public void solveQueries(Query q, ArrayList<User> users, Database database) {
        this.id = q.getActionId();

        /*
         * todo query actions
         *  mostly messages
         */
        if (Objects.equals(q.getCriteria(), "average")) {
            message.append("Query result: [");
            if (Objects.equals(q.getObjectType(), "actors")) {
               ArrayList<Actor> sortedActors = database.sortActorsByGrade(q.getFilters());

               if (sortedActors.size() < q.getNumber()) {
                   q.setNumber(sortedActors.size());
               }
               if (Objects.equals(q.getSortType(), "asc") && sortedActors.size() != 0) {
                   for (int i = 0; i < q.getNumber() - 1; i++) {
                       message.append(sortedActors.get(i).getName());
                       message.append(", ");
                   }
                   message.append(sortedActors.get(q.getNumber() - 1).getName());
               }
               else if (Objects.equals(q.getSortType(), "desc") && sortedActors.size() != 0) {
                   for (int i = q.getNumber() - 1; i > 0; i--) {
                       message.append(sortedActors.get(i).getName());
                       message.append(", ");
                   }
                   message.append(sortedActors.get(0).getName());
               }
            }
            message.append("]");
        }

        if (Objects.equals(q.getCriteria(), "awards")) {
            message.append("Query result: [");
            if (Objects.equals(q.getObjectType(), "actors")) {
                ArrayList<Actor> sortedActors = database.sortActorsByAwards(q.getFilters());

                if (Objects.equals(q.getSortType(), "asc") && sortedActors.size() != 0) {
                    for (int i = 0; i < sortedActors.size() - 1; i++) {
                        message.append(sortedActors.get(i).getName());
                        message.append(", ");
                    }
                    message.append(sortedActors.get(sortedActors.size() - 1).getName());
                }
                else if (Objects.equals(q.getSortType(), "desc") && sortedActors.size() != 0) {
                    for (int i = sortedActors.size() - 1; i > 0; i--) {
                        message.append(sortedActors.get(i).getName());
                        message.append(", ");
                    }
                    message.append(sortedActors.get(0).getName());
                }
            }
            message.append("]");
        }

        if (Objects.equals(q.getCriteria(), "filter_description")) {
            message.append("Query result: [");
            if (Objects.equals(q.getObjectType(), "actors")) {
                ArrayList<Actor> sortedActors = database.sortActorsByWords(q.getFilters());

                if (Objects.equals(q.getSortType(), "asc") && sortedActors.size() != 0) {
                    for (int i = 0; i < sortedActors.size() - 1; i++) {
                        message.append(sortedActors.get(i).getName());
                        message.append(", ");
                    }
                    message.append(sortedActors.get(sortedActors.size() - 1).getName());
                }
                else if (Objects.equals(q.getSortType(), "desc") && sortedActors.size() != 0) {
                    for (int i = sortedActors.size() - 1; i > 0; i--) {
                        message.append(sortedActors.get(i).getName());
                        message.append(", ");
                    }
                    message.append(sortedActors.get(0).getName());
                }
            }
            message.append("]");
        }

        if (Objects.equals(q.getCriteria(), "num_ratings")) {
            message.append("Query result: [");
            if (Objects.equals(q.getObjectType(), "users")) {
                ArrayList<User> sortedUsers = database.sortUsersByRating();

                if (sortedUsers.size() < q.getNumber()) {
                    q.setNumber(sortedUsers.size());
                }
                if (Objects.equals(q.getSortType(), "asc") && sortedUsers.size() != 0) {
                    for (int i = 0; i < q.getNumber() - 1; i++) {
                        message.append(sortedUsers.get(i).getUsername());
                        message.append(", ");
                    }
                    message.append(sortedUsers.get(q.getNumber() - 1).getUsername());
                }
                else if (Objects.equals(q.getSortType(), "desc") && sortedUsers.size() != 0) {
                    for (int i = q.getNumber() - 1; i > 0; i--) {
                        message.append(sortedUsers.get(i).getUsername());
                        message.append(", ");
                    }
                    message.append(sortedUsers.get(0).getUsername());
                }
            }
            message.append("]");
        }

        if (Objects.equals(q.getCriteria(), "ratings")) {
            message.append("Query result: [");
            if (Objects.equals(q.getObjectType(), "movies")) {
                ArrayList<Movie> sortedMovies = database.sortMoviesByRating(q.getFilters());

                if (sortedMovies.size() < q.getNumber()) {
                    q.setNumber(sortedMovies.size());
                }
                if (Objects.equals(q.getSortType(), "asc") && sortedMovies.size() != 0) {
                    for (int i = 0; i < q.getNumber() - 1; i++) {
                        message.append(sortedMovies.get(i).getTitle());
                        message.append(", ");
                    }
                    message.append(sortedMovies.get(q.getNumber() - 1).getTitle());
                }
                else if (Objects.equals(q.getSortType(), "desc") && sortedMovies.size() != 0) {
                    for (int i = q.getNumber() - 1; i > 0; i--) {
                        message.append(sortedMovies.get(i).getTitle());
                        message.append(", ");
                    }
                    message.append(sortedMovies.get(0).getTitle());
                }
            }
            if (Objects.equals(q.getObjectType(), "shows")) {
                ArrayList<Serial> sortedSerials = database.sortSerialsByRating(q.getFilters());

                if (sortedSerials.size() < q.getNumber()) {
                    q.setNumber(sortedSerials.size());
                }
                if (Objects.equals(q.getSortType(), "asc") && sortedSerials.size() != 0) {
                    for (int i = 0; i < q.getNumber() - 1; i++) {
                        message.append(sortedSerials.get(i).getTitle());
                        message.append(", ");
                    }
                    message.append(sortedSerials.get(q.getNumber() - 1).getTitle());
                }
                else if (Objects.equals(q.getSortType(), "desc") && sortedSerials.size() != 0) {
                    for (int i = q.getNumber() - 1; i > 0; i--) {
                        message.append(sortedSerials.get(i).getTitle());
                        message.append(", ");
                    }
                    message.append(sortedSerials.get(0).getTitle());
                }
            }
            message.append("]");
        }

        if (Objects.equals(q.getCriteria(), "favorite")) {
            message.append("Query result: [");
            if (Objects.equals(q.getObjectType(), "movies")) {
                ArrayList<Movie> sortedMovies = database.sortMoviesByFavorite(q.getFilters());

                if (sortedMovies.size() < q.getNumber()) {
                    q.setNumber(sortedMovies.size());
                }
                if (Objects.equals(q.getSortType(), "asc") && sortedMovies.size() != 0) {
                    for (int i = 0; i < q.getNumber() - 1; i++) {
                        message.append(sortedMovies.get(i).getTitle());
                        message.append(", ");
                    }
                    message.append(sortedMovies.get(q.getNumber() - 1).getTitle());
                }
                else if (Objects.equals(q.getSortType(), "desc") && sortedMovies.size() != 0) {
                    for (int i = q.getNumber() - 1; i > 0; i--) {
                        message.append(sortedMovies.get(i).getTitle());
                        message.append(", ");
                    }
                    message.append(sortedMovies.get(0).getTitle());
                }
            }
            if (Objects.equals(q.getObjectType(), "shows")) {
                ArrayList<Serial> sortedSerials = database.sortSerialsByFavorite(q.getFilters());

                if (sortedSerials.size() < q.getNumber()) {
                    q.setNumber(sortedSerials.size());
                }
                if (Objects.equals(q.getSortType(), "asc") && sortedSerials.size() != 0) {
                    for (int i = 0; i < q.getNumber() - 1; i++) {
                        message.append(sortedSerials.get(i).getTitle());
                        message.append(", ");
                    }
                    message.append(sortedSerials.get(q.getNumber() - 1).getTitle());
                }
                else if (Objects.equals(q.getSortType(), "desc") && sortedSerials.size() != 0) {
                    for (int i = q.getNumber() - 1; i > 0; i--) {
                        message.append(sortedSerials.get(i).getTitle());
                        message.append(", ");
                    }
                    message.append(sortedSerials.get(0).getTitle());
                }
            }
            message.append("]");
        }

        if (Objects.equals(q.getCriteria(), "longest")) {
            message.append("Query result: [");
            if (Objects.equals(q.getObjectType(), "movies")) {
                ArrayList<Movie> sortedMovies = database.sortMoviesByLongest(q.getFilters());

                if (sortedMovies.size() < q.getNumber()) {
                    q.setNumber(sortedMovies.size());
                }
                if (Objects.equals(q.getSortType(), "asc") && sortedMovies.size() != 0) {
                    for (int i = 0; i < q.getNumber() - 1; i++) {
                        message.append(sortedMovies.get(i).getTitle());
                        message.append(", ");
                    }
                    message.append(sortedMovies.get(q.getNumber() - 1).getTitle());
                }
                else if (Objects.equals(q.getSortType(), "desc") && sortedMovies.size() != 0) {
                    for (int i = q.getNumber() - 1; i > 0; i--) {
                        message.append(sortedMovies.get(i).getTitle());
                        message.append(", ");
                    }
                    message.append(sortedMovies.get(0).getTitle());
                }
            }
            if (Objects.equals(q.getObjectType(), "shows")) {
                ArrayList<Serial> sortedSerials = database.sortSerialsByLongest(q.getFilters());

                if (sortedSerials.size() < q.getNumber()) {
                    q.setNumber(sortedSerials.size());
                }
                if (Objects.equals(q.getSortType(), "asc") && sortedSerials.size() != 0) {
                    for (int i = 0; i < q.getNumber() - 1; i++) {
                        message.append(sortedSerials.get(i).getTitle());
                        message.append(", ");
                    }
                    message.append(sortedSerials.get(q.getNumber() - 1).getTitle());
                }
                else if (Objects.equals(q.getSortType(), "desc") && sortedSerials.size() != 0) {
                    for (int i = q.getNumber() - 1; i > 0; i--) {
                        message.append(sortedSerials.get(i).getTitle());
                        message.append(", ");
                    }
                    message.append(sortedSerials.get(0).getTitle());
                }
            }
            message.append("]");
        }

    }

    public String getMessage() {
        return message.toString();
    }

    public int getId() {
        return id;
    }
}
