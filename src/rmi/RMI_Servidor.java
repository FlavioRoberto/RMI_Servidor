/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import Application.formataData;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import rmi.Controller.ClienteController;
import rmi.Controller.FuncionarioController;
import rmi.Controller.OrdemServicoController;
import rmi.Controller.PessoaController;
import rmi.Controller.ProdutoController;
import rmi.Controller.VendaController;
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
    public static void main(String[] args) {
        // TODO code application logic here
      
        
        Pessoa pessoa = new Pessoa();
        ArrayList<Pessoa> pessoas = new ArrayList();
        PessoaController controller = new PessoaController();
       
        pessoas = controller.findByList("cpf", "cpf");
        
        for(Pessoa itemPessoa : pessoas){
            JOptionPane.showMessageDialog(null, itemPessoa.getNome());
        }
    }
    
}
