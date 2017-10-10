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
import rmi.Model.Cliente;
import rmi.Model.Pessoa;

/**
 *
 * @author Admin
 */
public class ClienteController extends UnicastRemoteObject implements IControllerBase{
 
 public ClienteController() throws RemoteException{}
    
 @Override   
 public String create(Object clienteObject){
    Cliente cliente = (Cliente)clienteObject; 
   int retorno = 0; //variavel pra identificar se houve erro ou nao
   String erro = "";//variável pra armazenar o erro
     try{
         ConexaoBD conexao = new ConexaoBD(); 
         String sql = "INSERT INTO cliente (tipo,Pessoa_idPessoa) VALUES (?,?)"; //sentença de inserir
         PreparedStatement ps = conexao.connection.prepareStatement(sql); //prepara a string pra inserção
         ps.setString(1, cliente.getTipo());//na posição 1 (?) pegue o tipo do cliente
         ps.setInt(2, cliente.getIdPessoa());
         retorno = ps.executeUpdate(); //executa a acao pra salvar no banco e armazena seu retornno
         ps.close(); //encerra o preparete statement
         Conexao.closeConection(conexao);
         
     }catch(Exception e){
          erro += ("Erro ao adicionar cliente: \n"+e.getMessage()); //se houver uma falha no try catch
          return erro;
     }
     
     if(retorno == 1){
         return "Inserido com sucesso!"; //se não inserir
     }else{
        erro += "Erro na sentença inserir!";
        return erro;
     }
 }
 
 @Override  
 public Cliente read (int idCliente){
     Cliente cliente = new Cliente(); //objeto pra carregar os valores do banco
     ConexaoBD conexao = new ConexaoBD(); //objeto de conexao 
     String sql = "select *  from cliente as c  inner join pessoa   on idCliente ="+idCliente; //script de consulta pega o valor do cliente e da pessoa relacionada a ele
     try{
         ResultSet rs = conexao.sentenca.executeQuery(sql); //responsavel por varrer a tabela em busca dos valores
         while(rs.next()){ //enquanto existir item carrega os valores no objeto
            cliente = carregaCliente(rs);
         }
         Conexao.closeConection(conexao); //ao sair do laço encerra a conexão
         return cliente;  //retorne o ciente
         
     }catch(Exception e){
         return null; //se tiver erro retorne null
     }
 }

  @Override  
 public String update(Object clienteObject){
     Cliente cliente = (Cliente)clienteObject;
    boolean retorno = false;
    String erro = "";
    try{
      ConexaoBD conexao = new ConexaoBD();
      String sql = "update pessoa set nome = '"+cliente.getNome()+"',cpf='"+cliente.getCpf()+"',rg='"+cliente.getRg()+
                "',telefone ='"+cliente.getTelefone()+"' where idPessoa = "+cliente.getIdPessoa()+";";
    //  String sql2 =  "update cliente set tipo ='"+cliente.getTipo()+"' where idCliente = "+cliente.getIdCliente()+";";
     
      retorno = conexao.sentenca.execute(sql);
   //   retorno = conexao.sentenca.execute(sql2);
      Conexao.closeConection(conexao);
      return "Cliente atualizado!";

    }catch(Exception e){
      erro =  "Erro \n"+e.getMessage();
    }
    if(!retorno){
       erro = "Erro na sentença update!";         
    }
    return erro;
}

  @Override  
 public String delete(int idCliente){
     String erro;
     int retorno = 0;
     
     try{
         ConexaoBD conexao = new ConexaoBD();
         String sql = "DELETE FROM cliente where idCliente = ?";
         PreparedStatement ps = conexao.connection.prepareStatement(sql);
         ps.setInt(1, idCliente);
         retorno = ps.executeUpdate();
         ps.close();
         Conexao.closeConection(conexao);
         return "Apagado com sucesso!";
     }catch(Exception e){
         erro = "Erro \n"+e.getMessage();
     }
     if(retorno == 1){
         erro = "Erro na sentença DELETE";
     }
     return erro;
 }

 
 //metodo responscal por carregar em um objeto o cliente encontrdo pelo ResultSet
 private Cliente carregaCliente(ResultSet rs) throws SQLException{
    Cliente cliente = new Cliente();
     cliente.setCpf(rs.getString("cpf"));
            cliente.setIdCliente(rs.getInt("idCliente"));
            cliente.setIdPessoa(rs.getInt("Pessoa_idPessoa"));
            cliente.setNome(rs.getString("nome"));
            cliente.setRg(rs.getString("rg"));
            cliente.setTelefone(rs.getString("telefone"));
            cliente.setTipo(rs.getString("tipo"));
      return cliente;      
 }
 
 
  @Override
    public Object findBy(String campo,Object valorProcurado){
        Cliente cliente = new Cliente();
        
        try{
            ConexaoBD conexao = new ConexaoBD();
            String sql = "SELECT * FROM cliente WHERE "+campo.toLowerCase()+" = "+valorProcurado;
            ResultSet rs = conexao.sentenca.executeQuery(sql);
            while(rs.next()){
               cliente = carregaCliente(rs);  
            }
            //fecha conexao
            rs.close();
            //metodo que fecha a conexao
            Conexao.closeConection(conexao);
            return cliente;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"ERRO: \n"+e.getMessage());
            return cliente;
        }
         
    }
    
    public ArrayList<Object> findByList(String campo, Object valor){
        ArrayList<Object> clientes = new ArrayList();
        
        try{
            ConexaoBD conexao = new ConexaoBD();
            String sql = "SELECT * FROM cliente WHERE "+campo.toLowerCase()+"="+valor;
            ResultSet rs = conexao.sentenca.executeQuery(sql);
            while(rs.next()){
                Cliente cliente = carregaCliente(rs);
                clientes.add(cliente);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"ERRO: \n"+e.getMessage());
            return clientes;
        }
        
        return clientes;
    }
 
}