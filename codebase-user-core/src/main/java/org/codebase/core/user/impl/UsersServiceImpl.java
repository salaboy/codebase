/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this tpmplate file, choose Tools | Tpmplates
 * and open the tpmplate in the editor.
 */
package org.codebase.core.user.impl;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import org.codebase.core.user.api.UsersService;
import org.codebase.model.user.ServiceKey;
import org.codebase.model.user.User;
import org.codebase.core.exceptions.ServiceException;
import org.codebase.core.util.CodebaseUtil;
import org.codebase.core.util.PersistenceManager;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author salaboy
 */
@ApplicationScoped
public class UsersServiceImpl implements UsersService {

    @Inject
    private PersistenceManager pm;

    private final static Logger log = Logger.getLogger(UsersServiceImpl.class.getName());

    public UsersServiceImpl() {
    }

    @Override
    public Long newUser(User user) throws ServiceException {
        if (getByEmail(user.getEmail()) != null) {
            throw new ServiceException("User already registered with email: " + user.getEmail(), false);
        }
        user.setPassword(CodebaseUtil.hash(user.getPassword()));

        pm.persist(user);

        String key = generateWebKey(user.getEmail());
        log.log(Level.INFO, "User {0} registered. Service Key: {1}", new Object[]{user.getEmail(), key});
        return user.getId();
    }

    @Override
    public boolean exist(String email) {
        return (getByEmail(email) != null);
    }

    @Override
    public boolean exist(Long user_id) {
        return (getById(user_id) != null);
    }

    @Override
    public User getByEmail(String email) {
        try {
            return pm.createNamedQuery("User.getByEmail", User.class).setParameter("email", email).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }

    @Override
    public User getById(Long user_id) {
        try {
            return pm.find(User.class, user_id);
        } catch (NoResultException e) {
            return null;
        }

    }

    private String generateWebKey(String email) {
        String key = "webkey:" + email;
        log.log(Level.INFO, "Generating Key: {0}", key);
        pm.persist(new ServiceKey(key, email));
        return key;
    }

    @Override
    public String getKey(String serviceKey) {
        try {
            ServiceKey singleResult = pm.createNamedQuery("ServiceKey.getByKey", ServiceKey.class).setParameter("key", serviceKey).getSingleResult();
            if (singleResult != null) {
                return singleResult.getEmail();
            }
        } catch (NoResultException e) {
            return "";
        }
        return "";
    }

    @Override
    public boolean existKey(String serviceKey) {
        return (pm.createNamedQuery("ServiceKey.getByKey", ServiceKey.class).setParameter("key", serviceKey).getResultList().size() > 0);
    }

    @Override
    public void updateFirstName(Long user_id, String firstname) throws ServiceException {
        User u = pm.find(User.class, user_id);
        if (u == null) {
            throw new ServiceException("User doesn't exist: " + user_id);
        }
        u.setFirstname(firstname);
        pm.merge(u);
    }

    @Override
    public void updateLastName(Long user_id, String lastname) throws ServiceException {
        User u = pm.find(User.class, user_id);
        if (u == null) {
            throw new ServiceException("User doesn't exist: " + user_id);
        }
        u.setLastname(lastname);
        pm.merge(u);
    }

    @Override
    public void updateBio(Long user_id, String bio) throws ServiceException {
        User u = pm.find(User.class, user_id);
        if (u == null) {
            throw new ServiceException("User doesn't exist: " + user_id);
        }
        u.setBio(bio);
        pm.merge(u);
    }

    @Override
    public void updateFirstLogin(Long user_id) throws ServiceException {

        User u = pm.find(User.class, user_id);
        if (u == null) {
            throw new ServiceException("User doesn't exist: " + user_id);
        }
        u.setIsFirstLogin(false);
        pm.merge(u);

    }

    @Override
    public void updatePassword(Long user_id, String oldPassword, String newPassword) throws ServiceException {
        User u = pm.find(User.class, user_id);
        if (u == null) {
            throw new ServiceException("User doesn't exist: " + user_id);
        }
        if(u.getPassword().equals(CodebaseUtil.hash(oldPassword))){
            u.setPassword(CodebaseUtil.hash(newPassword));
            pm.merge(u);
        }else{
            throw new ServiceException("Old Password doesn't match. Password not updated! ");
        }
    }
    
    

    @Override
    public List<User> getAll() {
        return pm.createNamedQuery("User.getAll", User.class).getResultList();
    }

    @Override
    public List<User> getAllLive() {
        return pm.createNamedQuery("User.getAllLive", User.class).getResultList();
    }

    @Override
    public void updateBothNames(Long user_id, String firstname, String lastname) throws ServiceException {
        User u = pm.find(User.class, user_id);
        if (u == null) {
            throw new ServiceException("User doesn't exist: " + user_id);
        }
        u.setFirstname(firstname);
        u.setLastname(lastname);
        pm.merge(u);
    }

    @Override
    public void updateLive(Long user_id, String live) throws ServiceException {
        Boolean liveBoolean = Boolean.valueOf(live);
        User u = pm.find(User.class, user_id);
        if (u == null) {
            throw new ServiceException("User doesn't exist: " + user_id);
        }
        if (liveBoolean && calculateUserProfilePercentage(u) > 50) {
            u.setLive(liveBoolean);
            pm.merge(u);
        }
    }

    @Override
    public void removeUser(Long user_id) throws ServiceException {
        User u = pm.find(User.class, user_id);
        if (u == null) {
            throw new ServiceException("User doesn't exist: " + user_id);
        }
        pm.remove(u);
    }

    @Override
    public void updateLastLogin(Long user_id, Date date) throws ServiceException {
        User u = pm.find(User.class, user_id);
        if (u == null) {
            throw new ServiceException("User doesn't exist: " + user_id);
        }
        u.setLastLogin(date);
        pm.merge(u);
    }

    @Override
    public int calculateUserProfilePercentage(User u) {
        double increasingIndx = 0.0;
        double maxNumberOfWeight = 2.0;

        if (!(StringUtils.isEmpty(u.getFirstname()))) {
            increasingIndx += 2;
        }
        if (!(StringUtils.isEmpty(u.getLastname()))) {
            increasingIndx += 2;
        }

        return (int) ((increasingIndx / maxNumberOfWeight) * 100);
    }

}
