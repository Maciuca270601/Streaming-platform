package Tasks;


import fileio.ActionInputData;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Tasks {
    private ArrayList<ActionInputData> actions;
    private ArrayList<Command> commands = new ArrayList<Command>();
    private ArrayList<Query> queries = new ArrayList<Query>();
    private ArrayList<Recommandation> recommandations = new ArrayList<Recommandation>();

    public Tasks() {}

    public Tasks(List<ActionInputData> actions) {
        for (ActionInputData actionInput: actions) {
            if (Objects.equals(actionInput.getActionType(), "command")) {
                Command c = new Command(actionInput);
                assert false;
                this.commands.add(c);
            }
            if (Objects.equals(actionInput.getActionType(), "query")) {
                Query q = new Query(actionInput);
                assert false;
                this.queries.add(q);
            }
            if (Objects.equals(actionInput.getActionType(), "recommendation")) {
                Recommandation r = new Recommandation(actionInput);
                assert false;
                this.recommandations.add(r);
            }
        }
    }

    public ArrayList<Command> getCommands() {
        return commands;
    }

    public ArrayList<Query> getQueries() {
        return queries;
    }

    public ArrayList<Recommandation> getRecommandations() {
        return recommandations;
    }
}
