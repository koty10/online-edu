package cz.cvut.kotyna.onlineedu.jsf;

import cz.cvut.kotyna.onlineedu.entity.Attempt;
import cz.cvut.kotyna.onlineedu.entity.Task;
import cz.cvut.kotyna.onlineedu.service.LoginService;
import cz.cvut.kotyna.onlineedu.service.TaskService;
import cz.cvut.kotyna.onlineedu.service.TeachingService;
import cz.cvut.kotyna.onlineedu.service.UserService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Named(value = "taskBean")
@RequestScoped
public class TaskBean {

    @EJB
    private UserService userService;

    @EJB
    private LoginService loginService;

    @EJB
    private TeachingService teachingService;

    @EJB
    private TaskService taskService;

    private String taskId;

    /**
     * Creates a new instance of TeachingBean
     */
    public TaskBean() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        taskId = params.get("task");
    }

    public Task getTask() {
        return taskService.getTaskById(taskId);
    }

    public List<Attempt> getAttemptsReverseSorted() {
        Task task = taskService.getTaskById(taskId);
        List<Attempt> attempts = task.getAttemptCollection().stream().sorted((x, y) -> y.getTime().compareTo(x.getTime())).collect(Collectors.toList());
        return attempts;
    }
}
