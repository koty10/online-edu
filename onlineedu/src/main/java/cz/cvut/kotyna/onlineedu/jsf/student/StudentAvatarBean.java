package cz.cvut.kotyna.onlineedu.jsf.student;

import cz.cvut.kotyna.onlineedu.entity.Avatar;
import cz.cvut.kotyna.onlineedu.entity.UserAccount;
import cz.cvut.kotyna.onlineedu.entity.UsersAvatar;
import cz.cvut.kotyna.onlineedu.model.listDataModel.student.avatar.StudentAvatarModel;
import cz.cvut.kotyna.onlineedu.service.AvatarService;
import cz.cvut.kotyna.onlineedu.service.LoginService;
import cz.cvut.kotyna.onlineedu.service.StudentService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Named(value = "studentAvatarBean")
@ViewScoped
public class StudentAvatarBean implements Serializable {

    @EJB
    private AvatarService avatarService;
    @EJB
    private LoginService loginService;

    @Inject
    private StudentStudentBean studentStudentBean;

    private List<StudentAvatarModel> allAvatars;
    private StudentAvatarModel selectedAvatar;

    @PostConstruct
    public void init() {
        List<Avatar> avatars = new ArrayList<>(avatarService.getAllAvatars());
        allAvatars = new ArrayList<>();
        for (Avatar a : avatars) {
            StudentAvatarModel model = new StudentAvatarModel();
            model.setId(a.getId());
            model.setName(a.getName());
            model.setPrice(a.getPricePerMonth());
            model.setImage("/avatars/" + a.getId());

            UsersAvatar optionalUsersAvatar = a.getUsersAvatars().stream()
                    .filter(studentsAvatar -> studentsAvatar.getUserAccount().getId().equals(loginService.getLoggedInUser().getId()))
                    .filter(studentsAvatar -> studentsAvatar.getTimeTo().isAfter(LocalDateTime.now()))
                    .findFirst()
                    .orElse(null);

            model.setActive(false);
            if (optionalUsersAvatar != null) {
                long days = ChronoUnit.DAYS.between(LocalDateTime.now(), optionalUsersAvatar.getTimeTo());
                long hours = ChronoUnit.HOURS.between(LocalDateTime.now(), optionalUsersAvatar.getTimeTo()) - (24 * days);
                long minutes = ChronoUnit.MINUTES.between(LocalDateTime.now(), optionalUsersAvatar.getTimeTo()) - (24 * 60 * days) - (60 * hours);


                model.setExpiration(days + " dní | " + hours + " hodin | " + minutes + " minut");
                model.setActive(optionalUsersAvatar.isActive());
            }
            allAvatars.add(model);
        }
        selectedAvatar = allAvatars.stream().filter(StudentAvatarModel::isActive).findFirst().orElse(null);
    }

    public void buyAvatar(Integer avatarId) {
        UserAccount userAccount = loginService.getLoggedInUser();
        Avatar avatar = avatarService.findAvatar(avatarId);
        if (userAccount.getStudent().getPoints() < avatar.getPricePerMonth()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nedostatek bodů", null));
            return;
        }
        avatarService.buyAvatar(userAccount, avatar);
        init();
        studentStudentBean.initStudent();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Prodlouženo", null));
    }


    // Getters & Setters


    public List<StudentAvatarModel> getAllAvatars() {
        return allAvatars;
    }

    public void setAllAvatars(List<StudentAvatarModel> allAvatars) {
        this.allAvatars = allAvatars;
    }

    public StudentAvatarModel getSelectedAvatar() {
        return selectedAvatar;
    }

    public void setSelectedAvatar(StudentAvatarModel selectedAvatar) {
        this.selectedAvatar = selectedAvatar;
    }
}
