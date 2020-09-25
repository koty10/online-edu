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
@Table(name = "parent")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = Parent.FIND_ALL, query = "SELECT p FROM Parent p"),
    @NamedQuery(name = "Parent.findById", query = "SELECT p FROM Parent p WHERE p.id = :id"),
    @NamedQuery(name = "Parent.findByEmail", query = "SELECT p FROM Parent p WHERE p.email = :email"),
    @NamedQuery(name = "Parent.findByFirstname", query = "SELECT p FROM Parent p WHERE p.firstname = :firstname"),
    @NamedQuery(name = "Parent.findBySurname", query = "SELECT p FROM Parent p WHERE p.surname = :surname"),
    @NamedQuery(name = "Parent.findByAge", query = "SELECT p FROM Parent p WHERE p.age = :age"),
    @NamedQuery(name = "Parent.findByRegistered", query = "SELECT p FROM Parent p WHERE p.registered = :registered"),
    @NamedQuery(name = "Parent.findByStreet", query = "SELECT p FROM Parent p WHERE p.street = :street"),
    @NamedQuery(name = "Parent.findByZip", query = "SELECT p FROM Parent p WHERE p.zip = :zip"),
    @NamedQuery(name = "Parent.findByPhone", query = "SELECT p FROM Parent p WHERE p.phone = :phone")})
public class Parent implements Serializable {

    public static final String FIND_ALL = "Parent.findAll";

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
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "phone")
    private String phone;
    @OneToMany(mappedBy = "parent")
    private Collection<UserAccount> userAccountCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent")
    private Collection<Family> familyCollection;

    public Parent() {
    }

    public Parent(Integer id) {
        this.id = id;
    }

    public Parent(Integer id, String email, String firstname, String surname, String phone) {
        this.id = id;
        this.email = email;
        this.firstname = firstname;
        this.surname = surname;
        this.phone = phone;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
        if (!(object instanceof Parent)) {
            return false;
        }
        Parent other = (Parent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cz.cvut.kotyna.onlineedu.entity.Parent[ id=" + id + " ]";
    }
    
}
