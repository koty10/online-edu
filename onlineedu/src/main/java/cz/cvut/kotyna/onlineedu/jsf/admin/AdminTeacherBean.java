package cz.cvut.kotyna.onlineedu.jsf.admin;

import cz.cvut.kotyna.onlineedu.entity.Classroom;
import cz.cvut.kotyna.onlineedu.entity.Teacher;
import cz.cvut.kotyna.onlineedu.entity.UserAccount;
import cz.cvut.kotyna.onlineedu.service.ClassroomService;
import cz.cvut.kotyna.onlineedu.service.TeacherService;
import cz.cvut.kotyna.onlineedu.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named(value = "adminTeacherBean")
@ViewScoped
public class AdminTeacherBean implements Serializable {

    @EJB
    private TeacherService teacherService;
    @EJB
    private UserService userService;

    @Getter @Setter
    private List<Teacher> allTeachers;
    @Getter @Setter
    private Teacher teacher;
    @Getter @Setter
    private Integer teacherId;

    @PostConstruct
    public void init() {
        allTeachers = new ArrayList<>(teacherService.getAllTeachers());
        teacher = new Teacher();
        teacher.setUserAccount(new UserAccount());
    }

    public void initTeacher() {
        teacher = teacherService.findTeacher(teacherId);
    }

    public void initNewTeacher() {
        teacher = new Teacher();
        teacher.setUserAccount(new UserAccount());
    }

    public void saveTeacher() {

        teacher.getUserAccount().setRole("teacher");
        teacher.getUserAccount().setRegistered(new Date());

        // generate username and hashed password (same as username for testing purposes)
        teacher.setUserAccount(userService.generateUserAccountUsernameAndPassword(teacher.getUserAccount()));

        teacherService.saveTeacher(teacher);

        if (teacher.getId() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Učitel vytvořen"));
        }
        else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Učitel upraven"));
        }
        PrimeFaces.current().executeScript("PF('manageTeacherDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-teachers");
    }

}
