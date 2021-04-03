/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kotyna.onlineedu.service;

import cz.cvut.kotyna.onlineedu.entity.Avatar;
import cz.cvut.kotyna.onlineedu.entity.Student;
import cz.cvut.kotyna.onlineedu.entity.StudentsAvatar;

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

    public void buyAvatar(Student student, Avatar avatar) {
        StudentsAvatar newOrExistingStudentsAvatar = student.getStudentsAvatars().stream()
                .filter(studentsAvatar -> studentsAvatar.getAvatar().equals(avatar))
                .findFirst()
                .orElse(new StudentsAvatar());

        if (newOrExistingStudentsAvatar.getId() == null) {
            newOrExistingStudentsAvatar.setActive(false);
            newOrExistingStudentsAvatar.setAvatar(avatar);
            newOrExistingStudentsAvatar.setStudent(student);
            newOrExistingStudentsAvatar.setTimeFrom(LocalDateTime.now());
            newOrExistingStudentsAvatar.setTimeTo(LocalDateTime.now().plusMonths(1));
            student.getStudentsAvatars().add(newOrExistingStudentsAvatar);
            avatar.getStudentsAvatars().add(newOrExistingStudentsAvatar);
            em.persist(newOrExistingStudentsAvatar);
            em.merge(student);
            em.merge(avatar);
        } else {
            newOrExistingStudentsAvatar.setTimeTo(newOrExistingStudentsAvatar.getTimeTo().plusMonths(1));
            em.merge(newOrExistingStudentsAvatar);
        }
    }

}
