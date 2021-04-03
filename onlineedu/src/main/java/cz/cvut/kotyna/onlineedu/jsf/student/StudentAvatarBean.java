package cz.cvut.kotyna.onlineedu.jsf.student;

import cz.cvut.kotyna.onlineedu.entity.Avatar;
import cz.cvut.kotyna.onlineedu.entity.StudentsAvatar;
import cz.cvut.kotyna.onlineedu.model.listDataModel.student.avatar.StudentAvatarModel;
import cz.cvut.kotyna.onlineedu.service.AvatarService;
import cz.cvut.kotyna.onlineedu.service.LoginService;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Named(value = "studentAvatarBean")
@ViewScoped
public class StudentAvatarBean implements Serializable {

    @EJB
    AvatarService avatarService;
    @EJB
    LoginService loginService;

    private List<StudentAvatarModel> allAvatars;

    @PostConstruct
    public void init() {
        List<Avatar> avatars = new ArrayList<>(avatarService.getAllAvatars());
        allAvatars = new ArrayList<>();
        for (Avatar a : avatars) {
            StudentAvatarModel model = new StudentAvatarModel();
            model.setId(a.getId());
            model.setName(a.getName());
            model.setPrice(a.getPricePerMonth());
            model.setImage(DefaultStreamedContent.builder()
                    .contentType(a.getFileExtension())
                    .stream(() -> new ByteArrayInputStream(a.getBlob()))
                    .build());

            StudentsAvatar optionalStudentsAvatar = a.getStudentsAvatars().stream()
                    .filter(studentsAvatar -> studentsAvatar.getStudent().getId().equals(loginService.getLoggedInUser().getStudent().getId()))
                    .filter(studentsAvatar -> studentsAvatar.getTimeTo().isAfter(LocalDateTime.now()))
                    .findFirst()
                    .orElse(null);

            model.setActive(false);
            if (optionalStudentsAvatar != null) {
                model.setExpiration(optionalStudentsAvatar.getTimeTo());
                model.setActive(optionalStudentsAvatar.isActive());
            }
            allAvatars.add(model);
        }
    }


    // Getters & Setters


    public List<StudentAvatarModel> getAllAvatars() {
        return allAvatars;
    }

    public void setAllAvatars(List<StudentAvatarModel> allAvatars) {
        this.allAvatars = allAvatars;
    }
}
