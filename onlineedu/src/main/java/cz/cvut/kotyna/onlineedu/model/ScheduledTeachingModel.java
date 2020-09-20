package cz.cvut.kotyna.onlineedu.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "scheduled_teaching", schema = "public", catalog = "onlineedu")
public class ScheduledTeachingModel {
    private int id;
    private int teachingId;
    private Timestamp from;
    private Timestamp to;

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
    @Column(name = "from", nullable = false)
    public Timestamp getFrom() {
        return from;
    }

    public void setFrom(Timestamp from) {
        this.from = from;
    }

    @Basic
    @Column(name = "to", nullable = false)
    public Timestamp getTo() {
        return to;
    }

    public void setTo(Timestamp to) {
        this.to = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScheduledTeachingModel that = (ScheduledTeachingModel) o;

        if (id != that.id) return false;
        if (teachingId != that.teachingId) return false;
        if (from != null ? !from.equals(that.from) : that.from != null) return false;
        if (to != null ? !to.equals(that.to) : that.to != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + teachingId;
        result = 31 * result + (from != null ? from.hashCode() : 0);
        result = 31 * result + (to != null ? to.hashCode() : 0);
        return result;
    }
}
