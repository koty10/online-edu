/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kotyna.onlineedu.entity;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author daniel
 */
@Entity
@Table(name = "summary")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Summary.findAll", query = "SELECT s FROM Summary s"),
    @NamedQuery(name = "Summary.findById", query = "SELECT s FROM Summary s WHERE s.id = :id"),
    @NamedQuery(name = "Summary.findByFinalGrade", query = "SELECT s FROM Summary s WHERE s.finalGrade = :finalGrade"),
    @NamedQuery(name = "Summary.findByFeedback", query = "SELECT s FROM Summary s WHERE s.feedback = :feedback")})
public class Summary implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 64)
    @Column(name = "final_grade")
    private String finalGrade;
    @Size(max = 1024)
    @Column(name = "feedback")
    private String feedback;
    @JoinColumn(name = "student", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Student student;
    @JoinColumn(name = "teaching", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Teaching teaching;

    public Summary() {
    }

    public Summary(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(String finalGrade) {
        this.finalGrade = finalGrade;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
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
        if (!(object instanceof Summary)) {
            return false;
        }
        Summary other = (Summary) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cz.cvut.kotyna.onlineedu.entity.Summary[ id=" + id + " ]";
    }
    
}
