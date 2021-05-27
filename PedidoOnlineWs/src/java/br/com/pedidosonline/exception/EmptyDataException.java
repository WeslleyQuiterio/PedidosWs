/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidosonline.exception;

/**
 *
 * @author Weslley
 */
public class EmptyDataException extends RuntimeException{

    public EmptyDataException(String message) {
        super(message);
    }
    
}
