/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Application.formataData;
import java.rmi.RemoteException;
import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import rmi.Controller.ProdutoController;
import rmi.Controller.ServicoController;

import rmi.Controller.VendaController;
import rmi.Model.Cliente;
import rmi.Model.Funcionario;

import rmi.Model.Produto;
import rmi.Model.Servico;


/**
 *
 * @author Admin
 */
public class ServicoTeste {
   
    public ServicoTeste() {
        
    }
    
    @BeforeClass
    public static void setUpClass() throws RemoteException, SQLException {
        limpaBanco.execute();
    }
    
    @AfterClass
    public static void tearDownClass() throws SQLException {
        limpaBanco.execute();
    }
    
    @Before
    public void setUp() throws RemoteException, SQLException {
       
    }
    
    @After
    public void tearDown() throws SQLException {
       
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void cadastraServico() throws RemoteException {
        Servico servico = new Servico();
        ServicoController controller = new ServicoController();
        
        servico.setDescricao("Em produção");
        
        
         //System.out.println(controller.create(servico));
       assertEquals("Serviço cadastrado!",controller.create(servico));
       
    }
   
    @Test
    public void EditaProduto() throws RemoteException {
        Servico servico ;
        ServicoController controller = new ServicoController();
        
        servico = (Servico) controller.findBy("descricaoServico", "Em produção");
        
        servico.setDescricao("Em expedição!");
        
        //System.out.println(controller.update(servico));

        assertEquals("Servico atualizado!",controller.update(servico));

    }
     
    
    @Test
    public void deleteProduto() throws RemoteException{
        Servico servico = new Servico();
        ServicoController controller = new ServicoController();
        
        servico = (Servico) controller.findBy("descricaoServico", "Em expedição!");
        //System.out.println(controller.delete(servico.getIdServico()));
        assertEquals("Produto removido!",controller.delete(servico.getIdServico()));
    }
    
      
}
