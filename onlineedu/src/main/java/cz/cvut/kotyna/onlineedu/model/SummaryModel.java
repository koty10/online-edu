package cz.cvut.kotyna.onlineedu.model;

import javax.persistence.*;

@Entity
@Table(name = "summary", schema = "public", catalog = "onlineedu")
public class SummaryModel {
    private int id;
    private int teachingId;
    private int studentId;
    private String finalGrade;
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
    @Column(name = "teaching_id", nullable = false)
    public int getTeachingId() {
        return teachingId;
    }

    public void setTeachingId(int teachingId) {
        this.teachingId = teachingId;
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
    @Column(name = "final_grade", nullable = true, length = 64)
    public String getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(String finalGrade) {
        this.finalGrade = finalGrade;
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

        SummaryModel that = (SummaryModel) o;

        if (id != that.id) return false;
        if (teachingId != that.teachingId) return false;
        if (studentId != that.studentId) return false;
        if (finalGrade != null ? !finalGrade.equals(that.finalGrade) : that.finalGrade != null) return false;
        if (feedback != null ? !feedback.equals(that.feedback) : that.feedback != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + teachingId;
        result = 31 * result + studentId;
        result = 31 * result + (finalGrade != null ? finalGrade.hashCode() : 0);
        result = 31 * result + (feedback != null ? feedback.hashCode() : 0);
        return result;
    }
}
