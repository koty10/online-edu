package cz.cvut.kotyna.onlineedu.jsf.teacher;

import cz.cvut.kotyna.onlineedu.entity.Attempt;
import cz.cvut.kotyna.onlineedu.entity.Student;
import cz.cvut.kotyna.onlineedu.entity.Task;
import cz.cvut.kotyna.onlineedu.jsf.UrlHelperBean;
import cz.cvut.kotyna.onlineedu.model.listDataModel.teacher.task.StudentWithTaskState;
import cz.cvut.kotyna.onlineedu.model.listDataModel.teacher.tasks.TaskWithStatisticsModel;
import cz.cvut.kotyna.onlineedu.service.*;
import org.omnifaces.cdi.Param;

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

@Named(value = "teacherTaskBean")
@ViewScoped
public class TeacherTaskBean implements Serializable {

    @EJB
    private TaskService taskService;
    @Inject
    private TeacherUserBackingBean teacherUserBackingBean;
    @Inject
    private UrlHelperBean urlHelperBean;
    @Inject
    private TeacherTeachingBean teacherTeachingBean;

    private Integer taskId;
    private Task task;
    // filter
    @Inject @Param(name = "state")
    private List<String> states;
    private String attemptText;
    private String result;
    private ListDataModel<StudentWithTaskState> studentsDataModel;
    private ListDataModel<TaskWithStatisticsModel> taskWithStatisticsListDataModel;

    public void initTask() {
        if (taskId == null) {
            try {
                final String contextPathForCurrentUser = urlHelperBean.getContextPathForCurrentUser();
                FacesContext.getCurrentInstance().getExternalContext().redirect(contextPathForCurrentUser + "/tasks.xhtml?teachingId=" + teacherTeachingBean.getTeachingId());
                return;
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
        for (Student s : teacherUserBackingBean.getClassroom().getStudentCollection()) {
            String rawState = taskService.getRawStudentsTaskState(s.getUserAccount().getId(), task);
            if (states == null || states.contains(rawState)) {
                tmp.add(new StudentWithTaskState(
                        s,
                        taskService.getStudentsTaskState(s.getUserAccount().getId(), task),
                        rawState));
            }
        }
        studentsDataModel = new ListDataModel<>(tmp);
    }

    // initialize new Task (used by form to set it's properties and persist it)
    public void initNewTask(String type) {
        task = new Task();
        task.setType(type);
    }

    public List<Attempt> getStudentsAttemptsReverseSorted() {
        Student selectedStudent = studentsDataModel.getRowData().getStudent();

        if (taskId == null || selectedStudent == null) {
            return new ArrayList<>();
        }
        Task task = taskService.findTask(taskId);
        return task.getAttemptCollection().stream()
                .filter(a -> a.getStudent().getId().equals(selectedStudent.getId()))
                .sorted((x, y) -> y.getTime().compareTo(x.getTime()))
                .collect(Collectors.toList());
    }

    // it is used in "rendered" parameter because it is in prerender phase and row data is not available yet
    public Integer getNumberOfStudentsAttemptsForRhsStudent(Student selectedStudent) {
        if (taskId == null || selectedStudent == null) {
            return 0;
        }
        Task task = taskService.findTask(taskId);
        return (int) task.getAttemptCollection().stream()
                .filter(a -> a.getStudent().getId().equals(selectedStudent.getId())).count();
    }

    public void updateTaskText() {
        taskService.updateTask(task);
        FacesMessage msg = new FacesMessage("Uloženo");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void createTask(String type) {
        task.setTeaching(teacherTeachingBean.getTeaching());
        taskService.createTask(task);
        FacesMessage msg = new FacesMessage("Úkol vytvořen");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        // to update table data
        loadTasks(type);
        // to clear the form
        task = new Task();
        task.setType(type);
    }

    public void loadTasks(String type) {
        taskWithStatisticsListDataModel = new ListDataModel<>(taskService.getTaskWithStatisticsModels(teacherTeachingBean.getTeachingId(), type));
    }

    public ListDataModel<TaskWithStatisticsModel> getTaskWithStatisticsListDataModel(String type) {
        if (taskWithStatisticsListDataModel == null) {
            loadTasks(type);
        }
        return taskWithStatisticsListDataModel;
    }

    // Getters & Setters


    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
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

    public void setTaskWithStatisticsListDataModel(ListDataModel<TaskWithStatisticsModel> taskWithStatisticsListDataModel) {
        this.taskWithStatisticsListDataModel = taskWithStatisticsListDataModel;
    }

    public List<String> getStates() {
        return states;
    }

    public void setStates(List<String> states) {
        this.states = states;
    }
}