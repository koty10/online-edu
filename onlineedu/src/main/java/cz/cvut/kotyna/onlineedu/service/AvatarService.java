/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kotyna.onlineedu.service;

import cz.cvut.kotyna.onlineedu.entity.Avatar;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class AvatarService {

    // connection to the database
    @PersistenceContext
    EntityManager em;

    public Avatar findAvatar(Integer avatarId) {
        return em.find(Avatar.class, avatarId);
    }

    public List<Avatar> getAllAvatars() {
        return em.createNamedQuery(Avatar.FIND_ALL, Avatar.class).getResultList();
    }

    public void saveAvatar(Avatar avatar) {
        if (avatar.getId() == null) {
            em.persist(avatar);
        }
        else {
            em.merge(avatar);
        }
    }

}
