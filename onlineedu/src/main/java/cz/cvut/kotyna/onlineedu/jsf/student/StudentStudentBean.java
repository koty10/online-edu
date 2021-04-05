package cz.cvut.kotyna.onlineedu.jsf.student;

import cz.cvut.kotyna.onlineedu.entity.UsersAvatar;
import cz.cvut.kotyna.onlineedu.jsf.StudentBean;
import cz.cvut.kotyna.onlineedu.service.AvatarService;
import cz.cvut.kotyna.onlineedu.service.LoginService;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;

@Named(value = "studentStudentBean")
@ViewScoped
public class StudentStudentBean extends StudentBean implements Serializable {

    @EJB
    private AvatarService avatarService;
    @EJB
    private LoginService loginService;

    private UsersAvatar usersAvatar;
    private Collection<UsersAvatar> usersAvatars;

    @Override
    public void initStudent() {
        student = loginService.getLoggedInUser().getStudent();
        usersAvatars = student.getUserAccount().getUsersAvatars().stream()
                .filter(studentsAvatar
                        -> studentsAvatar.getTimeTo().isAfter(LocalDateTime.now()))
                .collect(Collectors.toList());


        usersAvatar = usersAvatars.stream().filter(UsersAvatar::isActive)
                .findFirst().orElse(null);
    }

    // Get active or default avatar on student profile page
    public StreamedContent getImage() {
        if (usersAvatar == null || usersAvatar.getAvatar() == null || usersAvatar.getAvatar().getBlob() == null) {
            try {
                InputStream inputStream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/images/avatar-woman.png");
                return DefaultStreamedContent.builder()
                        .contentType("image/png")
                        .stream(() -> inputStream)
                        .build();

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        try {
            return DefaultStreamedContent.builder()
                    .contentType(usersAvatar.getAvatar().getFileExtension())
                    .stream(() -> new ByteArrayInputStream(usersAvatar.getAvatar().getBlob()))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void changeAvatar() {
        for (UsersAvatar s : usersAvatars) {
            s.setActive(false);
        }
        usersAvatar.setActive(true);
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