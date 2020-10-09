package cz.cvut.kotyna.onlineedu.jsf;

import cz.cvut.kotyna.onlineedu.entity.*;
import cz.cvut.kotyna.onlineedu.service.LoginService;
import cz.cvut.kotyna.onlineedu.service.UserService;

import javax.annotation.PostConstruct;
import javax.ejb.AfterCompletion;
import javax.ejb.EJB;
import javax.ejb.EJBs;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

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

    //@PostConstruct
    private void init() {
        Student loggedInStudent = loginService.getLoggedInStudent();
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String teachingId = params.get("teaching");

        if (!loggedInStudent.getClassroom().getTeachingCollection().stream().map(x -> x.getId().toString()).collect(Collectors.toList()).contains(teachingId)) {
            try {
                HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                String uri = request.getRequestURI().toString();
                if (!uri.equals("/onlineedu/student/wrong.xhtml"))
                {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/onlineedu/student/wrong");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
