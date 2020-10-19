package cz.cvut.kotyna.onlineedu.jsf;

import cz.cvut.kotyna.onlineedu.entity.Attempt;
import cz.cvut.kotyna.onlineedu.entity.Task;
import cz.cvut.kotyna.onlineedu.service.*;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Named(value = "attemptBean")
@RequestScoped
public class AttemptBean {

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

    private String attemptId;

    /**
     * Creates a new instance of TeachingBean
     */
    public AttemptBean() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        attemptId = params.get("attempt");
    }

    public Attempt getAttempt() {
        return attemptService.getAttemptById(attemptId);
    }

    public void createAttempt(String text) {
        String taskId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("task");
        attemptService.createAttempt(text, loginService.getLoggedInUser().getStudent(), taskService.getTaskById(taskId));
    }

}
