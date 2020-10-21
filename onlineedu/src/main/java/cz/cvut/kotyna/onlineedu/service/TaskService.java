/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kotyna.onlineedu.service;

import cz.cvut.kotyna.onlineedu.entity.Task;
import cz.cvut.kotyna.onlineedu.entity.Teaching;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Stateless
public class TaskService {

    // connection to the database
    @PersistenceContext
    EntityManager em;

    public Task findTask(Integer taskId) {
        em.getEntityManagerFactory().getCache().evictAll();
        return em.find(Task.class, taskId);
    }

}
