package cz.cvut.kotyna.onlineedu.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "students_avatar", schema = "public", catalog = "onlineedu")
public class StudentsAvatar {
    private int id;
    private LocalDateTime timeFrom;
    private LocalDateTime timeTo;
    private Student student;
    private Avatar avatar;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "time_from", nullable = false)
    public LocalDateTime getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(LocalDateTime timeFrom) {
        this.timeFrom = timeFrom;
    }

    @Basic
    @Column(name = "time_to", nullable = false)
    public LocalDateTime getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(LocalDateTime timeTo) {
        this.timeTo = timeTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentsAvatar that = (StudentsAvatar) o;
        return id == that.id &&
                Objects.equals(timeFrom, that.timeFrom) &&
                Objects.equals(timeTo, that.timeTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timeFrom, timeTo);
    }

    @ManyToOne
    @JoinColumn(name = "student", referencedColumnName = "id", nullable = false)
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @ManyToOne
    @JoinColumn(name = "avatar", referencedColumnName = "id", nullable = false)
    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }
}
