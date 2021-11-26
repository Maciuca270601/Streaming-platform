package Solver;

import Entities.Actor;
import Entities.Database;
import Entities.User;
import Tasks.Query;
import java.util.ArrayList;
import java.util.Objects;

public class QuerySolver {
    private StringBuilder message = new StringBuilder();
    private int id;

    public void solveQueries(Query q, ArrayList<User> users, Database database) {
        this.id = q.getActionId();

        /*
         * todo query actions
         *  mostly messages
         */
        if (Objects.equals(q.getCriteria(), "average")) {
            message.append("Query result: [");
            if (Objects.equals(q.getObjectType(), "actors")) {
               ArrayList<Actor> sortedActors = database.sortActorsByGrade(q.getFilters());

               if (sortedActors.size() < q.getNumber()) {
                   q.setNumber(sortedActors.size());
               }
               if (Objects.equals(q.getSortType(), "asc") && sortedActors.size() != 0) {
                   for (int i = 0; i < q.getNumber() - 1; i++) {
                       message.append(sortedActors.get(i).getName());
                       message.append(", ");
                   }
                   message.append(sortedActors.get(q.getNumber() - 1).getName());
                   message.append("]");
               }
               else if (Objects.equals(q.getSortType(), "desc") && sortedActors.size() != 0) {
                   for (int i = q.getNumber() - 1; i > 0; i--) {
                       message.append(sortedActors.get(i).getName());
                       message.append(", ");
                   }
                   message.append(sortedActors.get(0).getName());
                   message.append("]");
               }
            }
        }
    }

    public String getMessage() {
        return message.toString();
    }

    public int getId() {
        return id;
    }
}
