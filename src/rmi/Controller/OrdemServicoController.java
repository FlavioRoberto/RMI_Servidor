/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi.Controller;

import Application.Conexao;
import Application.formataData;
import rmi.Util.ConexaoBD;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import javafx.scene.input.DataFormat;
import javax.swing.JOptionPane;
import rmi.Interface.IControllerBase;
import rmi.Model.OrdemServico;
import rmi.Model.Pessoa;

/**
 *
 * @author Admin
 */
public class OrdemServicoController extends UnicastRemoteObject implements IControllerBase{

 public OrdemServicoController() throws RemoteException{}    
    
//metodo criado pra reaproveitar o codigo no metodo create and update    
 private String preparaPS(String sql,OrdemServico ordemServico,ConexaoBD conexao){
       try{
           
       PreparedStatement ps = conexao.connection.prepareStatement(sql);
            ps.setDate(1, new Date(ordemServico.getDataExp().getTime()));
            ps.setDate(2,new Date(ordemServico.getDataConclusao().getTime()));
            ps.setInt(3, ordemServico.getIdFuncionario());
            ps.setInt(4,ordemServico.getIdCliente());
            ps.setInt(5,ordemServico.getIdVenda());
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
        OrdemServico ordemServico = (OrdemServico)ordemServicoObj;
       int retorno = 0;
       String erro = "";
          
        try{
            ConexaoBD conexao = new ConexaoBD();
            String sql ="INSERT INTO ordemservico(dataExp,dataConclusao,Funcionario_idFuncionario,Cliente_idCliente,Venda_idVenda) VALUES (?,?,?,?,?)";
            Conexao.closeConection(conexao);
            return erro = preparaPS(sql, ordemServico, conexao);
            
        }catch(Exception e){
            erro += "Erro: \n"+e.getMessage();
        }
        
        return erro;    
    
    }
   
    @Override
    public OrdemServico read(int idOrdemServico){
        OrdemServico os = new OrdemServico();
        try{
            ConexaoBD conexao = new ConexaoBD();
            String sql = "SELECT * FROM ordemservico WHERE idOrdemServico = "+idOrdemServico;
            ResultSet rs = conexao.sentenca.executeQuery(sql);
            while(rs.next()){
               os = carregaOs(rs);
            }
            
            rs.close();
            Conexao.closeConection(conexao);
            
            return os;
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        return os;
    }

    @Override
    public String update(Object ordemServicoObj){
        OrdemServico ordemServico = (OrdemServico)ordemServicoObj;
        int retorno = 0;
        String erro = "";
        try{
            ConexaoBD conexao = new ConexaoBD();
            String sql = "UPDATE ordemservico SET dataExp = ?, dataConclusao = ?, Funcionario_idFuncionario = ?,"
                    + "Cliente_idCliente = ?,Venda_idVenda = ?;";
            //PreparedStatement ps = conexao.connection.prepareStatement(sql);
            erro = preparaPS(sql, ordemServico, conexao);
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
            String sql = "DELETE FROM ordemservico WHERE idOrdemServico = ?";
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

    private OrdemServico carregaOs(ResultSet rs) throws SQLException {
        OrdemServico os = new OrdemServico();
         os.setDataExp(new java.util.Date(rs.getDate("dataExp").getTime()));
                os.setDataConclusao( new java.util.Date (rs.getDate("dataconclusao").getTime()));
                os.setIdCliente(rs.getInt("Cliente_idCliente"));
                os.setIdFuncionario(rs.getInt("Funcionario_idFuncionario"));
                os.setIdVenda(rs.getInt("Venda_idVenda"));
                os.setIdOrdemServico(rs.getInt("idordemServico"));
        return os;
    }


     @Override
    public Object findBy(String campo, Object valorProcurado){
        OrdemServico ordemServico  = new OrdemServico();
        
        try{
            ConexaoBD conexao = new ConexaoBD();
            String sql = "SELECT * FROM ordemservico WHERE "+campo.toLowerCase()+" = "+valorProcurado;
            ResultSet rs = conexao.sentenca.executeQuery(sql);
            while(rs.next()){
               ordemServico = carregaOs(rs);  
            }
            //fecha conexao
            rs.close();
            //metodo que fecha a conexao
            Conexao.closeConection(conexao);
            return ordemServico;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"ERRO: \n"+e.getMessage());
            return ordemServico;
        }
         
    }
    
    
    public ArrayList<Object> findByList(String campo, Object valor){
        ArrayList<Object> listaOs = new ArrayList();
        
        try{
            ConexaoBD conexao = new ConexaoBD();
            String sql = "SELECT * FROM ordemservico WHERE "+campo.toLowerCase()+"="+valor;
            ResultSet rs = conexao.sentenca.executeQuery(sql);
            while(rs.next()){
                OrdemServico os = carregaOs(rs);
                listaOs.add(os);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"ERRO: \n"+e.getMessage());
             return listaOs;
        }
        
        return listaOs;
    }
}
