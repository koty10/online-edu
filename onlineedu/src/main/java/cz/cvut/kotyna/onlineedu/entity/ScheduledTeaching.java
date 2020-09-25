/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kotyna.onlineedu.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author daniel
 */
@Entity
@Table(name = "scheduled_teaching")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScheduledTeaching.findAll", query = "SELECT s FROM ScheduledTeaching s"),
    @NamedQuery(name = "ScheduledTeaching.findById", query = "SELECT s FROM ScheduledTeaching s WHERE s.id = :id"),
    @NamedQuery(name = "ScheduledTeaching.findByFrom", query = "SELECT s FROM ScheduledTeaching s WHERE s.from = :from"),
    @NamedQuery(name = "ScheduledTeaching.findByTo", query = "SELECT s FROM ScheduledTeaching s WHERE s.to = :to")})
public class ScheduledTeaching implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "from")
    @Temporal(TemporalType.TIMESTAMP)
    private Date from;
    @Basic(optional = false)
    @NotNull
    @Column(name = "to")
    @Temporal(TemporalType.TIMESTAMP)
    private Date to;
    @JoinColumn(name = "teaching", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Teaching teaching;

    public ScheduledTeaching() {
    }

    public ScheduledTeaching(Integer id) {
        this.id = id;
    }

    public ScheduledTeaching(Integer id, Date from, Date to) {
        this.id = id;
        this.from = from;
        this.to = to;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
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
        if (!(object instanceof ScheduledTeaching)) {
            return false;
        }
        ScheduledTeaching other = (ScheduledTeaching) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cz.cvut.kotyna.onlineedu.entity.ScheduledTeaching[ id=" + id + " ]";
    }
    
}
