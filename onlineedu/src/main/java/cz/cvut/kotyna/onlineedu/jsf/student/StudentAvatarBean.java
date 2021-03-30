package cz.cvut.kotyna.onlineedu.jsf.student;

import cz.cvut.kotyna.onlineedu.entity.Avatar;
import cz.cvut.kotyna.onlineedu.model.listDataModel.student.avatar.StudentAvatarModel;
import cz.cvut.kotyna.onlineedu.service.AvatarService;
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
import java.util.ArrayList;
import java.util.List;

@Named(value = "studentAvatarBean")
@ViewScoped
public class StudentAvatarBean implements Serializable {

    @EJB
    AvatarService avatarService;

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
            //TODO is active
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
