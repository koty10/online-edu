package cz.cvut.kotyna.onlineedu.jsf.student;

import cz.cvut.kotyna.onlineedu.entity.Avatar;
import cz.cvut.kotyna.onlineedu.entity.StudentsAvatar;
import cz.cvut.kotyna.onlineedu.jsf.StudentBean;
import cz.cvut.kotyna.onlineedu.jsf.UrlHelperBean;
import cz.cvut.kotyna.onlineedu.jsf.teacher.TeacherTeachingBean;
import cz.cvut.kotyna.onlineedu.jsf.teacher.TeacherUserBackingBean;
import cz.cvut.kotyna.onlineedu.service.AvatarService;
import org.primefaces.PrimeFaces;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.*;
import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;

@Named(value = "studentStudentBean")
@ViewScoped
public class StudentStudentBean extends StudentBean implements Serializable {

    @EJB
    private AvatarService avatarService;

    @Inject
    private StudentUserBackingBean studentUserBackingBean;

    private StudentsAvatar studentsAvatar;
    private Collection<StudentsAvatar> studentsAvatars;

    @Override
    public void initStudent() {
        student = studentUserBackingBean.getUserAccount().getStudent();
        studentsAvatars = student.getStudentsAvatars().stream()
                .filter(studentsAvatar
                        -> studentsAvatar.getTimeFrom().isBefore(LocalDateTime.now())
                        && studentsAvatar.getTimeTo().isAfter(LocalDateTime.now()))
                .collect(Collectors.toList());


        studentsAvatar = studentsAvatars.stream().filter(StudentsAvatar::isActive)
                .findFirst().orElse(null);
    }

    public StreamedContent getImage() {
        if (studentsAvatar == null || studentsAvatar.getAvatar() == null || studentsAvatar.getAvatar().getBlob() == null) {
            try {
                InputStream inputStream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/images/avatar-woman.png");
                return DefaultStreamedContent.builder()
                        .contentType("image/png")
                        .stream(() -> inputStream)
                        .build();

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        try {
            return DefaultStreamedContent.builder()
                    .contentType(studentsAvatar.getAvatar().getFileExtension())
                    .stream(() -> new ByteArrayInputStream(studentsAvatar.getAvatar().getBlob()))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void changeAvatar() {
        for (StudentsAvatar s : studentsAvatars) {
            s.setActive(false);
        }
        studentsAvatar.setActive(true);
        saveStudent();
    }

    public StreamedContent getAvatarImage(Integer avatarId) {
        Avatar a = avatarService.findAvatar(avatarId);
        if (a == null) return null;
        try {
            return DefaultStreamedContent.builder()
                    .contentType(a.getFileExtension())
                    .stream(() -> new ByteArrayInputStream(a.getBlob()))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    // Getters & Setters


    public StudentsAvatar getStudentsAvatar() {
        return studentsAvatar;
    }

    public void setStudentsAvatar(StudentsAvatar studentsAvatar) {
        this.studentsAvatar = studentsAvatar;
    }

    public Collection<StudentsAvatar> getStudentsAvatars() {
        return studentsAvatars;
    }

    public void setStudentsAvatars(Collection<StudentsAvatar> studentsAvatars) {
        this.studentsAvatars = studentsAvatars;
    }
}