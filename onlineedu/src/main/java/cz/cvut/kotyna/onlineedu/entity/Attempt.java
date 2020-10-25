/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kotyna.onlineedu.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.DefaultValue;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author daniel
 */
@Entity
@Table(name = "attempt")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Attempt.findAll", query = "SELECT a FROM Attempt a"),
    @NamedQuery(name = "Attempt.findById", query = "SELECT a FROM Attempt a WHERE a.id = :id"),
    @NamedQuery(name = "Attempt.findByGrade", query = "SELECT a FROM Attempt a WHERE a.grade = :grade"),
    @NamedQuery(name = "Attempt.findByScore", query = "SELECT a FROM Attempt a WHERE a.score = :score"),
    @NamedQuery(name = "Attempt.findByFeedback", query = "SELECT a FROM Attempt a WHERE a.feedback = :feedback")})
public class Attempt implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 64)
    @Column(name = "grade")
    private String grade;
    @Size(max = 64)
    @Column(name = "score")
    private String score;
    @Size(max = 1024)
    @Column(name = "feedback")
    private String feedback;
    @Size(max = 4096)
    @Column(name = "text")
    private String text;
    @Basic(optional = false)
    @NotNull
    @Column(name = "time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    @JoinColumn(name = "student", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Student student;
    @JoinColumn(name = "task", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Task task;

    public Attempt() {
    }

    public Attempt(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getTime() {
        return time;
    }

    public String getTimeFormated() {
        SimpleDateFormat nf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        return nf.format(time);
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Attempt)) {
            return false;
        }
        Attempt other = (Attempt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cz.cvut.kotyna.onlineedu.entity.Attempt[ id=" + id + " ]";
    }
    
}
