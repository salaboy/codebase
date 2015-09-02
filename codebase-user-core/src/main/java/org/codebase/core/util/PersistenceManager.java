/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.codebase.core.util;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author salaboy
 */
@ApplicationScoped
public class PersistenceManager {

    @Inject
    private EntityManager em;

    public void persist(Object o) {
        EntityTransaction transaction = null;
        try {
            transaction = em.getTransaction();
        } catch (Exception e) {
            // If it fails it means that you are in a CMT environment where the 
            //  container will take care of it. 
            //e.printStackTrace();
        }
        if (transaction != null) {
            transaction.begin();
        }
        em.persist(o);
        if (transaction != null) {
            transaction.commit();
        }
    }

    public <T> T merge(T t) {
        EntityTransaction transaction = null;
        try {
            transaction = em.getTransaction();
        } catch (Exception e) {
            // If it fails it means that you are in a CMT environment where the 
            //  container will take care of it. 
            //e.printStackTrace();
        }
        if (transaction != null) {
            transaction.begin();
        }
        T merge = em.merge(t);
        if (transaction != null) {
            transaction.commit();
        }
        return merge;
    }

    public Query createNamedQuery(String string) {
        return em.createNamedQuery(string);
    }

    public <T> TypedQuery<T> createNamedQuery(String string, Class<T> type) {
        return em.createNamedQuery(string, type);
    }

    public void remove(Object o) {
        EntityTransaction transaction = null;
        try {
            transaction = em.getTransaction();
        } catch (Exception e) {
            // If it fails it means that you are in a CMT environment where the 
            //  container will take care of it. 
            //e.printStackTrace();
        }
        if (transaction != null) {
            transaction.begin();
        }
        em.remove(o);
        if (transaction != null) {
            transaction.commit();
        }
    }

    public <T> T find(Class<T> type, Object o) {
        return em.find(type, o);
    }

    public EntityManager getEm() {
        return em;
    }
    
    

}
