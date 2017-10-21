/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi.Controller;

import Application.Conexao;
import rmi.Util.ConexaoBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import rmi.Interface.IControllerBase;
import rmi.Model.Venda;

/**
 *
 * @author Admin
 */
public class VendaController implements IControllerBase{
    
    private final String IDVENDA = "idVenda",QUANTIDADE ="quantidade",
            IDPRODUTO = "produto_idProduto",IDCLIENTE = "cliente_idCliente";
    
    @Override
    public String create(Object vendaObj){
        Venda venda = (Venda)vendaObj;
        String erro = "";
        int retorno = 0;
        try{
            ConexaoBD conexao = new ConexaoBD();
            String sql = "INSERT INTO venda("+QUANTIDADE+","+IDPRODUTO+","+IDCLIENTE+") VALUES (?,?,?) ";
            PreparedStatement ps = conexao.connection.prepareStatement(sql);
            ps.setInt(1, venda.getQuantidade());
            ps.setInt(2,venda.getProduto_idProduto());
            ps.setInt(3, venda.getCliente_idCliente());
            retorno = ps.executeUpdate();
            ps.close();
            Conexao.closeConection(conexao);
            
            return "Venda cadastrada!";
        }catch(Exception e){
            erro = "Erro: \n"+e.getMessage();
        }
        
        if(retorno == 1){
            erro = "Erro na sentença Insert";
            return erro;
        }
        return erro;
        
        
    }
    
    @Override
    public Venda read(int idVenda){
       Venda venda = new Venda();
        try{
            ConexaoBD conexao = new ConexaoBD();
            String sql = "SELECT * FROM venda WHERE idVenda = "+idVenda;
            ResultSet rs = conexao.sentenca.executeQuery(sql);
            while(rs.next()){
               venda = carregaVenda(rs);
              }
           rs.close();
          Conexao.closeConection(conexao);
           return venda; 
        }catch(Exception e){
          return null;    
        }
    }

    @Override
    public String update(Object vendaObj){
        Venda venda = (Venda) vendaObj;
        int retorno = 0;
        String erro = "";
        try{
            ConexaoBD conexao = new ConexaoBD();
            String sql = "UPDATE venda set "+QUANTIDADE+"="+venda.getQuantidade()+","+IDPRODUTO+"="+venda.getProduto_idProduto()+","+IDCLIENTE+"="+venda.getCliente_idCliente()+" WHERE "+IDVENDA+" = "+venda.getIdVenda();
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

    @Override
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

    private Venda carregaVenda(ResultSet rs) throws SQLException {
        Venda venda = new Venda();
         venda.setQuantidade(rs.getInt("quantidade"));
                venda.setIdVenda(rs.getInt("idVenda"));
        return venda;        
    }
    
    
    @Override
    public Object findBy(String campo, Object valorProcurado){
        Venda venda  = new Venda();
        
        try{
            ConexaoBD conexao = new ConexaoBD();
            String sql = "SELECT * FROM venda WHERE "+campo.toLowerCase()+" = "+valorProcurado;
            ResultSet rs = conexao.sentenca.executeQuery(sql);
            while(rs.next()){
               venda = carregaVenda(rs);  
            }
            //fecha conexao
            rs.close();
            //metodo que fecha a conexao
            Conexao.closeConection(conexao);
            return venda;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"ERRO: \n"+e.getMessage());
            return venda;
        }
        
    }
    
    
    public ArrayList<Object> findByList(String campo, Object valor){
        ArrayList<Object> vendas = new ArrayList();
        
        try{
            ConexaoBD conexao = new ConexaoBD();
            String sql = "SELECT * FROM venda WHERE "+campo.toLowerCase()+"="+valor;
            ResultSet rs = conexao.sentenca.executeQuery(sql);
            while(rs.next()){
                Venda venda = carregaVenda(rs);
                vendas.add(venda);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"ERRO: \n"+e.getMessage());
        }
        
        return vendas;
    }
}
