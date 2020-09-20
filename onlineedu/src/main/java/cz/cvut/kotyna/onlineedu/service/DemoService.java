/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kotyna.onlineedu.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class DemoService {

    // connection to the database
    @PersistenceContext
    EntityManager em;

    public String getGreetings() {
        return "Hello from DemoService";
    }
}
