package solver;

import entities.Database;
import entities.User;
import tasks.Tasks;
import fileio.Writer;
import tasks.Command;
import tasks.Query;
import tasks.Recommendation;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
@SuppressWarnings("unchecked")

public final class Solver {
    private String field;
    private Writer fileWriter;
    private JSONArray arrayResult;
    private int id;
    private String message;

    public Solver() {

    }

    public Solver(final Writer fileWriter, final JSONArray arrayResult) {
        this.fileWriter = fileWriter;
        this.arrayResult = arrayResult;
    }
    /**
     * solve
     * -> designed to solve all the tasks received from input
     */
    public void solve(final ArrayList<User> users, final Tasks tasks,
                      final Database database) throws IOException {

        CommandSolver cmdSolve = new CommandSolver();
        QuerySolver qSolve = new QuerySolver();
        RecommendationSolver rSolve = new RecommendationSolver();

        int numberOfTasks = tasks.getCommands().size() + tasks.getRecommendations().size();
        numberOfTasks = numberOfTasks + tasks.getQueries().size();
        for (int i = 1; i <= numberOfTasks; i++) {
            int found = 0;
            for (Command c: tasks.getCommands()) {
                if (c.getActionId() == i) {
                    cmdSolve.solveCommands(c, users, database);
                    JSONObject object = fileWriter.writeFile(cmdSolve.getId(),
                                        field, cmdSolve.getMessage());
                    arrayResult.add(object);
                    found = 1;
                    break;
                }
            }
            if (found == 0) {
                for (Query q: tasks.getQueries()) {
                    if (q.getActionId() == i) {
                        qSolve.solveQueries(q, database);
                        JSONObject object = fileWriter.writeFile(qSolve.getId(),
                                            field, qSolve.getMessage().toString());
                        arrayResult.add(object);
                        found = 1;
                        break;
                    }
                }
            }
            if (found == 0) {
                for (Recommendation r: tasks.getRecommendations()) {
                    if (r.getActionId() == i) {
                        rSolve.solveRecommendations(r, users, database);
                        JSONObject object = fileWriter.writeFile(rSolve.getId(),
                                            field, rSolve.getMessage().toString());
                        arrayResult.add(object);
                        found = 1;
                        break;
                    }
                }
            }
        }
    }
}
