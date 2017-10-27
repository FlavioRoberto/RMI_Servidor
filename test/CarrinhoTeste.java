/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Application.Conexao;
import Application.formataData;
import java.rmi.RemoteException;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import rmi.Controller.CarrinhoController;
import rmi.Controller.ProdutoController;

import rmi.Model.Carrinho;
import rmi.Model.Produto;
import rmi.Model.Venda;


/**
 *
 * @author Admin
 */
public class CarrinhoTeste {
   
    public CarrinhoTeste() {
        
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
       //limpaBanco.execute();
    }
    
    @After
    public void tearDown() throws SQLException {
       // limpaBanco.execute();
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void cadastraCarrinho() throws RemoteException {
        Carrinho carrinho = new Carrinho();
        CarrinhoController controller = new CarrinhoController();
        
        Venda venda = retornaVenda();
        Produto produto = retornaProduto();
        
        carrinho.setIdProduto(produto.getIdProduto());
        carrinho.setIdVenda(venda.getIdVenda());
        carrinho.setQuantidadeItemVenda(20);
        //System.out.println(controller.create(carrinho));
        assertEquals("Carrinho cadastrado!",controller.create(carrinho));
    }
   
    @Test
    public void EditaVenda() throws RemoteException {
        Carrinho carrinho = new Carrinho();
        ProdutoController pController = new ProdutoController();
        CarrinhoController controller = new CarrinhoController();
        
        Produto produto = (Produto) pController.findBy("quantidade", 20);
        carrinho = (Carrinho) controller.findBy("idProduto", produto.getIdProduto());
        carrinho.setQuantidadeItemVenda(0);
        
        //System.out.println(controller.update(carrinho));
        assertEquals("Carrinho atualizado!",controller.update(carrinho));
      
    }
     
    
    @Test
    public void deleteFuncionario() throws RemoteException{
        Carrinho carrinho = new Carrinho();
        CarrinhoController controller = new CarrinhoController();
        
        carrinho = (Carrinho) controller.findBy("quantidadeItemVenda", 0);
       // System.out.println(controller.delete(carrinho.getIdVenda()));
        assertEquals("Carrinho removido!",controller.delete(carrinho.getIdVenda()));
    }
    
       
   
    private Venda retornaVenda() throws RemoteException{
        VendaTest vendaTeste = new VendaTest();
        vendaTeste.cadastraVenda();
        return vendaTeste.retornaVendaByValorTotal();
        
    }
    

    private Produto retornaProduto() throws RemoteException{
            ProdutoTeste produtoTeste = new ProdutoTeste();
            produtoTeste.cadastraProduto();
            ProdutoController controller = new ProdutoController();
            return (Produto)controller.findBy("quantidade", 20);
        }

}
