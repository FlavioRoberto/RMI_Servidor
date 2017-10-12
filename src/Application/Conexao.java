/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import rmi.Util.ConexaoBD;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class Conexao {
 
    public static void closeConection(final ConexaoBD conexao) throws SQLException{
        conexao.sentenca.close();
        conexao.connection.close();
    }
    
}
