package cz.cvut.kotyna.onlineedu.model.listDataModel.teacher.tasks;

public class TaskWithStatisticsModel {
    private Integer taskId;
    private String taskName;
    private String taskDate;
    private String taskTimeFrom;
    private String taskTimeTo;
    private Integer numberOfStudentsInNewState;
    private Integer numberOfStudentsInSubmittedState;
    private Integer numberOfStudentsInReturnedState;
    private Integer numberOfStudentsInResubmittedState;
    private Integer numberOfStudentsInAcceptedState;
    private Integer numberOfStudentsInExcusedState;
    private Integer numberOfStudentsInFailedState;
    private String type;
    private Integer points;

    // Constructor


    public TaskWithStatisticsModel(Integer taskId, String taskName, String taskDate, String taskTimeFrom, String taskTimeTo, Integer numberOfStudentsInNewState, Integer numberOfStudentsInSubmittedState, Integer numberOfStudentsInReturnedState, Integer numberOfStudentsInResubmittedState, Integer numberOfStudentsInAcceptedState, Integer numberOfStudentsInExcusedState, Integer numberOfStudentsInFailedState, String type, Integer points) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDate = taskDate;
        this.taskTimeFrom = taskTimeFrom;
        this.taskTimeTo = taskTimeTo;
        this.numberOfStudentsInNewState = numberOfStudentsInNewState;
        this.numberOfStudentsInSubmittedState = numberOfStudentsInSubmittedState;
        this.numberOfStudentsInReturnedState = numberOfStudentsInReturnedState;
        this.numberOfStudentsInResubmittedState = numberOfStudentsInResubmittedState;
        this.numberOfStudentsInAcceptedState = numberOfStudentsInAcceptedState;
        this.numberOfStudentsInExcusedState = numberOfStudentsInExcusedState;
        this.numberOfStudentsInFailedState = numberOfStudentsInFailedState;
        this.type = type;
        this.points = points;
    }

    public TaskWithStatisticsModel() {
    }

    public void setNumberOfStudentsInState(String state, Integer number) {
        switch (state) {
            case "new":
                numberOfStudentsInNewState = number; break;
            case "submitted":
                numberOfStudentsInSubmittedState = number; break;
            case "returned":
                numberOfStudentsInReturnedState = number; break;
            case "resubmitted":
                numberOfStudentsInResubmittedState = number; break;
            case "accepted":
                numberOfStudentsInAcceptedState = number; break;
            case "excused":
                numberOfStudentsInExcusedState = number; break;
            case "failed":
                numberOfStudentsInFailedState = number; break;
        }
    }

    // Getters & Setters

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }
    
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(String taskDate) {
        this.taskDate = taskDate;
    }

    public String getTaskTimeFrom() {
        return taskTimeFrom;
    }

    public void setTaskTimeFrom(String taskTimeFrom) {
        this.taskTimeFrom = taskTimeFrom;
    }

    public String getTaskTimeTo() {
        return taskTimeTo;
    }

    public void setTaskTimeTo(String taskTimeTo) {
        this.taskTimeTo = taskTimeTo;
    }

    public Integer getNumberOfStudentsInNewState() {
        return numberOfStudentsInNewState;
    }

    public void setNumberOfStudentsInNewState(Integer numberOfStudentsInNewState) {
        this.numberOfStudentsInNewState = numberOfStudentsInNewState;
    }

    public Integer getNumberOfStudentsInSubmittedState() {
        return numberOfStudentsInSubmittedState;
    }

    public void setNumberOfStudentsInSubmittedState(Integer numberOfStudentsInSubmittedState) {
        this.numberOfStudentsInSubmittedState = numberOfStudentsInSubmittedState;
    }

    public Integer getNumberOfStudentsInReturnedState() {
        return numberOfStudentsInReturnedState;
    }

    public void setNumberOfStudentsInReturnedState(Integer numberOfStudentsInReturnedState) {
        this.numberOfStudentsInReturnedState = numberOfStudentsInReturnedState;
    }

    public Integer getNumberOfStudentsInResubmittedState() {
        return numberOfStudentsInResubmittedState;
    }

    public void setNumberOfStudentsInResubmittedState(Integer numberOfStudentsInResubmittedState) {
        this.numberOfStudentsInResubmittedState = numberOfStudentsInResubmittedState;
    }

    public Integer getNumberOfStudentsInAcceptedState() {
        return numberOfStudentsInAcceptedState;
    }

    public void setNumberOfStudentsInAcceptedState(Integer numberOfStudentsInAcceptedState) {
        this.numberOfStudentsInAcceptedState = numberOfStudentsInAcceptedState;
    }

    public Integer getNumberOfStudentsInExcusedState() {
        return numberOfStudentsInExcusedState;
    }

    public void setNumberOfStudentsInExcusedState(Integer numberOfStudentsInExcusedState) {
        this.numberOfStudentsInExcusedState = numberOfStudentsInExcusedState;
    }

    public Integer getNumberOfStudentsInFailedState() {
        return numberOfStudentsInFailedState;
    }

    public void setNumberOfStudentsInFailedState(Integer numberOfStudentsInFailedState) {
        this.numberOfStudentsInFailedState = numberOfStudentsInFailedState;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}