/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi.Controller;

import Application.Conexao;
import Util.ConexaoBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import rmi.Model.Venda;

/**
 *
 * @author Admin
 */
public class VendaController {
    
    public String create(Venda venda){
        String erro = "";
        int retorno = 0;
        try{
            ConexaoBD conexao = new ConexaoBD();
            String sql = "INSERT INTO venda(quantidade) VALUES (?) ";
            PreparedStatement ps = conexao.connection.prepareStatement(sql);
            ps.setInt(1, venda.getQuantidade());
            retorno = ps.executeUpdate();
            ps.close();
            Conexao.closeConection(conexao);
            
            return "Venda cadastrada!";
        }catch(Exception e){
            erro = "Erro: \n"+e.getMessage();
        }
        
        if(retorno == 1){
            erro = "Erro na sentença Insert";
        }
        
        return erro;
    }
    
    public Venda read(int idVenda){
       Venda venda = new Venda();
        try{
            ConexaoBD conexao = new ConexaoBD();
            String sql = "SELECT * FROM venda WHERE idVenda = "+idVenda;
            ResultSet rs = conexao.sentenca.executeQuery(sql);
            while(rs.next()){
                venda.setQuantidade(rs.getInt("quantidade"));
                venda.setIdVenda(rs.getInt("idVenda"));
              }
           rs.close();
          Conexao.closeConection(conexao);
           return venda; 
        }catch(Exception e){
          return null;    
        }
    }

    public String update(Venda venda){
        int retorno = 0;
        String erro = "";
        try{
            ConexaoBD conexao = new ConexaoBD();
            String sql = "UPDATE venda set quantidade ="+venda.getQuantidade()+" WHERE idVenda = "+venda.getIdVenda();
            conexao.sentenca.execute(sql);
            Conexao.closeConection(conexao);
            return "Venda atualizada!";
           
        }catch(Exception e){
            erro = "Erro: \n"+e.getMessage();
        }
        
         if(retorno == 1){
             erro = "Erro na sentença update!";
            }
        
        return erro;
    }

    public String delete(int idVenda){
     String erro = "";
     int retorno = 0;
        try{
            ConexaoBD conexao = new ConexaoBD();
            String sql = "DELETE  FROM venda WHERE idVenda = ?";
            PreparedStatement ps = conexao.connection.prepareStatement(sql);
            ps.setInt(1, idVenda);
            retorno = ps.executeUpdate();
            ps.close();
            Conexao.closeConection(conexao);
            
            return "Venda excluida!";
            
        }catch(Exception e){
            erro = "Erro: \n"+e.getMessage();
        }
        
        if(retorno == 1){
            erro = "Erro na sentença Delete!";
        }
        
        return erro;
    }
}
