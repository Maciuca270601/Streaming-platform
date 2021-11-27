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
    private String genre;

    public Recommandation() {}

    public Recommandation(ActionInputData action) {
        this.actionId = action.getActionId();
        this.actionType = action.getActionType();
        this.type = action.getType();
        this.grade = action.getGrade();
        this.username = action.getUsername();
        this.title = action.getTitle();
        this.seasonNumber = action.getSeasonNumber();
        this.genre = action.getGenre();
    }

    public int getActionId() { return actionId; }

    public String getActionType() { return actionType; }

    public String getType() { return type; }

    public Double getGrade() { return grade; }

    public String getUsername() { return username; }

    public String getTitle() { return title; }

    public int getSeasonNumber() { return seasonNumber; }

    public String getGenre() { return genre; }
}
