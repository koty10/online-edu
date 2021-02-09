package cz.cvut.kotyna.onlineedu.service;

import cz.cvut.kotyna.onlineedu.entity.Chat;
import cz.cvut.kotyna.onlineedu.entity.Message;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class MessageService {

    // connection to the database
    @PersistenceContext
    EntityManager em;

    @EJB
    TeachingService teachingService;

    public Message findMessage(Integer messageId) {
        //em.getEntityManagerFactory().getCache().evictAll();
        return em.find(Message.class, messageId);
    }

    public void saveMessage(Message message) {
        em.persist(message);
    }

}
