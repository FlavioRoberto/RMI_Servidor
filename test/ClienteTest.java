/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Application.Conexao;
import java.rmi.RemoteException;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import rmi.Controller.ClienteController;
import rmi.Controller.FuncionarioController;
import rmi.Controller.PessoaController;
import rmi.Model.Cliente;
import rmi.Model.Funcionario;
import rmi.Model.Pessoa;
import rmi.Util.ConexaoBD;

/**
 *
 * @author Admin
 */
public class ClienteTest {
   
    public ClienteTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() throws RemoteException {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
        
    }
    
    @Before
    public void setUp() throws RemoteException, SQLException {
       limpaBanco.execute();
    }
    
    @After
    public void tearDown() throws SQLException {
       limpaBanco.execute();
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void cadastraCliente() throws RemoteException {
        Cliente cliente = new Cliente();
        ClienteController controller = new ClienteController();
        
        cliente.setCelular("celular");
        cliente.setCpf("cpf");
        cliente.setNome("nome");
        cliente.setRg("RG");
        cliente.setTelefone("telefone");
        cliente.setTipo("tipo");
        
        
       // System.out.println(controller.create(cliente));
        assertEquals("Inserido com sucesso!",controller.create(cliente));
       
    }
    
    @Test
    public void EditaCliente() throws RemoteException {
        Cliente cliente = new Cliente();
        ClienteController controller = new ClienteController();
        
        cliente = consultaCliente(controller);
       
        cliente.setTipo("tipo4");
         
        //System.out.println(controller.update(cliente));
        assertEquals("Cliente atualizado!",controller.update(cliente));
      
    }
    
    @Test
    public void deleteFuncionario() throws RemoteException{
        Cliente cliente = new Cliente();
        ClienteController controller = new ClienteController();
        
        consultaCliente(controller);
       // System.out.println(controller.delete(cliente.getIdPessoa()));
        assertEquals("Apagado com sucesso!",controller.delete(cliente.getIdPessoa()));
    }
    
    
    private Cliente consultaCliente(ClienteController controller) throws RemoteException{
        PessoaController pController = new PessoaController();
        Pessoa p = (Pessoa) pController.findBy("cpf","cpf" );
        return (Cliente)controller.findBy("idPessoa",p.getIdPessoa());
    }
    
}
