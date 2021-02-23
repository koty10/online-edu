package cz.cvut.kotyna.onlineedu.jsf.admin;

import cz.cvut.kotyna.onlineedu.entity.Classroom;
import cz.cvut.kotyna.onlineedu.entity.UserAccount;
import cz.cvut.kotyna.onlineedu.service.ClassroomService;
import cz.cvut.kotyna.onlineedu.service.LoginService;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.component.datatable.DataTable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named(value = "adminClassroomBean")
@ViewScoped
public class AdminClassroomBean implements Serializable {

    @EJB
    private ClassroomService classroomService;

    @Getter @Setter
    private List<Classroom> allClassrooms;
    @Getter @Setter
    private Classroom classroom;
    @Getter @Setter
    private Integer classroomId;

    @PostConstruct
    public void init() {
        allClassrooms = new ArrayList<>(classroomService.getAllClassrooms());
    }

    public void initClassroom() {
        classroom = classroomService.findClassroom(classroomId);
    }

}
