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
        this.message = new StringBuilder();
        /*
         * todo recommandations
         *  doamne coaie cat am putut sa muncesc la tema asta nici mie nu imi vine inca sa cred
         */
        if (Objects.equals(r.getType(), "standard")) {
            message.append("StandardRecommendation");
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
                message.setLength(0);
                message.append("StandardRecommendation cannot be applied!");
            }
        }

        if (Objects.equals(r.getType(), "best_unseen")) {
            message.append("BestRatedUnseenRecommendation ");
            ArrayList<Video> sortedVideos = database.sortVideosByViews(r.getUsername());
            if (sortedVideos.size() != 0) {
                message.append("result: ");
                message.append(sortedVideos.get(0).getTitle());
            }
            else {
                message.setLength(0);
                message.append("BestRatedUnseenRecommendation cannot be applied!");
            }
        }

        if (Objects.equals(r.getType(), "search")) {
            message.append("SearchRecommendation ");
            ArrayList<Video> sortedVideos = database.sortVideosBySearch(r.getUsername(), r.getGenre());
            User u = database.findUser(r.getUsername());
            int ok = 0;
            if (Objects.equals(u.getSubscriptionType(), "PREMIUM")) {
                ok = 1;
            }
            if (sortedVideos.size() != 0 && ok == 1) {
                message.append("result: [");
                for (int i = 0; i < sortedVideos.size() - 1; i++) {
                    message.append(sortedVideos.get(i).getTitle());
                    message.append(", ");
                }
                message.append(sortedVideos.get(sortedVideos.size() - 1).getTitle());
                message.append("]");
            }
            else {
                message.setLength(0);
                message.append("SearchRecommendation cannot be applied!");
            }
        }

        if (Objects.equals(r.getType(), "favorite")) {
            message.append("FavoriteRecommendation ");
            User user = database.findUser(r.getUsername());
            ArrayList<Video> sortedVideos = database.bestFavorite(user);
            int ok = 0;
            if (Objects.equals(user.getSubscriptionType(), "PREMIUM")) {
                ok = 1;
            }
            if (sortedVideos.size() != 0 && ok == 1) {
                message.append("result: ");
                message.append(sortedVideos.get(0).getTitle());
            }
            else {
                message.setLength(0);
                message.append("SearchRecommendation cannot be applied!");
            }
        }


    }

    public String getMessage() { return message.toString(); }

    public int getId() { return id; }
}
