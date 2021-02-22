package cz.cvut.kotyna.onlineedu.jsf.teacher;

import cz.cvut.kotyna.onlineedu.entity.Attempt;
import cz.cvut.kotyna.onlineedu.entity.Task;
import cz.cvut.kotyna.onlineedu.service.*;
import lombok.Getter;
import lombok.Setter;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Named(value = "teacherAttemptBean")
@ViewScoped
public class TeacherAttemptBean implements Serializable {

    @EJB
    private AttemptService attemptService;
    @Inject
    private TeacherTeachingBean teacherTeachingBean;

    @Getter @Setter
    private Integer attemptId;
    @Getter
    private Attempt attempt;
    @Getter
    ListDataModel<Attempt> lastAttemptsListDataModel;


    public void initAttempt() {

        //set attempt
        if (attemptId == null) {
            String message = "Bad request. Please use a link from within the system.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
            return;
        }

        attempt = attemptService.findAttempt(attemptId);

        if (attempt == null) {
            String message = "Bad request. Unknown attempt.";
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
        }
    }

    public void acceptAttempt() {
        attemptService.acceptAttempt(attempt);
        FacesMessage msg = new FacesMessage("Schváleno");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void returnAttempt() {
        attemptService.returnAttempt(attempt);
        FacesMessage msg = new FacesMessage("Vráceno");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    // loads ListDataModel for dataTable on teachers task page
    public void loadLastAttemptsListDataModel() {
        List<Attempt> tmp = new ArrayList<>();
        for (Task t2 : teacherTeachingBean.getTeaching().getTaskCollection()) {
            tmp.addAll(t2.getAttemptCollection().stream()
                    .sorted((x, y) -> y.getTime().compareTo(x.getTime()))
                    .filter(distinctByKey(Attempt::getStudent))
                    .collect(Collectors.toList()));
        }
        tmp = tmp.stream()
                .sorted((x, y) -> y.getTime().compareTo(x.getTime()))
                .filter(x -> !x.getState().equals("accepted") && !x.getState().equals("returned"))
                .limit(10)
                .collect(Collectors.toList());

        lastAttemptsListDataModel = new ListDataModel<>(tmp);
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }
}