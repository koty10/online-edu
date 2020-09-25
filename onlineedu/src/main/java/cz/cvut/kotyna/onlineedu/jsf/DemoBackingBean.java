package cz.cvut.kotyna.onlineedu.jsf;

import cz.cvut.kotyna.onlineedu.entity.Parent;
import cz.cvut.kotyna.onlineedu.entity.Student;
import cz.cvut.kotyna.onlineedu.entity.Teacher;
import cz.cvut.kotyna.onlineedu.entity.UserAccount;
import cz.cvut.kotyna.onlineedu.service.LoginService;
import cz.cvut.kotyna.onlineedu.service.UserService;

import javax.ejb.AfterCompletion;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Named(value = "demoBackingBean")
@RequestScoped
public class DemoBackingBean {

    @EJB
    private UserService userService;

    @EJB
    private LoginService loginService;

    /**
     * Creates a new instance of DemoBackingBean
     */
    public DemoBackingBean() {
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
        return loginService.getLoggedInStudent();
    }

    public List<Student> getClassmates() {
        try {
            return userService.getClassmates(loginService.getLoggedInStudent().getClassroom().getId());
        } catch (NullPointerException e) {
            return new ArrayList<>();
        }
    }
}
