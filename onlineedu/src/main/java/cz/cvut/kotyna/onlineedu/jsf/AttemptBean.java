package cz.cvut.kotyna.onlineedu.jsf;

import cz.cvut.kotyna.onlineedu.entity.Attempt;
import cz.cvut.kotyna.onlineedu.entity.Task;
import cz.cvut.kotyna.onlineedu.entity.Teaching;
import cz.cvut.kotyna.onlineedu.service.*;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

    @Inject
    private UserBackingBean userBackingBean;

    @Inject
    private TeachingBean teachingBean;

    @EJB
    private AttemptService attemptService;

    private Integer attemptId;
    //private Integer taskId;
    private Attempt attempt;
    //private Task task;

    ListDataModel<Attempt> lastAttemptsListDataModel;

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

    public void returnAttempt() {
        attemptService.returnAttempt(attempt);
        FacesMessage msg = new FacesMessage("Vráceno");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    // loads ListDataModel for dataTable on teachers task page
    public void loadLastAttemptsListDataModel() {
        List<Attempt> tmp = new ArrayList<>();
        for (Task t2 : teachingBean.getTeaching().getTaskCollection()) {
            tmp.addAll(t2.getAttemptCollection().stream()
                    .sorted((x, y) -> y.getTime().compareTo(x.getTime()))
                    .filter(distinctByKey(Attempt::getStudent))
                    .collect(Collectors.toList()));
        }
        tmp = tmp.stream().limit(10).collect(Collectors.toList());

        lastAttemptsListDataModel = new ListDataModel<>(tmp);
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    public ListDataModel<Attempt> getLastAttemptsListDataModel() {
        return lastAttemptsListDataModel;
    }
}
