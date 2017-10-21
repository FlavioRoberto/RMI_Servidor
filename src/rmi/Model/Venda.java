/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi.Model;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class Venda implements Serializable {
    private int idVenda, quantidade,produto_idProduto,cliente_idCliente;

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getProduto_idProduto() {
        return produto_idProduto;
    }

    public void setProduto_idProduto(int produto_idProduto) {
        this.produto_idProduto = produto_idProduto;
    }

    public int getCliente_idCliente() {
        return cliente_idCliente;
    }

    public void setCliente_idCliente(int cliente_idCliente) {
        this.cliente_idCliente = cliente_idCliente;
    }
    
    
    
}
