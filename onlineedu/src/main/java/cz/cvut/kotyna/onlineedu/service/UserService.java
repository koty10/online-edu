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
import java.text.Normalizer;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Stateless
public class UserService {

    // connection to the database
    @PersistenceContext
    EntityManager em;

    @EJB
    TaskService taskService;

    private static final Logger logger = Logger.getLogger(UserService.class.getName());

    public void saveUserAccount(UserAccount userAccount) {
        if (userAccount.getId() == null) {
            em.persist(userAccount);
        }
        else {
            em.merge(userAccount);
        }
    }

    public List<UserAccount> getAllUsers() {
        return em.createNamedQuery(UserAccount.FIND_ALL, UserAccount.class).getResultList();
    }

    public List<UserAccount> getAllAdmins() {
        return em.createNamedQuery(UserAccount.FIND_ALL, UserAccount.class).getResultList().stream()
                .filter(userAccount -> userAccount.getRole().equals("admin")).collect(Collectors.toList());
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

            Integer studentPoints = 0;

            List<Task> extraTasks = teaching.getTaskCollection().stream().filter(task -> (task.getType() != null && task.getType().equals("extra"))).collect(Collectors.toList());
            for (Task task : extraTasks) {
                List<Attempt> acceptedAttempts = task.getAttemptCollection().stream()
                        .filter(attempt -> attempt.getState().equals("accepted"))
                        .filter(attempt -> attempt.getStudent().equals(student))
                        .filter(distinctByKey(Attempt::getStudent))
                        .collect(Collectors.toList());
                // There should be none or only one attempt
                for (Attempt attempt : acceptedAttempts) {
                    if (attempt.getTask() != null && attempt.getTask().getPoints() != null) {
                        studentPoints += attempt.getTask().getPoints();
                    }
                }
            }

            studentStatisticsModel.setPoints(studentPoints);

            list.add(studentStatisticsModel);
        }
        return list;
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
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

    public UserAccount generateUserAccountUsernameAndPassword(UserAccount userAccount) {
        String usernameBase = normalizeString(userAccount.getFirstname() + "." + userAccount.getSurname());
        String username = usernameBase;
        int counter = 1;
        while(!em.createNamedQuery(UserAccount.FIND_USER_ACCOUNT_BY_USERNAME, UserAccount.class).setParameter("username", username).getResultList().isEmpty()) {
            username = usernameBase + counter++;
        }

        userAccount.setUsername(username);

        // If the user is created now
        if (userAccount.getPassword() == null) {
            try {
                userAccount.setPassword(AuthService.encodeSHA256(userAccount.getUsername(), ""));
            } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }

        return userAccount;
    }

    /**
     * Removes all diacritics from the string and sets to lowercase.
     */
    private String normalizeString(final String string) {
        if (string == null) {
            return null;
        }

        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");

        String temp = string.trim();
        temp = Normalizer.normalize(temp, Normalizer.Form.NFD);
        temp = pattern.matcher(temp).replaceAll("");
        temp = temp.replaceAll("[\uFEFF-\uFFFF]", ""); // remove 'ZERO WIDTH NO-BREAK SPACE' chars (U+FEFF)

        return temp.toLowerCase();
    }
}
