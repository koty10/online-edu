/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kotyna.onlineedu.entity;

import cz.cvut.kotyna.onlineedu.enums.TaskState;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author daniel
 */
@Entity
@Table(name = "task")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Task.findAll", query = "SELECT t FROM Task t"),
    @NamedQuery(name = "Task.findById", query = "SELECT t FROM Task t WHERE t.id = :id"),
    @NamedQuery(name = "Task.findByText", query = "SELECT t FROM Task t WHERE t.text = :text"),
    @NamedQuery(name = "Task.findByTimeFrom", query = "SELECT t FROM Task t WHERE t.timeFrom = :timeFrom"),
    @NamedQuery(name = "Task.findByTimeTo", query = "SELECT t FROM Task t WHERE t.timeTo = :timeTo"),
    @NamedQuery(name = "Task.findByDate", query = "SELECT t FROM Task t WHERE t.date = :date"),
    @NamedQuery(name = "Task.findByType", query = "SELECT t FROM Task t WHERE t.type = :type")})
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 2147483647)
    @Column(name = "text")
    private String text;
    @Size(max = 64)
    @Column(name = "name")
    private String name;
    @Column(name = "time_from")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeFrom;
    @Column(name = "time_to")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeTo;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Size(max = 64)
    @Column(name = "type")
    private String type;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "task")
    private Collection<Attempt> attemptCollection;
    @JoinColumn(name = "teaching", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Teaching teaching;

    public Task() {
    }

    public Task(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTimeFrom() {
        return timeFrom;
    }

    public String getTimeFromFormated() {
        SimpleDateFormat nf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        if (timeFrom != null) {
            return nf.format(timeFrom);
        }
        else {
            return "";
        }
    }

    public void setTimeFrom(Date timeFrom) {
        this.timeFrom = timeFrom;
    }

    public Date getTimeTo() {
        return timeTo;
    }

    public String getTimeToFormated() {
        SimpleDateFormat nf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        if (timeTo != null) {
            return nf.format(timeTo);
        }
        else {
            return "";
        }
    }

    public void setTimeTo(Date timeTo) {
        this.timeTo = timeTo;
    }

    public Date getDate() {
        return date;
    }

    public String getDateFormated() {
        SimpleDateFormat nf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        return nf.format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlTransient
    public Collection<Attempt> getAttemptCollection() {
        return attemptCollection;
    }

    public void setAttemptCollection(Collection<Attempt> attemptCollection) {
        this.attemptCollection = attemptCollection;
    }

    public Teaching getTeaching() {
        return teaching;
    }

    public void setTeaching(Teaching teaching) {
        this.teaching = teaching;
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
        if (!(object instanceof Task)) {
            return false;
        }
        Task other = (Task) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cz.cvut.kotyna.onlineedu.entity.Task[ id=" + id + " ]";
    }
    
}
