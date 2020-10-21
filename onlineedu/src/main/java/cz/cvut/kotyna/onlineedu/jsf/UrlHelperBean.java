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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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

    public boolean isCurrentTeaching(String teaching) {
        Map<String, String> map = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        if (!map.containsKey("teachingId")) return false;
        String value = map.get("teachingId");
        return value.equals(teaching);
    }

    public String getCurrentOrDefaultTeachingId() {
        Student loggedInStudent =  loginService.getLoggedInUser().getStudent();
        Map<String, String> map = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        if (!map.containsKey("teaching")) {
            Collection<Teaching> teachings = loggedInStudent.getClassroom().getTeachingCollection();
            if (!teachings.isEmpty()) {
                return teachings.stream().findFirst().get().getId().toString();
            }
            else {
                try {
                    // TODO !!!!! přidat stránku none.xhtml
                    FacesContext.getCurrentInstance().getExternalContext().redirect("student/none.xhtml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            return map.get("teaching");
        }
        return "";
    }
}
