/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import rmi.Controller.ClienteController;
import rmi.Controller.PessoaController;
import rmi.Interface.IControllerBase;
import rmi.Model.Cliente;
import rmi.Model.Pessoa;

/**
 *
 * @author Admin
 */
public class main {
    
    public static void main(String[] args) throws RemoteException {
        Cliente cliente = new Cliente();
        ClienteController CController = new ClienteController();
        cliente.setCpf("cpf");
        cliente.setNome("Teste cliPessoa");
        cliente.setRg("rg");
        cliente.setTelefone("telefone");
        cliente.setTipo("tipo");
        CController.create(cliente);
        PessoaController pessoaController = new PessoaController();
       
        ArrayList<Object> listaPessoa = pessoaController.findByList("cpf","cpf");
        for(Object itemCliente : listaPessoa){
           Pessoa itemConvCliente = (Pessoa)itemCliente;
           System.out.println(itemConvCliente.getNome());
        }
        
    }
}
