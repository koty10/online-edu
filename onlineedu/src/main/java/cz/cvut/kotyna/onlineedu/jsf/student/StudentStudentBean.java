package cz.cvut.kotyna.onlineedu.jsf.student;

import cz.cvut.kotyna.onlineedu.entity.Avatar;
import cz.cvut.kotyna.onlineedu.entity.StudentsAvatar;
import cz.cvut.kotyna.onlineedu.jsf.StudentBean;
import cz.cvut.kotyna.onlineedu.jsf.UrlHelperBean;
import cz.cvut.kotyna.onlineedu.jsf.teacher.TeacherTeachingBean;
import cz.cvut.kotyna.onlineedu.jsf.teacher.TeacherUserBackingBean;
import org.primefaces.PrimeFaces;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.*;
import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Named(value = "studentStudentBean")
@ViewScoped
public class StudentStudentBean extends StudentBean implements Serializable {

    @Inject
    StudentUserBackingBean studentUserBackingBean;

    private Avatar avatar;

    @Override
    public void initStudent() {
        student = studentUserBackingBean.getUserAccount().getStudent();
        avatar = student.getStudentsAvatars().stream()
                .filter(studentsAvatar
                        -> studentsAvatar.getTimeFrom().isBefore(LocalDateTime.now())
                        && studentsAvatar.getTimeTo().isAfter(LocalDateTime.now())
                        && studentsAvatar.isActive())
                .map(StudentsAvatar::getAvatar)
                .findFirst().orElse(null);
    }

    public StreamedContent getImage() {
        if (avatar == null || avatar.getBlob() == null) {
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
                    .contentType(avatar.getFileExtension())
                    .stream(() -> new ByteArrayInputStream(avatar.getBlob()))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}