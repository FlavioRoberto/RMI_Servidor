/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi.Model;

import java.io.Serializable;
import java.util.Date;



/**
 *
 * @author Admin
 */
public class OrdemServico implements Serializable {
    private int idOrdemServico, idFuncionario, /*idVenda,idClienteHasproduto,*/servicoCompleto,produtoId;
    private Date dataExp;//, dataConclusao;

    public int getIdOrdemServico() {
        return idOrdemServico;
    }

    public void setIdOrdemServico(int idOrdemServico) {
        this.idOrdemServico = idOrdemServico;
    }
/*
    public int getIdClienteHasproduto() {
        return idClienteHasproduto;
    }
*/
    public int getServicoCompleto() {
        return servicoCompleto;
    }

    public void setServicoCompleto(int servicoCompleto) {
        this.servicoCompleto = servicoCompleto;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    
    /*
    
    public void setIdClienteHasproduto(int idClienteHasproduto) {
        this.idClienteHasproduto = idClienteHasproduto;
    }
*/
    
    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

/*
    public int getIdVenda() {
        return idVenda;
    }
*/
    /*
    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }*/

    public Date getDataExp() {
        return dataExp;
    }

    public void setDataExp(Date dataExp) {
        this.dataExp = dataExp;
    }

    /*public Date getDataConclusao() {
        return dataConclusao;
    }*/

    /*
    public void setDataConclusao(Date dataConclusao) {
        this.dataConclusao = dataConclusao;
    }*/
    
    
}
