package parser;

import entities.Database;
import tasks.Tasks;
import fileio.Input;

public final class Parser {
    private Tasks tasks;

    public Parser() {

    }

    /**
     * BuildDatabase
     * -> designed to load the database with the specified input.
     */
    public void buildDatabase(final Input input, final Database database) {
        database.addUsers(input.getUsers());
        database.addActors(input.getActors());
        database.addMovies(input.getMovies());
        database.addSerials(input.getSerials());
        database.addVideos();
    }

    /**
     * BuildTasks
     * -> designed to load the tasks with the specified input.
     */
    public void buildTasks(final Input input) {
        this.tasks = new Tasks(input.getCommands());
    }

    public Tasks getTasks() {
        return tasks;
    }
}
