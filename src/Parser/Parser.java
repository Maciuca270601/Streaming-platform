package Parser;

import Entities.Database;
import Tasks.Tasks;
import fileio.Input;

public class Parser {
    private Tasks tasks;

    public Parser() {}

    public void BuildDatabase(Input input, Database database) {
        database.addUsers(input.getUsers());
        database.addActors(input.getActors());
        database.addMovies(input.getMovies());
        database.addSerials(input.getSerials());
        database.addVideos();
    }

    public void BuildTasks(Input input) {
        this.tasks = new Tasks(input.getCommands());
    }

    public Tasks getTasks() {
        return tasks;
    }
}
