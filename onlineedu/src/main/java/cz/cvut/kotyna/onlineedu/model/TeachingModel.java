package cz.cvut.kotyna.onlineedu.model;

import javax.persistence.*;

@Entity
@Table(name = "teaching", schema = "public", catalog = "onlineedu")
public class TeachingModel {
    private int id;
    private int teacherId;
    private int classroomId;
    private int subjectId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "teacher_id", nullable = false)
    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    @Basic
    @Column(name = "classroom_id", nullable = false)
    public int getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(int classroomId) {
        this.classroomId = classroomId;
    }

    @Basic
    @Column(name = "subject_id", nullable = false)
    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeachingModel that = (TeachingModel) o;

        if (id != that.id) return false;
        if (teacherId != that.teacherId) return false;
        if (classroomId != that.classroomId) return false;
        if (subjectId != that.subjectId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + teacherId;
        result = 31 * result + classroomId;
        result = 31 * result + subjectId;
        return result;
    }
}
