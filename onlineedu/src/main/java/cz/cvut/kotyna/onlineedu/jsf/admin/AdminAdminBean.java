package cz.cvut.kotyna.onlineedu.jsf.admin;

import cz.cvut.kotyna.onlineedu.entity.UserAccount;
import cz.cvut.kotyna.onlineedu.service.AuthService;
import cz.cvut.kotyna.onlineedu.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named(value = "adminAdminBean")
@ViewScoped
public class AdminAdminBean implements Serializable {

    @EJB
    private UserService userService;

    @Getter @Setter
    private List<UserAccount> allAdmins;
    @Getter @Setter
    private UserAccount admin;
    @Getter @Setter
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

        admin.setRole("admin");
        admin.setRegistered(new Date());

        // generate username and hashed password (same as username for testing purposes)
        admin = userService.generateUserAccountUsernameAndPassword(admin);

        userService.saveUserAccount(admin);

        if (admin.getId() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Administrátor vytvořen"));
        }
        else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Administrátor upraven"));
        }
        PrimeFaces.current().executeScript("PF('manageAdminDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-admins");
    }
}
