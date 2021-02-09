package cz.cvut.kotyna.onlineedu.service;

import cz.cvut.kotyna.onlineedu.entity.Chat;
import cz.cvut.kotyna.onlineedu.entity.Classroom;
import cz.cvut.kotyna.onlineedu.entity.Message;
import cz.cvut.kotyna.onlineedu.entity.Teaching;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ChatService {

    // connection to the database
    @PersistenceContext
    EntityManager em;

    @EJB
    TeachingService teachingService;

    public Chat findChat(Integer chatId) {
        //em.getEntityManagerFactory().getCache().evictAll();
        return em.find(Chat.class, chatId);
    }

    public Chat getChatByTeachingId(Integer teachingId) {
        List<Chat> chats = em.createNamedQuery("Chat.findByTeachingId", Chat.class).setParameter("id", teachingId).getResultList();
        if (chats.isEmpty()) {
            Chat newChat = new Chat();
            newChat.setTeaching(teachingService.findTeaching(teachingId));
            em.persist(newChat);
            return em.createNamedQuery("Chat.findByTeachingId", Chat.class).setParameter("id", teachingId).getSingleResult();
        }
        return chats.get(0);
    }

    public void saveChat(Chat chat) {
        em.merge(chat);
    }

}
