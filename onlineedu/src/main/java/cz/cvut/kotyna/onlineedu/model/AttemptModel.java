package cz.cvut.kotyna.onlineedu.model;

import javax.persistence.*;

@Entity
@Table(name = "attempt", schema = "public", catalog = "onlineedu")
public class AttemptModel {
    private int id;
    private int studentId;
    private int taskId;
    private String grade;
    private String score;
    private String feedback;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "student_id", nullable = false)
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Basic
    @Column(name = "task_id", nullable = false)
    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    @Basic
    @Column(name = "grade", nullable = true, length = 64)
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Basic
    @Column(name = "score", nullable = true, length = 64)
    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Basic
    @Column(name = "feedback", nullable = true, length = 1024)
    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AttemptModel that = (AttemptModel) o;

        if (id != that.id) return false;
        if (studentId != that.studentId) return false;
        if (taskId != that.taskId) return false;
        if (grade != null ? !grade.equals(that.grade) : that.grade != null) return false;
        if (score != null ? !score.equals(that.score) : that.score != null) return false;
        if (feedback != null ? !feedback.equals(that.feedback) : that.feedback != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + studentId;
        result = 31 * result + taskId;
        result = 31 * result + (grade != null ? grade.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + (feedback != null ? feedback.hashCode() : 0);
        return result;
    }
}
