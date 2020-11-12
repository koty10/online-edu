package cz.cvut.kotyna.onlineedu.jsf;

import cz.cvut.kotyna.onlineedu.entity.Attempt;
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

@Named(value = "attemptBean")
@ViewScoped
public class AttemptBean implements Serializable {

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

    private Integer attemptId;
    //private Integer taskId;
    private Attempt attempt;
    //private Task task;

    /**
     * Creates a new instance of TeachingBean
     */
    public AttemptBean() {
    }

    public void init() {
        // set task
        /*
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
        */

        //set attempt
        if (attemptId == null) {
            String message = "Bad request. Please use a link from within the system.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
            return;
        }

        attempt = attemptService.findAttempt(attemptId);

        if (attempt == null) {
            String message = "Bad request. Unknown attempt.";
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
        }
    }



    public Integer getAttemptId() {
        return attemptId;
    }

    public void setAttemptId(Integer attemptId) {
        this.attemptId = attemptId;
    }

    /*
    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Task getTask() {
        return task;
    }
     */

    public Attempt getAttempt() {
        return attempt;
    }

    public void acceptAttempt() {
        attemptService.acceptAttempt(attempt);
        FacesMessage msg = new FacesMessage("Schváleno");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
