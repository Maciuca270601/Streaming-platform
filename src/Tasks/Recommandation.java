package Tasks;


import fileio.ActionInputData;

public class Recommandation {
    private int actionId;
    private String actionType;
    private String type;
    private Double grade;
    private String username;
    private String title;
    private int seasonNumber;

    public Recommandation() {}

    public Recommandation(ActionInputData action) {
        this.actionId = action.getActionId();
        this.actionType = action.getActionType();
        this.type = action.getType();
        this.grade = action.getGrade();
        this.username = action.getUsername();
        this.title = action.getTitle();
        this.seasonNumber = action.getSeasonNumber();
    }
}
