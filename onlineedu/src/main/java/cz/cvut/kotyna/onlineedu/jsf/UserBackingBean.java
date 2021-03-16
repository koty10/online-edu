package cz.cvut.kotyna.onlineedu.jsf;

import cz.cvut.kotyna.onlineedu.entity.*;
import cz.cvut.kotyna.onlineedu.model.listDataModel.teacher.classbook.StudentStatisticsModel;
import cz.cvut.kotyna.onlineedu.service.AuthService;
import cz.cvut.kotyna.onlineedu.service.ClassroomService;
import cz.cvut.kotyna.onlineedu.service.LoginService;
import cz.cvut.kotyna.onlineedu.service.UserService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.ArrayDataModel;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;

@Named(value = "userBackingBean")
@ViewScoped
public class UserBackingBean implements Serializable {

    @EJB
    private UserService userService;
    @EJB
    private LoginService loginService;

    private String currentPassword;
    private String newPassword;
    private String newPasswordRepeated;

    public List<UserAccount> getAllUsers() {
        return userService.getAllUsers();
    }

    public List<Student> getAllStudents() {
        return userService.getAllStudents();
    }

    public List<Parent> getAllParents() {
        return userService.getAllParents();
    }

    public List<Teacher> getAllTeachers() {
        return userService.getAllTeachers();
    }

    public UserAccount getLoggedInUser() {
        return loginService != null ? loginService.getLoggedInUser() : null;
    }

    public void changePassword(UserAccount userAccount) {
        String currentPasswordHashed = "";
        String newPasswordHashed = "";

        try {
            currentPasswordHashed = AuthService.encodeSHA256(currentPassword, "");
            newPasswordHashed = AuthService.encodeSHA256(newPassword, "");
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return;
        }

        if (!currentPasswordHashed.equals(userAccount.getPassword())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Chybné heslo", "Zadané současné heslo je chybné!"));
        }
        else if (!newPassword.equals(newPasswordRepeated)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Různá hesla", "Zadaná nová hesla se neshodují!"));
        }
        else {
            userAccount.setPassword(newPasswordHashed);
            userService.saveUserAccount(userAccount);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Heslo změněno", "Heslo bylo úspěšně změněno!"));
        }
    }

    //FIXME smazat - jen pro testovani
    public List<String> getPasswordsHashed() {
        return loginService.getPasswordsHashed();
    }


    // Getters & Setters


    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordRepeated() {
        return newPasswordRepeated;
    }

    public void setNewPasswordRepeated(String newPasswordRepeated) {
        this.newPasswordRepeated = newPasswordRepeated;
    }
}
