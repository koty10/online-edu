package cz.cvut.kotyna.onlineedu.jsf.admin;

import cz.cvut.kotyna.onlineedu.entity.Avatar;
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
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named(value = "adminAvatarBean")
@ViewScoped
public class AdminAvatarBean implements Serializable {

    @EJB
    AvatarService avatarService;

    private List<Avatar> allAvatars;
    private Avatar avatar;
    private Integer avatarId;

    @PostConstruct
    public void init() {
        allAvatars = new ArrayList<>(avatarService.getAllAvatars());
        avatar = new Avatar();
    }

    public void initNewAvatar() {
        avatar = new Avatar();
    }

    public void saveAvatar() {
        if (avatar.getBlob() == null || avatar.getFileExtension() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Obrázek je povinné pole","Obrázek je povinné pole"));
            return;
        }

        if (avatar.getId() == null) {
            avatarService.saveAvatar(avatar);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Avatar vytvořen"));
        }
        else {
            avatarService.saveAvatar(avatar);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Avatar upraven"));
        }
        PrimeFaces.current().executeScript("PF('manageAvatarDialog').hide()");
        PrimeFaces.current().ajax().update("form");
        allAvatars = new ArrayList<>(avatarService.getAllAvatars());
    }

    public void handleFileUpload(FileUploadEvent fileUploadEvent) {
        avatar.setBlob(fileUploadEvent.getFile().getContent());
        avatar.setFileExtension(fileUploadEvent.getFile().getContentType());
    }



    public StreamedContent getImage() {
        if (avatar.getBlob() == null) return null;
        try {
            return DefaultStreamedContent.builder()
                    .contentType(avatar.getFileExtension())
                    .stream(() -> new ByteArrayInputStream(avatar.getBlob()))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    // Getters & Setters

    public List<Avatar> getAllAvatars() {
        return allAvatars;
    }

    public void setAllAvatars(List<Avatar> allAvatars) {
        this.allAvatars = allAvatars;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public Integer getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(Integer avatarId) {
        this.avatarId = avatarId;
    }
}
