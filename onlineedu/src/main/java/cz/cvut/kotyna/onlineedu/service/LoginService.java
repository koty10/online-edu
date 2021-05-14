/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kotyna.onlineedu.service;

import cz.cvut.kotyna.onlineedu.entity.Student;
import cz.cvut.kotyna.onlineedu.entity.UserAccount;

import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class LoginService {

    // connection to the database
    @PersistenceContext
    EntityManager em;

    public UserAccount getLoggedInUser() {
        try {
            Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
            if (principal != null) {
                return em.createNamedQuery(UserAccount.FIND_USER_ACCOUNT_BY_USERNAME, UserAccount.class).setParameter("username", principal.getName()).getSingleResult();
            } else {
                return null;
            }
        }
        catch(NoResultException e) {
            return null;
        }
    }
}
