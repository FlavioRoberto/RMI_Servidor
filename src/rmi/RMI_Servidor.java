/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import javax.swing.JOptionPane;
import rmi.Controller.ClienteController;
import rmi.Controller.FuncionarioController;
import rmi.Controller.PessoaController;
import rmi.Model.Cliente;
import rmi.Model.Funcionario;
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
       
        Funcionario funcionario = new Funcionario();
        FuncionarioController controller = new FuncionarioController();
        
        funcionario = controller.read(1);
        funcionario.setNome("Flávio");
        
       JOptionPane.showMessageDialog(null,controller.delete(1));
        
        
    }
    
}
