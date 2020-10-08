package cz.cvut.kotyna.onlineedu.jsf;

import cz.cvut.kotyna.onlineedu.entity.Task;
import cz.cvut.kotyna.onlineedu.service.LoginService;
import cz.cvut.kotyna.onlineedu.service.TeachingService;
import cz.cvut.kotyna.onlineedu.service.UserService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Map;

@Named(value = "teachingBean")
@RequestScoped
public class TeachingBean {

    @EJB
    private UserService userService;

    @EJB
    private LoginService loginService;

    @EJB
    private TeachingService teachingService;

    private String teachingId;

    /**
     * Creates a new instance of TeachingBean
     */
    public TeachingBean() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        teachingId = params.get("teaching");
    }

    public Collection<Task> getTasks() {
        return teachingService.getTasks(teachingId);
    }
}
