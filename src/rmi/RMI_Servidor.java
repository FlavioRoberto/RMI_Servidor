/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import javax.swing.JOptionPane;
import rmi.Controller.ProdutoController;
import rmi.Model.Produto;

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
      
        Produto produto = new Produto();
        ProdutoController controller = new ProdutoController();
        
      
        
       JOptionPane.showMessageDialog(null,controller.delete(1));
        
    }
    
}
