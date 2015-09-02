/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.codebase.core.user.api;

import java.util.List;
import org.codebase.core.exceptions.ServiceException;
import org.codebase.model.user.User;

/**
 *
 * @author salaboy
 */
public interface UsersQueryService {

    List<User> search(Integer offset, Integer limit, List<String> exludesEmails) throws ServiceException;

}
