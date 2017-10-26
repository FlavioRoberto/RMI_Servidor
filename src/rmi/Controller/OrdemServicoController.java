/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi.Controller;

import Application.Conexao;
import rmi.Util.ConexaoBD;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import rmi.Interface.IControllerBase;
import rmi.Model.OrdemServico;

/**
 *
 * @author Admin
 */
public class OrdemServicoController extends UnicastRemoteObject implements IControllerBase{

    
    private final String IDOS = "idOrdemServico",DATAEXP = "dataExp", IDFUNC="fk_funcionario", IDSERVICOATUAL ="estado",IDVENDA = "fk_venda",
            TABELA="ordemservico",DESCORDEM="descricaoOrdem",COMPLETADO="completado",DATAHINICIO="dataHoraInicio",DATAHFIM="dataHoraFim";
    
    public OrdemServicoController() throws RemoteException{}    
    
    //metodo criado pra reaproveitar o codigo no metodo create and update    
     private String preparaPS(String sql,OrdemServico ordemServico,ConexaoBD conexao){
       try{
           
       PreparedStatement ps = conexao.connection.prepareStatement(sql);
            //ps.setInt(5, ordemServico.getIdOrdemServico());
           // ps.setInt(1,ordemServico.getIdOrdemServico());   
            ps.setInt(1,ordemServico.getIdFuncionario());  
            ps.setInt(2,ordemServico.getIdVenda());      
            ps.setInt(3,ordemServico.getIdServico());  
            ps.setString(4,ordemServico.getDescricao());
            ps.setBoolean(5,ordemServico.isCompletado());
            ps.setDate(6, (Date) ordemServico.getDataInicio());
            ps.setDate(7, (Date) ordemServico.getDataFim());

            ps.executeUpdate();
            ps.close();
            Conexao.closeConection(conexao);
            return "Ordem de serviço atualizada!";
       }catch(SQLException e){
           return ("Erro: \n"+e.getMessage());
       }
       
    }
 
    @Override
    public String create(Object ordemServicoObj) {
        OrdemServico ordemServico = (OrdemServico)ordemServicoObj;
       int retorno = 0;
       String erro = "";
          
        try{
            ConexaoBD conexao = new ConexaoBD();
            String sql ="INSERT INTO "+TABELA+"("+IDFUNC+","+IDVENDA+","+IDSERVICOATUAL+","
                    +DESCORDEM+","+COMPLETADO+","+DATAHINICIO+","+DATAHFIM+")VALUES (?,?,?,?,?,?,?)";
           // Conexao.closeConection(conexao);
            return erro = preparaPS(sql, ordemServico, conexao);
            
        }catch(Exception e){
            erro += "Erro: \n"+e.getMessage();
        }
        
        return erro;    
    
    }
   
    @Override
    public OrdemServico read(int idOrdemServico){
        OrdemServico os = new OrdemServico();
        try{
            ConexaoBD conexao = new ConexaoBD();
            String sql = "SELECT * FROM "+TABELA+" WHERE "+IDOS+" = "+idOrdemServico;
            ResultSet rs = conexao.sentenca.executeQuery(sql);
            while(rs.next()){
               os = carregaOs(rs);
            }
            
            rs.close();
            Conexao.closeConection(conexao);
            
            return os;
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        return os;
    }

    @Override
    public String update(Object ordemServicoObj){
        OrdemServico ordemServico = (OrdemServico)ordemServicoObj;
        int retorno = 0;
        String erro = "";
        try{
            ConexaoBD conexao = new ConexaoBD();
            String sql = "UPDATE "+TABELA+" SET "+IDFUNC+" = ?, "+IDVENDA+"= ?,"+IDSERVICOATUAL+"=?,"
                    +DESCORDEM+"=?,"+COMPLETADO+"=?,"+DATAHINICIO+"=?,"+DATAHFIM+"=? WHERE "+IDOS+"="+ordemServico.getIdOrdemServico();
            //PreparedStatement ps = conexao.connection.prepareStatement(sql);
            erro = preparaPS(sql, ordemServico, conexao);
            Conexao.closeConection(conexao);
        }catch(Exception e){
            erro += "Erro:\n"+e.getMessage();
        }
        
        return erro;
    }
     
    @Override
    public String delete(int idOrdemServico){
        String erro = "";
       
        try{
            ConexaoBD conexao = new ConexaoBD();
            String sql = "DELETE FROM "+TABELA+" WHERE "+IDOS+" = ?";
            PreparedStatement ps = conexao.connection.prepareStatement(sql);
            ps.setInt(1, idOrdemServico);
            ps.executeUpdate();
            ps.close();
            Conexao.closeConection(conexao);
            
            return "Ordem de serviço removida!";
        }catch(SQLException e){
            erro += "Erro: \n"+e.getMessage();
        }catch(Exception e){
            erro += "Erro: \n"+e.getMessage();
        }
        return erro;
    }

    private OrdemServico carregaOs(ResultSet rs) throws SQLException {
        OrdemServico os = new OrdemServico();

                os.setIdOrdemServico(rs.getInt(IDOS));
                os.setIdFuncionario(rs.getInt(IDFUNC));
                os.setIdVenda(rs.getInt(IDVENDA));
                os.setCompletado(rs.getBoolean(COMPLETADO));
                os.setDataFim(rs.getDate(DATAHFIM));
                os.setDataInicio(rs.getDate(DATAHINICIO));
                os.setDescricao(rs.getString(DESCORDEM));
                os.setIdServico(rs.getInt(IDSERVICOATUAL));
                return os;
    }

    @Override
    public Object findBy(String campo, Object valorProcurado){
        OrdemServico ordemServico  = new OrdemServico();
        
        try{
            ConexaoBD conexao = new ConexaoBD();
            String sql = "SELECT * FROM "+TABELA+" WHERE "+campo.toLowerCase()+" = "+valorProcurado;
            ResultSet rs = conexao.sentenca.executeQuery(sql);
            while(rs.next()){
               ordemServico = carregaOs(rs);  
            }
            //fecha conexao
            rs.close();
            //metodo que fecha a conexao
            Conexao.closeConection(conexao);
            return ordemServico;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"ERRO: \n"+e.getMessage());
            return ordemServico;
        }
         
    }
    
    public ArrayList<Object> findByList(String campo, Object valor){
        ArrayList<Object> listaOs = new ArrayList();
        
        try{
            ConexaoBD conexao = new ConexaoBD();
            String sql = "SELECT * FROM "+TABELA+" WHERE "+campo.toLowerCase()+"="+valor;
            ResultSet rs = conexao.sentenca.executeQuery(sql);
            while(rs.next()){
                OrdemServico os = carregaOs(rs);
                listaOs.add(os);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"ERRO: \n"+e.getMessage());
             return listaOs;
        }
        
        return listaOs;
    }
}
