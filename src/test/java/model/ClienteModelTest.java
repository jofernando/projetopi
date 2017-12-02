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
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import util.JpaUtil;

/**
 *
 * @author fernando
 */
public class ClienteModelTest {

    private static ClienteModel clienteModel;

    public ClienteModelTest() {
    }

    @Test
    public void deveBuscar() {
        Cliente cliente = clienteModel.buscar(1);
        Assert.assertEquals("José", cliente.getNome());
    }

    @BeforeClass
    public static void setUpClass() {
        clienteModel = new ClienteModel(ClienteModel.BANCODADOS, "PersistenceUnitedTest");
    }

    @AfterClass
    public static void tearDownClass() {
        JpaUtil.close();
    }

    @Before
    public void setUp() {
        new DbUnitHelper().cleanInsert("/tabelas/Cliente.xml");
    }

    @After
    public void tearDown() {
    }

}
