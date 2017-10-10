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
import rmi.Model.Pessoa;

/**
 *
 * @author Admin
 */
public class main {
    
    public static void main(String[] args) throws RemoteException {
        PessoaController controller = new PessoaController();
        ArrayList<Object> pessoa = new ArrayList();
        
        pessoa = controller.findByList("cpf","cpf");
        
        for(Object itemPessoa : pessoa){
            Pessoa itemConvertido = (Pessoa)itemPessoa;
            System.out.println(itemConvertido.getNome());
        }
        
    }
}
