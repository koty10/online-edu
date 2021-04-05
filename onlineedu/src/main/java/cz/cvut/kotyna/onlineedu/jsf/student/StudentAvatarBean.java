package cz.cvut.kotyna.onlineedu.jsf.student;

import cz.cvut.kotyna.onlineedu.entity.Avatar;
import cz.cvut.kotyna.onlineedu.entity.UsersAvatar;
import cz.cvut.kotyna.onlineedu.model.listDataModel.student.avatar.StudentAvatarModel;
import cz.cvut.kotyna.onlineedu.service.AvatarService;
import cz.cvut.kotyna.onlineedu.service.LoginService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
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
            model.setImage("/avatars/" + a.getId());

            UsersAvatar optionalUsersAvatar = a.getUsersAvatars().stream()
                    .filter(studentsAvatar -> studentsAvatar.getUserAccount().getId().equals(loginService.getLoggedInUser().getId()))
                    .filter(studentsAvatar -> studentsAvatar.getTimeTo().isAfter(LocalDateTime.now()))
                    .findFirst()
                    .orElse(null);

            model.setActive(false);
            if (optionalUsersAvatar != null) {
                model.setExpiration(optionalUsersAvatar.getTimeToFormatted());
                model.setActive(optionalUsersAvatar.isActive());
            }
            allAvatars.add(model);
        }
    }

    public void buyAvatar(Integer avatarId) {
        avatarService.buyAvatar(loginService.getLoggedInUser(), avatarService.findAvatar(avatarId));
        init();
    }


    // Getters & Setters


    public List<StudentAvatarModel> getAllAvatars() {
        return allAvatars;
    }

    public void setAllAvatars(List<StudentAvatarModel> allAvatars) {
        this.allAvatars = allAvatars;
    }
}
