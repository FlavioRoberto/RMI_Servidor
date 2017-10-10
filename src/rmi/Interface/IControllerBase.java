/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi.Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import rmi.Model.Pessoa;

/**
 *
 * @author Admin
 */
public interface IControllerBase extends Remote {
    
    public String create(Object object) throws RemoteException;
    public Object read(int id) throws RemoteException;
    public String update(Object objecct) throws RemoteException;
    public String delete(int id) throws RemoteException;
    public Object findBy(String campo,Object valor)throws RemoteException;
    public ArrayList<Object> findByList(String campo, Object valor)throws RemoteException;
}
