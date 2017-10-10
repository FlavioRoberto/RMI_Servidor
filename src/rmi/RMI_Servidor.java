/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import rmi.Controller.PessoaController;
import rmi.Interface.IControllerBase;

/**
 *
 * @author Admin
 */
public class RMI_Servidor {

      
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         
        try {
            IControllerBase objetoCliente = new PessoaController();
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
