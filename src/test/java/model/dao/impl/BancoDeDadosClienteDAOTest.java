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

    @Test
    public void deveNaoEstarCadastrado() {
        Cliente amelia
                = new Cliente("Amelia Pond", "pond@gmail.com", "333.333.333-33", "Escócia", "333.333.333-33");
        Assert.assertFalse(bancoDeDadosClienteDAO.estaCadastrado(amelia));
    }

    @Test
    public void deveEstarCadastrado() {
        Cliente cliente
                = new Cliente("José", "jose@gmail.com", "(11)11111-1111", "Rua primeira", "111.111.111-11");
        Assert.assertTrue(bancoDeDadosClienteDAO.estaCadastrado(cliente));
    }

    @Test
    public void deveBuscarPorCpf() {
        Cliente cliente
                = new Cliente("José", "jose@gmail.com", "(11)11111-1111", "Rua primeira", "111.111.111-11");
        cliente.setId(1);
        Assert.assertEquals(cliente, bancoDeDadosClienteDAO.buscarPorCpf("111.111.111-11"));
    }

    @Test
    public void deveBuscarNadaPorCpf() {
        Assert.assertEquals(null, bancoDeDadosClienteDAO.buscarPorCpf("1231231231"));
    }

    @Test
    public void deveBuscar() {
        Cliente cliente
                = new Cliente("José", "jose@gmail.com", "(11)11111-1111", "Rua primeira", "111.111.111-11");
        cliente.setId(1);
        Assert.assertEquals(cliente, bancoDeDadosClienteDAO.buscar(1));
    }

    @Test
    public void deveBuscarNada() {
        Assert.assertNull(bancoDeDadosClienteDAO.buscar(0));
    }

    @Test
    public void deveInserir() throws DataSetException, SQLException, MalformedURLException, DatabaseUnitException {
        Cliente jonas = new Cliente("Jonas", "jonas@mail.com", "3109231321", "asdasdas", "123.123.423-93");
        Assert.assertTrue(bancoDeDadosClienteDAO.inserir(jonas));

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

        Assert.assertEquals(jonas.getCpf(), atual.getCpf());
        Assert.assertEquals(jonas.getNome(), atual.getNome());
        Assert.assertEquals(jonas.getEndereco(), atual.getEndereco());
        Assert.assertEquals(jonas.getEmail(), atual.getEmail());
        Assert.assertEquals(jonas.getTelefone(), atual.getTelefone());
        Assert.assertEquals(jonas, atual);
    }

    @Test
    public void deveAlterar() throws DatabaseUnitException, SQLException {
        Cliente jonas = new Cliente("JoséAlterado", "joseAlterado@gmail.com", "(11)11111-1111", "Rua primeira-alterado", "111.111.111-11");
        jonas.setId(1);
        Assert.assertTrue(bancoDeDadosClienteDAO.alterar(jonas));

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

        Assert.assertEquals(jonas.getCpf(), atual.getCpf());
        Assert.assertEquals(jonas.getNome(), atual.getNome());
        Assert.assertEquals(jonas.getEndereco(), atual.getEndereco());
        Assert.assertEquals(jonas.getEmail(), atual.getEmail());
        Assert.assertEquals(jonas.getTelefone(), atual.getTelefone());
        Assert.assertEquals(jonas, atual);
    }

    @Test
    public void deveDeletar() throws DataSetException, DatabaseUnitException, SQLException {
        Cliente jose = new Cliente("José", "jose@gmail.com", "(11)11111-1111", "Rua primeira", "111.111.111-11");
        jose.setId(1);
        Assert.assertTrue(bancoDeDadosClienteDAO.deletar(jose));

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
