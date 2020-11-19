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

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.List;

@Stateless
public class TaskService {

    // connection to the database
    @PersistenceContext
    EntityManager em;

    @EJB
    UserService userService;

    @EJB
    AttemptService attemptService;

    public Task findTask(Integer taskId) {
        em.getEntityManagerFactory().getCache().evictAll();
        return em.find(Task.class, taskId);
    }

    public void updateTask(Task task) {
        em.merge(task);
    }

    public String getStudentsTaskState(Integer userAccountId, Integer taskId) {
        List<Attempt> attempts = attemptService.getAttempts(userAccountId, taskId);
        if (attempts.isEmpty()) return "Nový";
        if (attempts.stream().map(Attempt::getState).anyMatch(x -> x.equals("accepted"))) return "Schváleno";
        return attempts.get(attempts.size() - 1).getStateCzechFormated();
    }

    public String getRawStudentsTaskState(Integer userAccountId, Integer taskId) {
        List<Attempt> attempts = attemptService.getAttempts(userAccountId, taskId);
        if (attempts.isEmpty()) return "new";
        if (attempts.stream().map(Attempt::getState).anyMatch(x -> x.equals("accepted"))) return "accepted";
        return attempts.get(attempts.size() - 1).getState();
    }
}
