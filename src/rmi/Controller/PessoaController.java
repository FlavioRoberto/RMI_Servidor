/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi.Controller;

import Application.Conexao;
import rmi.Util.ConexaoBD;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import rmi.Interface.IControllerBase;
import rmi.Model.Pessoa;

/**
 *
 * @author Admin
 */
public class PessoaController extends UnicastRemoteObject  implements IControllerBase {
    
    public PessoaController() throws RemoteException{}
    
    private final String TABELA = "pessoa",ID="idPessoa",NOME="nome",CPF="cpf",RG="rg",TELEFONE="telefone",CELULAR="celular";
    
    @Override
    public String create(Object pessoaObjetc){
        Pessoa pessoa = (Pessoa)pessoaObjetc;
        int retorno = 0;
        String erro ="";
        try {           
            ConexaoBD conexao = new ConexaoBD();
            String sql = "INSERT INTO pessoa("+NOME+","+CPF+","+RG+","+TELEFONE+","+CELULAR+") VALUES (?,?,?,?,?)";
            PreparedStatement ps = conexao.connection.prepareStatement(sql);
            
            ps.setString(1, pessoa.getNome());
            ps.setString(2,pessoa.getCpf());
            ps.setString(3,pessoa.getRg());
            ps.setString(4,pessoa.getTelefone());
            ps.setString(5,pessoa.getCelular());
 
            retorno = ps.executeUpdate();
            ps.close();
            
            Conexao.closeConection(conexao);
            return "Pessoa adicionada!";
        }catch(Exception e){
            erro = "erro \n"+e.getMessage();
        }
        
        if(retorno == 1){
            erro = "Erro na sentença Insert";
        }
        
        return erro;
    }
       
    @Override
    public Pessoa read(int idPessoa){
  
        Pessoa pessoa = new Pessoa();
    
        try{       
            ConexaoBD conexao  = new ConexaoBD();
            String sql = "SELECT * FROM "+TABELA+" WHERE "+ID+" = "+idPessoa;
            ResultSet rs = conexao.sentenca.executeQuery(sql);
            
            while(rs.next()){
                pessoa.setCpf(rs.getString(CPF));
                pessoa.setIdPessoa(rs.getInt(ID));
                pessoa.setNome(rs.getString(NOME));
                pessoa.setRg(rs.getString(RG));
                pessoa.setTelefone(rs.getString(TELEFONE));
                pessoa.setCelular(rs.getString(CELULAR));
                
            }
            
            rs.close();
            conexao.sentenca.close();
            conexao.connection.close();
            
            return pessoa;
            
        }catch(Exception e){
            return null;
        }
      
    }
    
    @Override
    public String update(Object pessoaObject){ 
        Pessoa pessoa = (Pessoa)pessoaObject;
        String erro = "";
        int retorno = 0;
        
      try{
      
          ConexaoBD conexao = new ConexaoBD();
          String sql = "UPDATE "+TABELA+" SET "+NOME+" = ?, "+CPF+" = ?, "+RG+" = ?, "+TELEFONE+" = ?,"+CELULAR+" = ? WHERE "+ID+" = ?";
          PreparedStatement ps = conexao.connection.prepareStatement(sql);
          
          ps.setString(1,pessoa.getNome());
          ps.setString(2,pessoa.getCpf());
          ps.setString(3,pessoa.getRg());
          ps.setString(4,pessoa.getTelefone());
          ps.setString(5,pessoa.getCelular());
          ps.setInt(6,pessoa.getIdPessoa());
          retorno = ps.executeUpdate();
          
          ps.close();
          Conexao.closeConection(conexao);
          
          return "Pessoa Atualizada!";
         
          
      }catch(Exception e){
          erro = "Erro \n"+e.getMessage();
      }  
      
      if(retorno == 1){
          erro = "Erro na sentença Update";
      }
      
      return erro;
    }

    @Override
    public String delete(int idPessoa){
        
        String erro = "";
        boolean retorno = true;
        
        try{
           
            ConexaoBD conexao = new ConexaoBD();
            String sql = "DELETE FROM "+TABELA+" where "+ID+" = "+idPessoa;
            retorno = conexao.sentenca.execute(sql);
            Conexao.closeConection(conexao);
            
            return "Pessoa removida!";
            
        }catch(Exception e){
            erro = "Erro \n"+e.getMessage();
        }
        
        if(!retorno){
            erro = "Erro na sentença Delete";
        }
        
        return erro;
    }
    
    @Override
    public Object findBy(String campo,Object valorProcurado){
        Pessoa pessoa  = new Pessoa();
        
        try{
            ConexaoBD conexao = new ConexaoBD();
            String sql = "SELECT * FROM "+TABELA+" WHERE "+campo.toLowerCase()+" = "+valorProcurado;
            ResultSet rs = conexao.sentenca.executeQuery(sql);
            while(rs.next()){
               pessoa = carregaPessoa(rs);  
            }
            //fecha conexao
            rs.close();
            //metodo que fecha a conexao
            Conexao.closeConection(conexao);
            return pessoa;
        }catch(Exception e){
           // JOptionPane.showMessageDialog(null,"ERRO: \n"+e.getMessage());
           return pessoa;
        }
         
    }
    
    
    public ArrayList<Object> findByList(String campo, Object valor){
        ArrayList<Object> pessoas = new ArrayList();
        
        try{
            ConexaoBD conexao = new ConexaoBD();
            String sql = "SELECT * FROM "+TABELA+" WHERE "+campo.toLowerCase()+"="+valor;
            ResultSet rs = conexao.sentenca.executeQuery(sql);
            while(rs.next()){
                Pessoa pessoa = carregaPessoa(rs);
                pessoas.add(pessoa);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"ERRO: \n"+e.getMessage());
            return pessoas;
        }
        
        return pessoas;
    }
    
    
    private Pessoa carregaPessoa(ResultSet rs) throws SQLException{
         Pessoa pessoa = new Pessoa();
         pessoa.setCpf(rs.getString(CPF));
         pessoa.setIdPessoa(rs.getInt(ID));
         pessoa.setNome(rs.getString(NOME));
         pessoa.setRg(rs.getString(RG));
         pessoa.setTelefone(rs.getString(TELEFONE));
         pessoa.setCelular(rs.getString(CELULAR));
         
         return pessoa;
    }
    
    
    
}
