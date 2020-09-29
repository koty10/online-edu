package cz.cvut.kotyna.onlineedu.jsf;

import cz.cvut.kotyna.onlineedu.entity.*;
import cz.cvut.kotyna.onlineedu.service.LoginService;
import cz.cvut.kotyna.onlineedu.service.UserService;

import javax.ejb.AfterCompletion;
import javax.ejb.EJB;
import javax.ejb.EJBs;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Named(value = "studentBackingBean")
@RequestScoped
public class StudentBackingBean {

    @EJB
    private UserService userService;

    @EJB
    private LoginService loginService;

    private Student loggedInStudent = getLoggedInStudent();

    /**
     * Creates a new instance of StudentBackingBean
     */
    public StudentBackingBean() {
    }

    public List<UserAccount> getAllUsers() {
        return userService.getAllUsers();
    }

    public List<Student> getAllStudents() {
        return userService.getAllStudents();
    }

    public List<Parent> getAllParents() {
        return userService.getAllParents();
    }

    public List<Teacher> getAllTeachers() {
        return userService.getAllTeachers();
    }

    public Student getLoggedInStudent() {
        return loginService != null ? loginService.getLoggedInStudent() : null;
    }

    public List<Student> getClassmates() {
        try {
            return userService.getClassmates(loginService.getLoggedInStudent().getClassroom().getId());
        } catch (NullPointerException e) {
            return new ArrayList<>();
        }
    }

    public Collection<Teaching> getTeachings() {
        return userService.getTeachings(loggedInStudent);
    }

    //FIXME smazat - jen pro testovani
    public List<String> getPasswordsHashed() {
        return loginService.getPasswordsHashed();
    }
}
