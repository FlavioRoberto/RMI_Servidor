/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import javax.swing.JOptionPane;
import rmi.Controller.VendaController;
import rmi.Model.Venda;

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
        
       Venda venda = new Venda();
        
       VendaController controller = new VendaController();
       
       venda = controller.read(1);
       venda.setQuantidade(4);
       
       JOptionPane.showMessageDialog(null,controller.delete(1));
        
    }
    
}
