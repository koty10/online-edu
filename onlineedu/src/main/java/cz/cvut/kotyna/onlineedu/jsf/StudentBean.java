package cz.cvut.kotyna.onlineedu.jsf;

import cz.cvut.kotyna.onlineedu.entity.Student;
import cz.cvut.kotyna.onlineedu.entity.Summary;
import cz.cvut.kotyna.onlineedu.entity.UserAccount;
import cz.cvut.kotyna.onlineedu.service.StudentService;
import cz.cvut.kotyna.onlineedu.service.TeachingService;
import cz.cvut.kotyna.onlineedu.service.UserService;
import org.primefaces.PrimeFaces;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class StudentBean {

    @EJB
    protected StudentService studentService;
    @EJB
    protected UserService userService;
    @EJB
    protected TeachingService teachingService;

    protected List<Student> allStudents;
    protected Student student;
    protected Integer studentId;

    @PostConstruct
    public void init() {
        allStudents = new ArrayList<>(studentService.getAllStudents());
        student = new Student();
        student.setUserAccount(new UserAccount());
    }

    public void initStudent() {
        student = studentService.findStudent(studentId);
    }

    public void initNewStudent() {
        student = new Student();
        student.setUserAccount(new UserAccount());
    }

    public void saveStudent() {
        if (student.getId() == null) {
            student.getUserAccount().setRole("student");
            student.getUserAccount().setRegistered(new Date());
            // generate username and hashed password (same as username for testing purposes)
            student.setUserAccount(userService.generateUserAccountUsernameAndPassword(student.getUserAccount()));
            studentService.saveStudent(student);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Student vytvořen"));
        }
        else {
            studentService.saveStudent(student);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Student upraven"));
        }
    }

    public Summary getStudentSummaryForRhsTeaching(Integer teachingId) {
        if (student == null) return null;
        Optional<Summary> s = student.getSummaryCollection().stream().filter(x -> x.getTeaching().getId().equals(teachingId)).findFirst();
        if (s.isPresent()) {
            return s.get();
        }
        Summary summary = new Summary();
        summary.setStudent(student);
        summary.setTeaching(teachingService.findTeaching(teachingId));
        summary.setFeedback("");
        summary.setFinalGrade("");
        student.getSummaryCollection().add(summary);
        return summary;
    }

    // Getters & Setters


    public List<Student> getAllStudents() {
        return allStudents;
    }

    public void setAllStudents(List<Student> allStudents) {
        this.allStudents = allStudents;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
}
