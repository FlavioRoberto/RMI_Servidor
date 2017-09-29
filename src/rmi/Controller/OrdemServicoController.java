/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi.Controller;

import Application.formataData;
import Util.ConexaoBD;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import javafx.scene.input.DataFormat;
import javax.swing.JOptionPane;
import rmi.Model.OrdemServico;

/**
 *
 * @author Admin
 */
public class OrdemServicoController {
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
            conexao.connection.close();
            return "Ordem de serviço atualizada!";
       }catch(SQLException e){
           return ("Erro: \n"+e.getMessage());
       }
       
    }
  
    public String create(OrdemServico ordemServico) {
       int retorno = 0;
       String erro = "";
          
        try{
            ConexaoBD conexao = new ConexaoBD();
            String sql ="INSERT INTO ordemservico(dataExp,dataConclusao,Funcionario_idFuncionario,Cliente_idCliente,Venda_idVenda) VALUES (?,?,?,?,?)";
            return erro = preparaPS(sql, ordemServico, conexao);
            
        }catch(Exception e){
            erro += "Erro: \n"+e.getMessage();
        }
        
        return erro;    
    
    }
   
    public OrdemServico read(int idOrdemServico){
        OrdemServico os = new OrdemServico();
        try{
            ConexaoBD conexao = new ConexaoBD();
            String sql = "SELECT * FROM ordemservico WHERE idOrdemServico = "+idOrdemServico;
            ResultSet rs = conexao.sentenca.executeQuery(sql);
            while(rs.next()){
                os.setDataExp(new java.util.Date(rs.getDate("dataExp").getTime()));
                os.setDataConclusao( new java.util.Date (rs.getDate("dataconclusao").getTime()));
                os.setIdCliente(rs.getInt("Cliente_idCliente"));
                os.setIdFuncionario(rs.getInt("Funcionario_idFuncionario"));
                os.setIdVenda(rs.getInt("Venda_idVenda"));
                os.setIdOrdemServico(rs.getInt("idordemServico"));
            }
            
            rs.close();
            conexao.connection.close();
            
            return os;
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        return os;
    }

    public String update(OrdemServico ordemServico){
        int retorno = 0;
        String erro = "";
        try{
            ConexaoBD conexao = new ConexaoBD();
            String sql = "UPDATE ordemservico SET dataExp = ?, dataConclusao = ?, Funcionario_idFuncionario = ?,"
                    + "Cliente_idCliente = ?,Venda_idVenda = ?;";
            //PreparedStatement ps = conexao.connection.prepareStatement(sql);
            erro = preparaPS(sql, ordemServico, conexao);
        }catch(Exception e){
            erro += "Erro:\n"+e.getMessage();
        }
        
        return erro;
    }
     
    public String delete(int idOrdemServico){
        String erro = "";
       
        try{
            ConexaoBD conexao = new ConexaoBD();
            String sql = "DELETE FROM ordemservico WHERE idOrdemServico = ?";
            PreparedStatement ps = conexao.connection.prepareStatement(sql);
            ps.setInt(1, idOrdemServico);
            ps.executeUpdate();
            ps.close();
            conexao.connection.close();
            
            return "Ordem de serviço removida!";
        }catch(SQLException e){
            erro += "Erro: \n"+e.getMessage();
        }catch(Exception e){
            erro += "Erro: \n"+e.getMessage();
        }
        return erro;
    }
}