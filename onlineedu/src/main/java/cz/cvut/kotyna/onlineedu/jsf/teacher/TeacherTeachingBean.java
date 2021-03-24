package cz.cvut.kotyna.onlineedu.jsf.teacher;

import cz.cvut.kotyna.onlineedu.entity.*;
import cz.cvut.kotyna.onlineedu.service.*;
import org.primefaces.PrimeFaces;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;

@Named(value = "teacherTeachingBean")
@ViewScoped
public class TeacherTeachingBean implements Serializable {

    @EJB
    private TaskService taskService;
    @EJB
    private ClassroomService classroomService;
    @EJB
    private LoginService loginService;
    @EJB
    private TeachingService teachingService;
    @Inject
    private TeacherUserBackingBean teacherUserBackingBean;

    private Integer teachingId;
    private Teaching teaching;

    public void init() {

        // If teacher enter no teachingId, then redirect him to his default teaching
        if (teachingId == null && loginService.getLoggedInUser().getRole().equals("teacher")) {
            if (teacherUserBackingBean.getClassroomId() == null) {
                teacherUserBackingBean.setClassroom();
            }
            if (teacherUserBackingBean.getClassroomId() != null) {
                teachingId = getDefaultTeacherTeaching(teacherUserBackingBean.getClassroomId()).getId();
            }
            else {
                String message = "Bad request. Please use a link from within the system.";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
                return;
            }
        }

        // If teacher tries to enter a teaching he does not teach, then redirect him to his default teaching
        if (loginService.getLoggedInUser().getRole().equals("teacher")) {
            if (!loginService.getLoggedInUser().getTeacher().getTeachingCollection().stream().map(Teaching::getId).collect(Collectors.toList()).contains(teachingId)) {
                if (teacherUserBackingBean.getClassroomId() != null) {
                    teachingId = getDefaultTeacherTeaching(teacherUserBackingBean.getClassroomId()).getId();
                }
                else {
                    String message = "Bad request. Please use a link from within the system.";
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
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

    public Integer getNumberOfStudentsInState(String state, Task task) {
        Collection<Student> students = teaching.getClassroom().getStudentCollection();
        return (int) students.stream()
                .map(student -> taskService.getRawStudentsTaskState(student.getUserAccount().getId(), task))
                .filter(taskState -> taskState.equals(state))
                .count();
    }

    public Integer getNumberOfStudentsThatHaveSomeTaskInSubmittedOrResubmittedStateForRhsTeaching(Integer teachingId) {
        Teaching teaching = teachingService.findTeaching(teachingId);
        Collection<Student> students = teaching.getClassroom().getStudentCollection();
        Collection<Task> tasks = teaching.getTaskCollection();
        return getNumberOfStudentsThatHaveSomeTaskInSubmittedOrResubmittedState(students, tasks);
    }

    public Integer getNumberOfStudentsThatHaveSomeTaskInSubmittedOrResubmittedStateForRhsType(String type) {
        Collection<Student> students = teaching.getClassroom().getStudentCollection();
        Collection<Task> tasks = teaching.getTaskCollection().stream()
                .filter(task -> task.getType().equals(type))
                .collect(Collectors.toList());
        return getNumberOfStudentsThatHaveSomeTaskInSubmittedOrResubmittedState(students, tasks);
    }

    private Integer getNumberOfStudentsThatHaveSomeTaskInSubmittedOrResubmittedState(Collection<Student> students, Collection<Task> tasks) {
        int count = 0;

        for (Task task : tasks) {
            count += (int) students.stream()
                    .map(student -> taskService.getRawStudentsTaskState(student.getUserAccount().getId(), task))
                    .filter(taskState -> taskState.equals("submitted") || taskState.equals("resubmitted"))
                    .count();
        }
        return  count;
    }

    public Integer getNumberOfStudentsThatHaveSomeTaskInSubmittedOrResubmittedStateForRhsClassroom(Integer classroomId) {
        int count = 0;
        Collection<Teaching> myTeachings = classroomService.findClassroom(classroomId).getTeachingCollection().stream()
                .filter(t -> t.getTeacher().getUserAccount().getId().equals(loginService.getLoggedInUser().getId()))
                .collect(Collectors.toList());
        for (Teaching teaching : myTeachings) {
            count += getNumberOfStudentsThatHaveSomeTaskInSubmittedOrResubmittedStateForRhsTeaching(teaching.getId());
        }
        return  count;
    }

    public Teaching getDefaultTeacherTeaching(Integer classroomId) {
        Teacher loggedInTeacher = loginService.getLoggedInUser().getTeacher();
        return loggedInTeacher.getTeachingCollection().stream().filter(t -> t.getClassroom().getId().equals(classroomId)).findFirst().orElse(null);
    }

    public boolean isCurrentTeaching(String teachingId) {
        if (this.teachingId == null) return false;
        return this.teachingId.toString().equals(teachingId);
    }





    public void initNewTeaching() {
        teaching = new Teaching();
    }

    public void saveTeaching(Teacher teacher) {

        teaching.setTeacher(teacher);

        if (teaching.getId() == null) {
            teachingService.saveTeaching(teaching);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Výuka vytvořena"));
        }
        else {
            teachingService.saveTeaching(teaching);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Výuka upravena"));
        }
        PrimeFaces.current().executeScript("PF('manageTeachingDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-teachings");
    }

    // Getters & Setters

    public Integer getTeachingId() {
        return teachingId;
    }

    public void setTeachingId(Integer teachingId) {
        this.teachingId = teachingId;
    }

    public Teaching getTeaching() {
        return teaching;
    }

    public void setTeaching(Teaching teaching) {
        this.teaching = teaching;
    }
}