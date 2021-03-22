/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kotyna.onlineedu.service;

import cz.cvut.kotyna.onlineedu.entity.Attempt;
import cz.cvut.kotyna.onlineedu.entity.Summary;
import cz.cvut.kotyna.onlineedu.entity.Task;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class SummaryService {

    // connection to the database
    @PersistenceContext
    EntityManager em;

    public Summary findSummary(Integer summaryId) {
        return em.find(Summary.class, summaryId);
    }

    public void updateSummary(Summary summary) {
        em.merge(summary);
    }

    public void createSummary(Summary summary) {
        em.persist(summary);
    }
}
