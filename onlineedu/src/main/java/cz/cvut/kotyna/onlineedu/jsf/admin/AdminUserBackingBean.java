package cz.cvut.kotyna.onlineedu.jsf.admin;

import cz.cvut.kotyna.onlineedu.entity.Teacher;
import cz.cvut.kotyna.onlineedu.entity.Teaching;
import cz.cvut.kotyna.onlineedu.entity.UserAccount;
import cz.cvut.kotyna.onlineedu.service.UserService;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Named(value = "adminUserBackingBean")
@ViewScoped
public class AdminUserBackingBean implements Serializable {

    @EJB
    UserService userService;

    @Inject
    AdminClassroomBean adminClassroomBean;

    public List<Teacher> getTeachersWithoutClassroom() {
        return userService.getAllTeachers().stream().filter(x -> x.getClassroom() == null).collect(Collectors.toList());
    }

    public List<Teacher> getTeachersWithoutClassroomIncludingCurrentTeacher() {
        List<Teacher> teachers = userService.getAllTeachers().stream().filter(x -> x.getClassroom() == null).collect(Collectors.toList());
        teachers.add(adminClassroomBean.getClassroom().getTeacher());
        return teachers;
    }
}
