/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi.Controller;

import Application.Conexao;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import rmi.Interface.IControllerBase;
import rmi.Model.Cliente_has_produto;
import rmi.Util.ConexaoBD;

/**
 *
 * @author Admin
 */
public class Cliente_has_produtoController extends UnicastRemoteObject implements IControllerBase {

    private final String TABELA = "Cliente_has_Produto",IDCLIENTE = "Cliente_idCliente",IDPRODUTO = "Produto_idProduto",IDVENDA = "Venda_idVenda";
    
    
    
    @Override
    public String create(Object ordemServicoObj) {
        Cliente_has_produto chs = (Cliente_has_produto)ordemServicoObj;
       int retorno = 0;
       String erro = "";
          
        try{
            ConexaoBD conexao = new ConexaoBD();
            String sql ="INSERT INTO"+TABELA+"("+IDCLIENTE+","+IDPRODUTO+""+IDVENDA+") VALUES (?,?,?)";
            return erro = preparaPS(sql, chs, conexao);
            
        }catch(Exception e){
            erro += "Erro: \n"+e.getMessage();
        }
        
        return erro;    
    
    }
   
    @Override
    public Cliente_has_produto read(int idOrdemServico){
        Cliente_has_produto chp = new Cliente_has_produto();
        try{
            ConexaoBD conexao = new ConexaoBD();
            String sql = "SELECT * FROM "+TABELA+" WHERE idOrdemServico = "+idOrdemServico;
            ResultSet rs = conexao.sentenca.executeQuery(sql);
            while(rs.next()){
               chp = carregaCHP(rs);
            }
            
            rs.close();
            Conexao.closeConection(conexao);
            
            return chp;
            
        }catch(SQLException e){
           // JOptionPane.showMessageDialog(null,e.getMessage());
        }catch(Exception e){
            //JOptionPane.showMessageDialog(null,e.getMessage());
        }
        return chp;
    }

    @Override
    public String update(Object chp){
        Cliente_has_produto ordemServico = (Cliente_has_produto)chp;
        int retorno = 0;
        String erro = "";
        try{
            ConexaoBD conexao = new ConexaoBD();
            String sql = "UPDATE "+TABELA+" SET "+IDCLIENTE+" = ?, "+IDPRODUTO+" = ?, "+IDVENDA+" = ?";
            //PreparedStatement ps = conexao.connection.prepareStatement(sql);
            erro = preparaPS(sql, ordemServico, conexao);
            Conexao.closeConection(conexao);
        }catch(Exception e){
            erro += "Erro:\n"+e.getMessage();
        }
        
        return erro;
    }
     
    @Override
    public String delete(int idCliente){
        String erro = "";
       
        try{
            ConexaoBD conexao = new ConexaoBD();
            String sql = "DELETE FROM "+TABELA+" WHERE "+IDCLIENTE+" = ?";
            PreparedStatement ps = conexao.connection.prepareStatement(sql);
            ps.setInt(1, idCliente);
            ps.executeUpdate();
            ps.close();
            Conexao.closeConection(conexao);
            
            return "Ordem de serviço removida!";
        }catch(SQLException e){
            erro += "Erro: \n"+e.getMessage();
        }catch(Exception e){
            erro += "Erro: \n"+e.getMessage();
        }
        return erro;
    }

    private Cliente_has_produto carregaCHP(ResultSet rs) throws SQLException {
        Cliente_has_produto chp = new Cliente_has_produto();
         chp.setIdCliente(rs.getInt(IDCLIENTE));
         chp.setIdProduto(rs.getInt(IDPRODUTO));
         chp.setIdVenda(rs.getInt(IDVENDA));
        return chp;
    }


     @Override
    public Object findBy(String campo, Object valorProcurado){
        Cliente_has_produto chp  = new Cliente_has_produto();
        
        try{
            ConexaoBD conexao = new ConexaoBD();
            String sql = "SELECT * FROM "+TABELA+" WHERE "+campo.toLowerCase()+" = "+valorProcurado;
            ResultSet rs = conexao.sentenca.executeQuery(sql);
            while(rs.next()){
               chp = carregaCHP(rs);  
            }
            //fecha conexao
            rs.close();
            //metodo que fecha a conexao
            Conexao.closeConection(conexao);
            return chp;
        }catch(Exception e){
            //JOptionPane.showMessageDialog(null,"ERRO: \n"+e.getMessage());
            return chp;
        }
         
    }
    
    
    public ArrayList<Object> findByList(String campo, Object valor){
        ArrayList<Object> listaOs = new ArrayList();
        
        try{
            ConexaoBD conexao = new ConexaoBD();
            String sql = "SELECT * FROM "+TABELA+" WHERE "+campo.toLowerCase()+"="+valor;
            ResultSet rs = conexao.sentenca.executeQuery(sql);
            while(rs.next()){
                Cliente_has_produto chp = carregaCHP(rs);
                listaOs.add(chp);
            }
        }catch(Exception e){
           // JOptionPane.showMessageDialog(null,"ERRO: \n"+e.getMessage());
             return listaOs;
        }
        
        return listaOs;
    }


    
    //metodo criado pra reaproveitar o codigo no metodo create and update    
 private String preparaPS(String sql,Cliente_has_produto chs,ConexaoBD conexao){
       try{
           
       PreparedStatement ps = conexao.connection.prepareStatement(sql);
            ps.setInt(1, chs.getIdCliente());
            ps.setInt(2, chs.getIdProduto());
            ps.setInt(2, chs.getIdVenda());
            ps.executeUpdate();
            ps.close();
            Conexao.closeConection(conexao);
            return "Ordem de serviço atualizada!";
       }catch(Exception e){
           return ("Erro: \n"+e.getMessage());
       }
       
    }

    
}
