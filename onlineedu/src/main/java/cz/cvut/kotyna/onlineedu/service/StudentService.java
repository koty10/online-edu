/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kotyna.onlineedu.service;

import cz.cvut.kotyna.onlineedu.entity.Student;
import cz.cvut.kotyna.onlineedu.entity.Teacher;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class StudentService {

    // connection to the database
    @PersistenceContext
    EntityManager em;

    @EJB
    UserService userService;

    private static final Logger logger = Logger.getLogger(StudentService.class.getName());

    public List<Student> getAllStudents() {
        return em.createNamedQuery(Student.FIND_ALL, Student.class).getResultList();
    }

    public Student findStudent(Integer studentId) {
        return em.find(Student.class, studentId);
    }

    public void saveStudent(Student student) {
        if (student.getId() == null) {
            em.persist(student);
        }
        else {
            em.merge(student);
        }
    }
}
