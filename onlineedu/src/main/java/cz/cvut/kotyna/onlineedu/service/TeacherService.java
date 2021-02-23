/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kotyna.onlineedu.service;

import cz.cvut.kotyna.onlineedu.entity.*;
import cz.cvut.kotyna.onlineedu.model.listDataModel.teacher.classbook.StudentStatisticsModel;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class TeacherService {

    // connection to the database
    @PersistenceContext
    EntityManager em;

    @EJB
    UserService userService;

    private static final Logger logger = Logger.getLogger(TeacherService.class.getName());

    public List<Teacher> getAllTeachers() {
        return em.createNamedQuery(Teacher.FIND_ALL, Teacher.class).getResultList();
    }

    public Teacher findTeacher(Integer teacherId) {
        return em.find(Teacher.class, teacherId);
    }

    public void saveTeacher(Teacher teacher) {
        if (teacher.getId() == null) {
            em.persist(teacher);
        }
        else {
            em.merge(teacher);
        }
    }
}
