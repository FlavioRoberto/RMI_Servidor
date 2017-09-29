/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi.Controller;

import Util.ConexaoBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import rmi.Model.Produto;

/**
 *
 * @author Admin
 */

public class ProdutoController {
    
    public String create(Produto produto){
        String erro = "";
        int retorno = 0;
        try{
            ConexaoBD conexao = new ConexaoBD();
            String sql = "INSERT INTO produto(nome,preco) VALUES (?,?)";
            PreparedStatement ps = conexao.connection.prepareStatement(sql);
            ps.setString(1, produto.getNome());
            ps.setFloat(2, produto.getPreco());
            retorno =  ps.executeUpdate();
            ps.close();
            conexao.connection.close();
            return "Produto cadastrado!";
        }catch(Exception e){
            erro = "Erro: \n"+e.getMessage();
        }
        
        if(retorno  == 1){
            erro = "Erro na sentença Insert";
        }
        
        return erro;
    }

    public Produto read(int idProduto){
     Produto produto = new Produto();
       // String erro = "";
        try{
            ConexaoBD conexao = new ConexaoBD();
            String sql = "SELECT * FROM produto WHERE idProduto = "+idProduto;
            ResultSet rs = conexao.sentenca.executeQuery(sql);
            while(rs.next()){
                produto.setNome(rs.getString("nome"));
                produto.setPreco(rs.getFloat("preco"));
                produto.setIdProduto(rs.getInt("idProduto"));
            }
            
            rs.close();
            conexao.sentenca.close();
            conexao.connection.close();
            
        }catch(Exception e){
         //erro = "Erro: \n"+e.getMessage();
        }
     
        return produto;
    }

    public String update(Produto produto){
       String erro = "";
       int retorno = 0;
        try{
            ConexaoBD conexao = new ConexaoBD();
            String sql = "UPDATE produto SET nome = ?, preco = ? WHERE idProduto = ?";
            PreparedStatement ps = conexao.connection.prepareStatement(sql);
            ps.setString(1, produto.getNome());
            ps.setFloat(2, produto.getPreco());
            ps.setInt(3, produto.getIdProduto());
            retorno = ps.executeUpdate();
            ps.close();
            conexao.connection.close();
            
            return "Produto atualizado!";
        }catch(Exception e){
             erro = "Erro: \n"+e.getMessage();
        }
        
        if(retorno == 1){
            erro = "Erro na sentença update!";
        }
        
        return erro;
    }
    
    public String delete(int idProduto){
        int retorno = 0;
        String erro = "";
        try{
            ConexaoBD conexao = new ConexaoBD();
            String sql  = "DELETE FROM produto WHERE idProduto = ?";
            PreparedStatement ps = conexao.connection.prepareStatement(sql);
            ps.setInt(1, idProduto);
            retorno = ps.executeUpdate();
            ps.close();
            conexao.connection.close();
           
            return  "Produto removido!";
        }catch(Exception e){
            erro = "Erro: \n"+e.getMessage();
        }
        
        if(retorno == 1){
            erro = "Erro na sentença delete!";
        }
        
        return erro;
        
    }
}
