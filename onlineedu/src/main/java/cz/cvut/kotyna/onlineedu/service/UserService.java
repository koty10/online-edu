/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kotyna.onlineedu.service;

import cz.cvut.kotyna.onlineedu.entity.Parent;
import cz.cvut.kotyna.onlineedu.entity.Student;
import cz.cvut.kotyna.onlineedu.entity.Teacher;
import cz.cvut.kotyna.onlineedu.entity.UserAccount;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class UserService {

    // connection to the database
    @PersistenceContext
    EntityManager em;

    public List<UserAccount> getAllUsers() {
        return em.createNamedQuery(UserAccount.FIND_ALL, UserAccount.class).getResultList();
    }

    public List<Student> getAllStudents() {
        return em.createNamedQuery(Student.FIND_ALL, Student.class).getResultList();
    }

    public List<Parent> getAllParents() {
        return em.createNamedQuery(Parent.FIND_ALL, Parent.class).getResultList();
    }

    public List<Teacher> getAllTeachers() {
        return em.createNamedQuery(Teacher.FIND_ALL, Teacher.class).getResultList();
    }

    public List<Student> getClassmates(int classroomId) {
        return em.createNamedQuery(Student.FIND_CLASSMATES, Student.class).setParameter("classroomId", classroomId).getResultList();
    }
}
