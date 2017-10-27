
import java.sql.SQLException;
import rmi.Util.ConexaoBD;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class limpaBanco {
    
    public static void execute() throws SQLException{
        ConexaoBD conexao = new ConexaoBD();
        conexao.sentenca.execute("DELETE FROM  carrinho");
        conexao.sentenca.execute("delete from  venda");
        conexao.sentenca.execute("delete from  cliente");
        conexao.sentenca.execute("delete from  funcionario");
        conexao.sentenca.execute("delete from pessoa");
    }
}
