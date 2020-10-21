/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kotyna.onlineedu.service;

import cz.cvut.kotyna.onlineedu.entity.Attempt;
import cz.cvut.kotyna.onlineedu.entity.Student;
import cz.cvut.kotyna.onlineedu.entity.Task;

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

    //TODO nevim proč ale nerefreshuje to
    public void createAttempt(String text, Student student, Task task) {
        Attempt attempt = new Attempt();
        attempt.setTime(new Date());
        attempt.setStudent(student);
        attempt.setTask(task);
        attempt.setText(text);
        em.persist(attempt);

    }
}
