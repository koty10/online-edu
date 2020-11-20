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

    public Collection<Task> getTasks(Integer teachingId) {
        em.getEntityManagerFactory().getCache().evictAll();
        Teaching teaching = em.find(Teaching.class, teachingId);
        if (teaching == null) {
            return new ArrayList<>();
        }
        return teaching.getTaskCollection();
    }

    public Teaching findTeaching(Integer id) {
        return em.find(Teaching.class, id);
    }
}
