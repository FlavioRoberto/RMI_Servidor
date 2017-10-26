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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import rmi.Interface.IControllerBase;
import rmi.Model.Servico;
import rmi.Util.ConexaoBD;

/**
 *
 * @author Admin
 */
public class ServicoController extends UnicastRemoteObject implements IControllerBase{

    private final String IDSERVICO = "idServico",DESCRICAO = "descricao",TABELA = "servico";
    
    public ServicoController() throws RemoteException {
    }

//metodo criado pra reaproveitar o codigo no metodo create and update    
 private String preparaPS(String sql,Servico servico,ConexaoBD conexao){
       try{
           
       PreparedStatement ps = conexao.connection.prepareStatement(sql);
           // ps.setInt(1, ordemServico.getIdOrdemServico());
            ps.setInt(1,servico.getIdServico());
            ps.setString(2, servico.getDescricao());

            ps.executeUpdate();
            ps.close();
            Conexao.closeConection(conexao);
            return "Ordem de serviço atualizada!";
       }catch(SQLException e){
           return ("Erro: \n"+e.getMessage());
       }
       
    }
 
    @Override
    public String create(Object ordemServicoObj) {
        Servico servico = (Servico)ordemServicoObj;
       int retorno = 0;
       String erro = "";
          
        try{
            ConexaoBD conexao = new ConexaoBD();
            String sql ="INSERT INTO "+TABELA+"("+DESCRICAO+") VALUES (?)";
            conexao.sentenca.execute(sql);
           // Conexao.closeConection(conexao);
            return erro = "Ordem de serviço cadastrada!";
            
        }catch(Exception e){
            erro += "Erro: \n"+e.getMessage();
        }
        
        return erro;    
    
    }
   
    @Override
    public Servico read(int idOrdemServico){
        
        try{
            ConexaoBD conexao = new ConexaoBD();
            String sql = "SELECT * FROM "+TABELA+" WHERE "+IDSERVICO+" = "+idOrdemServico;
            //ResultSet rs = conexao.sentenca.executeQuery(sql);
                        
            //rs.close();
            Conexao.closeConection(conexao);
            
            return null;
            
        }catch(SQLException e){
            //JOptionPane.showMessageDialog(null,e.getMessage());
        }catch(Exception e){
            //JOptionPane.showMessageDialog(null,e.getMessage());
        }
        return null;
    }

    @Override
    public String update(Object servicoObj){
        Servico servico = (Servico)servicoObj;
        int retorno = 0;
        String erro = "";
        try{
            ConexaoBD conexao = new ConexaoBD();
            String sql = "UPDATE "+TABELA+" SET "+DESCRICAO+" = ? WHERE "+IDSERVICO+"= ?";
            //PreparedStatement ps = conexao.connection.prepareStatement(sql);
            erro = preparaPS(sql, servico, conexao);
            Conexao.closeConection(conexao);
        }catch(Exception e){
            erro += "Erro:\n"+e.getMessage();
        }
        
        return erro;
    }
     
    @Override
    public String delete(int idOrdemServico){
        String erro = "";
       
        try{
            ConexaoBD conexao = new ConexaoBD();
            String sql = "DELETE FROM "+TABELA+" WHERE "+IDSERVICO+" = ?";
            PreparedStatement ps = conexao.connection.prepareStatement(sql);
            ps.setInt(1, idOrdemServico);
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

    private Servico carregaServico(ResultSet rs) throws SQLException {
        Servico servico = new Servico();
        servico.setIdServico(rs.getInt(IDSERVICO));
         servico.setDescricao(rs.getString(DESCRICAO));
        return servico;
    }


     @Override
    public Object findBy(String campo, Object valorProcurado){
        Servico servico  = new Servico();
        
        try{
            ConexaoBD conexao = new ConexaoBD();
            String sql = "SELECT * FROM "+TABELA+" WHERE "+campo.toLowerCase()+" = "+valorProcurado;
            ResultSet rs = conexao.sentenca.executeQuery(sql);
            while(rs.next()){
               servico = carregaServico(rs);  
            }
            //fecha conexao
            rs.close();
            //metodo que fecha a conexao
            Conexao.closeConection(conexao);
            return servico;
        }catch(Exception e){
            //JOptionPane.showMessageDialog(null,"ERRO: \n"+e.getMessage());
            return servico;
        }
         
    }
    
    
    public ArrayList<Object> findByList(String campo, Object valor){
        ArrayList<Object> listaServicos = new ArrayList();
        
        try{
            ConexaoBD conexao = new ConexaoBD();
            String sql = "SELECT * FROM "+TABELA+" WHERE "+campo.toLowerCase()+"="+valor;
            ResultSet rs = conexao.sentenca.executeQuery(sql);
            while(rs.next()){
                Servico servico = carregaServico(rs);
                listaServicos.add(servico);
            }
        }catch(Exception e){
           // JOptionPane.showMessageDialog(null,"ERRO: \n"+e.getMessage());
             return listaServicos;
        }
        
        return listaServicos;
    }    
}
