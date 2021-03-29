package cz.cvut.kotyna.onlineedu.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "students_avatar", schema = "public", catalog = "onlineedu")
public class StudentsAvatar {
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "time_from", nullable = false)
    private LocalDateTime timeFrom;
    @Basic
    @Column(name = "time_to", nullable = false)
    private LocalDateTime timeTo;
    @Basic
    @Column(name = "active", nullable = false)
    private boolean active;
    @ManyToOne
    @JoinColumn(name = "student", referencedColumnName = "id", nullable = false)
    private Student student;
    @ManyToOne
    @JoinColumn(name = "avatar", referencedColumnName = "id", nullable = false)
    private Avatar avatar;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(LocalDateTime timeFrom) {
        this.timeFrom = timeFrom;
    }

    public LocalDateTime getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(LocalDateTime timeTo) {
        this.timeTo = timeTo;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }
}
