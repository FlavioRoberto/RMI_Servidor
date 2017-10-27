/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Application.formataData;
import java.rmi.RemoteException;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import rmi.Controller.OrdemServicoController;
import rmi.Model.OrdemServico;
import rmi.Model.Servico;

import rmi.Model.Venda;

/**
 *
 * @author Admin
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrdemServicoTeste {
   
   
    
    @BeforeClass
    public static void setUpClass() throws RemoteException, SQLException {
        limpaBanco.execute();
    }
    
    @AfterClass
    public static void tearDownClass() throws SQLException {
        limpaBanco.execute();
    }
    
    @Before
    public void setUp() throws RemoteException, SQLException {
        
        }
    
    @After
    public void tearDown() throws SQLException {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void CadastraOs() throws RemoteException {
        OrdemServico os = new OrdemServico();
        OrdemServicoController controller = new OrdemServicoController();
        
        os.setCompletado(false);
        os.setDataFim(formataData.dataAtual());
        os.setDataInicio(formataData.dataAtual());
        os.setDescricao("descricao");
        
        Venda venda = consultaVenda();
        Servico servico = consultaServico();
        
        os.setIdFuncionario(venda.getIdFuncionario());
        os.setIdVenda(venda.getIdVenda());
        os.setIdServico(servico.getIdServico());
        
       // System.out.println(controller.create(os));
        assertEquals("Completado com sucesso!",controller.create(os));
     
    }
    
    @Test
    public void EditaOs() throws RemoteException{
        OrdemServico os = new OrdemServico();
        OrdemServicoController controller = new OrdemServicoController();
        
        os = (OrdemServico) controller.findBy("completado", false);
        os.setCompletado(true);
        assertEquals("Completado com sucesso!", controller.update(os));
        
    }
    
    @Test
    public void RemoveOs() throws RemoteException{
        OrdemServico os = new OrdemServico();
        OrdemServicoController controller = new OrdemServicoController();
        
        os = (OrdemServico) controller.findBy("completado", true);
        assertEquals("Ordem de serviço removida!", controller.delete(os.getIdOrdemServico()));
        
    }
    
   private Venda consultaVenda() throws RemoteException{
       VendaTest vendaTest = new VendaTest();
       vendaTest.cadastraVenda();
       return vendaTest.retornaVendaByValorTotal();
   }
   
   private Servico consultaServico() throws RemoteException{
       ServicoTeste servicoTeste = new ServicoTeste();
       servicoTeste.cadastraServico();
       return servicoTeste.retornaServicoByDesc();
   }
}
