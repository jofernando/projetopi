/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.impl;

import helper.ConnectionHelper;
import helper.DbUnitHelper;
import java.net.MalformedURLException;
import java.sql.SQLException;
import model.entidades.Cliente;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
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
public class BancoDeDadosClienteDAOTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private static BancoDeDadosClienteDAO bancoDeDadosClienteDAO;
    private static DbUnitHelper dbUnitHelper;

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
    public void deveNaoEstarCadastrado() {
        Assert.assertFalse(bancoDeDadosClienteDAO.estaCadastrado(novoCliente()));
    }

    @Test
    public void deveEstarCadastrado() {
        Assert.assertTrue(bancoDeDadosClienteDAO.estaCadastrado(cliente1()));
    }

    @Test
    public void deveBuscarPorCpf() {
        Assert.assertEquals(cliente1(), bancoDeDadosClienteDAO.buscarPorCpf("111.111.111-11"));
    }

    @Test
    public void deveBuscarNadaPorCpf() {
        Assert.assertEquals(null, bancoDeDadosClienteDAO.buscarPorCpf("1231231231"));
    }

    @Test
    public void deveBuscar() {
        Assert.assertEquals(cliente1(), bancoDeDadosClienteDAO.buscar(1));
    }

    @Test
    public void deveBuscarNada() {
        Assert.assertNull(bancoDeDadosClienteDAO.buscar(0));
    }

    @Test
    public void deveInserir() throws DataSetException, SQLException, MalformedURLException, DatabaseUnitException {
        Cliente amelia = novoCliente();
        Assert.assertTrue(bancoDeDadosClienteDAO.inserir(amelia));

        IDatabaseConnection connection = new DatabaseConnection(ConnectionHelper.getConnection());
        IDataSet databaseDataSet = connection.createDataSet();
        ITable actualTable = databaseDataSet.getTable("Cliente");

        Cliente atual = new Cliente();
        atual.setCpf((String) actualTable.getValue(2, "cpf"));
        atual.setEmail((String) actualTable.getValue(2, "email"));
        atual.setEndereco((String) actualTable.getValue(2, "endereco"));
        atual.setNome((String) actualTable.getValue(2, "nome"));
        atual.setTelefone((String) actualTable.getValue(2, "telefone"));
        atual.setId((int) actualTable.getValue(2, "id"));

        Assert.assertEquals(amelia.getCpf(), atual.getCpf());
        Assert.assertEquals(amelia.getNome(), atual.getNome());
        Assert.assertEquals(amelia.getEndereco(), atual.getEndereco());
        Assert.assertEquals(amelia.getEmail(), atual.getEmail());
        Assert.assertEquals(amelia.getTelefone(), atual.getTelefone());
        Assert.assertEquals(amelia, atual);
    }

    @Test
    public void deveAlterar() throws DatabaseUnitException, SQLException {
        Cliente jose = cliente1();
        jose.setEmail("josealterado@gmail.com");
        jose.setNome("jose alterado");
        Assert.assertTrue(bancoDeDadosClienteDAO.alterar(jose));

        IDatabaseConnection connection = new DatabaseConnection(ConnectionHelper.getConnection());
        IDataSet databaseDataSet = connection.createDataSet();
        ITable actualTable = databaseDataSet.getTable("Cliente");

        Cliente atual = new Cliente();
        atual.setCpf((String) actualTable.getValue(0, "cpf"));
        atual.setEmail((String) actualTable.getValue(0, "email"));
        atual.setEndereco((String) actualTable.getValue(0, "endereco"));
        atual.setNome((String) actualTable.getValue(0, "nome"));
        atual.setTelefone((String) actualTable.getValue(0, "telefone"));
        atual.setId((int) actualTable.getValue(0, "id"));

        Assert.assertEquals(jose.getCpf(), atual.getCpf());
        Assert.assertEquals(jose.getNome(), atual.getNome());
        Assert.assertEquals(jose.getEndereco(), atual.getEndereco());
        Assert.assertEquals(jose.getEmail(), atual.getEmail());
        Assert.assertEquals(jose.getTelefone(), atual.getTelefone());
        Assert.assertEquals(jose, atual);
    }

    @Test
    public void deveDeletar() throws DataSetException, DatabaseUnitException, SQLException {
        Assert.assertTrue(bancoDeDadosClienteDAO.deletar(cliente1()));

        IDatabaseConnection connection = new DatabaseConnection(ConnectionHelper.getConnection());
        IDataSet databaseDataSet = connection.createDataSet();
        ITable actualTable = databaseDataSet.getTable("Cliente");

        Assert.assertEquals(1, actualTable.getRowCount());
    }

    @Test
    public void deveBuscarTodos() {
        Assert.assertEquals(2, bancoDeDadosClienteDAO.buscarTodos().size());
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
