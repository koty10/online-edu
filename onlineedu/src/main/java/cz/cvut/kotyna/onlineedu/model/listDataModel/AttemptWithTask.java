package cz.cvut.kotyna.onlineedu.model.listDataModel;

import cz.cvut.kotyna.onlineedu.entity.Student;

public class AttemptWithTask {
    Student student;
    String taskState;
    String rawTaskState;

    public AttemptWithTask(Student student, String taskState, String rawTaskState) {
        this.student = student;
        this.taskState = taskState;
        this.rawTaskState = rawTaskState;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getTaskState() {
        return taskState;
    }

    public void setTaskState(String taskState) {
        this.taskState = taskState;
    }

    public String getRawTaskState() {
        return rawTaskState;
    }

    public void setRawTaskState(String rawTaskState) {
        this.rawTaskState = rawTaskState;
    }
}
