package solver;

import entities.Database;
import entities.Movie;
import entities.Season;
import entities.Serial;
import entities.User;
import tasks.Command;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public final class CommandSolver {
    private String message;
    private int id;

    public CommandSolver() {

    }
    /**
     * solveCommands
     * -> designed to solve each and every type of command received from input.
     */
    public void solveCommands(final Command c, final ArrayList<User> users,
                              final Database database) {
        this.id = c.getActionId();
        if (Objects.equals(c.getType(), "favorite")) {
            User user = database.findUser(c.getUsername());
            assert user != null;
            // check if the specified user has already marked as favorite the video
            for (String existingMovie : user.getFavoriteMovies()) {
                if (Objects.equals(existingMovie, c.getTitle())) {
                    this.message = "error -> " + c.getTitle() + " is already in favourite list";
                    return;
                }
            }
            // if the video has not been found in the favorite list,  then add it
            for (Map.Entry<String, Integer> entry : user.getHistory().entrySet()) {
                if (Objects.equals(entry.getKey(), c.getTitle())) {
                    user.getFavoriteMovies().add(c.getTitle());
                    this.message = "success -> " + c.getTitle() + " was added as favourite";
                    return;
                }
            }
            this.message = "error -> " + c.getTitle() + " is not seen";
        }

        if (Objects.equals(c.getType(), "view")) {
            User user = database.findUser(c.getUsername());
            assert user != null;
            // check if the specified user has already seen the video
            for (Map.Entry<String, Integer> entry : user.getHistory().entrySet()) {
                if (Objects.equals(entry.getKey(), c.getTitle())) {
                    // the video has already been seen so viewCount it is incremented
                    int viewCount = entry.getValue() + 1;
                    entry.setValue(viewCount);
                    this.message = "success -> " + c.getTitle() + " was viewed with total";
                    this.message = this.message + " views of " + entry.getValue();
                    return;
                }
            }
            // the video has not been seen yet so it is added to the hashmap
            user.getHistory().put(c.getTitle(), 1);
            this.message = "success -> " + c.getTitle() + " was viewed with total views of 1";
        }

        if (Objects.equals(c.getType(), "rating")) {
            int ok = 1, viewed = 0, eligible = 1;
            /*
             * ok = 1 => the video is a movie
             * ok = 0 => the video is a serial
             * viewed = 0 => the video has not been viewed yet
             * viewed = 1 => the video has already been viewed
             * eligible = 0 => the video has already been rated
             * eligible = 1 => the video has not been rated yet
             */
            if (c.getSeasonNumber() == 0) {
                ok = 0;
            }

            // the video is a movie
            if (ok == 0) {
                Movie findMovie = database.findMovie(c.getTitle());
                User findUser = database.findUser(c.getUsername());
                // mark video as viewed if it has been already viewed
                assert findUser != null;
                for (Map.Entry<String, Integer> entry : findUser.getHistory().entrySet()) {
                    if (Objects.equals(entry.getKey(), c.getTitle())) {
                        viewed = 1;
                        break;
                    }
                }
                // if viewed by a specified user, check if it was already rated by him
                if (viewed == 1) {
                    assert findMovie != null;
                    for (String s: findMovie.getEligibleRaters()) {
                        if (Objects.equals(s, findUser.getUsername())) {
                            eligible = 0;
                            break;
                        }
                    }
                }
                if (viewed == 0) {
                    this.message = "error -> " + c.getTitle() + " is not seen";
                }
                if (viewed == 1 && eligible == 0) {
                    this.message = "error -> " + c.getTitle() + " has been already rated";
                }
                if (viewed == 1 && eligible == 1) {
                    findMovie.getRatings().add(c.getGrade());
                    findMovie.getEligibleRaters().add(c.getUsername());
                    this.message = "success -> " + c.getTitle() + " was rated with ";
                    this.message = this.message + c.getGrade() + " by " + c.getUsername();
                }
            } else {
                // the video is a serial
                User findUser = database.findUser(c.getUsername());
                Season findSeason = database.findSeason(c.getTitle(), c.getSeasonNumber());
                Serial findSerial = database.findSerial(c.getTitle());
                // mark serial as viewed if it has already been viewed
                assert findUser != null;
                for (Map.Entry<String, Integer> entry : findUser.getHistory().entrySet()) {
                    if (Objects.equals(entry.getKey(), c.getTitle())) {
                        viewed = 1;
                        break;
                    }
                }
                // if viewed by a specified user, check if it was already rated by him
                if (viewed == 1) {
                    assert findSeason != null;
                    for (String s: findSeason.getEligibleRaters()) {
                        if (Objects.equals(s, findUser.getUsername())) {
                            eligible = 0;
                            break;
                        }
                    }
                }
                if (viewed == 0) {
                    this.message = "error -> " + c.getTitle() + " is not seen";
                }
                if (viewed == 1 && eligible == 0) {
                    this.message = "error -> " + c.getTitle() + " has been already rated";
                }

                if (viewed == 1 && eligible == 1) {
                    findSeason.getRatings().add(c.getGrade());
                    findSeason.getEligibleRaters().add(c.getUsername());
                    this.message = "success -> " + c.getTitle() + " was rated with ";
                    this.message = this.message + c.getGrade() + " by " + c.getUsername();
                }
            }
        }
    }

    public String getMessage() {
        return message;
    }

    public int getId() {
        return id;
    }
}
