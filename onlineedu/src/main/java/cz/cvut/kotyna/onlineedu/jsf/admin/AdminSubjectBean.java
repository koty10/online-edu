package cz.cvut.kotyna.onlineedu.jsf.admin;

import cz.cvut.kotyna.onlineedu.entity.Subject;
import cz.cvut.kotyna.onlineedu.entity.Teacher;
import cz.cvut.kotyna.onlineedu.entity.Teaching;
import cz.cvut.kotyna.onlineedu.service.SubjectService;
import cz.cvut.kotyna.onlineedu.service.TeachingService;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Named(value = "adminSubjectBean")
@ViewScoped
public class AdminSubjectBean implements Serializable {

    @EJB
    private SubjectService subjectService;

    public Collection<Subject> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

}