package cz.cvut.kotyna.onlineedu.jsf;

import cz.cvut.kotyna.onlineedu.entity.Attempt;
import cz.cvut.kotyna.onlineedu.entity.Student;
import cz.cvut.kotyna.onlineedu.entity.Task;
import cz.cvut.kotyna.onlineedu.service.*;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Named(value = "taskBean")
@ViewScoped
public class TaskBean implements Serializable {

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

    private Integer taskId;
    private Task task;

    // attempt text content
    private String text;
    private String result;

    /**
     * Creates a new instance of TeachingBean
     */
    public TaskBean() {
    }

    public void init() {
        if (taskId == null) {
            String message = "Bad request. Please use a link from within the system.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
            return;
        }

        task = taskService.findTask(taskId);

        if (task == null) {
            String message = "Bad request. Unknown task.";
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
        }
    }

    public void createAttempt() {
        attemptService.createAttempt(text, loginService.getLoggedInUser().getStudent(), task);
        result = "Pokus úspěšně odeslán!";
    }
    public Task getTask() {
        return task;
    }

    public List<Attempt> getLoggedInStudentsAttemptsReverseSorted() {
        if (taskId == null) {
            return new ArrayList<>();
        }
        Student student = loginService.getLoggedInUser().getStudent();
        Task task = taskService.findTask(taskId);
        List<Attempt> attempts = task.getAttemptCollection().stream().filter(a -> a.getStudent().getId().equals(student.getId())).sorted((x, y) -> y.getTime().compareTo(x.getTime())).collect(Collectors.toList());
        return attempts;
    }

    public String getTaskState(String studentUsername, Integer taskId) {
        Student student = userService.getStudentByUsername(studentUsername);
        Task task = taskService.findTask(taskId);
        List<Attempt> attempts = task.getAttemptCollection().stream().filter(a -> a.getStudent().getUserAccount().getUsername().equals(studentUsername)).sorted(Comparator.comparing(Attempt::getTime)).collect(Collectors.toList());
        return attempts.get(attempts.size() - 1).getStateCzechFormated();
    }

    public String getLoggedInStudentsTaskState(Integer taskId) {
        Student student = loginService.getLoggedInUser().getStudent();
        Task task = taskService.findTask(taskId);
        List<Attempt> attempts = task.getAttemptCollection().stream().filter(a -> a.getStudent().getId().equals(student.getId())).sorted(Comparator.comparing(Attempt::getTime)).collect(Collectors.toList());
        return attempts.get(attempts.size() - 1).getStateCzechFormated();
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
