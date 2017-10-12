/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoBD {
    public Connection connection;
    public Statement sentenca;
    public ConexaoBD(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/dersos";
            String usuario = "root";
            String senha = "engenhariacomputacao";
            connection = DriverManager.getConnection(url,usuario,senha);
            sentenca = connection.createStatement();
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver não encontrado!\n"+ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Erro de conexao com Banco!\n"+ex.getMessage());
        }
    }
}
