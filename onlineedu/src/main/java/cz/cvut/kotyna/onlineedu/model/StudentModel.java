package cz.cvut.kotyna.onlineedu.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "student", schema = "public", catalog = "onlineedu")
public class StudentModel {
    private int id;
    private String email;
    private String firstname;
    private String surname;
    private Integer age;
    private Timestamp registered;
    private String street;
    private String zip;
    private String chatAlert;
    private int classroomId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 255)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "firstname", nullable = false, length = 255)
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "surname", nullable = false, length = 255)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "age", nullable = true)
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Basic
    @Column(name = "registered", nullable = true)
    public Timestamp getRegistered() {
        return registered;
    }

    public void setRegistered(Timestamp registered) {
        this.registered = registered;
    }

    @Basic
    @Column(name = "street", nullable = true, length = -1)
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Basic
    @Column(name = "zip", nullable = true, length = -1)
    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Basic
    @Column(name = "chat_alert", nullable = true, length = -1)
    public String getChatAlert() {
        return chatAlert;
    }

    public void setChatAlert(String chatAlert) {
        this.chatAlert = chatAlert;
    }

    @Basic
    @Column(name = "classroom_id", nullable = false)
    public int getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(int classroomId) {
        this.classroomId = classroomId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentModel that = (StudentModel) o;

        if (id != that.id) return false;
        if (classroomId != that.classroomId) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (age != null ? !age.equals(that.age) : that.age != null) return false;
        if (registered != null ? !registered.equals(that.registered) : that.registered != null) return false;
        if (street != null ? !street.equals(that.street) : that.street != null) return false;
        if (zip != null ? !zip.equals(that.zip) : that.zip != null) return false;
        if (chatAlert != null ? !chatAlert.equals(that.chatAlert) : that.chatAlert != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (registered != null ? registered.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (zip != null ? zip.hashCode() : 0);
        result = 31 * result + (chatAlert != null ? chatAlert.hashCode() : 0);
        result = 31 * result + classroomId;
        return result;
    }
}
