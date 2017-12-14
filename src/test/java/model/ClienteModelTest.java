/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import helper.DbUnitHelper;

/**
 *
 * @author fernando
 */
public class ClienteModelTest {

    private static ClienteModel clienteModel;
    private static DbUnitHelper dbUnitHelper;

    public ClienteModelTest() {
    }

//    @Test
//    public void deveBuscar() {
//        Cliente cliente = clienteModel.buscar(8);
//        Assert.assertEquals("José", cliente.getNome());
//    }
//
//    @BeforeClass
//    public static void setUpClass() {
//        clienteModel = new ClienteModel(ClienteModel.BANCODADOS, "PersistenceUnitedTest");
//        dbUnitHelper = new DbUnitHelper();
//    }
//
//    @AfterClass
//    public static void tearDownClass() {
//        JpaUtil.close();
//    }
//
//    @Before
//    public void setUp() {
//        dbUnitHelper.cleanInsert("/tabelas/Cliente.xml");
//    }
//
//    @After
//    public void tearDown() {
//        dbUnitHelper.deleteAll("/tabelas/Cliente.xml");
//    }
}
