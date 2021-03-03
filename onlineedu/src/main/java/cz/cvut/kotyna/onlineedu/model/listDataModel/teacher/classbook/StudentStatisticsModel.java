package cz.cvut.kotyna.onlineedu.model.listDataModel.teacher.classbook;

public class StudentStatisticsModel {
    String studentFirstname;
    String studentSurname;
    Integer numberOfTasksInNewState;
    Integer numberOfTasksInSubmittedState;
    Integer numberOfTasksInReturnedState;
    Integer numberOfTasksInResubmittedState;
    Integer numberOfTasksInAcceptedState;
    Integer numberOfTasksInExcusedState;
    Integer numberOfTasksInFailedState;
    Integer points;

    // Constructor

    public StudentStatisticsModel(String studentFirstname, String studentSurname, Integer numberOfTasksInNewState, Integer numberOfTasksInSubmittedState, Integer numberOfTasksInReturnedState, Integer numberOfTasksInResubmittedState, Integer numberOfTasksInAcceptedState, Integer numberOfTasksInExcusedState, Integer numberOfTasksInFailedState, Integer points) {
        this.studentFirstname = studentFirstname;
        this.studentSurname = studentSurname;
        this.numberOfTasksInNewState = numberOfTasksInNewState;
        this.numberOfTasksInSubmittedState = numberOfTasksInSubmittedState;
        this.numberOfTasksInReturnedState = numberOfTasksInReturnedState;
        this.numberOfTasksInResubmittedState = numberOfTasksInResubmittedState;
        this.numberOfTasksInAcceptedState = numberOfTasksInAcceptedState;
        this.numberOfTasksInExcusedState = numberOfTasksInExcusedState;
        this.numberOfTasksInFailedState = numberOfTasksInFailedState;
        this.points = points;
    }

    public StudentStatisticsModel() {

    }

    // Getters & Setters

    public String getStudentFirstname() {
        return studentFirstname;
    }

    public void setStudentFirstname(String studentFirstname) {
        this.studentFirstname = studentFirstname;
    }

    public String getStudentSurname() {
        return studentSurname;
    }

    public void setStudentSurname(String studentSurname) {
        this.studentSurname = studentSurname;
    }

    public Integer getNumberOfTasksInNewState() {
        return numberOfTasksInNewState;
    }

    public void setNumberOfTasksInNewState(Integer numberOfTasksInNewState) {
        this.numberOfTasksInNewState = numberOfTasksInNewState;
    }

    public Integer getNumberOfTasksInSubmittedState() {
        return numberOfTasksInSubmittedState;
    }

    public void setNumberOfTasksInSubmittedState(Integer numberOfTasksInSubmittedState) {
        this.numberOfTasksInSubmittedState = numberOfTasksInSubmittedState;
    }

    public Integer getNumberOfTasksInReturnedState() {
        return numberOfTasksInReturnedState;
    }

    public void setNumberOfTasksInReturnedState(Integer numberOfTasksInReturnedState) {
        this.numberOfTasksInReturnedState = numberOfTasksInReturnedState;
    }

    public Integer getNumberOfTasksInResubmittedState() {
        return numberOfTasksInResubmittedState;
    }

    public void setNumberOfTasksInResubmittedState(Integer numberOfTasksInResubmittedState) {
        this.numberOfTasksInResubmittedState = numberOfTasksInResubmittedState;
    }

    public Integer getNumberOfTasksInAcceptedState() {
        return numberOfTasksInAcceptedState;
    }

    public void setNumberOfTasksInAcceptedState(Integer numberOfTasksInAcceptedState) {
        this.numberOfTasksInAcceptedState = numberOfTasksInAcceptedState;
    }

    public Integer getNumberOfTasksInExcusedState() {
        return numberOfTasksInExcusedState;
    }

    public void setNumberOfTasksInExcusedState(Integer numberOfTasksInExcusedState) {
        this.numberOfTasksInExcusedState = numberOfTasksInExcusedState;
    }

    public Integer getNumberOfTasksInFailedState() {
        return numberOfTasksInFailedState;
    }

    public void setNumberOfTasksInFailedState(Integer numberOfTasksInFailedState) {
        this.numberOfTasksInFailedState = numberOfTasksInFailedState;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
