package cz.cvut.kotyna.onlineedu.jsf;

import cz.cvut.kotyna.onlineedu.entity.*;
import cz.cvut.kotyna.onlineedu.service.*;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewParameter;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewMetadata;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.beans.Visibility;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Named(value = "teachingBean")
@ViewScoped
public class TeachingBean implements Serializable {

    @EJB
    private UserService userService;

    @EJB
    private TaskService taskService;

    @EJB
    private ClassroomService classroomService;

    @EJB
    private LoginService loginService;

    @EJB
    private TeachingService teachingService;

    @Inject
    private UserBackingBean userBackingBean;

    private Integer teachingId;
    private Teaching teaching;

    /**
     * Creates a new instance of TeachingBean
     */
    public TeachingBean() {
    }

    public void init() {

        // If teacher enter no teachingId, then redirect him to his default teaching
        if (teachingId == null && userBackingBean.getLoggedInUser().getRole().equals("teacher")) {
            if (userBackingBean.getClassroomId() != null) {
                teachingId = getDefaultTeacherTeaching(userBackingBean.getClassroomId()).getId();
            }
            else {
                String message = "Bad request. Please use a link from within the system.";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
                return;
            }
        }

        // If teacher tries to enter a teaching he does not teach, then redirect him to his default teaching
        if (userBackingBean.getLoggedInUser().getRole().equals("teacher")) {
            if (!userBackingBean.getLoggedInUser().getTeacher().getTeachingCollection().stream().map(Teaching::getId).collect(Collectors.toList()).contains(teachingId)) {
                if (userBackingBean.getClassroomId() != null) {
                    teachingId = getDefaultTeacherTeaching(userBackingBean.getClassroomId()).getId();
                }
                else {
                    String message = "Bad request. Please use a link from within the system.";
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
                    return;
                }
            }
        }

        // If student tries to enter a teaching he does not attend, then redirect him to his default teaching
        if (userBackingBean.getLoggedInUser().getRole().equals("student")) {
            if (!userBackingBean.getLoggedInUser().getStudent().getClassroom().getTeachingCollection().stream().map(Teaching::getId).collect(Collectors.toList()).contains(teachingId)) {
                teachingId = getDefaultStudentTeaching().getId();
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

    public Integer getNumberOfStudentsInState(String state, Integer taskId) {
        Collection<Student> students = teaching.getClassroom().getStudentCollection();
        return (int) students.stream()
                .map(student -> taskService.getRawStudentsTaskState(student.getUserAccount().getId(), taskId))
                .filter(taskState -> taskState.equals(state))
                .count();
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

    public Integer getNumberOfStudentsThatHaveSomeTaskInSubmittedOrResubmittedStateForRhsTeaching(Integer teachingId) {
        Teaching teaching = teachingService.findTeaching(teachingId);
        Collection<Student> students = teaching.getClassroom().getStudentCollection();
        Collection<Task> tasks = teaching.getTaskCollection();
        int count = 0;

        for (Task task : tasks) {
            count += (int) students.stream()
                    .map(student -> taskService.getRawStudentsTaskState(student.getUserAccount().getId(), task.getId()))
                    .filter(taskState -> taskState.equals("submitted") || taskState.equals("resubmitted"))
                    .count();
        }
        return  count;
    }

    public Integer getNumberOfStudentsThatHaveSomeTaskInSubmittedOrResubmittedStateForRhsClassroom(Integer classroomId) {
        int count = 0;
        for (Teaching teaching : classroomService.findClassroom(classroomId).getTeachingCollection()) {
            count += getNumberOfStudentsThatHaveSomeTaskInSubmittedOrResubmittedStateForRhsTeaching(teaching.getId());
        }
        return  count;
    }

    public Teaching getDefaultStudentTeaching() {
        Student loggedInStudent = userService.getStudentByUsername(loginService.getLoggedInUser().getUsername());
        Collection<Teaching> teachings = userService.getTeachings(loggedInStudent);
        return teachings.stream().findFirst().get();
    }

    public Teaching getDefaultTeacherTeaching(Integer classroomId) {
        Teacher loggedInTeacher = loginService.getLoggedInUser().getTeacher();
        return loggedInTeacher.getTeachingCollection().stream().filter(t -> t.getClassroom().getId().equals(classroomId)).findFirst().get();
    }

    public boolean isCurrentTeaching(String teachingId) {
        if (this.teachingId == null) return false;
        return this.teachingId.toString().equals(teachingId);
    }
}
