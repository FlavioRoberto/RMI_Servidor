/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Application.Conexao;
import Application.formataData;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import rmi.Controller.ClienteController;
import rmi.Controller.FuncionarioController;
import rmi.Controller.PessoaController;
import rmi.Controller.VendaController;
import rmi.Model.Cliente;
import rmi.Model.Funcionario;
import rmi.Model.Pessoa;
import rmi.Model.Venda;
import rmi.Util.ConexaoBD;

/**
 *
 * @author Admin
 */
public class VendaTest {
   
    public VendaTest() {
        
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
    public void cadastraVenda() throws RemoteException {
        Venda venda = new Venda();
        VendaController controller = new VendaController();
        
        Funcionario func = retornaFuncionario();
        Cliente cliente = retornaCliente();
        
        venda.setData(formataData.dataAtual());
        venda.setIdFuncionario(func.getIdFuncionario());
        venda.setValorTotal(2000);
        venda.setIdCliente(cliente.getIdCliente());
       
        
       // System.out.println(controller.create(venda));
        assertEquals("Venda Cadastrada!",controller.create(venda));
       
    }
    /*
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
    
    */
       
    
    private Funcionario retornaFuncionario() throws RemoteException{
        FuncionarioTest funcionarioTest = new FuncionarioTest();
        funcionarioTest.cadastraFuncionario();
        return funcionarioTest.retornaFuncionarioBySalario();
        
    }
    
    private Cliente retornaCliente() throws RemoteException{
        
        ClienteTest clienteTest = new ClienteTest();
        clienteTest.cadastraCliente();
        return clienteTest.retornaClienteByTipo();
        
    }
}
