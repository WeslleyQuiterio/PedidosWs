/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidosonline.service;

import br.com.pedidosonline.dao.ProdutoDAO;
import br.com.pedidosonline.exception.DataNotFoundException;
import br.com.pedidosonline.exception.EmptyDataException;
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
            throw new RuntimeException("Erro ao atualizar produtos, Detalhe: " + ex.getMessage(), ex);
        }

    }

    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Path("/{idProduto}")
    public Produto getProduto(@PathParam("idProduto") Integer idProduto) {     
   
        try { 
            
            if (idProduto == null || idProduto <= 0) {
                throw new EmptyDataException("id do produto inválido");
            }

            Produto p = produtoDAO.buscarPorId(idProduto);
            
            if (p == null){
               throw new DataNotFoundException("Nenhum produto encontrado com o id: " + idProduto);  
            }
            
            return p;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException("Erro ao buscar produto, Detalhe: " + ex.getMessage(), ex);
        }
      
    }

    /**
     * PUT method for updating or creating an instance of ProdutoResource
     *
     * @param produto    
     * @return 
     */
    @POST
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Produto postProduto(Produto produto) {

        try {
            if (produto != null && (produto.getDescricaoCompleta() == null || produto.getValorUnitario() == null)) {
                throw new EmptyDataException("Descrição e/ou valor unitários estão vazios, verifique e tente novamente");
            }
            produtoDAO.incluir(produto);
            return produto;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException("Erro ao incluir produto, Detalhe: " + ex.getMessage(), ex);
        }

    }

    @PUT
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Path("/{idProduto}")
    public void putProduto(@PathParam("idProduto") Integer idProduto, Produto produto) {
        try {
            Produto p = produtoDAO.buscarPorId(idProduto);
            if (p == null) {
                throw new DataNotFoundException("Produto não encontrado com o id: " + idProduto);
            }

            if (produto != null && (produto.getDescricaoCompleta() == null || produto.getValorUnitario() == null)) {
                throw new EmptyDataException("Descrição e/ou valor unitários estão vazios, verifique e tente novamente");
            }

            produtoDAO.alterar(produto);

        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException("Erro ao atualizar produto, Detalhe: " + ex.getMessage(), ex);
        }
    }

    @DELETE
    @Path("/{idProduto}")
    public void deleteProduto(@PathParam("idProduto") Integer idProduto) {

        try {

            if (idProduto == null || idProduto <= 0) {
                throw new EmptyDataException("id do produto inválido");
            }

            Produto p = produtoDAO.buscarPorId(idProduto);
            if (p == null) {
                throw new DataNotFoundException("Produto não encontrado com o id: " + idProduto);
            }
            produtoDAO.excluir(p);

        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException("Erro ao excluir produto, Detalhe: " + ex.getMessage(), ex);
        }
    }
}
