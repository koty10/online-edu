package cz.cvut.kotyna.onlineedu.jsf;

import cz.cvut.kotyna.onlineedu.entity.Student;
import cz.cvut.kotyna.onlineedu.entity.Task;
import cz.cvut.kotyna.onlineedu.entity.Teaching;
import cz.cvut.kotyna.onlineedu.service.LoginService;
import cz.cvut.kotyna.onlineedu.service.TeachingService;
import cz.cvut.kotyna.onlineedu.service.UserService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewParameter;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewMetadata;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.beans.Visibility;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Named(value = "teachingBean")
@RequestScoped
public class TeachingBean {

    @EJB
    private UserService userService;

    @EJB
    private LoginService loginService;

    @EJB
    private TeachingService teachingService;

    private Integer teachingId;
    private Teaching teaching;

    /**
     * Creates a new instance of TeachingBean
     */
    public TeachingBean() {
    }

    public void init() {
        if (teachingId == null) {
            String message = "Bad request. Please use a link from within the system.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
            return;
        }

        teaching = teachingService.findTeaching(teachingId);

        if (teaching == null) {
            String message = "Bad request. Unknown teaching.";
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
        }
    }

    public Integer getTeachingId() {
        return teachingId;
    }

    public void setTeachingId(Integer teachingId) {
        this.teachingId = teachingId;
    }

    public Teaching getTeaching() {
        if (teaching == null) {
            teaching = teachingService.findTeaching(teachingId);
        }

        return teaching;
    }

    public Collection<Task> getTasks() {
        return teachingService.getTasks(teachingId);
    }

    public Teaching getDefaultStudentTeaching() {
        Student loggedInStudent = userService.getStudentByUsername(loginService.getLoggedInUser().getUsername());
        Collection<Teaching> teachings = userService.getTeachings(loggedInStudent);
        return teachings.stream().findFirst().get();
    }
}
