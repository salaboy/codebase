/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.codebase.core.exceptions;

import java.io.Serializable;

/**
 *
 * @author salaboy
 */
public class ServiceException extends Exception implements Serializable {

    private boolean isSecurityRelated = false;

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(boolean isSecurityRelated) {
        super();
        this.isSecurityRelated = isSecurityRelated;
    }

    public ServiceException(String message, boolean isSecurityRelated) {
        super(message);
        this.isSecurityRelated = isSecurityRelated;
    }

    public ServiceException(String message, Throwable cause, boolean isSecurityRelated) {
        super(message, cause);
        this.isSecurityRelated = isSecurityRelated;
    }

    public boolean isIsSecurityRelated() {
        return isSecurityRelated;
    }

    
}
