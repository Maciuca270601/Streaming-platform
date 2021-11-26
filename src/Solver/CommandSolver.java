package Solver;

import Entities.*;
import Tasks.Command;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class CommandSolver {
    private String message;
    private int id;

    public CommandSolver() {}

    public void solveCommands(Command c, ArrayList<User> users, Database database) {
        this.id = c.getActionId();
        if (Objects.equals(c.getType(), "favorite")) {
            for (User u : users) {
                if (Objects.equals(c.getUsername(), u.getUsername())) {
                    for (String existingMovie : u.getFavoriteMovies()) {
                        if (Objects.equals(existingMovie, c.getTitle())) {
                            this.message = "error -> " + c.getTitle() + " is already in favourite list";
                            return;
                        }
                    }
                    for (Map.Entry<String, Integer> entry : u.getHistory().entrySet()) {
                        if (Objects.equals(entry.getKey(), c.getTitle())) {
                            u.getFavoriteMovies().add(c.getTitle());
                            this.message = "success -> " + c.getTitle() + " was added as favourite";
                            return;
                        }
                    }
                    this.message = "error -> " + c.getTitle() + " is not seen";
                }
            }
        }

        if (Objects.equals(c.getType(), "view")) {
            for (User u : users) {
                if (Objects.equals(c.getUsername(), u.getUsername())) {
                    for (Map.Entry<String, Integer> entry : u.getHistory().entrySet()) {
                        if (Objects.equals(entry.getKey(), c.getTitle())) {
                            int viewCount = entry.getValue() + 1;
                            entry.setValue(viewCount);
                            this.message = "success -> " + c.getTitle() + " was viewed with total views of " + entry.getValue();
                            return;
                        }
                    }
                    u.getHistory().put(c.getTitle(), 1);
                    this.message = "success -> " + c.getTitle() + " was viewed with total views of 1";
                }
            }
        }

        if (Objects.equals(c.getType(), "rating")) {
            int ok = 1, viewed = 0, eligible = 1;
            if (c.getSeasonNumber() == 0) {
                ok = 0;
            }

            User findUser = new User();
            if (ok == 0) {
                Movie findMovie = new Movie();
                findUser = database.findUser(c.getUsername());
                findMovie = database.findMovie(c.getTitle());

                for (Map.Entry<String, Integer> entry : findUser.getHistory().entrySet()) {
                    if (Objects.equals(entry.getKey(), c.getTitle())) {
                        viewed = 1;
                        break;
                    }
                }
                if (viewed == 1) {
                    for (String s: findMovie.getEligibleRaters()) {
                        if (Objects.equals(s, findUser.getUsername())) {
                            eligible = 0;
                            break;
                        }
                    }
                }
                if (viewed == 1 && eligible == 0) {
                    this.message = "error -> " + c.getTitle() + " has been already rated";
                }
                if (viewed == 1 && eligible == 1) {
                    findMovie.getRatings().add(c.getGrade());
                    findMovie.getEligibleRaters().add(c.getUsername());
                    this.message = "success -> " + c.getTitle() + " was rated with " + c.getGrade() + " by " + c.getUsername();
                }
            }
            else {
                Season findSeason = new Season();
                Serial findSerial = new Serial();
                findUser = database.findUser(c.getUsername());
                findSeason = database.findSeason(c.getTitle(), c.getSeasonNumber());
                findSerial = database.findSerial(c.getTitle());

                for (Map.Entry<String, Integer> entry : findUser.getHistory().entrySet()) {
                    if (Objects.equals(entry.getKey(), c.getTitle())) {
                        viewed = 1;
                        break;
                    }
                }

                if (viewed == 1) {
                    for (String s: findSeason.getEligibleRaters()) {
                        if (Objects.equals(s, findUser.getUsername())) {
                            eligible = 0;
                            break;
                        }
                    }
                }

                if (viewed == 1 && eligible == 0) {
                    this.message = "error -> " + c.getTitle() + " has been already rated";
                }

                if (viewed == 1 && eligible == 1) {
                    findSeason.getRatings().add(c.getGrade());
                    findSeason.getEligibleRaters().add(c.getUsername());
                    this.message = "success -> " + c.getTitle() + " was rated with " + c.getGrade() + " by " + c.getUsername();
                }
            }
        }
    }

    public String getMessage() { return message; }

    public int getId() { return id; }
}
