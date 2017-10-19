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
import rmi.Controller.FuncionarioController;
import rmi.Controller.PessoaController;
import rmi.Interface.IControllerBase;
import rmi.Model.Cliente;
import rmi.Model.Funcionario;
import rmi.Model.Pessoa;

/**
 *
 * @author Admin
 */
public class main {
    
    public static void main(String[] args) throws RemoteException {
       Funcionario funcionario = new Funcionario();
        FuncionarioController controller = new FuncionarioController();
        
        funcionario = (Funcionario) controller.findBy("idFuncionario",17);
        
        System.out.println(funcionario.getNome());
    }
}
