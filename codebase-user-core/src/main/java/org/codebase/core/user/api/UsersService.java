/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.codebase.core.user.api;

import java.util.Date;
import java.util.List;
import org.codebase.model.user.User;
import org.codebase.shared.exceptions.ServiceException;

/**
 *
 * @author salaboy
 */
public interface UsersService {

    public Long newUser(User user) throws ServiceException;

    public boolean existKey(String serviceKey);

    public String getKey(String serviceKey);

    public boolean exist(String email);

    public boolean exist(Long user_id);

    public User getByEmail(String email);

    public User getById(Long user_id);

    public void updateFirstLogin(Long user_id) throws ServiceException;
    
    public void updatePassword(Long user_id, String oldPassword, String newPassword) throws ServiceException;

    public void updateFirstName(Long user_id, String firstname) throws ServiceException;

    public void updateLastName(Long user_id, String lastname) throws ServiceException;

    public void updateBio(Long user_id, String bio) throws ServiceException;

    public List<User> getAll();

    public List<User> getAllLive();

    public void updateBothNames(Long user_id, String firstname, String lastname) throws ServiceException;

    public void updateLive(Long user_id, String live) throws ServiceException;

    public void removeUser(Long user_id) throws ServiceException;

    public void updateLastLogin(Long user_id, Date date) throws ServiceException;

    public int calculateUserProfilePercentage(User u) throws ServiceException;

}
