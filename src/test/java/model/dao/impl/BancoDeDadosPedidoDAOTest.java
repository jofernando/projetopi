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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.entidades.Cliente;
import model.entidades.ItemPedido;
import model.entidades.Pedido;
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
public class BancoDeDadosPedidoDAOTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private static BancoDeDadosPedidoDAO bancoDeDadosPedidoDAO;
    private static DbUnitHelper dbUnitHelper;

    public Pedido pedido1() {
        Pedido pedido = new Pedido();
        Cliente cliente = new Cliente("José", "jose@gmail.com", "(11)11111-1111", "Rua primeira", "111.111.111-11");
        cliente.setId(1);
        Produto produto = new Produto("1111-1", "primeiro", 11.1);
        produto.setId(1);
        ItemPedido item = new ItemPedido(2, produto);;
        item.setId(1);
        pedido.setCampanha("a1");
        pedido.setCliente(cliente);
        Set<ItemPedido> itens = new HashSet<>();
        itens.add(item);
        pedido.setItens(itens);
        pedido.setId(1);
        return pedido;
    }

    public Pedido pedido2() {
        Pedido pedido = new Pedido();
        Cliente cliente = new Cliente("Maria", "maria@gmail.com", "(22)22222-2222", "Rua segunda", "222.222.222-22");
        cliente.setId(2);
        Produto produto = new Produto("2222-2", "segunda", 222.2);
        produto.setId(2);
        ItemPedido item = new ItemPedido(4, produto);
        item.setId(2);
        pedido.setCampanha("b2");
        pedido.setCliente(cliente);
        Set<ItemPedido> itens = new HashSet<>();
        itens.add(item);
        pedido.setItens(itens);
        pedido.setId(2);
        return pedido;
    }

    public Pedido novoPedido() {
        Cliente cliente = new Cliente("Maria", "maria@gmail.com", "(22)22222-2222", "Rua segunda", "222.222.222-22");
        cliente.setId(2);
        Produto produto = new Produto("2222-2", "segunda", 222.2);
        produto.setId(2);
        ItemPedido item = new ItemPedido(4, produto);
        Set<ItemPedido> itens = new HashSet<>();
        itens.add(item);

        Pedido pedido = new Pedido("c3", cliente, itens);
        return pedido;
    }

    public BancoDeDadosPedidoDAOTest() {
    }

    @Test
    public void deveBuscar() {
        Pedido pedido = pedido1();
        assertEquals(pedido, bancoDeDadosPedidoDAO.buscar(1));
    }

    @Test
    public void deveBuscarNada() {
        assertNull(bancoDeDadosPedidoDAO.buscar(0));
    }

    @Test
    public void deveEstarCadastrado() {
        Pedido pedido = pedido1();
        assertTrue(bancoDeDadosPedidoDAO.estaCadastrado(pedido));
    }

    @Test
    public void deveNaoEstarCadastrado() {
        Pedido pedido = novoPedido();
        assertFalse(bancoDeDadosPedidoDAO.estaCadastrado(pedido));
    }

    @Test
    public void deveInserir() throws DatabaseUnitException, SQLException {
        Pedido esperado = novoPedido();
        assertTrue(bancoDeDadosPedidoDAO.inserir(esperado));
        esperado.setId(3);

        IDatabaseConnection connection = new DatabaseConnection(ConnectionHelper.getConnection());
        IDataSet iDataSet = connection.createDataSet();
        ITable iTable = iDataSet.getTable("Pedido");

        Pedido atual = new Pedido();
        atual.setId((int) iTable.getValue(2, "id"));
        atual.setCampanha((String) iTable.getValue(2, "campanha"));
        atual.getCliente().setId((int) iTable.getValue(2, "cliente_id"));

        assertEquals(esperado, atual);
        assertEquals(3, iTable.getRowCount());
    }

    @Test
    public void deveAlterar() throws SQLException, DatabaseUnitException {
        Pedido pedido = pedido2();
        pedido.setCampanha("b2-alterado");
        Produto produto = new Produto("1111-1", "primeiro", 11.1);
        produto.setId(1);
        pedido.getItens().add(new ItemPedido(5, produto));
        assertTrue(bancoDeDadosPedidoDAO.alterar(pedido));

        IDatabaseConnection connection = new DatabaseConnection(ConnectionHelper.getConnection());
        IDataSet iDataSet = connection.createDataSet();
        ITable iTable = iDataSet.getTable("Pedido");

        Pedido atual = new Pedido();
        atual.setId((int) iTable.getValue(1, "id"));
        atual.setCampanha((String) iTable.getValue(1, "campanha"));
        atual.getCliente().setId((int) iTable.getValue(1, "cliente_id"));
        assertEquals("b2-alterado", atual.getCampanha());
        assertEquals(2, iTable.getRowCount());
    }

    @Test
    public void deveBuscarTodos() throws SQLException, DatabaseUnitException {
        List<Pedido> esperado = new ArrayList<>();
        esperado.add(pedido1());
        esperado.add(pedido2());

        IDatabaseConnection connection = new DatabaseConnection(ConnectionHelper.getConnection());
        IDataSet iDataSet = connection.createDataSet();
        ITable iTable = iDataSet.getTable("Pedido");

        List<Pedido> atual = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Pedido pedido = new Pedido();
            pedido.setId((int) iTable.getValue(i, "id"));
            atual.add(pedido);
        }

        assertEquals(esperado, atual);
        assertEquals(2, iTable.getRowCount());
    }

    @Test
    public void deveDeletar() throws SQLException, DatabaseUnitException {
        Pedido pedido = pedido1();
        assertTrue(bancoDeDadosPedidoDAO.deletar(pedido));

        IDatabaseConnection connection = new DatabaseConnection(ConnectionHelper.getConnection());
        IDataSet iDataSet = connection.createDataSet();
        ITable iTable = iDataSet.getTable("Pedido");

        assertEquals(1, iTable.getRowCount());
    }

    @BeforeClass
    public static void setUpClass() {
        bancoDeDadosPedidoDAO = new BancoDeDadosPedidoDAO("PersistenceUnitedTest");
        dbUnitHelper = new DbUnitHelper();
    }

    @AfterClass
    public static void tearDownClass() {
        JpaUtil.close();
    }

    @Before
    public void setUp() {
        dbUnitHelper.cleanInsert("/tabelas/Cliente.xml");
        dbUnitHelper.cleanInsert("/tabelas/Produto.xml");
        dbUnitHelper.cleanInsert("/tabelas/item_pedido.xml");
        dbUnitHelper.cleanInsert("/tabelas/pedidos_itens.xml");
        dbUnitHelper.cleanInsert("/tabelas/Pedido.xml");
    }

    @After
    public void tearDown() {
        dbUnitHelper.deleteAll("/tabelas/Pedido.xml");
        dbUnitHelper.deleteAll("/tabelas/pedidos_itens.xml");
        dbUnitHelper.deleteAll("/tabelas/item_pedido.xml");
        dbUnitHelper.deleteAll("/tabelas/Cliente.xml");
        dbUnitHelper.deleteAll("/tabelas/Produto.xml");
    }
}
