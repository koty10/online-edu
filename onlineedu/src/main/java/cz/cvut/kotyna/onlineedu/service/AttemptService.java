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

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Date;

@Stateless
public class AttemptService {

    // connection to the database
    @PersistenceContext
    EntityManager em;

    public Attempt findAttempt(Integer attemptId) {
        return em.find(Attempt.class, attemptId);
    }

    public void createAttempt(String text, Student student, Task task) {
        Attempt attempt = new Attempt();
        attempt.setTime(new Date());
        attempt.setStudent(student);
        attempt.setTask(task);
        attempt.setText(text);
        /*
        if (task.getState().equals(TaskState.NEW.toString())) {
            task.setState(TaskState.SUBMITTED.toString());
        }
        else if (task.getState().equals(TaskState.RETURNED.toString())) {
            task.setState(TaskState.RESUBMITTED.toString());
        }
         */
        em.merge(task);
        em.persist(attempt);
    }
}
