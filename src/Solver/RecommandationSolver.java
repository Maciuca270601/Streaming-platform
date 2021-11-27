package Solver;

import Entities.Database;
import Entities.User;
import Entities.Video;
import Tasks.Recommandation;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class RecommandationSolver {
    private StringBuilder message = new StringBuilder();
    private int id;

    public RecommandationSolver() {}

    public void solveRecommandations(Recommandation r, ArrayList<User> users, Database database) {
        this.id = r.getActionId();
        /*
         * todo recommandations
         *  doamne coaie cat am putut sa muncesc la tema asta nici mie nu imi vine inca sa cred
         */
        if (Objects.equals(r.getType(), "standard")) {
            message.append(r.getType().substring(0, 1).toUpperCase()).append(r.getType().substring(1));
            message.append(r.getActionType().substring(0, 1).toUpperCase()).append(r.getActionType().substring(1));
            int ok = 0;
            for (User u : users) {
                if (Objects.equals(r.getUsername(), u.getUsername())) {
                    message.append(" result: ");
                    for (Video v: database.getVideos()) {
                        if (u.isVideo(v.getTitle()) == 0) {
                            message.append(v.getTitle());
                            ok = 1;
                            break;
                        }
                    }
                }
            }
            if (ok == 0) {
                message.append(" cannot be applied!");
            }
        }

        if (Objects.equals(r.getType(), "best_unseen")) {
            message.append("BestRatedUnseenRecommendation result: ");
            ArrayList<Video> sortedVideos = database.sortVideosByViews(r.getUsername());
            if (sortedVideos.size() != 0) {
                message.append(sortedVideos.get(0).getTitle());
            }
        }

        if (Objects.equals(r.getType(), "search")) {
            message.append("SearchRecommendation ");
            ArrayList<Video> sortedVideos = database.sortVideosBySearch(r.getUsername(), r.getGenre());
            if (sortedVideos.size() != 0) {
                message.append(" result: ");
                for (int i = 0; i < sortedVideos.size() - 1; i++) {
                    message.append(sortedVideos.get(i).getTitle());
                    message.append(", ");
                }
                message.append(sortedVideos.get(sortedVideos.size() - 1).getTitle());
                message.append("]");
            }
            else {
                message.append("cannot be applied!");
            }
        }
    }

    public String getMessage() { return message.toString(); }

    public int getId() { return id; }
}
