package br.com.pedidosonline.dao;

import br.com.pedidosonline.model.PedidoItem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.io.Serializable;

public class PedidoItemDAO implements Serializable {

    public PedidoItemDAO(){
    }

    public void incluir(PedidoItem pedidoitem) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        ResultSet rs = null;
        int update = 0;
        PreparedStatement pstm = null;
        String sql = "INSERT INTO PEDIDOS_ONLINE.PEDIDO_ITEM(IDPEDIDO,IDPRODUTO,QTD,VALOR_UNITARIO,DESCONTO,TOTAL,SEQUENCIA)"+
        "VALUES (?,?,?,?,?,?,?);";
        try {
              conexao = ConectaDB.getInstance().getConexao();
              pstm = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

              pstm.setInt(1,pedidoitem.getIdpedido());
              pstm.setInt(2,pedidoitem.getIdproduto());
              pstm.setDouble(3,pedidoitem.getQtd());
              pstm.setDouble(4,pedidoitem.getValorUnitario());
              pstm.setDouble(5,pedidoitem.getDesconto());
              pstm.setBigDecimal(6,pedidoitem.getTotal());
              pstm.setInt(7,pedidoitem.getSequencia());

              update = pstm.executeUpdate();
              rs = pstm.getGeneratedKeys();
              rs.next();
              pedidoitem.setIdpedidoitem(rs.getInt(1));


        }finally {
            if (pstm != null){
                pstm.close();
            }
            if (rs != null){
                rs.close();
            }

        }

    }

    public void alterar(PedidoItem pedidoitem) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        int update = 0;
        PreparedStatement pstm = null;
        String sql = "UPDATE PEDIDOS_ONLINE.PEDIDO_ITEM SET QTD= ?,VALOR_UNITARIO= ?,DESCONTO= ?,TOTAL= ?,SEQUENCIA= ?, IDPRODUTO = ? "
                    +"WHERE IDPEDIDOITEM = ?;";
        try {
              conexao = ConectaDB.getInstance().getConexao();
              pstm = conexao.prepareStatement(sql);

              pstm.setDouble(1,pedidoitem.getQtd());
              pstm.setDouble(2,pedidoitem.getValorUnitario());
              pstm.setDouble(3,pedidoitem.getDesconto());
              pstm.setBigDecimal(4,pedidoitem.getTotal());
              pstm.setInt(5,pedidoitem.getSequencia());
              pstm.setInt(6,pedidoitem.getIdproduto());
              pstm.setInt(7,pedidoitem.getIdpedidoitem());

              update = pstm.executeUpdate();


        }finally {
            if (pstm != null){
                pstm.close();
            }

        }

    }

    public PedidoItem GetObject(ResultSet rs) throws SQLException, ClassNotFoundException {

        PedidoItem pedidoitem = new PedidoItem();
        pedidoitem.setIdpedidoitem(rs.getInt("IDPEDIDOITEM"));
        pedidoitem.setIdpedido(rs.getInt("IDPEDIDO"));
        pedidoitem.setIdproduto(rs.getInt("IDPRODUTO"));
        pedidoitem.setQtd(rs.getDouble("QTD"));
        pedidoitem.setValorUnitario(rs.getDouble("VALOR_UNITARIO"));
        pedidoitem.setDesconto(rs.getDouble("DESCONTO"));
        pedidoitem.setTotal(rs.getBigDecimal("TOTAL"));
        pedidoitem.setSequencia(rs.getInt("SEQUENCIA"));
        return pedidoitem;

    }

    public List<PedidoItem> buscarTodos() throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        ResultSet rs = null;
        Statement stm = null;
        List<PedidoItem> itens = new ArrayList<>();

        String sql = "SELECT * FROM PEDIDOS_ONLINE.PEDIDO_ITEM";

        try {
              conexao = ConectaDB.getInstance().getConexao();
              stm = conexao.createStatement();
              rs = stm.executeQuery(sql);

              while(rs.next()){
                  PedidoItem pedidoitem = GetObject(rs);
                  itens.add(pedidoitem);
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
    
    public List<PedidoItem> buscarPorPedido(Integer idPedido) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        List<PedidoItem> itens = new ArrayList<>();

        String sql = "SELECT * FROM PEDIDOS_ONLINE.PEDIDO_ITEM WHERE IDPEDIDO = ?";

        try {
              conexao = ConectaDB.getInstance().getConexao();
              stm = conexao.prepareStatement(sql);
              stm.setInt(1, idPedido);
              rs = stm.executeQuery();

              while(rs.next()){
                  PedidoItem pedidoitem = GetObject(rs);
                  itens.add(pedidoitem);
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

    public PedidoItem buscarPorId(Integer IdPedidoItem)throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM PEDIDOS_ONLINE.PEDIDO_ITEM WHERE " 
         + "IDPEDIDOITEM = ?;";
        try {
              conexao = ConectaDB.getInstance().getConexao();
              pstm = conexao.prepareStatement(sql);

              pstm.setInt(1,IdPedidoItem);
              rs = pstm.executeQuery();
              rs.next();

              PedidoItem pedidoitem = GetObject(rs);

              return pedidoitem;

        }finally {
            if (pstm != null){
                pstm.close();
            }
            if (rs != null){
                rs.close();
            }

        }

    }
    public void excluir  (PedidoItem pedidoitem) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement pstm = null;
        String sql = "DELETE FROM PEDIDOS_ONLINE.PEDIDO_ITEM WHERE IDPEDIDOITEM = ?;";
        try {
              conexao = ConectaDB.getInstance().getConexao();
              pstm = conexao.prepareStatement(sql);

              pstm.setInt(1,pedidoitem.getIdpedidoitem());
              pstm.executeUpdate();

        }finally {
            if (pstm != null){
                pstm.close();
            }

        }

    }

}
