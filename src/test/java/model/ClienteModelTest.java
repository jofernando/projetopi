/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import helper.DbUnitHelper;
import model.entidades.Cliente;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import util.JpaUtil;

/**
 *
 * @author fernando
 */
public class ClienteModelTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private static ClienteModel clienteModel;
    private static DbUnitHelper dbUnitHelper;

    public ClienteModelTest() {
    }

    public Cliente cliente1() {
        Cliente cliente = new Cliente("José", "jose@gmail.com", "(11)11111-1111", "Rua primeira", "111.111.111-11");
        cliente.setId(1);
        return cliente;
    }

    public Cliente cliente2() {
        Cliente cliente = new Cliente("Maria", "maria@gmail.com", "(22)22222-2222", "Rua segunda", "222.222.222-22");
        cliente.setId(2);
        return cliente;
    }

    public Cliente novoCliente() {
        return new Cliente("Amelia Pond", "pond@gmail.com", "333.333.333-33", "Escócia", "333.333.333-33");
    }

    @Test
    public void deveLancarExcecaoCpfCadastrado() throws Exception {
        exception.expect(Exception.class);
        exception.expectMessage("Cliente com esse CPF já cadastrado");
        clienteModel.inserir(cliente1());
    }

    @BeforeClass
    public static void setUpClass() {
        clienteModel = new ClienteModel(ClienteModel.BANCODADOS, "PersistenceUnitedTest");
        dbUnitHelper = new DbUnitHelper();
    }

    @AfterClass
    public static void tearDownClass() {
        JpaUtil.close();
    }

    @Before
    public void setUp() {
        dbUnitHelper.cleanInsert("/tabelas/Cliente.xml");
    }

    @After
    public void tearDown() {
        dbUnitHelper.deleteAll("/tabelas/Cliente.xml");
    }
}
