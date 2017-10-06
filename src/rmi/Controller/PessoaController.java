/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi.Controller;

import Util.ConexaoBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import rmi.Model.Pessoa;

/**
 *
 * @author Admin
 */
public class PessoaController {
    
    public String create(Pessoa pessoa){
        int retorno = 0;
        String erro ="";
        try {           
            ConexaoBD conexao = new ConexaoBD();
            String sql = "INSERT INTO pessoa(nome,cpf,rg,telefone) VALUES (?,?,?,?)";
            PreparedStatement ps = conexao.connection.prepareStatement(sql);
            
            ps.setString(1, pessoa.getNome());
            ps.setString(2,pessoa.getCpf());
            ps.setString(3,pessoa.getRg());
            ps.setString(4,pessoa.getTelefone());
            retorno = ps.executeUpdate();
            ps.close();
            
            conexao.connection.close();
            return "Pessoa adicionada!";
        }catch(Exception e){
            erro = "erro \n"+e.getMessage();
        }
        
        if(retorno == 1){
            erro = "Erro na sentença Insert";
        }
        
        return erro;
    }
    /*    
    public Pessoa read(int idPessoa){
  
        Pessoa pessoa = new Pessoa();
    
        try{       
            ConexaoBD conexao  = new ConexaoBD();
            String sql = "SELECT * FROM pessoa WHERE idPessoa = "+idPessoa;
            ResultSet rs = conexao.sentenca.executeQuery(sql);
            
            while(rs.next()){
                pessoa.setCpf(rs.getString("cpf"));
                pessoa.setIdPessoa(rs.getInt("idPessoa"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setRg(rs.getString("rg"));
                pessoa.setTelefone(rs.getString("telefone"));
            }
            
            rs.close();
            conexao.sentenca.close();
            conexao.connection.close();
            
            return pessoa;
            
        }catch(Exception e){
            return null;
        }
      
    }
*/
    
    public String update(Pessoa pessoa){ 
        String erro = "";
        int retorno = 0;
        
      try{
      
          ConexaoBD conexao = new ConexaoBD();
          String sql = "UPDATE pessoa SET nome = ?, cpf = ?, rg = ?, telefone = ? WHERE idPessoa = ?";
          PreparedStatement ps = conexao.connection.prepareStatement(sql);
          
          ps.setString(1,pessoa.getNome());
          ps.setString(2,pessoa.getCpf());
          ps.setString(3,pessoa.getRg());
          ps.setString(4,pessoa.getTelefone());
          ps.setInt(5,pessoa.getIdPessoa());
          retorno = ps.executeUpdate();
          
          ps.close();
          conexao.connection.close();
          
          return "Pessoa Atualizada!";
         
          
      }catch(Exception e){
          erro = "Erro \n"+e.getMessage();
      }  
      
      if(retorno == 1){
          erro = "Erro na sentença Update";
      }
      
      return erro;
    }

    public String delete(int idPessoa){
        
        String erro = "";
        boolean retorno = true;
        
        try{
           
            ConexaoBD conexao = new ConexaoBD();
            String sql = "DELETE FROM pessoa where idPessoa = "+idPessoa;
            retorno = conexao.sentenca.execute(sql);
            conexao.sentenca.close();
            conexao.connection.close();
            
            return "Pessoa removida!";
            
        }catch(Exception e){
            erro = "Erro \n"+e.getMessage();
        }
        
        if(!retorno){
            erro = "Erro na sentença Delete";
        }
        
        return erro;
    }
    
    public Pessoa findBy(String campo,Object valorProcurado){
        Pessoa pessoa  = new Pessoa();
        
        try{
            ConexaoBD conexao = new ConexaoBD();
            String sql = "SELECT * FROM pessoa WHERE "+campo.toLowerCase()+" = "+valorProcurado;
            ResultSet rs = conexao.sentenca.executeQuery(sql);
            while(rs.next()){
                pessoa.setCpf(rs.getString("cpf"));
                pessoa.setIdPessoa(rs.getInt("idPessoa"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setRg(rs.getString("rg"));
                pessoa.setTelefone(rs.getString("telefone"));
                
            }
            //fecha conexao
            rs.close();
            conexao.sentenca.close();
            conexao.connection.close();
            return pessoa;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"ERRO: \n"+e.getMessage());
           
        }
         return pessoa;
    }
}
