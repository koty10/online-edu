/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kotyna.onlineedu.service;

import cz.cvut.kotyna.onlineedu.entity.Attempt;
import cz.cvut.kotyna.onlineedu.entity.Student;
import cz.cvut.kotyna.onlineedu.entity.Task;
import cz.cvut.kotyna.onlineedu.enums.TaskState;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class AttemptService {

    // connection to the database
    @PersistenceContext
    EntityManager em;

    @EJB
    TaskService taskService;
    @EJB
    StudentService studentService;

    public Attempt findAttempt(Integer attemptId) {
        return em.find(Attempt.class, attemptId);
    }

    public void createAttempt(String text, Student student, Task task) {
        Attempt attempt = new Attempt();
        attempt.setTime(new Date());
        attempt.setStudent(student);
        attempt.setTask(task);
        attempt.setText(text);
        List<Attempt> studentsAttempts = task.getAttemptCollection().stream().filter(a -> a.getStudent().equals(student)).collect(Collectors.toList());
        if (studentsAttempts.isEmpty()) {
            attempt.setState(TaskState.SUBMITTED.toString());
        }
        else {
            attempt.setState(TaskState.RESUBMITTED.toString());
        }
        /*
        if (task.getState().equals(TaskState.NEW.toString())) {
            task.setState(TaskState.SUBMITTED.toString());
        }
        else if (task.getState().equals(TaskState.RETURNED.toString())) {
            task.setState(TaskState.RESUBMITTED.toString());
        }
         */
        //em.merge(task);
        em.persist(attempt);
    }

    public void acceptAttempt(Attempt attempt) {
        // If the task is accepted first time (as it should be), then give student the points
        String state = taskService.getRawStudentsTaskState(attempt.getStudent().getUserAccount().getId(), attempt.getTask().getId());
        if (!state.equals(TaskState.ACCEPTED.toString()) && attempt.getTask().getType().equals("extra")) {
            attempt.getStudent().setPoints(attempt.getStudent().getPoints() + attempt.getTask().getPoints());
            studentService.saveStudent(attempt.getStudent());
        }

        attempt.setState(TaskState.ACCEPTED.toString());
        em.merge(attempt);
    }

    public void returnAttempt(Attempt attempt) {
        attempt.setState(TaskState.RETURNED.toString());
        em.merge(attempt);
    }

    public List<Attempt> getAttempts(Integer userAccountId, Integer taskId) {
        if (taskId == null) {
            return new ArrayList<>();
        }
        Task task = taskService.findTask(taskId);
        return task.getAttemptCollection().stream().filter(a -> a.getStudent().getUserAccount().getId().equals(userAccountId)).sorted(Comparator.comparing(Attempt::getTime)).collect(Collectors.toList());
    }
}