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

import rmi.Controller.VendaController;
import rmi.Model.Cliente;
import rmi.Model.Funcionario;

import rmi.Model.Produto;


/**
 *
 * @author Admin
 */
public class ProdutoTeste {
   
    public ProdutoTeste() {
        
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
    public void cadastraProduto() throws RemoteException {
        Produto produto = new Produto();
        ProdutoController controller = new ProdutoController();
       
        produto.setNome("produto");
        produto.setPreco(200);
        produto.setQuantidade(20);
       // System.out.println(controller.create(produto));
        assertEquals("Produto cadastrado!",controller.create(produto));
       
    }
   
    @Test
    public void EditaProduto() throws RemoteException {
        Produto produto ;
        ProdutoController controller = new ProdutoController();
        
        produto = (Produto) controller.findBy("quantidade",20);
        System.out.println(produto.getNome());
        produto.setNome("teste");
        //System.out.println(controller.update(produto));
        assertEquals("Produto atualizado!",controller.update(produto));
       // System.out.println(produto.getNome());

    }
     
    
    @Test
    public void deleteProduto() throws RemoteException{
        Produto produto = new Produto();
        ProdutoController controller = new ProdutoController();
        
        produto = (Produto) controller.findBy("preco", 200);
       // System.out.println(controller.delete(produto.getIdProduto()));
        assertEquals("Produto removido!",controller.delete(produto.getIdProduto()));
    }
    
      
}
