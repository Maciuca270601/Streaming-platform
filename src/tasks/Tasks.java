package tasks;


import fileio.ActionInputData;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Tasks {
    private ArrayList<ActionInputData> actions;
    private final ArrayList<Command> commands = new ArrayList<>();
    private final ArrayList<Query> queries = new ArrayList<>();
    private final ArrayList<Recommendation> recommendations = new ArrayList<>();

    public Tasks() {

    }

    public Tasks(final List<ActionInputData> actions) {
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
                Recommendation r = new Recommendation(actionInput);
                assert false;
                this.recommendations.add(r);
            }
        }
    }

    public ArrayList<Command> getCommands() {
        return commands;
    }

    public ArrayList<Query> getQueries() {
        return queries;
    }

    public ArrayList<Recommendation> getRecommendations() {
        return recommendations;
    }
}
