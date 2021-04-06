package cz.cvut.kotyna.onlineedu.jsf.student;

import cz.cvut.kotyna.onlineedu.entity.Avatar;
import cz.cvut.kotyna.onlineedu.entity.UsersAvatar;
import cz.cvut.kotyna.onlineedu.jsf.StudentBean;
import cz.cvut.kotyna.onlineedu.model.listDataModel.student.avatar.StudentAvatarModel;
import cz.cvut.kotyna.onlineedu.service.AvatarService;
import cz.cvut.kotyna.onlineedu.service.LoginService;
import cz.cvut.kotyna.onlineedu.service.UsersAvatarService;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;

@Named(value = "studentStudentBean")
@ViewScoped
public class StudentStudentBean extends StudentBean implements Serializable {

    @EJB
    private LoginService loginService;

    @Inject
    private StudentAvatarBean studentAvatarBean;

    private UsersAvatar usersAvatar;
    private Collection<UsersAvatar> usersAvatars;

    @Override
    @PostConstruct
    public void initStudent() {
        student = loginService.getLoggedInUser().getStudent();
        usersAvatars = student.getUserAccount().getUsersAvatars().stream()
                .filter(studentsAvatar
                        -> studentsAvatar.getTimeTo().isAfter(LocalDateTime.now()))
                .collect(Collectors.toList());


        usersAvatar = usersAvatars.stream().filter(UsersAvatar::isActive)
                .findFirst().orElse(null);
    }

    public void changeAvatar() {
        for (UsersAvatar s : usersAvatars) {
            s.setActive(false);
        }
        usersAvatar.setActive(true);
        studentService.saveStudent(student);
    }

    public void changeAvatarFromList() {
        usersAvatar = student.getUserAccount().getUsersAvatars().stream()
                .filter(usersAvatar1 -> usersAvatar1.getAvatar().getId().equals(studentAvatarBean.getSelectedAvatar().getId()))
                .findFirst().orElse(null);
        changeAvatar();
    }

    public void changeAvatarToDefault() {
        for (UsersAvatar s : usersAvatars) {
            s.setActive(false);
        }
        studentService.saveStudent(student);
    }


    // Getters & Setters


    public UsersAvatar getUsersAvatar() {
        return usersAvatar;
    }

    public void setUsersAvatar(UsersAvatar usersAvatar) {
        this.usersAvatar = usersAvatar;
    }

    public Collection<UsersAvatar> getUsersAvatars() {
        return usersAvatars;
    }

    public void setUsersAvatars(Collection<UsersAvatar> usersAvatars) {
        this.usersAvatars = usersAvatars;
    }
}