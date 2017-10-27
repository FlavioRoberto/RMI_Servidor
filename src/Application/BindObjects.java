/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import rmi.Controller.CarrinhoController;
import rmi.Controller.ClienteController;
import rmi.Controller.FuncionarioController;
import rmi.Controller.OrdemServicoController;
import rmi.Controller.PessoaController;
import rmi.Controller.ProdutoController;
import rmi.Controller.VendaController;
import rmi.Interface.IControllerBase;

/**
 *
 * @author Admin
 */
public class BindObjects {
    
    public static void preparaBindObject(Registry conexao) throws RemoteException, AlreadyBoundException{
           bindFuncionario(conexao);
           bindCliente(conexao);
           bindOrdemServico(conexao);
           bindPessoa(conexao);
           bindProduto(conexao);
           bindVenda(conexao);
           bindCarrinho(conexao);
    }

    private static void bindFuncionario(Registry conexao) throws RemoteException, AlreadyBoundException {
           IControllerBase objetoFuncionario = new FuncionarioController();
           conexao.bind("funcionario", objetoFuncionario);
    }

    private static void bindCliente(Registry conexao) throws RemoteException, AlreadyBoundException {
        IControllerBase objetoCliente = new ClienteController();
        conexao.bind("cliente", objetoCliente);
    }
    
    private static void bindOrdemServico(Registry conexao) throws RemoteException, AlreadyBoundException {
        IControllerBase objetoOS = new OrdemServicoController();
        conexao.bind("ordem_servico", objetoOS);
    }
    
    private static void bindPessoa(Registry conexao) throws RemoteException, AlreadyBoundException {
        IControllerBase objetoPessoa = new PessoaController();
        conexao.bind("pessoa", objetoPessoa);
    }
    
    private static void bindProduto(Registry conexao) throws RemoteException, AlreadyBoundException {
        IControllerBase objetoProduto = new ProdutoController();
        conexao.bind("produto", objetoProduto);
    } 
    
    private static void bindVenda(Registry conexao) throws RemoteException, AlreadyBoundException {
        IControllerBase objetoVenda = new VendaController();
        conexao.bind("venda", objetoVenda);
    } 
    
    private static void bindCarrinho(Registry conexao) throws RemoteException, AlreadyBoundException {
        IControllerBase objetoCarrinho = new CarrinhoController();
        conexao.bind("carrinho", objetoCarrinho);
    }

}
