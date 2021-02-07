package cz.cvut.kotyna.onlineedu.ws;

import javax.enterprise.context.ApplicationScoped;

import cz.cvut.kotyna.onlineedu.entity.Classroom;
import cz.cvut.kotyna.onlineedu.entity.Student;
import cz.cvut.kotyna.onlineedu.entity.Teacher;
import cz.cvut.kotyna.onlineedu.jsf.UserBackingBean;
import org.omnifaces.cdi.Push;
import org.omnifaces.cdi.PushContext;
import org.omnifaces.cdi.ViewScoped;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;

@Named(value = "chatBean")
@ApplicationScoped
public class ChatBean implements Serializable {
    @Inject
    @Push(channel = "messagePusher")
    private PushContext push;
    private String messageText;

    @Inject
    private UserBackingBean userBackingBean;

    /**
     * Initiates a notification to all Websocket clients. This method is used
     * for example 5-19.
     */
    /*
    public void sendMessage() {
        System.out.println("sending message");
        push.send(messageText);
        messageText = null;
    }
     */

    public void sendMessage() {
        Collection<Integer> recipientUserIds = userBackingBean.getClassroom().getStudentCollection().stream()
                .map(x -> x.getUserAccount().getId()).collect(Collectors.toList());
        Collection<Integer> teachersIds = userBackingBean.getClassroom().getTeacherCollection().stream()
                .map(x -> x.getUserAccount().getId()).collect(Collectors.toList());
        recipientUserIds.addAll(teachersIds);
        push.send(messageText, recipientUserIds);
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
}
