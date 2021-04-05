/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kotyna.onlineedu.service;

import cz.cvut.kotyna.onlineedu.entity.Avatar;
import cz.cvut.kotyna.onlineedu.entity.Student;
import cz.cvut.kotyna.onlineedu.entity.UserAccount;
import cz.cvut.kotyna.onlineedu.entity.UsersAvatar;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
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

    public void buyAvatar(UserAccount userAccount, Avatar avatar) {
        UsersAvatar newOrExistingUsersAvatar = userAccount.getUsersAvatars().stream()
                .filter(usersAvatar -> usersAvatar.getAvatar().equals(avatar))
                .findFirst()
                .orElse(new UsersAvatar());

        if (newOrExistingUsersAvatar.getId() == null) {
            newOrExistingUsersAvatar.setActive(false);
            newOrExistingUsersAvatar.setAvatar(avatar);
            newOrExistingUsersAvatar.setUserAccount(userAccount);
            newOrExistingUsersAvatar.setTimeTo(LocalDateTime.now().plusMonths(1));
            userAccount.getUsersAvatars().add(newOrExistingUsersAvatar);
            avatar.getUsersAvatars().add(newOrExistingUsersAvatar);
            em.persist(newOrExistingUsersAvatar);
            em.merge(avatar);
        } else {
            // If it is already expired
            if (newOrExistingUsersAvatar.getTimeTo().isBefore(LocalDateTime.now())) {
                newOrExistingUsersAvatar.setTimeTo(LocalDateTime.now());
            }
            newOrExistingUsersAvatar.setTimeTo(newOrExistingUsersAvatar.getTimeTo().plusMonths(1));
            em.merge(newOrExistingUsersAvatar);
        }
        if (userAccount.getRole().equals("student")) {
            userAccount.getStudent().setPoints(userAccount.getStudent().getPoints() - avatar.getPricePerMonth());
        }
        em.merge(userAccount);
    }

}
