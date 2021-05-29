/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidosonline.service;

import br.com.pedidosonline.dao.ConectaDB;
import br.com.pedidosonline.dao.PedidoDAO;
import br.com.pedidosonline.dao.PedidoItemDAO;
import br.com.pedidosonline.dao.ProdutoDAO;
import br.com.pedidosonline.exception.DataNotFoundException;
import br.com.pedidosonline.exception.EmptyDataException;
import br.com.pedidosonline.model.Pedido;
import br.com.pedidosonline.model.PedidoItem;
import br.com.pedidosonline.model.Produto;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
    PedidoDAO pedidoDAO = new PedidoDAO();
    ProdutoDAO produtoDAO = new ProdutoDAO();
    PedidoItemDAO pedidoItemDAO = new PedidoItemDAO();

    /**
     * Creates a new instance of PedidosResource
     */
    public PedidosResource() {
    }

    /**
     * Retrieves representation of an instance of
     * br.com.pedidosonline.service.PedidosResource
     *
     * @return an instance of br.com.pedidosonline.model.Pedido
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pedido> getPedidos() {
        List<Pedido> pedidos = new ArrayList<>();
        try {
            pedidos = pedidoDAO.buscarTodos();

            for (Pedido pedido : pedidos) {
                pedido.setItens(pedidoItemDAO.buscarPorPedido(pedido.getIdpedido()));
                for (PedidoItem i : pedido.getItens()) {
                    i.setProduto(produtoDAO.buscarPorId(i.getIdproduto()));
                }
            }

            return pedidos;

        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * PUT method for updating or creating an instance of PedidosResource
     *
     * @param pedido representation for the resource
     * @return
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Pedido postPedido(Pedido pedido) {
        Connection conexao = null;
        try {

            if (pedido.getTotal() == null) {
                throw new EmptyDataException("Total de pedido vazio");
            }

            if (pedido.getItens() == null || pedido.getItens().isEmpty()) {
                throw new EmptyDataException("O Pedido deverá conter no mínimo 1 item");
            }

            for (PedidoItem i : pedido.getItens()) {

                Produto p = produtoDAO.buscarPorId(i.getIdproduto());
                if (p == null) {
                    throw new EmptyDataException("Não foi encontrado um produto com o id: " + i.getIdproduto());
                }
            }

            conexao = ConectaDB.getInstance().getConexao();
            conexao.setAutoCommit(false);

            pedidoDAO.incluir(pedido);

            for (PedidoItem i : pedido.getItens()) {
                i.setIdpedido(pedido.getIdpedido());
                pedidoItemDAO.incluir(i);
            }

            conexao.commit();

            return pedido;

        } catch (SQLException | ClassNotFoundException ex) {
            if (conexao != null) {
                try {
                    conexao.rollback();

                } catch (SQLException ex1) {
                    throw new RuntimeException(ex1);
                }
            }

            throw new RuntimeException(ex);
        } finally {
            if (conexao != null) {
                try {
                    conexao.setAutoCommit(true);

                } catch (SQLException ex1) {
                    throw new RuntimeException(ex1);
                }
            }
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{idPedido}")
    public Pedido putPedido(@PathParam("idPedido") Integer idPedido, Pedido pedido) {
        Connection conexao = null;
        try {

            Pedido ped = pedidoDAO.buscarPorId(pedido.getIdpedido());

            if (ped == null) {
                throw new EmptyDataException("Não foi encontrado um pedido com o id: " + idPedido);
            }

            if (pedido.getTotal() == null) {
                throw new EmptyDataException("Total de pedido vazio");
            }

            if (pedido.getItens() == null || pedido.getItens().isEmpty()) {
                throw new EmptyDataException("O Pedido deverá conter no mínimo 1 item");
            }

            for (PedidoItem i : pedido.getItens()) {

                Produto p = produtoDAO.buscarPorId(i.getIdproduto());
                if (p == null) {
                    throw new EmptyDataException("Não foi encontrado um produto com o id: " + i.getIdproduto());
                }
            }

            conexao = ConectaDB.getInstance().getConexao();
            conexao.setAutoCommit(false);

            pedidoDAO.alterar(pedido);

            for (PedidoItem i : pedido.getItens()) {
                i.setIdpedido(pedido.getIdpedido());
                if (i.getIdpedidoitem() == null || i.getIdpedidoitem() == 0) {
                    pedidoItemDAO.incluir(i);
                } else {
                    pedidoItemDAO.alterar(i);
                }

            }

            conexao.commit();

            return pedido;

        } catch (SQLException | ClassNotFoundException ex) {
            if (conexao != null) {
                try {
                    conexao.rollback();

                } catch (SQLException ex1) {
                    throw new RuntimeException(ex1);
                }
            }

            throw new RuntimeException(ex);
        } finally {
            if (conexao != null) {
                try {
                    conexao.setAutoCommit(true);

                } catch (SQLException ex1) {
                    throw new RuntimeException(ex1);
                }
            }
        }
    }

    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Path("/{idPedido}")
    public Pedido getPedido(@PathParam("idPedido") Integer idPedido) {

        try {
            Pedido pedido = pedidoDAO.buscarPorId(idPedido);
            if (pedido == null) {
                throw new DataNotFoundException("Pedido não encontrado com o id: " + idPedido);
            }

            pedido.setItens(pedidoItemDAO.buscarPorPedido(pedido.getIdpedido()));
            for (PedidoItem i : pedido.getItens()) {
                i.setProduto(produtoDAO.buscarPorId(i.getIdproduto()));
            }

            return pedido;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }

    }

    @DELETE
    @Path("/{idPedido}")
    public void deletePedido(@PathParam("idPedido") Integer idPedido) {
        Connection conexao = null;
        try {

            conexao = ConectaDB.getInstance().getConexao();
            conexao.setAutoCommit(false);
            Pedido pedido = pedidoDAO.buscarPorId(idPedido);
            if (pedido == null) {
                throw new DataNotFoundException("Pedido não encontrado com o id: " + idPedido);
            }
            pedidoItemDAO.excluirPorPedido(idPedido);
            pedidoDAO.excluir(pedido);
            conexao.commit();

        } catch (SQLException | ClassNotFoundException ex) {
            if (conexao != null) {
                try {
                    conexao.rollback();

                } catch (SQLException ex1) {
                    throw new RuntimeException(ex1);
                }
            }

            throw new RuntimeException(ex);
        } finally {
            if (conexao != null) {
                try {
                    conexao.setAutoCommit(true);

                } catch (SQLException ex1) {
                    throw new RuntimeException(ex1);
                }
            }
        }

    }
}
