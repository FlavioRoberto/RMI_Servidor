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
    private int idOrdemServico, idFuncionario,estado,idVenda;
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
    public int getEstado() {
        return estado;
    }

    public void setEstado(int servicoCompleto) {
        this.estado = servicoCompleto;
    }

    public int getVendaId() {
        return idVenda;
    }

    public void setVendaId(int produtoId) {
        this.idVenda = produtoId;
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
