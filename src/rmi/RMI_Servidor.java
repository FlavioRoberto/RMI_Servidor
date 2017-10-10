/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import Application.formataData;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Date;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
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
public class RMI_Servidor {

      
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws AlreadyBoundException{
         
        try {
            IControllerBase objetoCliente = new ClienteController();
            //criadno objeto de conexao
            Registry conexao = LocateRegistry.createRegistry(1500);
            System.out.println("Servidor conectado");
            //fica escutando na porta especifica o objeto
            conexao.bind("chave",objetoCliente);
            
        } catch(AlreadyBoundException e){
            System.out.println(e.getMessage());
        }
         catch(RemoteException e){
           System.out.println(e.getMessage());
       }
    }
    
}
