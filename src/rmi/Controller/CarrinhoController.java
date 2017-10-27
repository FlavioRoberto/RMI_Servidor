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
import rmi.Model.Carrinho;
import rmi.Util.ConexaoBD;

/**
 *
 * @author Admin
 */
public class CarrinhoController extends UnicastRemoteObject implements IControllerBase {
   
    private final String TABELA = "carrinho",ID="idCarrinho",IDVENDA = "idVenda",IDPRODUTO = "idProduto",QUANTIDADE = "quantidadeItemVenda";

    public CarrinhoController() throws RemoteException{
    }
    
   
    @Override
    public String create(Object ordemServicoObj) {
        Carrinho chs = (Carrinho)ordemServicoObj;
       int retorno = 0;
       String erro = "";
          
        try{
            ConexaoBD conexao = new ConexaoBD();
            String sql ="INSERT INTO "+TABELA+"("+IDVENDA+","+IDPRODUTO+","+QUANTIDADE+") VALUES (?,?,?)";
            erro = preparaPS(sql, chs, conexao);
            return "Carrinho cadastrado!";
     
        }catch(Exception e){
            erro += "Erro: \n"+e.getMessage();
        }
        
        return erro;    
    
    }
   
    @Override
    public Carrinho read(int idOrdemServico){
        Carrinho chp = new Carrinho();
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
        Carrinho ordemServico = (Carrinho)chp;
        int retorno = 0;
        String erro = "";
        try{
            ConexaoBD conexao = new ConexaoBD();
            String sql = "UPDATE "+TABELA+" SET "+IDVENDA+" = ?, "+IDPRODUTO+" = ?, "+QUANTIDADE+" = ?";
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
            String sql = "DELETE FROM "+TABELA+" WHERE "+IDVENDA+" = ?";
            PreparedStatement ps = conexao.connection.prepareStatement(sql);
            ps.setInt(1, idCliente);
            int concluiu = ps.executeUpdate();
            ps.close();
            Conexao.closeConection(conexao);
            if(concluiu == 1){
                return "Carrinho removido!";
            }else{
                return "Não foi possível remover.";
            }
        }catch(SQLException e){
            erro += "Erro: \n"+e.getMessage();
        }catch(Exception e){
            erro += "Erro: \n"+e.getMessage();
        }
        return erro;
    }

    private Carrinho carregaCHP(ResultSet rs) throws SQLException {
        Carrinho chp = new Carrinho();
         chp.setIdVenda(rs.getInt(IDVENDA));
         chp.setIdProduto(rs.getInt(IDPRODUTO));
         chp.setQuantidadeItemVenda(rs.getInt(IDVENDA));
        return chp;
    }


     @Override
    public Object findBy(String campo, Object valorProcurado){
        Carrinho chp  = new Carrinho();
        
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
                Carrinho chp = carregaCHP(rs);
                listaOs.add(chp);
            }
        }catch(Exception e){
           // JOptionPane.showMessageDialog(null,"ERRO: \n"+e.getMessage());
             return listaOs;
        }
        
        return listaOs;
    }


    
    //metodo criado pra reaproveitar o codigo no metodo create and update    
 private String preparaPS(String sql,Carrinho chs,ConexaoBD conexao){
       try{
           
       PreparedStatement ps = conexao.connection.prepareStatement(sql);
            ps.setInt(1, chs.getIdVenda());
            ps.setInt(2, chs.getIdProduto());
            ps.setInt(3, chs.getQuantidadeItemVenda());
            int erro = ps.executeUpdate();
            ps.close();
            Conexao.closeConection(conexao);
           if(erro != 0){
            return "Carrinho atualizado!";
           }else{
               return "erro na sentença";
           }
       }catch(Exception e){
           return ("Erro: \n"+e.getMessage());
       }
       
       
       
    }

    
}
