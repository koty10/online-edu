package cz.cvut.kotyna.onlineedu.jsf.admin;

import cz.cvut.kotyna.onlineedu.entity.UserAccount;
import cz.cvut.kotyna.onlineedu.service.UserService;
import org.primefaces.PrimeFaces;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named(value = "adminAdminBean")
@ViewScoped
public class AdminAdminBean implements Serializable {

    @EJB
    private UserService userService;

    private List<UserAccount> allAdmins;
    private UserAccount admin;
    private Integer adminId;

    @PostConstruct
    public void init() {
        allAdmins = new ArrayList<>(userService.getAllAdmins());
        admin = new UserAccount();
    }

    public void initAdmin() {
        admin = userService.findUserAccount(adminId);
    }

    public void initNewAdmin() {
        admin = new UserAccount();
    }

    public void saveAdmin() {
        if (admin.getId() == null) {
            admin.setRole("admin");
            admin.setRegistered(new Date());
            // generate username and hashed password (same as username for testing purposes)
            admin = userService.generateUserAccountUsernameAndPassword(admin);
            userService.saveUserAccount(admin);
            // reload admin
            admin = userService.findUserAccount(admin.getId());
            allAdmins = new ArrayList<>(userService.getAllAdmins());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Administrátor vytvořen"));
        } else {
            userService.saveUserAccount(admin);
            // reload admin
            admin = userService.findUserAccount(admin.getId());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Administrátor upraven"));
        }
        PrimeFaces.current().executeScript("PF('manageAdminDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-admins");
    }


    // Getters & Setters

    public List<UserAccount> getAllAdmins() {
        return allAdmins;
    }

    public void setAllAdmins(List<UserAccount> allAdmins) {
        this.allAdmins = allAdmins;
    }

    public UserAccount getAdmin() {
        return admin;
    }

    public void setAdmin(UserAccount admin) {
        this.admin = admin;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }
}
