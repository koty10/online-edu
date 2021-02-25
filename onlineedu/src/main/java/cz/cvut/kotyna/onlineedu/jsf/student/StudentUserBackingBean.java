package cz.cvut.kotyna.onlineedu.jsf.student;

import cz.cvut.kotyna.onlineedu.entity.Classroom;
import cz.cvut.kotyna.onlineedu.entity.Student;
import cz.cvut.kotyna.onlineedu.entity.Teaching;
import cz.cvut.kotyna.onlineedu.entity.UserAccount;
import cz.cvut.kotyna.onlineedu.model.listDataModel.teacher.classbook.StudentStatisticsModel;
import cz.cvut.kotyna.onlineedu.service.ClassroomService;
import cz.cvut.kotyna.onlineedu.service.LoginService;
import cz.cvut.kotyna.onlineedu.service.UserService;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Named(value = "studentUserBackingBean")
@ViewScoped
public class StudentUserBackingBean implements Serializable {

    @EJB
    private UserService userService;
    @EJB
    private LoginService loginService;


    public List<Student> getClassmates() {
        try {
            return userService.getClassmates(loginService.getLoggedInUser().getStudent().getClassroom().getId());
        } catch (NullPointerException e) {
            return new ArrayList<>();
        }
    }

    public Collection<Teaching> getTeachings() {
        return userService.getTeachings(loginService.getLoggedInUser().getStudent());
    }
}