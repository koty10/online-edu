package cz.cvut.kotyna.onlineedu.jsf;

import cz.cvut.kotyna.onlineedu.entity.Attempt;
import cz.cvut.kotyna.onlineedu.entity.Student;
import cz.cvut.kotyna.onlineedu.entity.Task;
import cz.cvut.kotyna.onlineedu.model.listDataModel.teacher.task.StudentWithTaskState;
import cz.cvut.kotyna.onlineedu.model.listDataModel.teacher.tasks.TaskWithStatisticsModel;
import cz.cvut.kotyna.onlineedu.service.*;

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
    @Inject
    private UserBackingBean userBackingBean;
    @Inject
    private UrlHelperBean urlHelperBean;
    @Inject
    private TeachingBean teachingBean;

    private final List<String> states = Arrays.asList("new", "accepted", "excused", "failed", "resubmitted", "returned", "submitted");
    private Integer taskId;
    private Task task;
    // attempt text content
    private String attemptText;
    private String result;

    private ListDataModel<StudentWithTaskState> studentsDataModel;

    private ListDataModel<TaskWithStatisticsModel> taskWithStatisticsListDataModel;

    // TODO rework like taskWithStatisticsListDataModel
    private ListDataModel<Task> tasksDataModel;

    public void init() {
        if (taskId == null) {
            try {
                final String contextPathForCurrentUser = urlHelperBean.getContextPathForCurrentUser();
                FacesContext.getCurrentInstance().getExternalContext().redirect(contextPathForCurrentUser + "/tasks.xhtml?teachingId=" + teachingBean.getTeachingId());
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

    // loads ListDataModel for dataTable on teachers task page
    public void loadStudentsListDataModel() {
        List<StudentWithTaskState> tmp = new ArrayList<>();
        for (Student s : userBackingBean.getClassroom().getStudentCollection()) {
            tmp.add(new StudentWithTaskState(
                    s,
                    taskService.getStudentsTaskState(s.getUserAccount().getId(), taskId),
                    taskService.getRawStudentsTaskState(s.getUserAccount().getId(), taskId)));
        }
        studentsDataModel = new ListDataModel<>(tmp);
    }

    // initialize new Task (used by form to set it's properties and persist it)
    public void initNewTask() {
        task = new Task();
    }

    public List<Attempt> getStudentsAttemptsReverseSorted() {
        Student selectedStudent = studentsDataModel.getRowData().getStudent();

        if (taskId == null || selectedStudent == null) {
            return new ArrayList<>();
        }
        Task task = taskService.findTask(taskId);
        List<Attempt> attempts = task.getAttemptCollection().stream()
                .filter(a -> a.getStudent().getId().equals(selectedStudent.getId()))
                .sorted((x, y) -> y.getTime().compareTo(x.getTime()))
                .collect(Collectors.toList());
        return attempts;
    }

    public void createAttempt() {
        attemptService.createAttempt(attemptText, loginService.getLoggedInUser().getStudent(), task);
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
        return attemptService.getAttempts(student.getUserAccount().getId(), taskId);
    }

    public String getLoggedInStudentsTaskState(Integer taskId) {
        return taskService.getStudentsTaskState(loginService.getLoggedInUser().getId(), taskId);
    }

    public void updateTaskText() {
        taskService.updateTask(task);
        FacesMessage msg = new FacesMessage("Uloženo");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void createTask() {
        task.setTeaching(teachingBean.getTeaching());
        taskService.createTask(task);
        FacesMessage msg = new FacesMessage("Úkol vytvořen");
        FacesContext.getCurrentInstance().addMessage(null, msg);

        // to update table data
        //tasks.add(task);
        loadTasks();
        //tasksDataModel.setWrappedData(tasks);
        // to clear the form
        task = new Task();

    }

    public void loadTasks() {
        List<TaskWithStatisticsModel> taskWithStatisticsModels = new ArrayList<>();
        for (Task t : teachingService.getTasks(teachingBean.getTeachingId())) {
            TaskWithStatisticsModel model = new TaskWithStatisticsModel();
            model.setTaskId(t.getId());
            model.setTaskName(t.getName());
            model.setTaskDate(t.getDateFormatted());
            model.setTaskTimeFrom(t.getTimeFromFormatted());
            model.setTaskTimeTo(t.getTimeToFormatted());

            for (String state : states) {
                Integer number = teachingBean.getNumberOfStudentsInState(state, t.getId());
                if (number > 0) model.setNumberOfStudentsInState(state, number);
            }

            taskWithStatisticsModels.add(model);
        }
        taskWithStatisticsListDataModel = new ListDataModel<>(taskWithStatisticsModels);
    }

    public ListDataModel<TaskWithStatisticsModel> getTaskWithStatisticsListDataModel() {
        if (taskWithStatisticsListDataModel == null) {
            loadTasks();
        }
        return taskWithStatisticsListDataModel;
    }

    public void setTaskWithStatisticsListDataModel(ListDataModel<TaskWithStatisticsModel> taskWithStatisticsListDataModel) {
        this.taskWithStatisticsListDataModel = taskWithStatisticsListDataModel;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getAttemptText() {
        return attemptText;
    }

    public void setAttemptText(String attemptText) {
        this.attemptText = attemptText;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ListDataModel<StudentWithTaskState> getStudentsDataModel() {
        return studentsDataModel;
    }

    public void setStudentsDataModel(ListDataModel<StudentWithTaskState> studentsDataModel) {
        this.studentsDataModel = studentsDataModel;
    }

    // TODO rework like taskWithStatisticsListDataModel
    public ListDataModel<Task> getTasksDataModel() {
        if (tasksDataModel == null) {
            tasksDataModel = new ListDataModel<>(new ArrayList<>(teachingService.getTasks(teachingBean.getTeachingId())));
        }
        return tasksDataModel;
    }

    public void setTasksDataModel(ListDataModel<Task> tasksDataModel) {
        this.tasksDataModel = tasksDataModel;
    }
}
