/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pedidosonline.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Weslley
 */
public class ConectaDB {

    Connection conexao;
    String Driver;

    private ConectaDB() {
    }

    public static ConectaDB getInstance() {
        return ConectaMySqlHolder.INSTANCE;
    }

    private static class ConectaMySqlHolder {

        private static final ConectaDB INSTANCE = new ConectaDB();
    }

    public Connection getConexao() throws ClassNotFoundException, SQLException {

        String url = "jdbc:mysql://localhost:3306/peidos_online";
        Driver = "com.mysql.jdbc.Driver";
        Class.forName(Driver);

        if (conexao == null || conexao.isClosed()) {
            conexao = DriverManager.getConnection(url, "pedidos", "123456");
        }

        return conexao;

        //return null;
    }

    public void closeConexao() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                System.out.println("fechando conexao");
                conexao.close();
            }

        } catch (SQLException ex) {
            System.out.println("falha ao encerrar conexao: " + ex);
        } catch (NullPointerException e) {
            System.out.println("conxao nula");
        }

    }
}
