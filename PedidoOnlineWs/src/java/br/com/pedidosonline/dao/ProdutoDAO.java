package br.com.pedidosonline.dao;

import br.com.pedidosonline.model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class ProdutoDAO implements Serializable {

    public ProdutoDAO(){
    }

    public void incluir(Produto produto) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        ResultSet rs = null;
        int update = 0;
        PreparedStatement pstm = null;
        String sql = "INSERT INTO PEDIDOS_ONLINE.PRODUTO(DESCRICAO_COMPLETA,DESCRICAO_RESUMIDA,UNIDADE,FATOR,VALOR_UNITARIO,COD_BARRAS)"+
        "VALUES (?,?,?,?,?,?);";
        try {
              conexao = ConectaDB.getInstance().getConexao();
              pstm = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

              pstm.setString(1,produto.getDescricaoCompleta());
              pstm.setString(2,produto.getDescricaoResumida());
              pstm.setString(3,produto.getUnidade());
              pstm.setDouble(4,produto.getFator());
              pstm.setDouble(5,produto.getValorUnitario());
              pstm.setString(6,produto.getCodBarras());

              update = pstm.executeUpdate();
              rs = pstm.getGeneratedKeys();
              rs.next();
              produto.setId(rs.getInt(1));


        }finally {
            if (pstm != null){
                pstm.close();
            }
            if (rs != null){
                rs.close();
            }

        }

    }

    public void alterar(Produto produto) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        int update = 0;
        PreparedStatement pstm = null;
        String sql = "UPDATE PEDIDOS_ONLINE.PRODUTO SET DESCRICAO_COMPLETA= ?,DESCRICAO_RESUMIDA= ?,UNIDADE= ?,FATOR= ?,VALOR_UNITARIO= ?,COD_BARRAS= ? "
                    +"WHERE IDPRODUTO = ?;";
        try {
              conexao = ConectaDB.getInstance().getConexao();
              pstm = conexao.prepareStatement(sql);

              pstm.setString(1,produto.getDescricaoCompleta());
              pstm.setString(2,produto.getDescricaoResumida());
              pstm.setString(3,produto.getUnidade());
              pstm.setDouble(4,produto.getFator());
              pstm.setDouble(5,produto.getValorUnitario());
              pstm.setString(6,produto.getCodBarras());
              pstm.setInt(7,produto.getId());

              update = pstm.executeUpdate();


        }finally {
            if (pstm != null){
                pstm.close();
            }

        }

    }

    public Produto GetObject(ResultSet rs) throws SQLException, ClassNotFoundException {

        Produto produto = new Produto();
        produto.setId(rs.getInt("IDPRODUTO"));
        produto.setDescricaoCompleta(rs.getString("DESCRICAO_COMPLETA"));
        produto.setDescricaoResumida(rs.getString("DESCRICAO_RESUMIDA"));
        produto.setUnidade(rs.getString("UNIDADE"));
        produto.setFator(rs.getDouble("FATOR"));
        produto.setValorUnitario(rs.getDouble("VALOR_UNITARIO"));
        produto.setCodBarras(rs.getString("COD_BARRAS"));
        return produto;

    }

    public List<Produto> buscarTodos() throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        ResultSet rs = null;
        Statement stm = null;
        List<Produto> itens = new ArrayList<>();

        String sql = "SELECT * FROM PEDIDOS_ONLINE.PRODUTO";

        try {
              conexao = ConectaDB.getInstance().getConexao();
              stm = conexao.createStatement();
              rs = stm.executeQuery(sql);

              while(rs.next()){
                  Produto produto = GetObject(rs);
                  itens.add(produto);
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

    public Produto buscarPorId(Integer IdProduto)throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM PEDIDOS_ONLINE.PRODUTO WHERE " 
         + "IDPRODUTO = ?;";
        try {
              conexao = ConectaDB.getInstance().getConexao();
              pstm = conexao.prepareStatement(sql);

              pstm.setInt(1,IdProduto);
              rs = pstm.executeQuery();
              rs.next();

              Produto produto = GetObject(rs);

              return produto;

        }finally {
            if (pstm != null){
                pstm.close();
            }
            if (rs != null){
                rs.close();
            }

        }

    }
    public void excluir  (Produto produto) throws SQLException, ClassNotFoundException {
        Connection conexao = null;
        PreparedStatement pstm = null;
        String sql = "DELETE FROM PEDIDOS_ONLINE.PRODUTO WHERE IDPRODUTO = ?;";
        try {
              conexao = ConectaDB.getInstance().getConexao();
              pstm = conexao.prepareStatement(sql);

              pstm.setInt(1,produto.getId());
              pstm.executeUpdate();

        }finally {
            if (pstm != null){
                pstm.close();
            }

        }

    }

}
