package cz.cvut.kotyna.onlineedu.jsf;

import cz.cvut.kotyna.onlineedu.entity.Student;
import cz.cvut.kotyna.onlineedu.entity.Summary;
import cz.cvut.kotyna.onlineedu.entity.UserAccount;
import cz.cvut.kotyna.onlineedu.service.StudentService;
import cz.cvut.kotyna.onlineedu.service.UserService;
import lombok.Getter;
import lombok.Setter;
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

    @Getter
    @Setter
    protected List<Student> allStudents;
    @Getter @Setter
    protected Student student;
    @Getter @Setter
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
        PrimeFaces.current().executeScript("PF('manageStudentDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-students");
    }

    public String getStudentFinalGradeForRhsTeaching(Integer teachingId) {
        if (student == null) return "";
        Optional<Summary> s = student.getSummaryCollection().stream().filter(x -> x.getTeaching().getId().equals(teachingId)).findFirst();
        if (s.isPresent()) {
            return s.get().getFinalGrade();
        }
        return "";
    }

}
