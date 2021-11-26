package Tasks;

import fileio.ActionInputData;

import java.util.List;

public class Query {
    private int actionId;
    private String actionType;
    private String objectType;
    private String sortType;
    private String criteria;
    private int number;
    private List<List<String>> filters;

    public Query() {}

    public Query(ActionInputData action) {
        this.actionId = action.getActionId();
        this.actionType = action.getActionType();
        this.objectType = action.getObjectType();
        this.sortType = action.getSortType();
        this.criteria = action.getCriteria();
        this.number = action.getNumber();
        this.filters = action.getFilters();
    }

    public int getActionId() { return actionId; }

    public String getActionType() { return actionType; }

    public String getObjectType() { return objectType; }

    public String getSortType() { return sortType; }

    public String getCriteria() { return criteria; }

    public int getNumber() { return number; }

    public void setNumber(int number) { this.number = number; }

    public List<List<String>> getFilters() { return filters; }
}
