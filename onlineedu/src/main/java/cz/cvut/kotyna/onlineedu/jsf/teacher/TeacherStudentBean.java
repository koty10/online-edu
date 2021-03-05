package cz.cvut.kotyna.onlineedu.jsf.teacher;

import cz.cvut.kotyna.onlineedu.entity.Student;
import cz.cvut.kotyna.onlineedu.entity.Summary;
import cz.cvut.kotyna.onlineedu.entity.UserAccount;
import cz.cvut.kotyna.onlineedu.jsf.StudentBean;
import cz.cvut.kotyna.onlineedu.jsf.UrlHelperBean;
import cz.cvut.kotyna.onlineedu.service.StudentService;
import cz.cvut.kotyna.onlineedu.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

@Named(value = "teacherStudentBean")
@ViewScoped
public class TeacherStudentBean extends StudentBean implements Serializable {

    @Inject
    private UrlHelperBean urlHelperBean;
    @Inject
    private TeacherTeachingBean teacherTeachingBean;
    @Inject
    private TeacherUserBackingBean teacherUserBackingBean;

    @Override
    public void initStudent() {
        if (studentId == null) {
            try {
                final String contextPathForCurrentUser = urlHelperBean.getContextPathForCurrentUser();
                FacesContext.getCurrentInstance().getExternalContext().redirect(contextPathForCurrentUser + "/students.xhtml?teachingId=" + teacherTeachingBean.getTeachingId());
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        setStudent(studentService.findStudent(studentId));
    }

    @Override
    public void saveStudent() {
        if (student.getId() == null) {
            student.setClassroom(teacherUserBackingBean.getClassroom());
        }
        super.saveStudent();
        teacherTeachingBean.init();
        PrimeFaces.current().executeScript("PF('manageStudentDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-students");
    }
}