package Solver;

import Entities.Database;
import Entities.User;
import Tasks.Tasks;
import fileio.Writer;
import Tasks.Command;
import Tasks.Query;
import Tasks.Recommandation;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.ArrayList;

public class Solver {
    private String field;
    private Writer fileWriter;
    private org.json.simple.JSONArray arrayResult;
    private int id;
    private String message;

    public Solver() {}

    public Solver(Writer fileWriter, org.json.simple.JSONArray arrayResult) {
        this.fileWriter = fileWriter;
        this.arrayResult = arrayResult;
    }

    public void solve(ArrayList<User> users, Tasks tasks, Database database) throws IOException {
        /*
         * Solve Commands
         */
        //System.out.println("Numarul de actori din basa de date este: " + Database.getDatabase().getActors().size());

        CommandSolver cmdSolve = new CommandSolver();
        for (Command c: tasks.getCommands()) {
            cmdSolve.solveCommands(c, users, database);
            id = cmdSolve.getId();
            message = cmdSolve.getMessage();
            makeOutput();
        }
        /*
         * Solve Query
         */
        QuerySolver qSolve = new QuerySolver();
        for (Query q: tasks.getQueries()) {
            qSolve.solveQueries(q, users, database);
            id = qSolve.getId();
            message = qSolve.getMessage();
            makeOutput();
        }
        /*
         * Solve Recommandation
         */
        RecommandationSolver rSolve = new RecommandationSolver();
        for (Recommandation r: tasks.getRecommandations()) {
            rSolve.solveRecommandations(r, users, database);
            id = rSolve.getId();
            message = rSolve.getMessage();
            makeOutput();
        }

    }

    public void makeOutput() throws IOException {
        org.json.simple.JSONObject obj = fileWriter.writeFile(this.id, this.field, this.message);
        this.arrayResult.add(obj);
    }
}
