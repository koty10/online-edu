/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kotyna.onlineedu.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author daniel
 */
@Entity
@Table(name = "teaching")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Teaching.findAll", query = "SELECT t FROM Teaching t"),
    @NamedQuery(name = "Teaching.findById", query = "SELECT t FROM Teaching t WHERE t.id = :id")})
public class Teaching implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teaching")
    private Collection<Summary> summaryCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teaching")
    private Collection<ScheduledTeaching> scheduledTeachingCollection;
    @JoinColumn(name = "classroom", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Classroom classroom;
    @JoinColumn(name = "subject", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Subject subject;
    @JoinColumn(name = "teacher", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Teacher teacher;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teaching")
    private Collection<Task> taskCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teaching")
    private Collection<Material> materialCollection;
    @OneToMany(mappedBy = "teaching")
    private Collection<Chat> chatCollection;

    public Teaching() {
    }

    public Teaching(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @XmlTransient
    public Collection<Summary> getSummaryCollection() {
        return summaryCollection;
    }

    public void setSummaryCollection(Collection<Summary> summaryCollection) {
        this.summaryCollection = summaryCollection;
    }

    @XmlTransient
    public Collection<ScheduledTeaching> getScheduledTeachingCollection() {
        return scheduledTeachingCollection;
    }

    public void setScheduledTeachingCollection(Collection<ScheduledTeaching> scheduledTeachingCollection) {
        this.scheduledTeachingCollection = scheduledTeachingCollection;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @XmlTransient
    public Collection<Task> getTaskCollection() {
        return taskCollection;
    }

    public void setTaskCollection(Collection<Task> taskCollection) {
        this.taskCollection = taskCollection;
    }

    @XmlTransient
    public Collection<Material> getMaterialCollection() {
        return materialCollection;
    }

    public void setMaterialCollection(Collection<Material> materialCollection) {
        this.materialCollection = materialCollection;
    }

    @XmlTransient
    public Collection<Chat> getChatCollection() {
        return chatCollection;
    }

    public void setChatCollection(Collection<Chat> chatCollection) {
        this.chatCollection = chatCollection;
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
        if (!(object instanceof Teaching)) {
            return false;
        }
        Teaching other = (Teaching) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cz.cvut.kotyna.onlineedu.entity.Teaching[ id=" + id + " ]";
    }
    
}
