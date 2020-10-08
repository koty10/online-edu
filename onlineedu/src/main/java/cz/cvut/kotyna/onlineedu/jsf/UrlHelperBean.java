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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Named(value = "urlHelperBean")
@RequestScoped
public class UrlHelperBean {

    @EJB
    private UserService userService;

    @EJB
    private LoginService loginService;

    /**
     * Creates a new instance of UrlHelperBean
     */
    public UrlHelperBean() {
    }

    public boolean isCurrentPage(String pathLastPart) {
        String uri = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRequestURI();
        String[] pathParts = uri.split("/");
        return pathParts[pathParts.length - 1].equals(pathLastPart) || pathParts[pathParts.length - 1].equals(pathLastPart + ".xhtml");
    }
}
