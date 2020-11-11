package cz.cvut.kotyna.onlineedu.model;

import cz.cvut.kotyna.onlineedu.entity.Student;

public class Students {
    Student student;
    String stateOfTheTask;

    public Students(Student student, String stateOfTheTask) {
        this.student = student;
        this.stateOfTheTask = stateOfTheTask;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getStateOfTheTask() {
        return stateOfTheTask;
    }

    public void setStateOfTheTask(String stateOfTheTask) {
        this.stateOfTheTask = stateOfTheTask;
    }
}
