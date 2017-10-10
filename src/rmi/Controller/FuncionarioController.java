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
import rmi.Model.Funcionario;
import rmi.Model.Pessoa;

/**
 *
 * @author Admin
 */
public class FuncionarioController extends UnicastRemoteObject implements IControllerBase {
   
    public FuncionarioController() throws RemoteException{}
    
     @Override  
    public String create(Object funcionarioObject){
        Funcionario funcionario = (Funcionario)funcionarioObject;
        String erro = "";
        int retorno = 0;
        
        try{
            
            ConexaoBD conexao = new ConexaoBD();
            String sql = "INSERT INTO funcionario(salario,especialidade,Pessoa_idPessoa) VALUES(?,?,?)";
            PreparedStatement ps = conexao.connection.prepareStatement(sql);
            ps.setFloat(1,funcionario.getSalario());
            ps.setString(2,funcionario.getEspecialidade());
            ps.setInt(3,funcionario.getIdPessoa());
            retorno = ps.executeUpdate();
            ps.close();
            Conexao.closeConection(conexao);
            
            return "Inserido com sucesso";
            
        }catch(Exception e){
            erro = "Erro: \n"+e.getMessage();
        }
        
        if(retorno == 1){
            erro = "Erro na sentença Insert";
        }
        
        return erro;
    }
    
     @Override  
    public Funcionario read(int idFuncionario){
        Funcionario funcionario = new Funcionario();
        
        try{
           ConexaoBD conexao = new ConexaoBD();
           String sql = "SELECT * FROM funcionario INNER JOIN pessoa on idFuncionario = "+idFuncionario;
           ResultSet rs = conexao.sentenca.executeQuery(sql);
           while(rs.next()){
             funcionario = carregaFuncionario(rs);               
           } 
        
           rs.close();
           Conexao.closeConection(conexao);
          
           return funcionario;
        }catch(Exception e){
            return null;
        }
        
    }

     @Override  
    public String update(Object funcionarioObject){
        Funcionario funcionario = (Funcionario)funcionarioObject;
        String erro = "";
        int retorno = 0;
        try{
            
            ConexaoBD conexao = new ConexaoBD();
            String sql = "UPDATE funcionario SET salario = ?, especialidade = ?, Pessoa_idPessoa = ? WHERE idFuncionario = ? ";
            PreparedStatement ps = conexao.connection.prepareStatement(sql);
            ps.setFloat(1,funcionario.getSalario());
            ps.setString(2,funcionario.getEspecialidade());
            ps.setInt(3,funcionario.getIdPessoa());
            ps.setInt(4,funcionario.getIdFuncionario());
            retorno = ps.executeUpdate();
            ps.close();
            Conexao.closeConection(conexao);
            
            return "Funcionário atualizado!";
            
        }catch(Exception e){
            erro = "Erro: \n"+e.getMessage();
        }
     
        if(retorno == 1){
            erro = "Erro na sentença Update";
        }
        
        return erro;
    }
    
    @Override  
    public String delete (int idFuncionario){
        String erro ="";
        int retorno = 0;
        try{
            ConexaoBD conexao = new ConexaoBD();
            String sql = "DELETE FROM funcionario WHERE idFuncionario = ?";
            PreparedStatement ps = conexao.connection.prepareStatement(sql);
            ps.setInt(1, idFuncionario);
            retorno = ps.executeUpdate();
            ps.close();
            Conexao.closeConection(conexao);
            
            return  "Funcionário removido!";
        }catch(Exception e){
            erro = "Erro: \n"+e.getMessage();
        } 
        
        if(retorno == 1){
            erro = "Erro na sentença Delete!";
        }
        
        return erro;
        
    }

    private Funcionario carregaFuncionario(ResultSet rs) throws SQLException {
        Funcionario funcionario = new Funcionario();
        funcionario.setCpf(rs.getString("cpf"));
               funcionario.setEspecialidade(rs.getString("especialidade"));
               funcionario.setIdFuncionario(rs.getInt("idFuncionario"));
               funcionario.setIdPessoa(rs.getInt("Pessoa_idPessoa"));
               funcionario.setNome(rs.getString("nome"));
               funcionario.setRg(rs.getString("rg"));
               funcionario.setSalario(rs.getFloat("salario"));
               funcionario.setTelefone(rs.getString("telefone"));
        
        return funcionario;
    }
    
    
     @Override
    public Object findBy(String campo,Object valorProcurado){
        Funcionario funcionario  = new Funcionario();
        
        try{
            ConexaoBD conexao = new ConexaoBD();
            String sql = "SELECT * FROM funcionario WHERE "+campo.toLowerCase()+" = "+valorProcurado;
            ResultSet rs = conexao.sentenca.executeQuery(sql);
            while(rs.next()){
               funcionario = carregaFuncionario(rs);  
            }
            //fecha conexao
            rs.close();
            //metodo que fecha a conexao
            Conexao.closeConection(conexao);
            return funcionario;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"ERRO: \n"+e.getMessage());
           return funcionario;
        }
         
    }
    
    public ArrayList<Object> findByList(String campo, Object valor){
        ArrayList<Object> funcionarios = new ArrayList();
        
        try{
            ConexaoBD conexao = new ConexaoBD();
            String sql = "SELECT * FROM funcionario WHERE "+campo.toLowerCase()+"="+valor;
            ResultSet rs = conexao.sentenca.executeQuery(sql);
            while(rs.next()){
                Funcionario funcionario = carregaFuncionario(rs);
                funcionarios.add(funcionario);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"ERRO: \n"+e.getMessage());
              return funcionarios;
        }
         return funcionarios;
    }

}
