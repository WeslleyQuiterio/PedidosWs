package br.com.pedidosonline.dao;

import br.com.pedidosonline.model.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.io.Serializable;

public class PedidoDAO implements Serializable {

    public PedidoDAO(){
    }

    public void incluir(Pedido pedido) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        ResultSet rs = null;
        int update = 0;
        PreparedStatement pstm = null;
        String sql = "INSERT INTO PEDIDOS_ONLINE.PEDIDO(DATA_CRIACAO,SUB_TOTAL,DESCONTO,TOTAL,QTD_ITENS,CLIENTE_MESA_COMANDA)"+
        "VALUES (?,?,?,?,?,?);";
        try {
              conexao = ConectaDB.getInstance().getConexao();
              pstm = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

              pstm.setDate(1,pedido.getDataCriacao() != null ? new java.sql.Date(pedido.getDataCriacao().getTime()): null);
              pstm.setBigDecimal(2,pedido.getSubTotal());
              pstm.setBigDecimal(3,pedido.getDesconto());
              pstm.setBigDecimal(4,pedido.getTotal());
              pstm.setInt(5,pedido.getQtdItens());
              pstm.setString(6,pedido.getClienteMesaComanda());

              update = pstm.executeUpdate();
              rs = pstm.getGeneratedKeys();
              rs.next();
              pedido.setId(rs.getInt(1));


        }finally {
            if (pstm != null){
                pstm.close();
            }
            if (rs != null){
                rs.close();
            }

        }

    }

    public void alterar(Pedido pedido) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        int update = 0;
        PreparedStatement pstm = null;
        String sql = "UPDATE PEDIDOS_ONLINE.PEDIDO SET DATA_CRIACAO= ?,SUB_TOTAL= ?,DESCONTO= ?,TOTAL= ?,QTD_ITENS= ?,CLIENTE_MESA_COMANDA= ? "
                    +"WHERE IDPEDIDO = ?;";
        try {
              conexao = ConectaDB.getInstance().getConexao();
              pstm = conexao.prepareStatement(sql);

              pstm.setDate(1,pedido.getDataCriacao() != null ? new java.sql.Date(pedido.getDataCriacao().getTime()): null);
              pstm.setBigDecimal(2,pedido.getSubTotal());
              pstm.setBigDecimal(3,pedido.getDesconto());
              pstm.setBigDecimal(4,pedido.getTotal());
              pstm.setInt(5,pedido.getQtdItens());
              pstm.setString(6,pedido.getClienteMesaComanda());
              pstm.setInt(7,pedido.getId());

              update = pstm.executeUpdate();


        }finally {
            if (pstm != null){
                pstm.close();
            }

        }

    }

    public Pedido GetObject(ResultSet rs) throws SQLException, ClassNotFoundException {

        Pedido pedido = new Pedido();
        pedido.setId(rs.getInt("IDPEDIDO"));
        pedido.setDataCriacao(rs.getDate("DATA_CRIACAO") != null ? new Date(rs.getDate("DATA_CRIACAO").getTime()) : null);
        pedido.setSubTotal(rs.getBigDecimal("SUB_TOTAL"));
        pedido.setDesconto(rs.getBigDecimal("DESCONTO"));
        pedido.setTotal(rs.getBigDecimal("TOTAL"));
        pedido.setQtdItens(rs.getInt("QTD_ITENS"));
        pedido.setClienteMesaComanda(rs.getString("CLIENTE_MESA_COMANDA"));
        return pedido;

    }

    public List<Pedido> buscarTodos() throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        ResultSet rs = null;
        Statement stm = null;
        List<Pedido> itens = new ArrayList<>();

        String sql = "SELECT * FROM PEDIDOS_ONLINE.PEDIDO";

        try {
              conexao = ConectaDB.getInstance().getConexao();
              stm = conexao.createStatement();
              rs = stm.executeQuery(sql);

              while(rs.next()){
                  Pedido pedido = GetObject(rs);
                  itens.add(pedido);
              }

              return itens;

        }finally {
            if (stm != null){
                stm.close();
            }
            if (rs != null){
                rs.close();
            }

        }

    }

    public Pedido buscarPorId(Integer IdPedido)throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM PEDIDOS_ONLINE.PEDIDO WHERE " 
         + "IDPEDIDO = ?;";
        try {
              conexao = ConectaDB.getInstance().getConexao();
              pstm = conexao.prepareStatement(sql);

              pstm.setInt(1,IdPedido);
              rs = pstm.executeQuery();
              rs.next();

              Pedido pedido = GetObject(rs);

              return pedido;

        }finally {
            if (pstm != null){
                pstm.close();
            }
            if (rs != null){
                rs.close();
            }

        }

    }
    public void excluir  (Pedido pedido) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement pstm = null;
        String sql = "DELETE FROM PEDIDOS_ONLINE.PEDIDO WHERE IDPEDIDO = ?;";
        try {
              conexao = ConectaDB.getInstance().getConexao();
              pstm = conexao.prepareStatement(sql);

              pstm.setInt(1,pedido.getId());
              pstm.executeUpdate();

        }finally {
            if (pstm != null){
                pstm.close();
            }

        }

    }

}
