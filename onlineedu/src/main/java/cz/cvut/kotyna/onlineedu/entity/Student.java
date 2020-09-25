/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kotyna.onlineedu.entity;

import java.io.Serializable;
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
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s"),
    @NamedQuery(name = "Student.findById", query = "SELECT s FROM Student s WHERE s.id = :id"),
    @NamedQuery(name = "Student.findByEmail", query = "SELECT s FROM Student s WHERE s.email = :email"),
    @NamedQuery(name = "Student.findByFirstname", query = "SELECT s FROM Student s WHERE s.firstname = :firstname"),
    @NamedQuery(name = "Student.findBySurname", query = "SELECT s FROM Student s WHERE s.surname = :surname"),
    @NamedQuery(name = "Student.findByAge", query = "SELECT s FROM Student s WHERE s.age = :age"),
    @NamedQuery(name = "Student.findByRegistered", query = "SELECT s FROM Student s WHERE s.registered = :registered"),
    @NamedQuery(name = "Student.findByStreet", query = "SELECT s FROM Student s WHERE s.street = :street"),
    @NamedQuery(name = "Student.findByZip", query = "SELECT s FROM Student s WHERE s.zip = :zip"),
    @NamedQuery(name = "Student.findByChatAlert", query = "SELECT s FROM Student s WHERE s.chatAlert = :chatAlert"),
    @NamedQuery(name = Student.FIND_LOGGED_IN_STUDENT, query = "select student from Student student join UserAccount user on student.id = user.student.id where user.username = :username"),
    @NamedQuery(name = Student.FIND_CLASSMATES, query = "select student from Student student where student.classroom.id = :classroomId")})
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
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "firstname")
    private String firstname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "surname")
    private String surname;
    @Column(name = "age")
    private Integer age;
    @Column(name = "registered")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registered;
    @Size(max = 2147483647)
    @Column(name = "street")
    private String street;
    @Size(max = 2147483647)
    @Column(name = "zip")
    private String zip;
    @Size(max = 2147483647)
    @Column(name = "chat_alert")
    private String chatAlert;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private Collection<Summary> summaryCollection;
    @JoinColumn(name = "classroom", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Classroom classroom;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private Collection<Attempt> attemptCollection;
    @OneToMany(mappedBy = "student")
    private Collection<Chat> chatCollection;
    @OneToMany(mappedBy = "student")
    private Collection<UserAccount> userAccountCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private Collection<Family> familyCollection;

    public Student() {
    }

    public Student(Integer id) {
        this.id = id;
    }

    public Student(Integer id, String email, String firstname, String surname) {
        this.id = id;
        this.email = email;
        this.firstname = firstname;
        this.surname = surname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getChatAlert() {
        return chatAlert;
    }

    public void setChatAlert(String chatAlert) {
        this.chatAlert = chatAlert;
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

    @XmlTransient
    public Collection<UserAccount> getUserAccountCollection() {
        return userAccountCollection;
    }

    public void setUserAccountCollection(Collection<UserAccount> userAccountCollection) {
        this.userAccountCollection = userAccountCollection;
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
