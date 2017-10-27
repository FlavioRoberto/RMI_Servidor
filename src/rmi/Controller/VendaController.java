/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi.Controller;

import Application.Conexao;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
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
public class VendaController extends UnicastRemoteObject implements IControllerBase{
     
    private final String TABELA="venda",ID = "idVenda",ID_CLIENTE="idCliente",ID_FUNCIONARIO="idFuncionario",DATA="dataVenda",CONFIRMADO="confirmado",
            VALOR_TOTAL="valorTotal";

    public VendaController() throws RemoteException{}
    
     @Override
    public String create(Object vendaObj){
        Venda venda = (Venda)vendaObj;
        String erro = "";
        int retorno = 0;
        try{
            ConexaoBD conexao = new ConexaoBD();
            String sql = "INSERT INTO venda("+ID_CLIENTE+","+ID_FUNCIONARIO+","+DATA+","+VALOR_TOTAL+","+CONFIRMADO+") VALUES (?,?,?,?,?) ";
            PreparedStatement ps = conexao.connection.prepareStatement(sql);
            ps.setInt(1, venda.getIdCliente());
            ps.setInt(2,venda.getIdFuncionario());
            ps.setDate(3, new java.sql.Date(venda.getData().getTime()));
            ps.setFloat(4, venda.getValorTotal());
            ps.setBoolean(5, venda.isConfirmado());
            retorno = ps.executeUpdate();
            ps.close();
            Conexao.closeConection(conexao);
            
            return "Venda Cadastrada!";
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
            String sql = "SELECT * FROM "+TABELA+" WHERE "+ID+" = "+idVenda;
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
            String sql = "UPDATE venda set "+ID_CLIENTE+"="+venda.getIdCliente()+","+ID_FUNCIONARIO+"="
                    +venda.getIdFuncionario()+","+DATA+"="+venda.getData()+","+VALOR_TOTAL+" = "
                    +venda.getValorTotal()+","+CONFIRMADO+"="+venda.isConfirmado()+" WHERE "+ID+" = "+venda.getIdVenda();
            
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
            String sql = "DELETE  FROM "+TABELA+" WHERE "+ID+" = ?";
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
        venda.setIdVenda(rs.getInt(ID));
        venda.setData(rs.getDate(DATA));
        venda.setIdCliente(rs.getInt(ID_CLIENTE));
        venda.setIdFuncionario(rs.getInt(ID_FUNCIONARIO));
        venda.setValorTotal(rs.getFloat(VALOR_TOTAL));
        venda.setConfirmado(rs.getBoolean(CONFIRMADO));
        return venda;        
    }
    
    
    @Override
    public Object findBy(String campo, Object valorProcurado){
        Venda venda  = new Venda();
        
        try{
            ConexaoBD conexao = new ConexaoBD();
            String sql = "SELECT * FROM "+TABELA+" WHERE "+campo.toLowerCase()+" = "+valorProcurado;
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
            String sql = "SELECT * FROM "+TABELA+" WHERE "+campo+" = " + valor;
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
