package cz.cvut.kotyna.onlineedu.ws;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.cvut.kotyna.onlineedu.entity.*;
import cz.cvut.kotyna.onlineedu.jsf.UserBackingBean;
import cz.cvut.kotyna.onlineedu.service.ChatService;
import cz.cvut.kotyna.onlineedu.service.MessageService;
import cz.cvut.kotyna.onlineedu.service.TeachingService;
import org.omnifaces.cdi.Push;
import org.omnifaces.cdi.PushContext;
import org.omnifaces.cdi.ViewScoped;
import org.primefaces.shaded.json.JSONObject;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Named(value = "chatBean")
@ApplicationScoped
public class ChatBean implements Serializable {

    @EJB
    private ChatService chatService;

    @EJB
    private MessageService messageService;

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

    public void sendMessage(Integer teachingId) {
        Chat chat = chatService.getChatByTeachingId(teachingId);
        Message message = new Message(messageText, new Date(), chat, userBackingBean.getLoggedInUser());
        messageService.saveMessage(message);


        String json = "";
        ObjectMapper mapper = new ObjectMapper();
        try {
            json = mapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        messageText = null;
        push.send(json, teachingId);
        push.send("someEvent", teachingId);
        messageText = null;
    }

    public Collection<Message> loadMessages(Integer teachingId) {
        return chatService.getChatByTeachingId(teachingId).getMessageCollection().stream().sorted((x, y) -> y.getTime().compareTo(x.getTime())).collect(Collectors.toList());
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
}
