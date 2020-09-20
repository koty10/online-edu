package cz.cvut.kotyna.onlineedu.backing;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@RequestScoped
public class UserLogin {
    private String message = "Enter username and password.";

    public String logout() {
//        String oldId = FacesContext.getCurrentInstance().getExternalContext().getSessionId(true);
//        Principal oldPrincipal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        try {
            ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).logout();
        } catch (ServletException ex) {
            Logger.getLogger(UserLogin.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
//        String newId = FacesContext.getCurrentInstance().getExternalContext().getSessionId(true);
//        Principal newPrincipal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        return null;
    }

    public boolean getLoggedIn() {
        return FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal() != null;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        if (getLoggedIn()) {
            return FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        } else {
            return "Not logged in";
        }
    }
}
