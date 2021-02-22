package cz.cvut.kotyna.onlineedu.jsf.teacher;

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

@Named(value = "teacherTaskBean")
@ViewScoped
public class TeacherTaskBean implements Serializable {

    @EJB
    private TeachingService teachingService;
    @EJB
    private TaskService taskService;
    @Inject
    private TeacherUserBackingBean teacherUserBackingBean;
    @Inject
    private UrlHelperBean urlHelperBean;
    @Inject
    private TeacherTeachingBean teacherTeachingBean;

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
    @Setter
    private ListDataModel<TaskWithStatisticsModel> taskWithStatisticsListDataModel;

    public void initTask() {
        if (taskId == null) {
            try {
                final String contextPathForCurrentUser = urlHelperBean.getContextPathForCurrentUser();
                FacesContext.getCurrentInstance().getExternalContext().redirect(contextPathForCurrentUser + "/tasks.xhtml?teachingId=" + teacherTeachingBean.getTeachingId());
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

    public void updateTaskText() {
        taskService.updateTask(task);
        FacesMessage msg = new FacesMessage("Uloženo");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void createTask() {
        task.setTeaching(teacherTeachingBean.getTeaching());
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
        for (Task t : teachingService.getTasks(teacherTeachingBean.getTeachingId())) {
            TaskWithStatisticsModel model = new TaskWithStatisticsModel();
            model.setTaskId(t.getId());
            model.setTaskName(t.getName());
            model.setTaskDate(t.getDateFormatted());
            model.setTaskTimeFrom(t.getTimeFromFormatted());
            model.setTaskTimeTo(t.getTimeToFormatted());

            for (String state : states) {
                Integer number = teacherTeachingBean.getNumberOfStudentsInState(state, t.getId());
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
}