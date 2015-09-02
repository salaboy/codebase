/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.codebase.services.exceptions;

/**
 *
 * @author salaboy
 */
public class ErrorMessage {
    private String error;
    private boolean isSecurityRelted;
    
    public ErrorMessage() {
    }

    public ErrorMessage(String error) {
        this.error = error;
    }

    public ErrorMessage(String error, boolean isSecurityRelted) {
        this.error = error;
        this.isSecurityRelted = isSecurityRelted;
    }
    
    public String getError() {
        return error;
    }

    public boolean isIsSecurityRelted() {
        return isSecurityRelted;
    }

    @Override
    public String toString() {
        return "ErrorMessage{" + "error=" + error + ", isSecurityRelted=" + isSecurityRelted + '}';
    }
    
    
}
