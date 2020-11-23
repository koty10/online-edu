/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kotyna.onlineedu.service;

import cz.cvut.kotyna.onlineedu.entity.*;
import cz.cvut.kotyna.onlineedu.enums.TaskState;
import cz.cvut.kotyna.onlineedu.model.listDataModel.teacher.classbook.StudentStatisticsModel;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.model.DataModel;
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
public class UserService {

    // connection to the database
    @PersistenceContext
    EntityManager em;

    @EJB
    TaskService taskService;

    private static Logger logger = Logger.getLogger(UserService.class.getName());

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

    public Collection<Teaching> getTeachings(Student student) {
        return student.getClassroom().getTeachingCollection();
    }

    public Student getStudentByUsername(String username) {
        try {
            if (username != null) {
                return em.createNamedQuery(Student.FIND_LOGGED_IN_STUDENT, Student.class).setParameter("username", username).getSingleResult();
            } else {
                return null;
            }
        }
        catch(NoResultException e) {
            return null;
        }
    }

    public UserAccount findUserAccount(Integer userAccountId) {
        return em.find(UserAccount.class, userAccountId);
    }

    public void createStudent(UserAccount userAccount, Classroom classroom) {
        userAccount.setRole("student");
        userAccount.setRegistered(new Date());

        generateUserAccountUsernameAndPassword(userAccount);

        Student student = new Student();
        student.setUserAccount(userAccount);
        student.setClassroom(classroom);


        try {
            em.persist(student);
            logger.log(Level.INFO, "Student with new UserAccount (" + userAccount.getUsername() + ") created.");
            //em.persist(userAccount);
        }

        catch (ConstraintViolationException e) {
            logger.log(Level.SEVERE,"Exception: ");
            e.getConstraintViolations().forEach(err->logger.log(Level.SEVERE,err.toString()));
        }
    }

    public List<StudentStatisticsModel> getClassbookModel(Teaching teaching) {
        List<StudentStatisticsModel> list = new ArrayList<>();
        for (Student student : getClassmates(teaching.getClassroom().getId())) {
            StudentStatisticsModel studentStatisticsModel = new StudentStatisticsModel();
            studentStatisticsModel.setStudentFirstname(student.getUserAccount().getFirstname());
            studentStatisticsModel.setStudentSurname(student.getUserAccount().getSurname());
            studentStatisticsModel.setNumberOfTasksInNewState(getNumberOfTasksInRhsStateForRhsStudent(student.getUserAccount().getId(), teaching, "new"));
            studentStatisticsModel.setNumberOfTasksInSubmittedState(getNumberOfTasksInRhsStateForRhsStudent(student.getUserAccount().getId(), teaching, "submitted"));
            studentStatisticsModel.setNumberOfTasksInReturnedState(getNumberOfTasksInRhsStateForRhsStudent(student.getUserAccount().getId(), teaching, "returned"));
            studentStatisticsModel.setNumberOfTasksInResubmittedState(getNumberOfTasksInRhsStateForRhsStudent(student.getUserAccount().getId(), teaching, "resubmitted"));
            studentStatisticsModel.setNumberOfTasksInAcceptedState(getNumberOfTasksInRhsStateForRhsStudent(student.getUserAccount().getId(), teaching, "accepted"));
            studentStatisticsModel.setNumberOfTasksInExcusedState(getNumberOfTasksInRhsStateForRhsStudent(student.getUserAccount().getId(), teaching, "excused"));
            studentStatisticsModel.setNumberOfTasksInFailedState(getNumberOfTasksInRhsStateForRhsStudent(student.getUserAccount().getId(), teaching, "failed"));
            list.add(studentStatisticsModel);
        }
        return list;
    }

    private Integer getNumberOfTasksInRhsStateForRhsStudent(Integer userAccountId, Teaching teaching, String state) {
        Integer count = 0;
        for (Task task : teaching.getTaskCollection()) {
            if (taskService.getRawStudentsTaskState(userAccountId, task.getId()).equals(state)) {
                count++;
            }
        }
        return count;
    }

    private void generateUserAccountUsernameAndPassword(UserAccount userAccount) {
        String usernameBase = userAccount.getFirstname() + "." + userAccount.getSurname();
        String username = userAccount.getFirstname() + "." + userAccount.getSurname();
        int counter = 1;
        while(!em.createNamedQuery(UserAccount.FIND_USER_ACCOUNT_BY_USERNAME, UserAccount.class).setParameter("username", username).getResultList().isEmpty()) {
            username = usernameBase + counter++;
        }

        userAccount.setUsername(username);

        try {
            userAccount.setPassword(AuthService.encodeSHA256(userAccount.getUsername(), ""));
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        //em.persist(userAccount);
    }
}
