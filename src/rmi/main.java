/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import Application.formataData;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import rmi.Controller.ClienteController;
import rmi.Controller.FuncionarioController;
import rmi.Controller.OrdemServicoController;
import rmi.Controller.PessoaController;
import rmi.Controller.ProdutoController;
import rmi.Controller.VendaController;
import rmi.Interface.IControllerBase;
import rmi.Model.Cliente;
import rmi.Model.Funcionario;
import rmi.Model.OrdemServico;
import rmi.Model.Pessoa;
import rmi.Model.Produto;
import rmi.Model.Venda;

/**
 *
 * @author Admin
 */
public class main {
    
    public static void main(String[] args) throws RemoteException {
       OrdemServico os = new OrdemServico();
        OrdemServicoController osController = new OrdemServicoController();
        
        Funcionario funcionario = new Funcionario();
        FuncionarioController funcController = new FuncionarioController();
        funcionario.setCpf("12520537620");
        funcionario.setEspecialidade("producao");
        funcionario.setNome("Flavio");
        funcionario.setRg("18853160");
        funcionario.setSalario(20000);
        funcionario.setSenha("200200");
        funcionario.setTelefone("35114349");
        
        Produto produto = new Produto();
        ProdutoController pController = new ProdutoController();
        produto.setNome("teste");
        produto.setPreco(200);
        
       // os.setDataConclusao(formataData.dataAtual());
        os.setDataExp(formataData.dataAtual());
        os.setIdFuncionario(3);
        os.setProdutoId(6);
        os.setServicoCompleto(0);
        
        Cliente cliente = new Cliente();
        ClienteController cliController = new ClienteController();
        cliente.setCpf("12344566655");
        cliente.setNome("Flavio Roberto");
        cliente.setRg("1877766");
        cliente.setTelefone("34556677");
        cliente.setTipo("cliente");
        
        Venda venda = new Venda();
        VendaController vController = new VendaController();
        //venda.setCliente_idCliente(3);
        //venda.setProduto_idProduto(8);
        //venda.setQuantidade(8);
        
        venda = (Venda)vController.findBy("idvenda",1);
        
        System.out.println(venda.getQuantidade());
    }
}
