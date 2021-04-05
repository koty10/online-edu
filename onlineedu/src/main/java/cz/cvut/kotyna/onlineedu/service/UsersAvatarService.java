/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kotyna.onlineedu.service;

import cz.cvut.kotyna.onlineedu.entity.UsersAvatar;
import cz.cvut.kotyna.onlineedu.entity.UserAccount;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Stateless
public class UsersAvatarService {

    // connection to the database
    @PersistenceContext
    EntityManager em;

    public UsersAvatar findUsersAvatar(Integer avatarId) {
        return em.find(UsersAvatar.class, avatarId);
    }

    public List<UsersAvatar> getAllUsersAvatars() {
        return em.createNamedQuery(UsersAvatar.FIND_ALL, UsersAvatar.class).getResultList();
    }

    public void saveUsersAvatar(UsersAvatar usersAvatar) {
        if (usersAvatar.getId() == null) {
            em.persist(usersAvatar);
        }
        else {
            em.merge(usersAvatar);
        }
    }
}
