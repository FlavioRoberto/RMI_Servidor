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
public class PessoaTeste {
   
    public PessoaTeste() {
        
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
    public void cadastraPessoa() throws RemoteException {
        Pessoa pessoa = new Pessoa();
        PessoaController controller = new PessoaController();
        
        pessoa.setCelular("celular");
        pessoa.setCpf("cpf");
        pessoa.setNome("nome");
        pessoa.setRg("RG");
        pessoa.setTelefone("telefone");
        String resposta = controller.create(pessoa);
        assertEquals("Pessoa adicionada!",resposta);
       
    }
    
    @Test
    public void EditaPessoa() throws RemoteException {
        Pessoa pessoa = new Pessoa();
        PessoaController controller = new PessoaController();
        
        pessoa = (Pessoa)controller.findBy("cpf", "cpf");
        
        pessoa.setCelular("celular2");
        pessoa.setCpf("cpf2");
        pessoa.setNome("nome2");
        pessoa.setRg("RG2");
        pessoa.setTelefone("telefone2");
        
        assertEquals("Pessoa Atualizada!",controller.update(pessoa));
      
    }
    @Test
    public void deletePessoa() throws RemoteException{
        Pessoa pessoa = new Pessoa();
        PessoaController controller = new PessoaController();
        
        pessoa = (Pessoa)controller.findBy("cpf", "cpf");
        assertEquals("Pessoa removida!",controller.delete(pessoa.getIdPessoa()));
    }
    
    
    
    
}
