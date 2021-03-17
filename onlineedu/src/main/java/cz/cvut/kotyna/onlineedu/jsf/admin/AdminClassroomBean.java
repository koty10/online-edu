package cz.cvut.kotyna.onlineedu.jsf.admin;

import cz.cvut.kotyna.onlineedu.entity.Classroom;
import cz.cvut.kotyna.onlineedu.entity.Teacher;
import cz.cvut.kotyna.onlineedu.entity.UserAccount;
import cz.cvut.kotyna.onlineedu.service.ClassroomService;
import cz.cvut.kotyna.onlineedu.service.LoginService;
import cz.cvut.kotyna.onlineedu.service.TeacherService;
import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named(value = "adminClassroomBean")
@ViewScoped
public class AdminClassroomBean implements Serializable {

    @EJB
    private TeacherService teacherService;
    @EJB
    private ClassroomService classroomService;

    @Inject
    private AdminUserBackingBean adminUserBackingBean;

    @Inject
    private AdminTeacherBean adminTeacherBean;

    private List<Classroom> allClassrooms;
    private Classroom classroom;
    private Integer classroomId;

    @PostConstruct
    public void init() {
        allClassrooms = new ArrayList<>(classroomService.getAllClassrooms());
        classroom = new Classroom();
    }

    public void initClassroom() {
        classroom = classroomService.findClassroom(classroomId);
    }

    public void initNewClassroom() {
        classroom = new Classroom();
    }

    public void saveClassroom() {
        if (classroom.getId() == null) {
            classroomService.saveClassroom(classroom);
            if (classroom.getTeacher() != null) {
                classroom.getTeacher().setClassroom(classroom);
                teacherService.saveTeacher(classroom.getTeacher());
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Třída vytvořena"));
        }
        else {
            classroomService.saveClassroom(classroom);
            if (classroom.getTeacher() != null) {
                classroom.getTeacher().setClassroom(classroom);
                teacherService.saveTeacher(classroom.getTeacher());
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Třída upravena"));
        }
        PrimeFaces.current().executeScript("PF('manageClassroomDialog').hide()");
        PrimeFaces.current().ajax().update("form");
        allClassrooms = new ArrayList<>(classroomService.getAllClassrooms());
    }

    public List<Classroom> getClassroomsWithoutTeacher() {
        List<Classroom> classroomsWithoutTeacher = classroomService.getAllClassrooms().stream().filter(classroom -> classroom.getTeacher() == null).collect(Collectors.toList());
        Classroom c = adminTeacherBean.getTeacher().getClassroom();
        if (c != null) {
            classroomsWithoutTeacher.add(c);
        }
        return classroomsWithoutTeacher;
    }


    // Getters & Setters

    public List<Classroom> getAllClassrooms() {
        return allClassrooms;
    }

    public void setAllClassrooms(List<Classroom> allClassrooms) {
        this.allClassrooms = allClassrooms;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public Integer getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Integer classroomId) {
        this.classroomId = classroomId;
    }
}
