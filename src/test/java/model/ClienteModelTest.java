/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.entidades.Cliente;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import util.JpaUtil;

/**
 *
 * @author Fernando
 */
public class ClienteModelTest {

    private ClienteModel model;

    public ClienteModelTest() {
    }

    @Test
    public void deveInserirCliente() {
	Cliente cliente = new Cliente(
		"José",
		"email@gmail.com",
		"99999-9999",
		"Avenida 2",
		"999.999.999-99");
	assertTrue(model.inserir(cliente));
    }

//    @Test
//    public void deveBuscarCliente() {
//	Cliente cliente = new Cliente(
//		"José",
//		"email@gmail.com",
//		"99999-9999",
//		"Avenida 2",
//		"999.999.999-99");
//	cliente.setId(1);
//	assertEquals(cliente, model.buscar(1));
//    }
//
//    public void deveAlterarCliente() {
//	Cliente cliente = new Cliente(
//		"José Alterado",
//		"emailAlterado@gmail.com",
//		"99999-9999",
//		"Avenida 2 Alterada",
//		"999.999.999-99");
//	cliente.setId(1);
//	assertTrue(model.alterar(cliente));
//    }
//
//    public void deveBuscarTodosClientes() {
//	Cliente cliente = new Cliente(
//		"José Alterado",
//		"emailAlterado@gmail.com",
//		"99999-9999",
//		"Avenida 2 Alterada",
//		"999.999.999-99");
//	cliente.setId(1);
//	List<Cliente> clientes = Arrays.asList(cliente);
//	assertEquals(clientes, model.buscarTodos());
//    }
    @Before
    public void inicializa() {
	this.model = new ClienteModel(ClienteModel.BANCODADOS, "PersistenceUnitedTest");
    }

    @After
    public void encerra() {
	JpaUtil.close();
    }

}
