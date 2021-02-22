/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kotyna.onlineedu.service;

import cz.cvut.kotyna.onlineedu.entity.Classroom;
import cz.cvut.kotyna.onlineedu.entity.Student;
import cz.cvut.kotyna.onlineedu.entity.Task;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ClassroomService {

    // connection to the database
    @PersistenceContext
    EntityManager em;

    public Classroom findClassroom(Integer classroomId) {
        em.getEntityManagerFactory().getCache().evictAll();
        return em.find(Classroom.class, classroomId);
    }

    public List<Classroom> getAllClassrooms() {
        return em.createNamedQuery(Classroom.FIND_ALL, Classroom.class).getResultList();
    }

}
