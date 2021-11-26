package Tasks;

import fileio.ActionInputData;

import java.util.List;

public class Command {
    private int actionId;
    private String actionType;
    private String type;
    private String username;
    private String objectType;
    private String sortType;
    private String criteria;
    private String title;
    private String genre;
    private int number;
    private double grade;
    private int seasonNumber;
    private List<List<String>> filters;

    public Command() {}

    public Command(ActionInputData action) {
        this.actionId = action.getActionId();
        this.actionType = action.getActionType();
        this.type = action.getType();
        this.username = action.getUsername();
        this.objectType = action.getObjectType();
        this.sortType = action.getSortType();
        this.criteria = action.getCriteria();
        this.title = action.getTitle();
        this.genre = action.getGenre();
        this.number = action.getNumber();
        this.grade = action.getGrade();
        this.seasonNumber = action.getSeasonNumber();
        this.filters = action.getFilters();
    }

    public int getActionId() { return actionId; }

    public String getActionType() { return actionType; }

    public String getType() { return type; }

    public String getUsername() { return username; }

    public String getGenre() { return genre; }

    public String getObjectType() { return objectType; }

    public String getSortType() { return sortType; }

    public String getCriteria() { return criteria; }

    public String getTitle() { return title; }

    public int getNumber() { return number; }

    public double getGrade() { return grade; }

    public int getSeasonNumber() { return seasonNumber; }

    public List<List<String>> getFilters() { return filters; }

}
