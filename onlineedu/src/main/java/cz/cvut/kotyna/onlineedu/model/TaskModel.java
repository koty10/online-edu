package cz.cvut.kotyna.onlineedu.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "task", schema = "public", catalog = "onlineedu")
public class TaskModel {
    private int id;
    private int teachingId;
    private String text;
    private Timestamp from;
    private Timestamp to;
    private Timestamp date;
    private String type;

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
    @Column(name = "text", nullable = true, length = -1)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Basic
    @Column(name = "from", nullable = true)
    public Timestamp getFrom() {
        return from;
    }

    public void setFrom(Timestamp from) {
        this.from = from;
    }

    @Basic
    @Column(name = "to", nullable = true)
    public Timestamp getTo() {
        return to;
    }

    public void setTo(Timestamp to) {
        this.to = to;
    }

    @Basic
    @Column(name = "date", nullable = true)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "type", nullable = true, length = 64)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskModel taskModel = (TaskModel) o;

        if (id != taskModel.id) return false;
        if (teachingId != taskModel.teachingId) return false;
        if (text != null ? !text.equals(taskModel.text) : taskModel.text != null) return false;
        if (from != null ? !from.equals(taskModel.from) : taskModel.from != null) return false;
        if (to != null ? !to.equals(taskModel.to) : taskModel.to != null) return false;
        if (date != null ? !date.equals(taskModel.date) : taskModel.date != null) return false;
        if (type != null ? !type.equals(taskModel.type) : taskModel.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + teachingId;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (from != null ? from.hashCode() : 0);
        result = 31 * result + (to != null ? to.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
