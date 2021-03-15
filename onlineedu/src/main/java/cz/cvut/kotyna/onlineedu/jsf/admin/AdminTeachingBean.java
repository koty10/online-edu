package cz.cvut.kotyna.onlineedu.jsf.admin;

import cz.cvut.kotyna.onlineedu.entity.Classroom;
import cz.cvut.kotyna.onlineedu.entity.Teacher;
import cz.cvut.kotyna.onlineedu.entity.Teaching;
import cz.cvut.kotyna.onlineedu.entity.UserAccount;
import cz.cvut.kotyna.onlineedu.service.TeacherService;
import cz.cvut.kotyna.onlineedu.service.TeachingService;
import cz.cvut.kotyna.onlineedu.service.UserService;
import lombok.Getter;
import lombok.Setter;
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

@Named(value = "adminTeachingBean")
@ViewScoped
public class AdminTeachingBean implements Serializable {

    @EJB
    private TeachingService teachingService;

    @Getter @Setter
    private Teaching teaching;
    @Getter @Setter
    private Integer teachingId;

    public void initTeaching() {
        teaching = teachingService.findTeaching(teachingId);
    }

    @PostConstruct
    public void initNewTeaching() {
        teaching = new Teaching();
    }

    public void saveTeaching(Teacher teacher) {

        teaching.setTeacher(teacher);

        if (teaching.getId() == null) {
            teachingService.saveTeaching(teaching);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Výuka vytvořena"));
        }
        else {
            teachingService.saveTeaching(teaching);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Výuka upravena"));
        }
        PrimeFaces.current().executeScript("PF('manageTeachingDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-teachings");
    }

}