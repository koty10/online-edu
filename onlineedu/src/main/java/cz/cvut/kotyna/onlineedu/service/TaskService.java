/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kotyna.onlineedu.service;

import cz.cvut.kotyna.onlineedu.entity.Attempt;
import cz.cvut.kotyna.onlineedu.entity.Student;
import cz.cvut.kotyna.onlineedu.entity.Task;
import cz.cvut.kotyna.onlineedu.entity.Teaching;
import cz.cvut.kotyna.onlineedu.enums.TaskState;
import cz.cvut.kotyna.onlineedu.model.listDataModel.teacher.tasks.TaskWithStatisticsModel;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;
import java.util.stream.Collectors;

@Stateless
public class TaskService {

    // connection to the database
    @PersistenceContext
    EntityManager em;

    @EJB
    UserService userService;
    @EJB
    TaskService taskService;
    @EJB
    TeachingService teachingService;
    @EJB
    AttemptService attemptService;

    private final List<String> states = Arrays.asList("new", "accepted", "excused", "failed", "resubmitted", "returned", "submitted");


    public Task findTask(Integer taskId) {
        em.getEntityManagerFactory().getCache().evictAll(); //nedelat
        return em.find(Task.class, taskId);
    }

    public void updateTask(Task task) {
        em.merge(task);
    }

    public String getStudentsTaskState(Integer userAccountId, Task task) {
        List<Attempt> attempts = attemptService.getAttempts(userAccountId, task).stream()
                .sorted(Comparator.comparing(Attempt::getTime)).collect(Collectors.toList());
        if (attempts.isEmpty()) return "Nový";
        if (attempts.stream().map(Attempt::getState).anyMatch(x -> x.equals("accepted"))) return "Schváleno";
        return attempts.get(attempts.size() - 1).getStateCzechFormated();
    }

    public String getRawStudentsTaskState(Integer userAccountId, Task task) {
        List<Attempt> attempts = attemptService.getAttempts(userAccountId, task).stream()
            .sorted(Comparator.comparing(Attempt::getTime)).collect(Collectors.toList());
        if (attempts.isEmpty()) return "new";
        if (attempts.stream().map(Attempt::getState).anyMatch(x -> x.equals("accepted"))) return "accepted";
        return attempts.get(attempts.size() - 1).getState();
    }

    public void createTask(Task task) {
        //Task task = new Task();
        task.setDate(new Date());

        /*
        task.setName(name);
        task.setTeaching(teaching);
        task.setText(text);
        task.setTimeFrom(timeFrom);
        task.setTimeTo(timeTo);
        task.setType(type);
        */

        em.persist(task);
    }



    public List<TaskWithStatisticsModel> getTaskWithStatisticsModels(Integer teachingId, String type) {

        Teaching teaching = teachingService.findTeaching(teachingId);
        List<TaskWithStatisticsModel> taskWithStatisticsModels = new ArrayList<>();

        Collection<Task> tasks = teachingService.getTasks(teachingId).stream().filter(task -> task.getType().equals(type)).collect(Collectors.toList());

        for (Task t : tasks) {
            TaskWithStatisticsModel model = new TaskWithStatisticsModel();
            model.setTaskId(t.getId());
            model.setTaskName(t.getName());
            model.setTaskDate(t.getDateFormatted());
            model.setTaskTimeFrom(t.getTimeFromFormatted());
            model.setTaskTimeTo(t.getTimeToFormatted());
            model.setType(t.getTypeCzechFormatted());
            model.setPoints(t.getPoints());

            for (String state : states) {
                int number = (int) teaching.getClassroom().getStudentCollection().stream()
                        .map(student -> taskService.getRawStudentsTaskState(student.getUserAccount().getId(), t))
                        .filter(taskState -> taskState.equals(state))
                        .count();
                if (number > 0) model.setNumberOfStudentsInState(state, number);
            }

            taskWithStatisticsModels.add(model);
        }
        return taskWithStatisticsModels;
    }


}
