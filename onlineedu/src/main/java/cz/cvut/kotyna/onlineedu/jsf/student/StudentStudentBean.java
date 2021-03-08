package cz.cvut.kotyna.onlineedu.jsf.student;

import cz.cvut.kotyna.onlineedu.jsf.StudentBean;
import cz.cvut.kotyna.onlineedu.jsf.UrlHelperBean;
import cz.cvut.kotyna.onlineedu.jsf.teacher.TeacherTeachingBean;
import cz.cvut.kotyna.onlineedu.jsf.teacher.TeacherUserBackingBean;
import org.primefaces.PrimeFaces;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

@Named(value = "studentStudentBean")
@ViewScoped
public class StudentStudentBean extends StudentBean implements Serializable {

    @Inject
    StudentUserBackingBean studentUserBackingBean;

    @Override
    public void initStudent() {
        student = studentUserBackingBean.getUserAccount().getStudent();
    }
}