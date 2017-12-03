/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.impl;

import helper.DbUnitHelper;
import model.entidades.Cliente;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import util.JpaUtil;

/**
 *
 * @author fernando
 */
public class BancoDeDadosClienteDAOTest {
	
	@Rule
	public ExpectedException exception = ExpectedException.none();

    private static BancoDeDadosClienteDAO bancoDeDadosClienteDAO;
    private static DbUnitHelper dbUnitHelper;

    @Test
    public void deveEstarCadastrado() {
        Cliente amelia = new Cliente("Amelia Pond", "pond@gmail.com", "333.333.333-33", "Escócia", "111.111.111-11");
        Assert.assertTrue(bancoDeDadosClienteDAO.estaCadastrado(amelia));
    }

    @Test
    public void deveNaoEstarCadastro() {
        Cliente hwo = new Cliente("mistery", "tardis@gmail.com", "(12)12312-1231", "Gallifrey", "122.121.423-23");
        Assert.assertFalse(bancoDeDadosClienteDAO.estaCadastrado(hwo));
    }

    @Test
    public void deveBuscarPorCpf() {
        Cliente cliente = new Cliente("José", "jose@gmail.com", "(11)11111-1111", "Rua primeira", "111.111.111-11");
        cliente.setId(8);
        Assert.assertEquals(cliente, bancoDeDadosClienteDAO.buscarPorCpf("111.111.111-11"));
    }
    
    @Test
    public void deveBuscarNadaPorCpf() {
    	Assert.assertEquals(null, bancoDeDadosClienteDAO.buscarPorCpf("1231231231"));
    }

    @Test
    public void deveBuscar() {
        Cliente cliente = new Cliente("José", "jose@gmail.com", "(11)11111-1111", "Rua primeira", "111.111.111-11");
        cliente.setId(8);
        Assert.assertEquals(cliente, bancoDeDadosClienteDAO.buscar(8));
    }
    
    @Test
    public void deveInserir() {
    	Cliente jonas = new Cliente("Jonas", "jonas@mail.com", "3109231321", "asdasdas", "123.123.423-93");
    	Assert.assertTrue(bancoDeDadosClienteDAO.inserir(jonas));
    }
    
    @Test
    public void deveAlterar() {
    	Cliente jose = new Cliente("JoséAlterado", "joseAlterado@gmail.com", "(11)11111-1111", "Rua primeira-alterado", "111.111.111-11");
    	jose.setId(8);
    	Assert.assertTrue(bancoDeDadosClienteDAO.alterar(jose));
    }
    
    @Test
    public void deveDeletar() {
    	Cliente jose = new Cliente("José", "jose@gmail.com", "(11)11111-1111", "Rua primeira", "111.111.111-11");
    	jose.setId(8);
    	Assert.assertTrue(bancoDeDadosClienteDAO.deletar(jose));
    }
    
    @Test
    public void deveBuscarTodos() {
    	Assert.assertEquals(2, bancoDeDadosClienteDAO.buscarTodos().size());
    }
    
    @Ignore
    public void deveBuscarNenhumTodos() {
    	dbUnitHelper.deleteAll("/tabelas/Cliente.xml");
    	bancoDeDadosClienteDAO.buscarTodos();
    }

    @BeforeClass
    public static void setUpClass() {
        bancoDeDadosClienteDAO = new BancoDeDadosClienteDAO("PersistenceUnitedTest");
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
