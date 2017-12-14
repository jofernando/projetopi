/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.impl;

import helper.ConnectionHelper;
import helper.DbUnitHelper;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.entidades.Produto;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
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
public class BancoDeDadosProdutoDAOTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private static BancoDeDadosProdutoDAO bancoDeDadosProdutoDAO;
    private static DbUnitHelper dbUnitHelper;

    public BancoDeDadosProdutoDAOTest() {
    }

    public Produto produto1() {
        Produto produto = new Produto("1111-1", "primeiro", 11.1);
        produto.setId(1);
        return produto;
    }

    public Produto produto2() {
        Produto produto = new Produto("2222-2", "segunda", 222.2);
        produto.setId(2);
        return produto;
    }

    public Produto novoProduto() {
        return new Produto("33333-3", "terceira", 333.33);
    }

    @Test
    public void deveEstarCadastrado() {
        assertTrue(bancoDeDadosProdutoDAO.estaCadastrado(produto1()));
    }

    @Test
    public void deveNaoEstarCadastrado() {
        assertFalse(bancoDeDadosProdutoDAO.estaCadastrado(novoProduto()));
    }

    @Test
    public void deveInserir() throws DatabaseUnitException, SQLException {
        Produto produto = novoProduto();
        assertTrue(bancoDeDadosProdutoDAO.inserir(produto));

        produto.setId(3);

        IDatabaseConnection connection = new DatabaseConnection(ConnectionHelper.getConnection());
        IDataSet iDataSet = connection.createDataSet();
        ITable iTable = iDataSet.getTable(("Produto"));

        Produto atual = new Produto();
        atual.setCodigo((String) iTable.getValue(2, "codigo"));
        atual.setId((int) iTable.getValue(2, "id"));
        atual.setNome((String) iTable.getValue(2, "nome"));
        atual.setPreco((double) iTable.getValue(2, "preco"));

        assertEquals(produto.getId(), atual.getId());
        assertEquals(produto.getCodigo(), atual.getCodigo());
        assertEquals(produto.getNome(), atual.getNome());
        assertEquals(produto.getPreco(), atual.getPreco(), 0.00001);
        assertEquals(3, iTable.getRowCount());
    }

    @Test
    public void deveAlterar() throws DatabaseUnitException, SQLException {
        Produto produto = produto1();
        produto.setNome("quarta");
        produto.setPreco(343.1);

        assertTrue(bancoDeDadosProdutoDAO.alterar(produto));

        IDatabaseConnection connection = new DatabaseConnection(ConnectionHelper.getConnection());
        IDataSet iDataSet = connection.createDataSet();
        ITable iTable = iDataSet.getTable(("Produto"));

        Produto atual = new Produto();
        atual.setCodigo((String) iTable.getValue(0, "codigo"));
        atual.setId((int) iTable.getValue(0, "id"));
        atual.setNome((String) iTable.getValue(0, "nome"));
        atual.setPreco((double) iTable.getValue(0, "preco"));

        assertEquals(produto.getId(), atual.getId());
        assertEquals(produto.getCodigo(), atual.getCodigo());
        assertEquals(produto.getNome(), atual.getNome());
        assertEquals(produto.getPreco(), atual.getPreco(), 0.00001);
        assertEquals(2, iTable.getRowCount());
    }

    @Test
    public void deveBuscar() {
        assertEquals(produto1(), bancoDeDadosProdutoDAO.buscar(1));
    }

    @Test
    public void deveBuscarNada() {
        assertNull(bancoDeDadosProdutoDAO.buscar(0));
    }

    @Test
    public void deveDeletar() throws DatabaseUnitException, SQLException {
        Produto produto = produto2();
        assertTrue(bancoDeDadosProdutoDAO.deletar(produto));

        IDatabaseConnection connection = new DatabaseConnection(ConnectionHelper.getConnection());
        IDataSet iDataSet = connection.createDataSet();
        ITable iTable = iDataSet.getTable(("Produto"));

        assertEquals(1, iTable.getRowCount());
    }

    @Test
    public void deveBuscarTodos() {
        List<Produto> produtos = new ArrayList<>();
        produtos.add(produto1());
        produtos.add(produto2());

        assertEquals(produtos, bancoDeDadosProdutoDAO.buscarTodos());
    }

    @Test
    public void deveBuscarPorCodigo() {
        assertEquals(produto1(), bancoDeDadosProdutoDAO.buscarPorCodigo("1111-1"));
    }

    @Test
    public void deveBuscarNadaPorCodigo() {
        assertNull(bancoDeDadosProdutoDAO.buscarPorCodigo("2343-2"));
    }

    @BeforeClass
    public static void setUpClass() {
        bancoDeDadosProdutoDAO = new BancoDeDadosProdutoDAO("PersistenceUnitedTest");
        dbUnitHelper = new DbUnitHelper();
    }

    @AfterClass
    public static void tearDownClass() {
        JpaUtil.close();
    }

    @Before
    public void setUp() {
        dbUnitHelper.cleanInsert("/tabelas/Produto.xml");
    }

    @After
    public void tearDown() {
        dbUnitHelper.deleteAll("/tabelas/Produto.xml");
    }
}
