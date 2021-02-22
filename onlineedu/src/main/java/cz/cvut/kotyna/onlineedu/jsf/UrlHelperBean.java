package cz.cvut.kotyna.onlineedu.jsf;

import cz.cvut.kotyna.onlineedu.entity.*;
import cz.cvut.kotyna.onlineedu.service.LoginService;
import cz.cvut.kotyna.onlineedu.service.UserService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@Named(value = "urlHelperBean")
@RequestScoped
public class UrlHelperBean {

    @EJB
    private LoginService loginService;

    public boolean isCurrentPage(String pathLastPart) {
        String uri = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRequestURI();
        String[] pathParts = uri.split("/");
        return Arrays.stream(pathParts).anyMatch(part -> part.equals(pathLastPart) || part.equals(pathLastPart + ".xhtml"));
        //return pathParts[pathParts.length - 1].equals(pathLastPart) || pathParts[pathParts.length - 1].equals(pathLastPart + ".xhtml");
    }

    public String getContextPathForCurrentUser() {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/" + loginService.getLoggedInUser().getRole();
    }
}
