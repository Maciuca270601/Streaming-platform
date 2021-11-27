package Solver;

import Entities.Database;
import Entities.User;
import Tasks.Tasks;
import fileio.Writer;
import Tasks.Command;
import Tasks.Query;
import Tasks.Recommandation;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
@SuppressWarnings("unchecked")

public class Solver {
    private String field;
    public Writer fileWriter;
    public JSONArray arrayResult;
    private int id;
    private String message;

    public Solver() {}

    public Solver(Writer fileWriter, JSONArray arrayResult) {
        this.fileWriter = fileWriter;
        this.arrayResult = arrayResult;
    }

    public void solve(ArrayList<User> users, Tasks tasks, Database database) throws IOException {

        CommandSolver cmdSolve = new CommandSolver();
        QuerySolver qSolve = new QuerySolver();
        RecommandationSolver rSolve = new RecommandationSolver();

        int numberOfTasks = tasks.getCommands().size() + tasks.getRecommandations().size() + tasks.getQueries().size();
        for (int i = 1; i <= numberOfTasks; i++) {
            int found = 0;
            for (Command c: tasks.getCommands()) {
                if (c.getActionId() == i) {
                    cmdSolve.solveCommands(c, users, database);
                    JSONObject object = fileWriter.writeFile(cmdSolve.getId(), field, cmdSolve.getMessage());
                    arrayResult.add(object);
                    found = 1;
                    break;
                }
            }
            if (found == 0) {
                for (Query q: tasks.getQueries()) {
                    if (q.getActionId() == i) {
                        qSolve.solveQueries(q, users, database);
                        JSONObject object = fileWriter.writeFile(qSolve.getId(), field, qSolve.getMessage().toString());
                        arrayResult.add(object);
                        found = 1;
                        break;
                    }
                }
            }
            if (found == 0) {
                for (Recommandation r: tasks.getRecommandations()) {
                    if (r.getActionId() == i) {
                        rSolve.solveRecommandations(r, users, database);
                        JSONObject object = fileWriter.writeFile(rSolve.getId(), field, rSolve.getMessage().toString());
                        arrayResult.add(object);
                        found = 1;
                        break;
                    }
                }
            }
        }
    }
}
