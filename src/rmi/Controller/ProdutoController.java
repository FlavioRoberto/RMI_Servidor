/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi.Controller;

import Application.Conexao;
import Util.ConexaoBD;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import rmi.Interface.IControllerBase;
import rmi.Model.OrdemServico;
import rmi.Model.Pessoa;
import rmi.Model.Produto;

/**
 *
 * @author Admin
 */

public class ProdutoController extends UnicastRemoteObject implements IControllerBase {
    
    public ProdutoController()throws RemoteException{}
    
    @Override
    public String create(Object produtoObj){
        Produto produto = (Produto) produtoObj;
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
            Conexao.closeConection(conexao);
            return "Produto cadastrado!";
        }catch(Exception e){
            erro = "Erro: \n"+e.getMessage();
        }
        
        if(retorno  == 1){
            erro = "Erro na sentença Insert";
        }
        
        return erro;
    }

    @Override
    public Produto read(int idProduto){
     Produto produto = new Produto();
       // String erro = "";
        try{
            ConexaoBD conexao = new ConexaoBD();
            String sql = "SELECT * FROM produto WHERE idProduto = "+idProduto;
            ResultSet rs = conexao.sentenca.executeQuery(sql);
            while(rs.next()){
               produto = carregaProduto(rs);
            }
            
            rs.close();
            Conexao.closeConection(conexao);
            
        }catch(Exception e){
         //erro = "Erro: \n"+e.getMessage();
        }
     
        return produto;
    }

    @Override
    public String update(Object produtoObj){
       Produto produto = (Produto)produtoObj; 
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
            Conexao.closeConection(conexao);
            
            return "Produto atualizado!";
        }catch(Exception e){
             erro = "Erro: \n"+e.getMessage();
        }
        
        if(retorno == 1){
            erro = "Erro na sentença update!";
        }
        
        return erro;
    }
    
    @Override
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
            Conexao.closeConection(conexao);
           
            return  "Produto removido!";
        }catch(Exception e){
            erro = "Erro: \n"+e.getMessage();
        }
        
        if(retorno == 1){
            erro = "Erro na sentença delete!";
        }
        
        return erro;
        
    }

    private Produto carregaProduto(ResultSet rs) throws SQLException {
          Produto produto = new Produto();
           produto.setNome(rs.getString("nome"));
                produto.setPreco(rs.getFloat("preco"));
                produto.setIdProduto(rs.getInt("idProduto"));
           return produto;     
    }
    
    
    @Override
    public Object findBy(String campo, Object valorProcurado){
        Produto produto  = new Produto();
        
        try{
            ConexaoBD conexao = new ConexaoBD();
            String sql = "SELECT * FROM produto WHERE "+campo.toLowerCase()+" = "+valorProcurado;
            ResultSet rs = conexao.sentenca.executeQuery(sql);
            while(rs.next()){
               produto = carregaProduto(rs);  
            }
            //fecha conexao
            rs.close();
            //metodo que fecha a conexao
            Conexao.closeConection(conexao);
            return produto;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"ERRO: \n"+e.getMessage());
            return produto;
        }
        
    }
    
    
    public ArrayList<Object> findByList(String campo, Object valor){
        ArrayList<Object> produtos = new ArrayList();
        
        try{
            ConexaoBD conexao = new ConexaoBD();
            String sql = "SELECT * FROM produto WHERE "+campo.toLowerCase()+"="+valor;
            ResultSet rs = conexao.sentenca.executeQuery(sql);
            while(rs.next()){
                Produto produto = carregaProduto(rs);
                produtos.add(produto);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"ERRO: \n"+e.getMessage());
             return produtos;
        }
        
        return produtos;
    }
}
