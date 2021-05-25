/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidosonline.model;

/**
 *
 * @author Weslley
 */
public class ErrorMessage {
    
    private String errrorMessage;
    private int errorCode;

    public ErrorMessage(String errrorMessage, int errorCode) {
        this.errrorMessage = errrorMessage;
        this.errorCode = errorCode;
    }

    public ErrorMessage() {
    }

    public String getErrrorMessage() {
        return errrorMessage;
    }

    public void setErrrorMessage(String errrorMessage) {
        this.errrorMessage = errrorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
    
    
    
    
}
