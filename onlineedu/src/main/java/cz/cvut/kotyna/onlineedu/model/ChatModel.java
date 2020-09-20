package cz.cvut.kotyna.onlineedu.model;

import javax.persistence.*;

@Entity
@Table(name = "chat", schema = "public", catalog = "onlineedu")
public class ChatModel {
    private int id;
    private Integer studentId;
    private Integer teachingId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "student_id", nullable = true)
    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    @Basic
    @Column(name = "teaching_id", nullable = true)
    public Integer getTeachingId() {
        return teachingId;
    }

    public void setTeachingId(Integer teachingId) {
        this.teachingId = teachingId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChatModel chatModel = (ChatModel) o;

        if (id != chatModel.id) return false;
        if (studentId != null ? !studentId.equals(chatModel.studentId) : chatModel.studentId != null) return false;
        if (teachingId != null ? !teachingId.equals(chatModel.teachingId) : chatModel.teachingId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (studentId != null ? studentId.hashCode() : 0);
        result = 31 * result + (teachingId != null ? teachingId.hashCode() : 0);
        return result;
    }
}
