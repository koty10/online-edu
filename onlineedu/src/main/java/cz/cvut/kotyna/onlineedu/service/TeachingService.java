/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kotyna.onlineedu.service;

import cz.cvut.kotyna.onlineedu.entity.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Stateless
public class TeachingService {

    // connection to the database
    @PersistenceContext
    EntityManager em;

    public Collection<Task> getTasks(Integer e) {
        List<Teaching> teachings = em.createNamedQuery("Teaching.findById", Teaching.class).setParameter("id", (e)).getResultList();
        if (teachings.isEmpty()) {
            return new ArrayList<>();
        }
        return teachings.get(0).getTaskCollection();
    }

    public Teaching findTeaching(Integer id) {
        return em.find(Teaching.class, id);
    }
}
