package cz.cvut.kotyna.onlineedu.jsf;

import cz.cvut.kotyna.onlineedu.entity.*;
import cz.cvut.kotyna.onlineedu.service.ClassroomService;
import cz.cvut.kotyna.onlineedu.service.LoginService;
import cz.cvut.kotyna.onlineedu.service.UserService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.ArrayDataModel;
import javax.faces.model.DataModel;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Named(value = "userBackingBean")
@ViewScoped
public class UserBackingBean implements Serializable {

    @EJB
    private UserService userService;

    @EJB
    private LoginService loginService;

    @EJB
    private ClassroomService classroomService;

    private Integer classroomId;
    private Classroom classroom;

    /**
     * Creates a new instance of UserBackingBean
     */
    public UserBackingBean() {
    }

    public void setClassroom() {
        if (classroomId == null) {
            String message = "Bad request (classroomId = null). Please use a link from within the system.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
            return;
        }

        classroom = classroomService.findClassroom(classroomId);

        if (classroom == null) {
            String message = "Bad request. Unknown classroom.";
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
        }

    }

    //@PostConstruct
    private void init() {
        Student loggedInStudent = loginService.getLoggedInUser().getStudent();
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

    public UserAccount getLoggedInUser() {
        return loginService != null ? loginService.getLoggedInUser() : null;
    }

    public List<Student> getClassmates() {
        try {
            return userService.getClassmates(loginService.getLoggedInUser().getStudent().getClassroom().getId());
        } catch (NullPointerException e) {
            return new ArrayList<>();
        }
    }

    public Collection<Teaching> getTeachings() {
        return userService.getTeachings(loginService.getLoggedInUser().getStudent());
    }

    // Used in the top nav for teacher
    public List<Classroom> getClassroomsTeachedByCurrentTeacher() {
        return loginService.getLoggedInUser().getTeacher().getTeachingCollection().stream().map(x -> x.getClassroom()).distinct().collect(Collectors.toList());
    }

    // Used in the top nav for teacher
    public List<Teaching> getTeachersTeachingsInCurrentClassroom() {
        return loginService.getLoggedInUser().getTeacher().getClassroom().getTeachingCollection().stream().filter(t -> t.getTeacher().getUserAccount().getId().equals(loginService.getLoggedInUser().getId())).filter(t -> t.getClassroom().getId().equals(classroomId)).collect(Collectors.toList());
    }

    public Classroom getDefaultTeacherClassroom() {
        return loginService.getLoggedInUser().getTeacher().getClassroom();
    }

    public Integer getClassroomId() {
        return classroomId;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroomId(Integer classroomId) {
        this.classroomId = classroomId;
    }

    //FIXME smazat - jen pro testovani
    public List<String> getPasswordsHashed() {
        return loginService.getPasswordsHashed();
    }
}
