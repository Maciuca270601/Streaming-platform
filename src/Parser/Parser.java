package Parser;

import Entities.Database;
import Tasks.Tasks;
import fileio.Input;

public class Parser {
    private Tasks tasks;

    public Parser() {}

    public void BuildDatabase(Input input) {
        Database.getDatabase().addUsers(input.getUsers());
        Database.getDatabase().addActors(input.getActors());
        Database.getDatabase().addMovies(input.getMovies());
        Database.getDatabase().addSerials(input.getSerials());
        Database.getDatabase().addVideos();
    }

    public void BuildTasks(Input input) {
        this.tasks = new Tasks(input.getCommands());
    }

    public Tasks getTasks() {
        return tasks;
    }
}
