/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi.Controller;

import Util.ConexaoBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import rmi.Model.Funcionario;

/**
 *
 * @author Admin
 */
public class FuncionarioController {
    
    public String create(Funcionario funcionario){
        
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
            conexao.connection.close();
            
            return "Inserido com sucesso";
            
        }catch(Exception e){
            erro = "Erro: \n"+e.getMessage();
        }
        
        if(retorno == 1){
            erro = "Erro na sentença Insert";
        }
        
        return erro;
    }
    
    public Funcionario read(int idFuncionario){
        Funcionario funcionario = new Funcionario();
        
        try{
           ConexaoBD conexao = new ConexaoBD();
           String sql = "SELECT * FROM funcionario INNER JOIN pessoa on idFuncionario = "+idFuncionario;
           ResultSet rs = conexao.sentenca.executeQuery(sql);
           while(rs.next()){
               funcionario.setCpf(rs.getString("cpf"));
               funcionario.setEspecialidade(rs.getString("especialidade"));
               funcionario.setIdFuncionario(rs.getInt("idFuncionario"));
               funcionario.setIdPessoa(rs.getInt("Pessoa_idPessoa"));
               funcionario.setNome(rs.getString("nome"));
               funcionario.setRg(rs.getString("rg"));
               funcionario.setSalario(rs.getFloat("salario"));
               funcionario.setTelefone(rs.getString("telefone"));
               
           } 
           
           rs.close();
           conexao.sentenca.close();
           conexao.connection.close();
          
           return funcionario;
        }catch(Exception e){
            return null;
        }
        
    }

    public String update(Funcionario funcionario){
     
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
            conexao.connection.close();
            
            return "Funcionário atualizado!";
            
        }catch(Exception e){
            erro = "Erro: \n"+e.getMessage();
        }
     
        if(retorno == 1){
            erro = "Erro na sentença Update";
        }
        
        return erro;
    }

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
            conexao.connection.close();
            
            return  "Funcionário removido!";
        }catch(Exception e){
            erro = "Erro: \n"+e.getMessage();
        } 
        
        if(retorno == 1){
            erro = "Erro na sentença Delete!";
        }
        
        return erro;
        
    }
}
