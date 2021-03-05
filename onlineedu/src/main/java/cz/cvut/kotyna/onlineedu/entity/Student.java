/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kotyna.onlineedu.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author daniel
 */
@Entity
@Table(name = "student")
@NamedQueries({
        @NamedQuery(name = Student.FIND_ALL, query = "SELECT s FROM Student s"),
        @NamedQuery(name = "Student.findById", query = "SELECT s FROM Student s WHERE s.id = :id"),
        @NamedQuery(name = "Student.findByChatAlert", query = "SELECT s FROM Student s WHERE s.chatAlert = :chatAlert"),
        @NamedQuery(name = Student.FIND_LOGGED_IN_STUDENT, query = "select student from Student student join UserAccount user on student.id = user.student.id where user.username = :username"),
        @NamedQuery(name = Student.FIND_CLASSMATES, query = "select student from Student student where student.classroom.id = :classroomId")})
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Student implements Serializable {

    public static final String FIND_ALL = "Student.findAll";
    public static final String FIND_LOGGED_IN_STUDENT = "Student.findLoggedInStudent";
    public static final String FIND_CLASSMATES = "Student.findClassmates";

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 2147483647)
    @Column(name = "chat_alert")
    private String chatAlert;
    @NotNull
    @Column(name = "points")
    private Integer points;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private Collection<Summary> summaryCollection;
    @JoinColumn(name = "classroom", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Classroom classroom;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private Collection<Attempt> attemptCollection;
    @OneToMany(mappedBy = "student")
    private Collection<Chat> chatCollection;
    @JoinColumn(name = "user_account", referencedColumnName = "id")
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    private UserAccount userAccount;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private Collection<Family> familyCollection;

    public Student() {
    }

    public Student(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChatAlert() {
        return chatAlert;
    }

    public void setChatAlert(String chatAlert) {
        this.chatAlert = chatAlert;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    @XmlTransient
    public Collection<Summary> getSummaryCollection() {
        return summaryCollection;
    }

    public void setSummaryCollection(Collection<Summary> summaryCollection) {
        this.summaryCollection = summaryCollection;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    @XmlTransient
    public Collection<Attempt> getAttemptCollection() {
        return attemptCollection;
    }

    public void setAttemptCollection(Collection<Attempt> attemptCollection) {
        this.attemptCollection = attemptCollection;
    }

    @XmlTransient
    public Collection<Chat> getChatCollection() {
        return chatCollection;
    }

    public void setChatCollection(Collection<Chat> chatCollection) {
        this.chatCollection = chatCollection;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    @XmlTransient
    public Collection<Family> getFamilyCollection() {
        return familyCollection;
    }

    public void setFamilyCollection(Collection<Family> familyCollection) {
        this.familyCollection = familyCollection;
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
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cz.cvut.kotyna.onlineedu.entity.Student[ id=" + id + " ]";
    }

}
