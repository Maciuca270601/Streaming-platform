package solver;

import entities.Database;
import entities.User;
import entities.Video;
import tasks.Recommendation;
import java.util.ArrayList;
import java.util.Objects;

public final class RecommendationSolver {
    private StringBuilder message = new StringBuilder();
    private int id;

    public RecommendationSolver() {

    }
    /**
     * solveRecommendations
     * -> designed to solve each and every type of recommendation received from input.
     */
    public void solveRecommendations(final Recommendation r, final ArrayList<User> users,
                                     final Database database) {
        this.id = r.getActionId();
        this.message = new StringBuilder();
        // solve recommendation standard
        if (Objects.equals(r.getType(), "standard")) {
            message.append("StandardRecommendation");
            int ok = 0;
            for (User u : users) {
                if (Objects.equals(r.getUsername(), u.getUsername())) {
                    message.append(" result: ");
                    // get first video that has not been seen by the specified user
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
        // solve recommendation best_unseen
        if (Objects.equals(r.getType(), "best_unseen")) {
            message.append("BestRatedUnseenRecommendation ");
            // sort all videos based on their total amount of views
            ArrayList<Video> sortedVideos = database.sortVideosByViews(r.getUsername());
            if (sortedVideos.size() != 0) {
                message.append("result: ");
                message.append(sortedVideos.get(0).getTitle());
            } else {
                message.setLength(0);
                message.append("BestRatedUnseenRecommendation cannot be applied!");
            }
        }
        // solve recommendation search
        if (Objects.equals(r.getType(), "search")) {
            message.append("SearchRecommendation ");
            // sort videos based on their genre
            String name = r.getUsername();
            ArrayList<Video> sortedVideos = database.sortVideosBySearch(name, r.getGenre());
            User u = database.findUser(r.getUsername());
            int ok = 0;
            assert u != null;
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
            } else {
                message.setLength(0);
                message.append("SearchRecommendation cannot be applied!");
            }
        }
        // solve favorite recommendation
        if (Objects.equals(r.getType(), "favorite")) {
            message.append("FavoriteRecommendation ");
            User user = database.findUser(r.getUsername());
            // sort videos based on their number of favorite nominations
            ArrayList<Video> sortedVideos = database.bestFavorite(user);

            int ok = 0;
            assert user != null;
            if (Objects.equals(user.getSubscriptionType(), "PREMIUM")) {
                ok = 1;
            }
//
            if (sortedVideos.size() != 0 && ok == 1) {
                message.append("result: ");
                int counter = sortedVideos.size() - 1;
                for (Video v: sortedVideos) {
                    if (Objects.equals(v.favoriteVideo(users), sortedVideos.get(counter)
                                      .favoriteVideo(users))) {
                        message.append(v.getTitle());
                        break;
                    }
                }
            } else {
                message.setLength(0);
                message.append("FavoriteRecommendation cannot be applied!");
            }
        }
        // solve recommendation popular
        if (Objects.equals(r.getType(), "popular")) {
            message.append("PopularRecommendation ");
            User user = database.findUser(r.getUsername());
            // sort videos based on their popularity
            Video popularVideo = database.bestPopular(user);
            int ok = 0;
            assert user != null;
            if (Objects.equals(user.getSubscriptionType(), "PREMIUM")) {
                ok = 1;
            }

            if (popularVideo != null && ok == 1) {
                message.append("result: ");
                message.append(popularVideo.getTitle());
            } else {
                message.setLength(0);
                message.append("PopularRecommendation cannot be applied!");
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
