package cz.cvut.kotyna.onlineedu.jsf.student;

import cz.cvut.kotyna.onlineedu.entity.*;
import cz.cvut.kotyna.onlineedu.jsf.UrlHelperBean;
import cz.cvut.kotyna.onlineedu.service.*;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;

@Named(value = "studentTeachingBean")
@ViewScoped
public class StudentTeachingBean implements Serializable {

    @EJB
    private UserService userService;
    @EJB
    private LoginService loginService;
    @EJB
    private TaskService taskService;
    @EJB
    private TeachingService teachingService;
    @Inject
    private UrlHelperBean urlHelperBean;

    private Integer teachingId;
    private Teaching teaching;

    public void initTeaching() {

        // If student tries to enter a teaching he does not attend, then redirect him to his default teaching
        if (loginService.getLoggedInUser().getRole().equals("student")) {
            if (!loginService.getLoggedInUser().getStudent().getClassroom().getTeachingCollection().stream().map(Teaching::getId).collect(Collectors.toList()).contains(teachingId)) {
                Teaching t = getDefaultStudentTeaching();
                if (t != null) {
                    teachingId = getDefaultStudentTeaching().getId();
                } else {
                    try {
                        final String contextPathForCurrentUser = urlHelperBean.getContextPathForCurrentUser();
                        FacesContext.getCurrentInstance().getExternalContext().redirect(contextPathForCurrentUser + "/user");
                        return;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
                }
            }
        }

        teaching = teachingService.findTeaching(teachingId);

        // Unable to find a teaching by entered teachingId, this situation should not appear due to upper conditions
        if (teaching == null) {
            String message = "Bad request. Unknown teaching.";
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
        }
    }

    public Teaching getTeaching() {
        if (teaching == null) {
            teaching = teachingService.findTeaching(teachingId);
        }

        return teaching;
    }

    public Teaching getDefaultStudentTeaching() {
        Student loggedInStudent = userService.getStudentByUsername(loginService.getLoggedInUser().getUsername());
        Collection<Teaching> teachings = userService.getTeachings(loggedInStudent);
        return teachings.stream().findFirst().orElse(null);
    }

    public boolean isCurrentTeaching(String teachingId) {
        if (this.teachingId == null) return false;
        return this.teachingId.toString().equals(teachingId);
    }

    public Integer getNumberOfTasksInNewOrReturnedStateForRhsTeaching(Integer teachingId) {
        Teaching teaching = teachingService.findTeaching(teachingId);
        Collection<Task> tasks = teaching.getTaskCollection();
        UserAccount loggedInUser = loginService.getLoggedInUser();
        int count = 0;

        for (Task task : tasks) {
            String state = taskService.getRawStudentsTaskState(loggedInUser.getId(), task.getId());
            if (state.equals("new") || state.equals("returned")) {
                count++;
            }
        }
        return count;
    }

    public Integer getNumberOfTasksInNewOrReturnedStateForRhsType(String type) {
        UserAccount loggedInUser = loginService.getLoggedInUser();
        int count = 0;

        for (Task task : teaching.getTaskCollection().stream().filter(task -> task.getType().equals(type)).collect(Collectors.toList())) {
            String state = taskService.getRawStudentsTaskState(loggedInUser.getId(), task.getId());
            if (state.equals("new") || state.equals("returned")) {
                count++;
            }
        }
        return count;
    }

    // Getters & Setters


    public Integer getTeachingId() {
        return teachingId;
    }

    public void setTeachingId(Integer teachingId) {
        this.teachingId = teachingId;
    }

    public void setTeaching(Teaching teaching) {
        this.teaching = teaching;
    }
}