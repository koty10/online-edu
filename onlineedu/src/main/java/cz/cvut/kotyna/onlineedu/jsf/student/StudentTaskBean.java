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
    @Getter @Setter
    private String result;
    @Getter @Setter
    private ListDataModel<StudentWithTaskState> studentsDataModel;
    // TODO rework like taskWithStatisticsListDataModel
    @Setter
    private ListDataModel<Task> tasksDataModel;

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
        result = "Pokus úspěšně odeslán!";
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

    // TODO rework like taskWithStatisticsListDataModel
    public ListDataModel<Task> getTasksDataModel() {
        if (tasksDataModel == null) {
            tasksDataModel = new ListDataModel<>(new ArrayList<>(teachingService.getTasks(studentTeachingBean.getTeachingId())));
        }
        return tasksDataModel;
    }
}