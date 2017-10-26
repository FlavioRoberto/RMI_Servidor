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
import rmi.Model.Cliente;
import rmi.Model.Funcionario;
import rmi.Model.Pessoa;

/**
 *
 * @author Admin
 */
public class FuncionarioController extends UnicastRemoteObject implements IControllerBase {
   
    private final String TABELA="funcionario", ID = "idFuncionario", ID_PESSOA = "idPessoa",SALARIO="salario",ESPECIALIDADE="especialidade",SENHA="senha";
    
    
    public FuncionarioController() throws RemoteException{}
    
     @Override  
    public String create(Object funcionarioObject){
        Funcionario funcionario = (Funcionario)funcionarioObject;
        String erro = "";
        int retorno = 0;
        
        try{
           Pessoa pessoa = inserePessoaFuncionairio(funcionario);
           ConexaoBD conexao = new ConexaoBD();

            String sql = "INSERT INTO "+TABELA+"("+ID_PESSOA+","+SALARIO+","+ESPECIALIDADE+","+SENHA+") VALUES(?,?,?,?)";
            PreparedStatement ps = conexao.connection.prepareStatement(sql);
            ps.setFloat(1,funcionario.getSalario());
            ps.setString(2,funcionario.getEspecialidade());
            ps.setString(3,funcionario.getSenha());
            ps.setInt(4,pessoa.getIdPessoa());
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
    
    private Pessoa inserePessoaFuncionairio(Funcionario funcionario) throws RemoteException{
        Pessoa pessoa = new Pessoa();
        PessoaController pController = new PessoaController();
        pessoa.setCpf(funcionario.getCpf());
        pessoa.setNome(funcionario.getNome());
        pessoa.setRg(funcionario.getRg());
        pessoa.setTelefone(funcionario.getTelefone());
         pController.create(pessoa);
         return (Pessoa)pController.findBy("cpf", pessoa.getCpf());
 }
    
     @Override  
    public Funcionario read(int idFuncionario){
        Funcionario funcionario = new Funcionario();
        
        try{
           ConexaoBD conexao = new ConexaoBD();
           String sql = "SELECT * FROM "+TABELA+" INNER JOIN pessoa on "+ID+" = "+idFuncionario;
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
            //String sql = "UPDATE funcionario SET salario = ?, especialidade = ?, Pessoa_idPessoa = ? WHERE idFuncionario = ? ";
            String sql = "UPDATE pessoa SET nome = ?, cpf = ?, rg = ?, telefone = ? WHERE idPessoa = ?";
            PreparedStatement ps = conexao.connection.prepareStatement(sql);
            ps.setString(1, funcionario.getNome());
            ps.setString(2, funcionario.getCpf());
            ps.setString(3, funcionario.getRg());
            ps.setString(4, funcionario.getTelefone());
            ps.setInt(5, funcionario.getIdPessoa());
            retorno = ps.executeUpdate();
            ps.close();
            
            String sql2 = "UPDATE "+TABELA+" SET "+SALARIO+" = ?, "+ESPECIALIDADE+" = ? WHERE "+ID+" = ?";
            PreparedStatement ps2 = conexao.connection.prepareStatement(sql2);
            
            ps2.setFloat(1, funcionario.getSalario());
            ps2.setString(2, funcionario.getEspecialidade());
            ps2.setInt(3, funcionario.getIdFuncionario());
            retorno = ps2.executeUpdate();
            ps2.close();
            
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
            String sql = "DELETE FROM "+TABELA+" WHERE "+ID_PESSOA+" = ?";
            PreparedStatement ps = conexao.connection.prepareStatement(sql);
            ps.setInt(1, idFuncionario);
            retorno = ps.executeUpdate();
            ps.close();
            
            String sql2 = "DELETE FROM pessoa WHERE idPessoa = ?";
            PreparedStatement ps2 = conexao.connection.prepareStatement(sql2);
            ps2.setInt(1, idFuncionario);
            retorno = ps2.executeUpdate();
            ps2.close();
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

    private Funcionario carregaFuncionario(ResultSet rs) throws SQLException, RemoteException {
        PessoaController  pessoaController = new PessoaController();
       
        Funcionario funcionario = new Funcionario();
        funcionario.setEspecialidade(rs.getString(ESPECIALIDADE));
        funcionario.setIdFuncionario(rs.getInt(ID));
        funcionario.setSalario(rs.getFloat(SALARIO));
        funcionario.setIdPessoa(rs.getInt(ID_PESSOA));
        funcionario.setSenha(rs.getString(SENHA));
        
        Pessoa pessoa = (Pessoa)pessoaController.findBy(ID_PESSOA, funcionario.getIdPessoa());
        funcionario.setNome(pessoa.getNome());
        funcionario.setRg(pessoa.getRg());
        funcionario.setTelefone(pessoa.getTelefone());
        funcionario.setCpf(pessoa.getCpf());
        funcionario.setIdPessoa(pessoa.getIdPessoa());
        
        return funcionario;
    }
    
    
     @Override
    public Object findBy(String campo,Object valorProcurado){
        Funcionario funcionario  = new Funcionario();
        
        try{
            ConexaoBD conexao = new ConexaoBD();
            String sql = "SELECT * FROM "+TABELA+" WHERE "+campo.toLowerCase()+" = "+valorProcurado;
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
            String sql = "SELECT * FROM "+TABELA+" WHERE "+campo.toLowerCase()+"="+valor;
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
