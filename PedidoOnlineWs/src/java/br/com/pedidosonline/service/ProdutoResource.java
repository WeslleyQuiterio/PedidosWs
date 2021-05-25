/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidosonline.service;

import br.com.pedidosonline.dao.ProdutoDAO;
import br.com.pedidosonline.exception.DataNotFoundException;
import br.com.pedidosonline.model.Produto;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private ProdutoDAO produtoDAO = new ProdutoDAO();

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
        List<Produto> produtos = new ArrayList<>();
        try {
            produtos = produtoDAO.buscarTodos();
            return produtos;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProdutoResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return produtos;
    }

    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Path("/{idProduto}")
    public Produto getProduto(@PathParam("idProduto") Integer id) {
        //TODO return proper representation object
        Produto p = new Produto();
        try {
            p = produtoDAO.buscarPorId(id);
        } catch (SQLException | ClassNotFoundException ex) {
            
            Logger.getLogger(ProdutoResource.class.getName()).log(Level.SEVERE, null, ex);
        }
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

        try {
            produtoDAO.incluir(content);
            return content;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProdutoResource.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @PUT
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Path("/{idProduto}")
    public void putProduto(@PathParam("idProduto") Integer idProduto, Produto produto) {
        try {
            Produto p = produtoDAO.buscarPorId(idProduto);
            if (p == null){
                throw new DataNotFoundException("Produto não encontrado com o id: " + idProduto);
            }
            
            produtoDAO.alterar(produto);
            
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException("Erro ao atualizar produto, Detalhe: " + ex.getMessage(), ex);
        }
    }

    @DELETE
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Path("/{idProduto}")
    public void deleteProduto(@PathParam("idProduto") Integer idProduto) {

    }
}
