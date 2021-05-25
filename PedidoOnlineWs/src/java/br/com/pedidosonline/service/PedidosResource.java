/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidosonline.service;

import br.com.pedidosonline.model.Pedido;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Weslley
 */
@Path("pedidos")
public class PedidosResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PedidosResource
     */
    public PedidosResource() {
    }

    /**
     * Retrieves representation of an instance of br.com.pedidosonline.service.PedidosResource
     * @return an instance of br.com.pedidosonline.model.Pedido
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pedido> getPedidos(){        
        List<Pedido> pedidos = new ArrayList<>();
        
        return pedidos;
    }

    /**
     * PUT method for updating or creating an instance of PedidosResource
     * @param content representation for the resource
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void postPedido(Pedido content) {
        
    }
    
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{idPedido}")
    public Pedido getPedido(){
        return new Pedido();
    }
}
