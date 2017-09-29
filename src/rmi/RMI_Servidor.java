/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import javax.swing.JOptionPane;
import rmi.Controller.ClienteController;
import rmi.Model.Cliente;
import rmi.Model.Pessoa;

/**
 *
 * @author Admin
 */
public class RMI_Servidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
               
        Cliente cliente = new Cliente();
        ClienteController controller = new ClienteController();
        cliente = controller.read(2);
        
        cliente.setNome("Bruno");
        cliente.setTipo("user");
        
        JOptionPane.showMessageDialog(null,controller.delete(2));
        
        
    }
    
}
