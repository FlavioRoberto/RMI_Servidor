/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import javax.swing.JOptionPane;
import rmi.Controller.ClienteController;
import rmi.Controller.PessoaController;
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
        
        Pessoa pessoa = new Pessoa();
       
        PessoaController controller = new PessoaController();
       
        pessoa = controller.read(2);
        
        pessoa.setNome("mudou");
        
        JOptionPane.showMessageDialog(null,controller.delete(2));
        
        
    }
    
}
