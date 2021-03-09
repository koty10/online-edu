package cz.cvut.kotyna.onlineedu.jsf.student;

import cz.cvut.kotyna.onlineedu.entity.Attempt;
import cz.cvut.kotyna.onlineedu.entity.Student;
import cz.cvut.kotyna.onlineedu.entity.Task;
import cz.cvut.kotyna.onlineedu.jsf.UrlHelperBean;
import cz.cvut.kotyna.onlineedu.model.listDataModel.teacher.task.StudentWithTaskState;
import cz.cvut.kotyna.onlineedu.model.listDataModel.teacher.tasks.TaskWithStatisticsModel;
import cz.cvut.kotyna.onlineedu.service.*;
import lombok.Getter;
import lombok.Setter;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Named(value = "studentTaskBean")
@ViewScoped
public class StudentTaskBean implements Serializable {

    @EJB
    private UserService userService;
    @EJB
    private LoginService loginService;
    @EJB
    private TeachingService teachingService;
    @EJB
    private TaskService taskService;
    @EJB
    private AttemptService attemptService;
    @Inject
    private StudentUserBackingBean studentUserBackingBean;
    @Inject
    private UrlHelperBean urlHelperBean;
    @Inject
    private StudentTeachingBean studentTeachingBean;

    private final List<String> states = Arrays.asList("new", "accepted", "excused", "failed", "resubmitted", "returned", "submitted");
    @Getter @Setter
    private Integer taskId;
    @Getter
    private Task task;
    @Getter @Setter
    private String attemptText;
    @Setter
    private Collection<Task> tasks;
    @Setter
    private Collection<Task> extraNotYetAcceptedTasks;
    @Setter
    private Collection<Task> extraAlreadyAcceptedTasks;
    @Setter
    private Collection<Task> extraAlreadySentTasks;
    @Setter
    private Collection<Task> normalNotYetAcceptedTasks;
    @Setter
    private Collection<Task> normalAlreadyAcceptedTasks;
    @Setter
    private Collection<Task> normalAlreadySentTasks;

    public void initTask() {
        if (taskId == null) {
            try {
                final String contextPathForCurrentUser = urlHelperBean.getContextPathForCurrentUser();
                FacesContext.getCurrentInstance().getExternalContext().redirect(contextPathForCurrentUser + "/tasks.xhtml?teachingId=" + studentTeachingBean.getTeachingId());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        task = taskService.findTask(taskId);

        if (task == null) {
            String message = "Neznámý úkol";
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
        }
    }

    public void createAttempt() {
        attemptService.createAttempt(attemptText, loginService.getLoggedInUser().getStudent(), task);
        try {
            final String contextPathForCurrentUser = urlHelperBean.getContextPathForCurrentUser();
            if (task.getType().equals("normal")) {
                FacesContext.getCurrentInstance().getExternalContext().redirect(contextPathForCurrentUser + "/tasks.xhtml?teachingId=" + studentTeachingBean.getTeachingId());
            }
            else {
                FacesContext.getCurrentInstance().getExternalContext().redirect(contextPathForCurrentUser + "/extra-tasks.xhtml?teachingId=" + studentTeachingBean.getTeachingId());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Attempt> getLoggedInStudentsAttemptsReverseSorted() {
        if (taskId == null) {
            return new ArrayList<>();
        }
        Student student = loginService.getLoggedInUser().getStudent();
        return attemptService.getAttempts(student.getUserAccount().getId(), taskId);
    }

    public String getLoggedInStudentsTaskState(Integer taskId) {
        return taskService.getStudentsTaskState(loginService.getLoggedInUser().getId(), taskId);
    }

    public String getLoggedInStudentsTaskStateRaw(Integer taskId) {
        return taskService.getRawStudentsTaskState(loginService.getLoggedInUser().getId(), taskId);
    }

    public Collection<Task> getTasks() {
        if (tasks == null) {
            tasks = teachingService.getTasks(studentTeachingBean.getTeachingId());
        }
        return tasks;
    }

    public Collection<Task> getExtraNotYetAcceptedTasks() {
        if (extraNotYetAcceptedTasks == null) {
            extraNotYetAcceptedTasks = teachingService.getTasks(studentTeachingBean.getTeachingId()).stream()
                    .filter(task ->
                            !getLoggedInStudentsTaskStateRaw(task.getId()).equals("accepted") &&
                            !getLoggedInStudentsTaskStateRaw(task.getId()).equals("submitted") &&
                            !getLoggedInStudentsTaskStateRaw(task.getId()).equals("resubmitted")
                    )
                    .filter(task -> task.getType() != null && task.getType().equals("extra"))
                    .collect(Collectors.toList());
        }
        return extraNotYetAcceptedTasks;
    }

    public Collection<Task> getExtraAlreadyAcceptedTasks() {
        if (extraAlreadyAcceptedTasks == null) {
            extraAlreadyAcceptedTasks = teachingService.getTasks(studentTeachingBean.getTeachingId()).stream()
                    .filter(task -> getLoggedInStudentsTaskStateRaw(task.getId()).equals("accepted"))
                    .filter(task -> task.getType() != null && task.getType().equals("extra"))
                    .collect(Collectors.toList());
        }
        return extraAlreadyAcceptedTasks;
    }

    public Collection<Task> getExtraAlreadySentTasks() {
        if (extraAlreadySentTasks == null) {
            extraAlreadySentTasks = teachingService.getTasks(studentTeachingBean.getTeachingId()).stream()
                    .filter(task ->
                            getLoggedInStudentsTaskStateRaw(task.getId()).equals("submitted") ||
                            getLoggedInStudentsTaskStateRaw(task.getId()).equals("resubmitted")
                    )
                    .filter(task -> task.getType() != null && task.getType().equals("extra"))
                    .collect(Collectors.toList());
        }
        return extraAlreadySentTasks;
    }

    public Collection<Task> getNormalNotYetAcceptedTasks() {
        if (normalNotYetAcceptedTasks == null) {
            normalNotYetAcceptedTasks = teachingService.getTasks(studentTeachingBean.getTeachingId()).stream()
                    .filter(task ->
                            !getLoggedInStudentsTaskStateRaw(task.getId()).equals("accepted") &&
                                    !getLoggedInStudentsTaskStateRaw(task.getId()).equals("submitted") &&
                                    !getLoggedInStudentsTaskStateRaw(task.getId()).equals("resubmitted")
                    )
                    .filter(task -> task.getType() != null && task.getType().equals("normal"))
                    .collect(Collectors.toList());
        }
        return normalNotYetAcceptedTasks;
    }

    public Collection<Task> getNormalAlreadyAcceptedTasks() {
        if (normalAlreadyAcceptedTasks == null) {
            normalAlreadyAcceptedTasks = teachingService.getTasks(studentTeachingBean.getTeachingId()).stream()
                    .filter(task -> getLoggedInStudentsTaskStateRaw(task.getId()).equals("accepted"))
                    .filter(task -> task.getType() != null && task.getType().equals("normal"))
                    .collect(Collectors.toList());
        }
        return normalAlreadyAcceptedTasks;
    }

    public Collection<Task> getNormalAlreadySentTasks() {
        if (normalAlreadySentTasks == null) {
            normalAlreadySentTasks = teachingService.getTasks(studentTeachingBean.getTeachingId()).stream()
                    .filter(task ->
                            getLoggedInStudentsTaskStateRaw(task.getId()).equals("submitted") ||
                                    getLoggedInStudentsTaskStateRaw(task.getId()).equals("resubmitted")
                    )
                    .filter(task -> task.getType() != null && task.getType().equals("normal"))
                    .collect(Collectors.toList());
        }
        return normalAlreadySentTasks;
    }
}