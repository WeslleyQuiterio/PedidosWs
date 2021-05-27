/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidosonline.exception;

import br.com.pedidosonline.model.ErrorMessage;
import com.google.gson.Gson;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Weslley
 */
@Provider
public class EmptyDataExceptionMapper implements ExceptionMapper<EmptyDataException>{

    @Override
    public Response toResponse(EmptyDataException e) {
         ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), 400);
         Gson gson = new Gson();
         String msg = gson.toJson(errorMessage);
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(msg)
                .build();
    }
    
}
