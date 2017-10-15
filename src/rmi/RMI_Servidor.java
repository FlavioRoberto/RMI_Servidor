/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import Application.BindObjects;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
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
public class RMI_Servidor {

      
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         
        try {
            //criadno objeto de conexao
            Registry conexao = LocateRegistry.createRegistry(1500);
            System.out.println("Servidor conectado");
            
            BindObjects.preparaBindObject(conexao);
            
        } catch(AlreadyBoundException e){
            System.out.println(e.getMessage());
        }
         catch(RemoteException e){
           System.out.println(e.getMessage());
       }
    }
    
}
