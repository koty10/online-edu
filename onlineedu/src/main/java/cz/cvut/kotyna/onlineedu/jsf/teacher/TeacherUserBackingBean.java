package cz.cvut.kotyna.onlineedu.jsf.teacher;

import cz.cvut.kotyna.onlineedu.entity.*;
import cz.cvut.kotyna.onlineedu.jsf.UrlHelperBean;
import cz.cvut.kotyna.onlineedu.model.listDataModel.teacher.classbook.StudentStatisticsModel;
import cz.cvut.kotyna.onlineedu.service.ClassroomService;
import cz.cvut.kotyna.onlineedu.service.LoginService;
import cz.cvut.kotyna.onlineedu.service.UserService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Named(value = "teacherUserBackingBean")
@ViewScoped
public class TeacherUserBackingBean implements Serializable {

    @EJB
    private UserService userService;
    @EJB
    private LoginService loginService;
    @EJB
    private ClassroomService classroomService;
    @Inject
    private TeacherTeachingBean teacherTeachingBean;
    @Inject
    private UrlHelperBean urlHelperBean;

    private Integer classroomId;
    private Classroom classroom;
    // used to create a new user
    private Integer userAccountId;
    private UserAccount userAccount;
    private ListDataModel<StudentStatisticsModel> studentStatisticsListDataModel;


    public void setClassroom() {

        // if classroomId is not set, then try to get it from teaching or get default
        if (classroomId == null) {
            if (teacherTeachingBean.getTeaching() != null) {
                classroomId = teacherTeachingBean.getTeaching().getClassroom().getId();
            }
            else {
                Classroom defaultClassroom = getDefaultTeacherClassroom();
                if (defaultClassroom != null) {
                    classroomId = defaultClassroom.getId();
                }
                else {
                    try {
                        final String contextPathForCurrentUser = urlHelperBean.getContextPathForCurrentUser();
                        FacesContext.getCurrentInstance().getExternalContext().redirect(contextPathForCurrentUser + "/user");
                        return;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
                }
            }
        }

        // Check if teacher tries to enter wrong classroomId. If so, set it to default value.
        UserAccount loggedInUser = loginService.getLoggedInUser();
        if (loggedInUser.getRole().equals("teacher")) {
            List<Integer> classroomIds = getClassroomsTeachedByCurrentTeacher().stream().map(Classroom::getId).collect(Collectors.toList());
            if (!classroomIds.contains(classroomId)) {
                classroomId = getDefaultTeacherClassroom().getId();
            }
        }

        classroom = classroomService.findClassroom(classroomId);

        if (classroom == null) {
            String message = "Bad request. Unknown classroom.";
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
        }

    }

    public boolean isCurrentClassroom(String classroomId) {
        if (this.classroomId == null) return false;
        return this.classroomId.toString().equals(classroomId);
    }

    public void initUserAccount() {
        if (userAccountId != null) {
            userAccount = userService.findUserAccount(userAccountId);
        } else {
            userAccount = loginService.getLoggedInUser();
        }
    }

    // used to create a new user account
    @PostConstruct
    public void initNewUserAccount() {
        userAccount = new UserAccount();
    }

    public void createStudent() {
        userService.createStudent(userAccount, classroom);
    }

    public ListDataModel<StudentStatisticsModel> getStudentStatisticsListDataModel() {
        if (studentStatisticsListDataModel == null) {
            studentStatisticsListDataModel = new ListDataModel<>(userService.getClassbookModel(teacherTeachingBean.getTeaching()));
        }
        return studentStatisticsListDataModel;
    }

    public void setStudentStatisticsListDataModel(ListDataModel<StudentStatisticsModel> studentStatisticsListDataModel) {
        this.studentStatisticsListDataModel = studentStatisticsListDataModel;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    // Used in the top nav for teacher
    public List<Classroom> getClassroomsTeachedByCurrentTeacher() {
        return loginService.getLoggedInUser().getTeacher().getTeachingCollection().stream().map(Teaching::getClassroom).distinct().collect(Collectors.toList());
    }

    // Used in the top nav for teacher
    public List<Teaching> getTeachersTeachingsInCurrentClassroom() {
        return this.classroom.getTeachingCollection().stream().filter(t -> t.getTeacher().getUserAccount().getId().equals(loginService.getLoggedInUser().getId())).filter(t -> t.getClassroom().getId().equals(classroomId)).collect(Collectors.toList());
    }

    public Classroom getDefaultTeacherClassroom() {
        List<Teaching> teachings = (List<Teaching>) loginService.getLoggedInUser().getTeacher().getTeachingCollection();
        if (!teachings.isEmpty()) {
            return teachings.get(0).getClassroom();
        }
        return null;
    }



    //FIXME smazat - jen pro testovani
    public List<String> getPasswordsHashed() {
        return loginService.getPasswordsHashed();
    }


    // Getters & Setters


    public Integer getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Integer classroomId) {
        this.classroomId = classroomId;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public Integer getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(Integer userAccountId) {
        this.userAccountId = userAccountId;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }
}
