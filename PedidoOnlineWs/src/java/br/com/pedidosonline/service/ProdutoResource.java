/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidosonline.service;

import br.com.pedidosonline.model.Produto;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

import javax.ws.rs.PathParam;

/**
 * REST Web Service
 *
 * @author Weslley
 */
@Path("produtos")
public class ProdutoResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ProdutoResource
     */
    public ProdutoResource() {
    }

    /**
     * Retrieves representation of an instance of
     * br.com.pedidosonline.service.ProdutoResource
     *
     * @return an instance of br.com.pedidosonline.model.Produto
     */
    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public List<Produto> getProdutos() {
        //TODO return proper representation object
        Produto p = new Produto();
        List<Produto> produtos = new ArrayList<>();
        return produtos;
    }

    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Path("/{idProduto}")
    public Produto getProduto(@PathParam("id") Integer id) {
        //TODO return proper representation object
        Produto p = new Produto();
        p.setId(id);
        return p;
    }

    /**
     * PUT method for updating or creating an instance of ProdutoResource
     *
     * @param content representation for the resource
     */
    @POST
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Produto postProduto(Produto content) {
        
        return content;
    }
   
    @PUT
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Path("/{idProduto}")    
    public void patchProduto(Produto produto){
        
    }
    
    @DELETE
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Path("/{idProduto}")    
    public void deleteProduto(@PathParam("idProduto")Integer idProduto){
        
    }
}
