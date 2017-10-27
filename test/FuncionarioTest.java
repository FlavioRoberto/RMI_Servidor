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
import rmi.Controller.FuncionarioController;
import rmi.Controller.PessoaController;
import rmi.Model.Funcionario;
import rmi.Model.Pessoa;
import rmi.Util.ConexaoBD;

/**
 *
 * @author Admin
 */
public class FuncionarioTest {
   
    public FuncionarioTest() {
        
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
    public void cadastraFuncionario() throws RemoteException {
        Funcionario func = new Funcionario();
        FuncionarioController controller = new FuncionarioController();
        
        func.setCelular("celular");
        func.setCpf("cpf");
        func.setNome("nome");
        func.setRg("RG");
        func.setTelefone("telefone");
        func.setEspecialidade("esp");
        func.setSalario(200);
        func.setSenha("123");
        
        //System.out.println(controller.create(func));
        assertEquals("Inserido com sucesso",controller.create(func));
       
    }
    
    @Test
    public void EditaFuncionario() throws RemoteException {
        Funcionario func = new Funcionario();
        FuncionarioController controller = new FuncionarioController();
        
        
        func.setCelular("celular2");
        func.setCpf("cpf2");
        func.setNome("nome2");
        func.setRg("RG2");
        func.setTelefone("telefone2");
        
        consultaFunc(controller, func);
        
        //System.out.println(controller.update(func));
        assertEquals("Funcionário atualizado!",controller.update(func));
      
    }
    @Test
    public void deleteFuncionario() throws RemoteException{
        Funcionario func = new Funcionario();
        FuncionarioController controller = new FuncionarioController();
        
        consultaFunc(controller, func);
       // System.out.println(controller.delete(func.getIdPessoa()));
        assertEquals("Funcionário removido!",controller.delete(func.getIdPessoa()));
    }
    
    
    private void consultaFunc(FuncionarioController controller,Funcionario func) throws RemoteException{
        PessoaController pController = new PessoaController();
        Pessoa p = (Pessoa) pController.findBy("cpf","cpf" );
        func = (Funcionario)controller.findBy("idPessoa",p.getIdPessoa());
        
    }
    
}
