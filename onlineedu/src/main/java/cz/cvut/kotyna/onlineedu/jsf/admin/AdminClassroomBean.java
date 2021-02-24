package cz.cvut.kotyna.onlineedu.jsf.admin;

import cz.cvut.kotyna.onlineedu.entity.Classroom;
import cz.cvut.kotyna.onlineedu.entity.UserAccount;
import cz.cvut.kotyna.onlineedu.service.ClassroomService;
import cz.cvut.kotyna.onlineedu.service.LoginService;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named(value = "adminClassroomBean")
@ViewScoped
public class AdminClassroomBean implements Serializable {

    @EJB
    private ClassroomService classroomService;

    @Inject
    private AdminUserBackingBean adminUserBackingBean;

    @Getter @Setter
    private List<Classroom> allClassrooms;
    @Getter @Setter
    private Classroom classroom;
    @Getter @Setter
    private Integer classroomId;

    @PostConstruct
    public void init() {
        allClassrooms = new ArrayList<>(classroomService.getAllClassrooms());
        classroom = new Classroom();
    }

    public void initClassroom() {
        classroom = classroomService.findClassroom(classroomId);
    }

    public void initNewClassroom() {
        classroom = new Classroom();
    }

    public void saveClassroom() {
        if (classroom.getId() == null) {
            classroomService.saveClassroom(classroom);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Třída vytvořena"));
        }
        else {
            classroomService.saveClassroom(classroom);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Třída upravena"));
        }
        PrimeFaces.current().executeScript("PF('manageClassroomDialog').hide()");
        PrimeFaces.current().ajax().update("form");
        allClassrooms = new ArrayList<>(classroomService.getAllClassrooms());
        adminUserBackingBean.init();
    }
}
